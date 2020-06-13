package com.example.outpass2;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class StatusActivity extends AppCompatActivity {

    IncomingStudents obj;
    private GifImageView gifImageView;
    FirebaseAuth auth;
    private ArrayList<IncomingStudents> cc = new ArrayList<>();
    private DatabaseReference myRef;
    String s;
    Button b;
     String k,x;
    int a;
    TextView roll, purpose, destination, vehicle, date1, time1;
    private FirebaseDatabase database;
    ProgressBar pg;
    OutpassInfo op;
    private FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pending);
        auth = FirebaseAuth.getInstance();

        op = TempClass.op;
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("outpass2-ae30f");

        String status;
        Intent intent = getIntent();
//        status= intent.getStringExtra("status");
        status = op.getStatus();
        if (status.equals("Pending")) {
            setContentView(R.layout.pending);
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("pending.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        } else if (status.equals("Accepted")) {
            setContentView(R.layout.accepted);
            TextView approvedBy = findViewById(R.id.name_approved);
            pg = findViewById(R.id.pgpg);
            if (!op.getApprovedBy().equals("")) {
                approvedBy.setText(op.getApprovedBy());
            }
            TextView returnTime = findViewById(R.id.in_time);
            if (!op.getReturnTime().equals("")) {
                returnTime.setText(op.getReturnTime());
            }
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("check.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        } else if (status.equals("Rejected")) {
            setContentView(R.layout.rejected);
            TextView approvedBy = findViewById(R.id.name_approved);
            if (!op.getApprovedBy().equals("")) {
                approvedBy.setText(op.getApprovedBy());
            }
//            TextView returnTime = findViewById(R.id.reason);
            if (!op.getReason().equals("")) {
//                returnTime.setText(op.getReason());
            }
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("cross.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        }


        roll = findViewById(R.id.rollno);
        roll.setText(auth.getCurrentUser().getEmail());
        purpose = findViewById(R.id.purpose);
        purpose.setText(op.getPurpose());
        destination = findViewById(R.id.destination);
        destination.setText(op.getGoing());
        vehicle = findViewById(R.id.vehicle);
        vehicle.setText(op.getVehicle());
        date1 = findViewById(R.id.date1);
        date1.setText(op.getDate());
        time1 = findViewById(R.id.time1);
        time1.setText(op.getTime());

        b = findViewById(R.id.home);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < cc.size(); i++) {
                    if (op.getEmail().equals(cc.get(i).getRoll())) {

                        obj = cc.get(i);

                        db
                                .collection(op.getEmail())
                                .document("History")
                                .collection("Outpasses")
                                .document(k)
                                .update("returnTime", cc.get(i).getDate() + "  " + cc.get(i).getTime()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(StatusActivity.this, "Verified", Toast.LENGTH_SHORT).show();


                                } else {
                                    Toast.makeText(StatusActivity.this, "Not Verified", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        a = 1;
                    }
                }





//                } else {
//                    finish();
//                }
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (op.getStatus().equals("Accepted")) {
            pg.setVisibility(View.VISIBLE);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                cc.clear();
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        IncomingStudents c = d.getValue(IncomingStudents.class);
                        cc.add(c);
                    }

                    if (cc.size() != 0) {

                        Toast.makeText(StatusActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }


                    // compare karo cc se rollno. if same fir mera kaam



                    String[] d = op.getDate().split("/");
                    String a = "";
                    for (int ii = 0; ii < d.length; ii++) {
                        a = a + d[ii] + "-";
                    }

                    k = op.getGoing() + "." + a + "." + op.getTime();
                    final String x = op.getEmail() + "." + k;

                    pg.setVisibility(View.GONE);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(StatusActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
