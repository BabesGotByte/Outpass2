package com.example.outpass2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    private EditText name,roll,going,purpose,date,time,vehicle;
    private Button b1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        going=view.findViewById(R.id.going);
        purpose=view.findViewById(R.id.purpose);
        date=view.findViewById(R.id.datet);
        time=view.findViewById(R.id.timet);
        vehicle=view.findViewById(R.id.vehicle);
        b1=view.findViewById(R.id.submit);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String going_t = going.getText().toString().trim();
                String purpose_t = purpose.getText().toString().trim();
                String date_t = date.getText().toString().trim();
                String time_t = time.getText().toString().trim();
                String vehicle_t = vehicle.getText().toString().trim();

                if (TextUtils.isEmpty(going_t)) {
                    going.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(purpose_t)) {
                    purpose.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(date_t)) {
                    date.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(time_t)) {
                    time.setError("This field should not be empty");
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
