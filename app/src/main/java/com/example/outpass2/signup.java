package com.example.outpass2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    private EditText name_t,contact_t,room_t,hostel_t,gname_t,gnumber_t;
    private Button b1;
    private FirstTimeRegistration firstTimeRegistration;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        b1=findViewById(R.id.but);
        name_t=findViewById(R.id.name);
        contact_t=findViewById(R.id.contact);
        room_t=findViewById(R.id.room);
        hostel_t=findViewById(R.id.hostel);
        gname_t=findViewById(R.id.guardian_name);
        gnumber_t=findViewById(R.id.guardian_contact);
        pg = findViewById(R.id.progressBar);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pg.setVisibility(View.VISIBLE);
                String name_e = name_t.getText().toString().trim();
                String contact_e = contact_t.getText().toString();
                String room_e = room_t.getText().toString().trim();
                String hostel_e = hostel_t.getText().toString();
                String gname_e = gname_t.getText().toString().trim();
                String gnumber_e = gnumber_t.getText().toString();


                if (TextUtils.isEmpty(name_e)) {
                    pg.setVisibility(View.GONE);
                    name_t.setError("This field should not be empty");

                    return;
                }
                if (TextUtils.isEmpty(contact_e)) {
                    pg.setVisibility(View.GONE);
                    contact_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(room_e)) {
                    room_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(hostel_e)) {
                    pg.setVisibility(View.GONE);
                    hostel_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(gname_e)) {
                    pg.setVisibility(View.GONE);
                    gname_t.setError("This field should not be empty");
                    return;
                }
                if (TextUtils.isEmpty(gnumber_e)) {
                    pg.setVisibility(View.GONE);
                    gnumber_t.setError("This field should not be empty");
                    return;
                }

                firstTimeRegistration = new FirstTimeRegistration(name_e,contact_e,room_e,hostel_e,gname_e,gnumber_e,null);

                db.collection(auth.getCurrentUser().getEmail()).document().set(firstTimeRegistration)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(signup.this, "Your details has been successfully registered", Toast.LENGTH_SHORT).show();
//                        progressBarShopRegistration.setVisibility(View.GONE);
//                        Toast.makeText(getContext(), "Your Shop is successfully registered", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
//                                progressBarShopRegistration.setVisibility(View.GONE);
                                pg.setVisibility(View.GONE);
                                Toast.makeText(signup.this, "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                startActivity(new Intent(signup.this, navbar.class));

            }
        });

    }
}
