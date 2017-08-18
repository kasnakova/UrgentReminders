package com.example.urgentreminders.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urgentreminders.utilities.Constants;
import com.example.urgentreminders.R;
import com.example.urgentreminders.utilities.DialogManager;
import com.example.urgentreminders.utilities.SettingsManager;

/**
 * Created by Liza on 14.5.2015 г..
 */
public class SettingsDialog {
    private Context context;

    public SettingsDialog(Context context){
        this.context = context;
    }

    public void show(){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setContentView(R.layout.dialog_settings);
        dialog.setTitle(Constants.TITLE_SETTINGS);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.diary);
        final CheckBox checkBoxFirstDayOfCalendar = (CheckBox) dialog.findViewById(R.id.checkBoxFirstDayOfCalendar);
        final boolean initialStateFirstDayOfCalendar = SettingsManager.isMondayFirstDayOfWeek(context);
        checkBoxFirstDayOfCalendar.setChecked(initialStateFirstDayOfCalendar);
        final CheckBox checkBoxTurnSoundOn = (CheckBox) dialog.findViewById(R.id.checkBoxTurnSoundOn);
        checkBoxTurnSoundOn.setChecked(SettingsManager.turnSoundOn(context));
        Button buttonCancel = (Button) dialog.findViewById(R.id.buttonSettingsCancel);
        Button buttonDone = (Button) dialog.findViewById(R.id.buttonSettingsDone);
        final EditText editTextSnoozeMinutes = (EditText) dialog.findViewById(R.id.editTextSnoozeMinutes);
        final String currSnoozeMinutes = String.valueOf(SettingsManager.getSnoozeIntervalMilis(context) / (60 * 1000));
        editTextSnoozeMinutes.setText(currSnoozeMinutes);
        editTextSnoozeMinutes.clearFocus();

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                    sharedPreferences.edit()
                            .putBoolean(Constants.IS_MONDAY_FIRST_DAY_OF_WEEK, checkBoxFirstDayOfCalendar.isChecked())
                            .putBoolean(Constants.TURN_SOUND_ON, checkBoxTurnSoundOn.isChecked())
                            .putLong(Constants.SNOOZE_INTERVAL_MILIS, Long.valueOf(editTextSnoozeMinutes.getText().toString()) * 60 * 1000)
                            .commit();

                    if (!currSnoozeMinutes.equals(editTextSnoozeMinutes.getText().toString())) {
                        ReminderManagerBroadcastReceiver alarm = new ReminderManagerBroadcastReceiver();
                        alarm.setAlarmsAfterReboot(context);
                    }

                    dialog.dismiss();
                    if(initialStateFirstDayOfCalendar != checkBoxFirstDayOfCalendar.isChecked()) {
                        DialogManager.makeAlert(context, Constants.TITLE_SAVED_SETTINGS, Constants.MESSAGE_SAVED_SETTINGS);
                    } else {
                        Toast.makeText(context, Constants.TITLE_SAVED_SETTINGS, Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException ex) {
                    DialogManager.makeAlert(context, Constants.TITLE_INVALID_TIME, Constants.MESSAGE_OVERFLOWN_LONG);
                }
            }
        });

        dialog.show();
    }
}
