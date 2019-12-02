package com.example.outpass2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;


public class HomeFragment extends Fragment {

    private TextView date1,timer;
    public static final String TAG="MainActivity";
    private EditText going,purpose,vehicle;
    private Button b1;
    private TimePicker timePicker1 = null;
    private TextView time;
    private Calendar calendar;
    private String format = "";
    private DatePickerDialog.OnDateSetListener dateSetListener;

    private TimePickerDialog.OnTimeSetListener timeSetListener;
    private String dater="",tim="";
    TimePickerDialog timePickerDialog;
    int currentHour;
    int currentMinute;
    String amPm;
    private OutpassInfo h;
    private FirstTimeRegistration f;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private ProgressBar pg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        going = view.findViewById(R.id.going);
        purpose = view.findViewById(R.id.purpose);
        date1 = view.findViewById(R.id.datet);
        timer = view.findViewById(R.id.timet);
        vehicle = view.findViewById(R.id.vehicle);
        b1 = view.findViewById(R.id.submit);
        time = view.findViewById(R.id.timet);
        auth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection(auth.getCurrentUser().getEmail()).document("Details");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                 f = documentSnapshot.toObject(FirstTimeRegistration.class);
            }
        });

        going=view.findViewById(R.id.going);
        purpose=view.findViewById(R.id.purpose);
        date1=view.findViewById(R.id.datet);
        timer=view.findViewById(R.id.timet);
        vehicle=view.findViewById(R.id.vehicle);
        b1=view.findViewById(R.id.submit);
        pg=view.findViewById(R.id.pgOutpass);

        //date picker
        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(calendar.YEAR);
                int month = calendar.get(calendar.MONTH);
                int day = calendar.get(calendar.DAY_OF_WEEK);
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"date"+year+"/"+month+"/"+dayOfMonth);

                dater = month+" / "+dayOfMonth+" / "+year;
                date1.setText(dater);
            }
        };


        //timepicker code
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        tim=String.format("%02d:%02d", hourOfDay, minutes) + amPm;
                        timer.setText(tim);
                    }
                }, currentHour, currentMinute, false);

                timePickerDialog.show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                final String going_t = going.getText().toString().trim();
                String purpose_t = purpose.getText().toString().trim();
                final String date_t = date1.getText().toString().trim();
                final String timer_t = timer.getText().toString().trim();
                String vehicle_t = vehicle.getText().toString().trim();

                if (TextUtils.isEmpty(going_t)) {
                    pg.setVisibility(View.GONE);
                    going.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(purpose_t)) {
                    pg.setVisibility(View.GONE);
                    purpose.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(date_t)) {
                    pg.setVisibility(View.GONE);
                    date1.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(timer_t)) {
                    pg.setVisibility(View.GONE);
                    timer.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(vehicle_t)) {
                    pg.setVisibility(View.GONE);
                    vehicle.setError("This field should not be empty");
                    return;
                }

                String[] d = date_t.split("/");
                String a="";
                for(int i=0;i<d.length;i++){
                    a=a+d[i]+"-";
                }

                h=new OutpassInfo(going_t,purpose_t,dater,tim,vehicle_t,f,"Pending");
                TempClass.op=h;

                db.collection(auth.getCurrentUser().getEmail()).document("History").collection("Outpasses").document(going_t+"."+a+"."+timer_t).set(h)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
//                                pg.setVisibility(View.GONE);
//                                Toast.makeText(getContext(), "Your Outpass has been successfully registered", Toast.LENGTH_SHORT).show();
                                db.collection(f.getHostel()).document(auth.getCurrentUser().getEmail()+"."+going_t+"."+date_t+"."+timer_t).set(h)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                pg.setVisibility(View.GONE);
                                                Toast.makeText(getContext(), "Your Outpass has been successfully registered", Toast.LENGTH_SHORT).show();
                                                getActivity().startActivity(new Intent(getContext(),StatusActivity.class));
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                pg.setVisibility(View.GONE);
                                                Toast.makeText(getContext(), "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });



            }
        });
        return view;
    }
}
