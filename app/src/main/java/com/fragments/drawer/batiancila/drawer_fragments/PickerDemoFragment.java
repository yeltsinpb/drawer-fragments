package com.fragments.drawer.batiancila.drawer_fragments;

import android.app.DatePickerDialog;
import android.support.v4.app.Fragment;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class PickerDemoFragment extends Fragment implements View.OnClickListener{
    private EditText dateEditText;
    private EditText timeEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.picker_demo_layout, container, false);


        dateEditText = layout.findViewById(R.id.date_editText);
        timeEditText = layout.findViewById(R.id.time_editText);

        dateEditText.setOnClickListener(this);
        timeEditText.setOnClickListener(this);

        return layout;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.date_editText:
                DialogFragment datePickerFragment = new DatePickerFragment();
                break;

            case R.id.time_editText:
                DialogFragment timePickerFragment = new TimePickerFragment();
                break;
        }
    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String time = "";

            if(hourOfDay > 12){
                time = (hourOfDay - 12) + ":" + minute + " PM";
            } else if(hourOfDay == 12){
                time = hourOfDay + ":" + minute + " PM";
            } else if(hourOfDay == 0){
                time = "12:" + minute + " AM";
            } else {
                time = hourOfDay + ":" + minute + " AM";
            }

            Toast.makeText(getActivity(), time, Toast.LENGTH_SHORT).show();
        }
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            int year = c.get(Calendar.YEAR);

            // Create a new instance of TimePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Do something with the date chosen by the user
            month += 1; // increment month since month starts with 0
            Toast.makeText(getActivity(), year+"-"+month+"-"+dayOfMonth, Toast.LENGTH_SHORT).show();
        }
    }
}
