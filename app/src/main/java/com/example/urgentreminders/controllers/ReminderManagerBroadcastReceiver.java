package com.example.urgentreminders.controllers;

/**
 * Created by Liza on 2.5.2015 г..
 */
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.urgentreminders.R;
import com.example.urgentreminders.activities.ReminderActivity;
import com.example.urgentreminders.models.ReminderModel;
import com.example.urgentreminders.utilities.Constants;
import com.example.urgentreminders.utilities.DateManager;
import com.example.urgentreminders.utilities.ReminderManager;
import com.example.urgentreminders.utilities.SettingsManager;
import com.example.urgentreminders.utilities.Utils;

public class ReminderManagerBroadcastReceiver extends BroadcastReceiver {
    private final String TAG = "ReminderManagerBroadcastReceiver";
    private boolean onBootOrSettingsChanged = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        wl.acquire();

        //Check if the phone wasn't rebooted - only then this extra will not exist
        if(intent.hasExtra(Constants.REMINDER_ACTION)){
            this.onBootOrSettingsChanged = false;
            ReminderAction reminderAction = (ReminderAction) intent.getSerializableExtra(Constants.REMINDER_ACTION);
            switch (reminderAction){
                case MakeNotification:
                    makeNotification(context, intent);
                    break;
                case CancelAlarm:
                    int reqCode = intent.getIntExtra(Constants.REQ_CODE, -1);
                    removeNotification(context, reqCode);
                    cancelAlarm(context, reqCode);
                    Toast.makeText(context, Constants.TOAST_REMINDER_CANCELED, Toast.LENGTH_LONG).show();
                    break;
                case SnoozeAlarm:
                    removeNotification(context, intent.getIntExtra(Constants.REQ_CODE, -1));
                    Toast.makeText(context, Constants.TOAST_REMINDER_SNOOZED, Toast.LENGTH_LONG).show();
                    //Do nothing - leave the repeating alarm continue
                    break;
                default:
                    break;
            }
        } else {
            setAlarmsAfterReboot(context);
        }

        wl.release();
    }

    public void cancelAlarm(Context context, int reqCode)
    {
        Intent intent = new Intent(context, ReminderManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, reqCode, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        ReminderManager.removeReminder(context, reqCode);
    }

    public void setAlarm(Context context, GregorianCalendar cal, String noteText, boolean turnSoundOn){
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReminderManagerBroadcastReceiver.class);
        intent.putExtra(Constants.NOTE_TEXT, noteText);
        intent.putExtra(Constants.DATE, DateManager.getDateTimeStringFromCalendar(cal));
        intent.putExtra(Constants.TURN_SOUND_ON, turnSoundOn);
        intent.putExtra(Constants.REMINDER_ACTION, ReminderAction.MakeNotification);
        //Alarms for different notes have to have different request codes and notification ids
        int requestCode = Utils.getId(noteText + DateManager.getDateTimeStringFromCalendar(cal));
        long interval = SettingsManager.getSnoozeIntervalMilis(context);
        PendingIntent pi = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), interval, pi);

        //No need to save them again if it's just setting the alarms after reboot
        if(!onBootOrSettingsChanged){
            ReminderManager.addReminder(context, requestCode, noteText, cal, turnSoundOn);
        }
    }

    private void removeNotification(Context context, int reqCode){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(reqCode);
    }

    private void makeNotification(Context context, Intent intent){
        //The intent if the notification itself is pressed
        String noteText = intent.getStringExtra(Constants.NOTE_TEXT);
        String date = intent.getStringExtra(Constants.DATE);
        boolean turnSoundOn = intent.getBooleanExtra(Constants.TURN_SOUND_ON, false);
        //Using the same value for notificationId and request code for the alarmManager
        int notificationId = Utils.getId(noteText + date);
        Intent resultIntent = new Intent(context, ReminderActivity.class);
        resultIntent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, notificationId, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //The intent if the cancel button on the notification is pressed
        Intent cancelIntent = new Intent(context, ReminderManagerBroadcastReceiver.class);
        cancelIntent.setAction(Long.toString(System.currentTimeMillis()));
        cancelIntent.putExtra(Constants.REMINDER_ACTION, ReminderAction.CancelAlarm);
        cancelIntent.putExtra(Constants.REQ_CODE, notificationId);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(context, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //The intent if the snooze button on the notification is pressed
        Intent snoozeIntent = new Intent(context, ReminderManagerBroadcastReceiver.class);
        snoozeIntent.setAction(Long.toString(System.currentTimeMillis()));
        snoozeIntent.putExtra(Constants.REMINDER_ACTION, ReminderAction.SnoozeAlarm);
        snoozeIntent.putExtra(Constants.REQ_CODE, notificationId);
        PendingIntent snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.diary)
                .setContentTitle(context.getResources().getString(R.string.my_diary_reminder))
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentText(noteText)
                .addAction(R.drawable.exit, context.getResources().getString(R.string.cancel), cancelPendingIntent)
                .addAction(R.drawable.alarm_small, context.getResources().getString(R.string.snooze), snoozePendingIntent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        if(SettingsManager.turnSoundOn(context) || turnSoundOn) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

    public void setAlarmsAfterReboot(Context context){
        this.onBootOrSettingsChanged = true;
        HashMap<String, ReminderModel> reminders = ReminderManager.getAllReminders(context);
        Iterator it = reminders.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry reminder = (Map.Entry)it.next();
            ReminderModel remind = (ReminderModel) reminder.getValue();
            setAlarm(context, remind.getDate(), remind.getNoteText(), remind.getTurnSoundOn());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
