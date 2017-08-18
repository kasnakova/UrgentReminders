package com.example.urgentreminders.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urgentreminders.R;

/**
 * Created by Liza on 1.5.2015 г..
 */
public class BlankForCalendarFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank_for_calendar, container, false);
        return v;
    }
}
