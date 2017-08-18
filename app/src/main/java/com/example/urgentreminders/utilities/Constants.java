package com.example.urgentreminders.utilities;

/**
 * Created by Liza on 3.5.2015 г..
 */
public class Constants {
    public static final String APPLICATION_ID = "McJvzAhz4mtU7mOfxKu1N9FU2s3eJsDxSdg4D4ha";
    public static final String CLIENT_KEY = "SzoXeOcW3L0YY452DdL2He8wTpsqTgILwyWzhMsp";

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final long DEFAULT_INTERVAL_MILIS = 300 * 1000; //5 min
    public static final int MAX_LOG_FILES = 10;

    public static final String IS_OFFLINE = "IS_OFFLINE";
    public static final String IS_MONDAY_FIRST_DAY_OF_WEEK = "IS_MONDAY_FIRST_DAY_OF_WEEK";
    public static final String TURN_SOUND_ON = "TURN_SOUND_ON";
    public static final String SNOOZE_INTERVAL_MILIS = "SNOOZE_INTERVAL_MILIS";

    public static final String EMPTY_STRING = "";
    public static final String LOG_PATH = "/MyDiary/Logs";
    public static final String LOG_FILE_EXTENSION = ".txt";
    public static final String REQUEST_CODES = "REQUEST_CODES";
    public static final String NOTES = "NOTES";
    public static final String DATES = "DATES";
    public static final String TURN_SOUNDS_ON = "TURN_SOUNDS_ON";
    public static final String DATE = "DATE";
    public static final String TOKEN = "TOKEN";
    public static final String NOTE_TEXT = "NOTE_TEXT";
    public static final String REQ_CODE = "REQ_CODE";
    public static final String REMINDER_ACTION = "REMINDER_ACTION";
    public static final String SPEECH_INPUT_LANGUAGE = "en-US";

    public static final String JSON_ACCESS_TOKEN = "access_token";
    public static final String JSON_ERROR_DESCRIPTION = "error_description";
    public static final String JSON_MODEL_STATE = "ModelState";
    public static final String JSON_MESSAGE = "Message";
    public static final String JSON_ID = "Id";
    public static final String JSON_NOTE_TEXT = "NoteText";
    public static final String JSON_HAS_PASSWORD = "HasPassword";
    public static final String JSON_NOTE_TYPE= "NoteType";
    public static final String JSON_DATE = "Date";

    public static final String MODE_OFFLINE = "Offline";
    public static final String PASSWORD_NEEDED= "Password needed";
    public static final String NO_NOTES_FOR_DAY = "No notes for this day";
    public static final String NO_REMINDERS= "You have no reminders to display";
    public static final String TOAST_REMINDER_CANCELED = "Reminder canceled";
    public static final String TOAST_REMINDER_SNOOZED = "Reminder snoozed";
    public static final String TOAST_REMINDER_MADE = "Reminder is made";
    public static final String TOAST_SET_PASSWORD = "Password saved. It will be applied once you save the note";
    public static final String TOAST_IN_OFFLINE_MODE = "Can't use that - you're in offline mode!";
    public static final String TOAST_REMINDER_UPDATED= "Reminder is updated";
    public static final String TOAST_SPEECH_INPUT_NOT_SUPPORTED = "Sorry your device doesn't support speech input";
    public static final String TOAST_ALL_REMINDERS_ALREADY_REMOVED= "There are no reminders already";
    public static final String TOAST_SUCCESSFULLY_SENT_NOTE = "Your note was successfully saved!";
    public static final String TITLE_SPEECH_INPUT = "Say something...";
    public static final String TITLE_SAVED_SETTINGS= "Settings have been saved";
    public static final String TITLE_INVALID_INPUT = "Invalid input";
    public static final String TITLE_INVALID_REMINDER = "Invalid reminder";
    public static final String TITLE_INVALID_TIME = "Invalid time";
    public static final String TITLE_INVALID_PASSWORD= "Invalid password";
    public static final String TITLE_INVALID_NOTE= "Invalid note";
    public static final String TITLE_MAKE_REMINDER = "Make reminder";
    public static final String TITLE_SETTINGS = "Settings";
    public static final String TITLE_LOGOUT = "Logout";
    public static final String TITLE_PASSWORD_MISMATCH = "Password mismatch";
    public static final String TITLE_DELETE_NOTE = "Delete note";
    public static final String TITLE_SET_PASSWORD = "Set password";
    public static final String TITLE_UNLOCK_NOTE = "Type password";
    public static final String TITLE_PROBLEM_OCCURRED = "Problem occurred";
    public static final String TITLE_DELETE_REMINDER = "Delete reminder";
    public static final String TITLE_DELETE_REMINDERS = "Delete all reminders";
    public static final String TITLE_SUCCESSFUL_REGISTRATION = "Successful registration";
    public static final String TITLE_PROBLEM_REGISTERING = "A problem occurred";
    public static final String TITLE_CONTINUE_OFFLINE= "Continue offline?";
    public static final String TITLE_PROBLEM_WITH_LOGIN = "Problem with login";
    public static final String TITLE_NO_CONNECTION = "No connection to internet or server";
    public static final String MESSAGE_PROBLEM_OCCURRED = "Please check your internet connection or whether the server is running";
    public static final String MESSAGE_DELETE_REMINDER= "Do you want to delete this reminder?";
    public static final String MESSAGE_DELETE_REMINDERS = "Do you want to delete all reminders?";
    public static final String MESSAGE_NO_CONNECTION = "Please check your connection. You will be redirected to the login screen";
    public static final String MESSAGE_EMAIL_CANNOT_BE_EMPTY = "Email can't be empty!";
    public static final String MESSAGE_PASSWORD_CANNOT_BE_EMPTY= "Password can't be empty!";
    public static final String MESSAGE_PROBLEM_DELETING_NOTE= "Sorry, but we couldn't delete your note!";
    public static final String MESSAGE_NAME_CANNOT_BE_EMPTY= "Name can't be empty!";
    public static final String MESSAGE_PASSWORD_CONFIRM_CANNOT_BE_EMPTY= "Password confirmation can't be empty!";
    public static final String MESSAGE_PASSWORD_CONFIRM_NOT_MATCH = "The password confirmation doesn't match!";
    public static final String MESSAGE_PASSWORD_LENGTH = "The password must be at least %d characters long!";
    public static final String MESSAGE_LOGOUT = "Are you sure you want to log out?";
    public static final String MESSAGE_DELETE_NOTE = "Are you sure you want to delete this valuable memory?";
    public static final String MESSAGE_SUCCESSFUL_REGISTRATION = "You may login now";
    public static final String MESSAGE_CONTINUE_OFFLINE = "You will only be able to use the reminder functionality. Do you want to proceed offline?";
    public static final String MESSAGE_INVALID_REMINDER = "You can't set a reminder without text!";
    public static final String MESSAGE_INVALID_TIME = "Your reminder must be after the current time!";
    public static final String MESSAGE_INVALID_NOTE = "Your note can't be empty!";
    public static final String MESSAGE_OVERFLOWN_LONG = "The snooze you set is too big!";
    public static final String MESSAGE_NOTE_NOT_SAVED = "Your note was not saved";
    public static final String MESSAGE_COULD_NOT_RETRIEVE_NOTES = "Sorry, we couldn't retrieve your notes";
    public static final String MESSAGE_SAVED_SETTINGS= "Calendar changes will be applied the next time you run the app";

}
