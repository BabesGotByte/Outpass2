package com.example.outpass2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class signup extends AppCompatActivity {
    private static final String TAG = "TAG";
    private EditText name_t,contact_t,room_t,hostel_t,gname_t,gnumber_t;
    private Button b1;
    private FirstTimeRegistration firstTimeRegistration,firstTimeRegistration2=null;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        pg = findViewById(R.id.progressBar);
        DocumentReference docRef = db.collection(auth.getCurrentUser().getEmail()).document("Details");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        pg.setVisibility(View.GONE);
                        finish();
                        startActivity(new Intent(signup.this, navbar.class));
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        pg.setVisibility(View.GONE);
                        Log.d(TAG, "No such document");
                    }
                } else {
                    pg.setVisibility(View.GONE);
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

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

                firstTimeRegistration = new FirstTimeRegistration(name_e,contact_e,room_e,hostel_e,gname_e,gnumber_e);

                db.collection(auth.getCurrentUser().getEmail()).document("Details").set(firstTimeRegistration)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(signup.this, "Your details has been successfully registered", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                pg.setVisibility(View.GONE);
                                Toast.makeText(signup.this, "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                startActivity(new Intent(signup.this, navbar.class));

            }
        });

    }
}
