package com.example.urgentreminders.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.urgentreminders.R;

/**
 * Created by Liza on 26.4.2015 г..
 */
public class DialogManager {
    private static final String TAG = "Utils";

    public static void makeAlert(Context context, String title, String message){
       new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.diary)
                .show();
    }

    public static void NoInternetOrServerAlert(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(Constants.TITLE_PROBLEM_OCCURRED)
                .setMessage(Constants.MESSAGE_PROBLEM_OCCURRED)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setIcon(R.drawable.diary);
        AlertDialog alert = builder.create();
        if(!alert.isShowing()){
            alert.show();
        }
    }
}
