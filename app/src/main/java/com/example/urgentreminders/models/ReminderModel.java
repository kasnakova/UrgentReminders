package com.example.urgentreminders.models;

import java.util.GregorianCalendar;

/**
 * Created by Liza on 4.5.2015 г..
 */
public class ReminderModel implements Comparable<ReminderModel> {
    private String noteText;
    private GregorianCalendar date;
    private boolean turnSoundOn;

    public ReminderModel(String noteText, GregorianCalendar date, boolean turnSoundOn){
        this.noteText = noteText;
        this.date = date;
        this.turnSoundOn = turnSoundOn;
    }

    public String getNoteText(){
        return this.noteText;
    }

    public GregorianCalendar getDate(){
        return this.date;
    }

    public void setNoteText(String noteText){
        this.noteText = noteText;
    }

    public void setDate(GregorianCalendar date){
        this.date = date;
    }

    public boolean getTurnSoundOn() {
        return turnSoundOn;
    }

    public void setTurnSoundOn(boolean turnSoundOn) {
        this.turnSoundOn = turnSoundOn;
    }

    @Override
    public int compareTo(ReminderModel other) {
        return getDate().compareTo(other.getDate());
    }
}
