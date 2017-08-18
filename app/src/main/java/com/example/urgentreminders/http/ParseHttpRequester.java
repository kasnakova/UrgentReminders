package com.example.urgentreminders.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.urgentreminders.interfaces.IMyDiaryHttpResponse;
import com.example.urgentreminders.utilities.Constants;
import com.example.urgentreminders.utilities.Logger;
import com.parse.LogInCallback;
import com.parse.ParseUser;
import com.parse.ParseException;
import com.parse.SignUpCallback;

/**
* Created by Liza on 8.9.2015 г..
*/
public class ParseHttpRequester {
    private  final String TAG = "ParseHttpRequester";
    private IMyDiaryHttpResponse delegate;
    private boolean isOffline;
    private ProgressDialog progress;


    public ParseHttpRequester(IMyDiaryHttpResponse delegate, boolean isOffline, Context contextForProgress){
        this.delegate = delegate;
        this.isOffline = isOffline;
        this.progress = new ProgressDialog(contextForProgress);
        this.progress.setCancelable(true);
        this.progress.setCanceledOnTouchOutside(true);
    }

    public boolean getIsOffline(){
        return this.isOffline;
    }

    public void setIsOffline(boolean isOffline){
        this.isOffline = isOffline;
    }

    public void register(String email, String password, String name) {
        if(this.isOffline){
            return;
        }


        try {
            progress.show();
            ParseUser user = new ParseUser();
            user.setUsername(name);
            user.setPassword(password);
            user.setEmail(email);

            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    progress.dismiss();
                    boolean success = true;
                    String data = Constants.EMPTY_STRING;
                    if (e != null) {
                        success = false;
                        data = e.getMessage();
                    }

                    MyDiaryHttpResult result = new MyDiaryHttpResult(success, MyDiaryHttpServices.Register, data);
                    delegate.myDiaryProcessFinish(result);
                }
            });
        } catch(Exception ex) {
            Log.d(TAG, "Exception: " + ex.getMessage());
            Logger.getInstance().logError(TAG, ex);
        }
    }

    public void login(String email, String password){
        if(this.isOffline){
            return;
        }

        try {
            ParseUser.logInInBackground(email, password, new LogInCallback() {
                public void done(ParseUser user, ParseException e) {
                    progress.dismiss();
                    boolean success = true;
                    String data = Constants.EMPTY_STRING;
                    if (user == null){
                        
                        success = false;
                        data = e.getMessage();
                    }

                    MyDiaryHttpResult result = new MyDiaryHttpResult(success, MyDiaryHttpServices.Login, data);
                    delegate.myDiaryProcessFinish(result);
                }
            });
        } catch(Exception ex) {
            Logger.getInstance().logError(TAG, ex);
        }
    }
//
//    public void logout(){
//        if(this.isOffline){
//            return;
//        }
//
//        try {
//            progress.show();
//            new HttpRequester(context)
//                    .execute(
//                            URL_LOGOUT,
//                            METHOD_POST,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void getName(){
//        if(this.isOffline){
//            return;
//        }
//
//        try {
//            progress.show();
//            new HttpRequester(context)
//                    .execute(
//                            URL_NAME,
//                            METHOD_GET,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void sendNote(String noteText, NoteType noteType, String password, GregorianCalendar calendar){
//
//        if(this.isOffline){
//            return;
//        }
//
//        try {
//            progress.show();
//            String date = DateManager.getDateTimeStringFromCalendar(calendar);
//            String urlParameters = String.format(FORMAT_SAVE_NOTE, noteText, date, noteType);
//            if(password != null){
//                urlParameters += "&Password=" + password;
//            }
//
//            new HttpRequester(context)
//                    .execute(
//                            URL_SEND_NOTE,
//                            METHOD_POST,
//                            urlParameters,
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void getNotesForDate(GregorianCalendar calendar){
//        if(this.isOffline){
//            return;
//        }
//
//        try{
//            progress.show();
//            String date = DateManager.getDateStringFromCalendar(calendar);
//            String url = URL_GET_NOTES_FOR_DATE + String.format(FORMAT_GET_NOTES_FOR_DATE, date);
//            new HttpRequester(context)
//                    .execute(
//                            url,
//                            METHOD_GET,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void deleteNote(int id){
//        if(this.isOffline){
//            return;
//        }
//
//        try{
//            progress.show();
//            String url = URL_DELETE_NOTE + String.format(FORMAT_DELETE_NOTE, id);
//            new HttpRequester(context)
//                    .execute(
//                            url,
//                            METHOD_DELETE,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void getDatesWithNotes(int month, int year){
//        if(this.isOffline){
//            return;
//        }
//
//        try{
//            progress.show();
//            String url = URL_GET_DATES_WITH_NOTES + String.format(FORMAT_GET_DATES_WITH_NOTES, month, year);
//            new HttpRequester(context)
//                    .execute(
//                            url,
//                            METHOD_GET,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
//
//    public void getDecryptedNoteText(int id, String password){
//        if(this.isOffline){
//            return;
//        }
//
//        try{
//            progress.show();
//            String url = URL_GET_DECRYPTED_NOTE_TEXT + String.format(FORMAT_GET_DECRYPTED_NOTE_TEXT, id, password);
//            new HttpRequester(context)
//                    .execute(
//                            url,
//                            METHOD_GET,
//                            "",
//                            MyDiaryUserModel.getToken());
//        } catch(Exception ex) {
//            Logger.getInstance().logError(TAG, ex);
//        }
//    }
}
