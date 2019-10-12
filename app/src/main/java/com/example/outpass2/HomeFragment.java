package com.example.outpass2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class HomeFragment extends Fragment {

    private TextView date1;
    public static final String TAG="MainActivity";
    private EditText going,timer,purpose,vehicle;
    private Button b1;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TimePickerDialog.OnTimeSetListener timeSetListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        going=view.findViewById(R.id.going);
        purpose=view.findViewById(R.id.purpose);
        date1=view.findViewById(R.id.datet);
        timer=view.findViewById(R.id.timet);
        vehicle=view.findViewById(R.id.vehicle);
        b1=view.findViewById(R.id.submit);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year=calendar.get(calendar.YEAR);
                int month=calendar.get(calendar.MONTH);;
                int day=calendar.get(calendar.DAY_OF_WEEK);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

//        timer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                int hour=calendar.get(calendar.HOUR_OF_DAY);
//                int minute=calendar.get(calendar.MINUTE);
//                TimePickerDialog dialog2 = new TimePickerDialog(
//                        getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(),
//                        hour,minute, DateFormat.is24HourFormat(getContext()));
//                dialog2.show();
//            }
//        });

        dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"date"+year+"/"+month+"/"+dayOfMonth);
                String dater = month+" / "+dayOfMonth+" / "+year;
                date1.setText(dater);
            }
        };

//        timeSetListener= new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Log.d(TAG,"date"+hourOfDay+"/"+minute);
//                timer.setText("time:"+" / "+hourOfDay+" / "+minute);
//            }
//        };


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String going_t = going.getText().toString().trim();
                String purpose_t = purpose.getText().toString().trim();
                String timer_t = timer.getText().toString().trim();
                String vehicle_t = vehicle.getText().toString().trim();

                if (TextUtils.isEmpty(going_t)) {
                    going.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(purpose_t)) {
                    purpose.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(timer_t)) {
                    timer.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(vehicle_t)) {
                    vehicle.setError("This field should not be empty");
                    return;
                }
            }
        });
        return view;
    }
}
