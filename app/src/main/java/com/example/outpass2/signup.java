package com.example.outpass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signup extends AppCompatActivity {
    private EditText name_t,contact_t,room_t,hostel_t,gname_t,gnumber_t;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b1=findViewById(R.id.but);
        name_t=findViewById(R.id.name);
        contact_t=findViewById(R.id.contact);
        room_t=findViewById(R.id.room);
        hostel_t=findViewById(R.id.hostel);
        gname_t=findViewById(R.id.guardian_name);
        gnumber_t=findViewById(R.id.guardian_contact);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_e = name_t.getText().toString().trim();
                String contact_e = contact_t.getText().toString();
                String room_e = room_t.getText().toString().trim();
                String hostel_e = hostel_t.getText().toString();
                String gname_e = gname_t.getText().toString().trim();
                String gnumber_e = gnumber_t.getText().toString();


                if (TextUtils.isEmpty(name_e)) {
                    name_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(contact_e)) {
                    contact_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(room_e)) {
                    room_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(hostel_e)) {
                    hostel_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(gname_e)) {
                    gname_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(gnumber_e)) {
                    gnumber_t.setError("This field should not be empty");
                    return;
                }

                startActivity(new Intent(signup.this, navbar.class));

            }
        });

    }
}
