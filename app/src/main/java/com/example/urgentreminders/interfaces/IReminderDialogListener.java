package com.example.urgentreminders.interfaces;

import com.example.urgentreminders.models.ReminderModel;

/**
 * Created by Liza on 15.5.2015 г..
 */
public interface IReminderDialogListener {
    void onReminderDialogDismiss(boolean isConfirmed, ReminderModel reminder);
}
