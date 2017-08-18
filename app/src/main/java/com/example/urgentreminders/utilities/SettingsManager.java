package com.example.urgentreminders.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Liza on 16.5.2015 г..
 */
public class SettingsManager {
    private Context context;
    private SharedPreferences sharedPreferences;

    public SettingsManager(Context context){
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public static boolean isOffline(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(Constants.IS_OFFLINE, false);
    }

    public static boolean isMondayFirstDayOfWeek(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(Constants.IS_MONDAY_FIRST_DAY_OF_WEEK, false);
    }

    public static boolean turnSoundOn(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(Constants.TURN_SOUND_ON, false);
    }

    public static long getSnoozeIntervalMilis(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getLong(Constants.SNOOZE_INTERVAL_MILIS, Constants.DEFAULT_INTERVAL_MILIS);
    }
}
