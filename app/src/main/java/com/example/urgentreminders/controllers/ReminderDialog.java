package com.example.urgentreminders.controllers;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.urgentreminders.utilities.Constants;
import com.example.urgentreminders.R;
import com.example.urgentreminders.interfaces.IReminderDialogListener;
import com.example.urgentreminders.models.ReminderModel;
import com.example.urgentreminders.utilities.DateManager;
import com.example.urgentreminders.utilities.DialogManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Liza on 15.5.2015 г..
 */
public class ReminderDialog {
    private Context context;
    private IReminderDialogListener delegate;
    private DatePicker.OnDateChangedListener listener;
    int mYear = 2011;
    int mMonth = 4;
    int mDay = 30;

    public ReminderDialog(Context context, IReminderDialogListener delegate){
        this.context = context;
        this.delegate = delegate;

        listener = new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonth = monthOfYear;
                mDay = dayOfMonth;
            }
        };
    }

    public void show(String title, String currReminderText, String minDate){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setContentView(R.layout.dialog_reminder);
        dialog.setTitle(title);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.diary);

        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePickerUpdateReminder);
        datePicker.init(mYear, mMonth, mDay, listener);
        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePickerUpdateReminder);
        Button buttonUpdate = (Button) dialog.findViewById(R.id.buttonDone);
        Button buttonUpdateReminderCancel = (Button) dialog.findViewById(R.id.buttonUpdateReminderCancel);
        final EditText editTextReminder = (EditText) dialog.findViewById(R.id.editTextUpdateReminder);
        final CheckBox checkTurnSoundOn = (CheckBox) dialog.findViewById(R.id.checkTurnSoundOn);
        editTextReminder.setText(currReminderText);
        datePicker.setCalendarViewShown(false);
        timePicker.setIs24HourView(true);

        int[] dateNumbers = DateManager.getDateInNumbersFromString(minDate);
        datePicker.updateDate(dateNumbers[2], dateNumbers[1] - 1, dateNumbers[0]);
        datePicker.setMinDate(DateManager.getTimeInMilisFromString(minDate));
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        buttonUpdateReminderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                delegate.onReminderDialogDismiss(false, null);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextReminder.getText().toString().equals("")) {
                    DialogManager.makeAlert(context, Constants.TITLE_INVALID_REMINDER, Constants.MESSAGE_INVALID_REMINDER);
                    return;
                }

                GregorianCalendar now = new GregorianCalendar();
                datePicker.clearFocus();
                timePicker.clearFocus();
                if (DateManager.isDateValid(now, datePicker, timePicker)) {
                    ReminderManagerBroadcastReceiver alarm = new ReminderManagerBroadcastReceiver();
                    GregorianCalendar cal = DateManager.getGregorianCalendarFromNumbers(mDay,
                            mMonth + 1, mYear,
                            timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                    String d = DateManager.getBGDateTimeStringFromCalendar(cal); //for debugging
                    boolean turnSoundOn = checkTurnSoundOn.isChecked();
                    alarm.setAlarm(context, cal, editTextReminder.getText().toString(), turnSoundOn);
                    dialog.dismiss();
                    ReminderModel reminder = new ReminderModel(editTextReminder.getText().toString(), cal, turnSoundOn);
                    delegate.onReminderDialogDismiss(true, reminder);
                } else {
                    DialogManager.makeAlert(context, Constants.TITLE_INVALID_TIME, Constants.MESSAGE_INVALID_TIME);
                }
            }
        });

        dialog.show();
    }
}
