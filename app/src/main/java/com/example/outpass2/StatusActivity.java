package com.example.outpass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class StatusActivity extends AppCompatActivity {

    private GifImageView gifImageView;
    FirebaseAuth auth;
    String s;
    Button b;

    TextView roll,purpose,destination,vehicle,date1,time1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pending);
        auth=FirebaseAuth.getInstance();
        OutpassInfo op = TempClass.op;


        String status;
        Intent intent=getIntent();
        status= intent.getStringExtra("status");

        if(status.equals("Pending")){
            setContentView(R.layout.pending);
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("pending.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        }
        else if(status.equals("Accepted")){
            setContentView(R.layout.accepted);
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("check.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        }
        else if(status.equals("Rejected")){
            setContentView(R.layout.rejected);
            gifImageView = (GifImageView) findViewById(R.id.gifimage);
            try {
                InputStream inputStream = getAssets().open("cross.gif");
                byte[] bytes = IOUtils.toByteArray(inputStream);
                gifImageView.setBytes(bytes);
                gifImageView.startAnimation();
            } catch (IOException ex) {

            }
        }



        roll=findViewById(R.id.rollno);
        roll.setText(auth.getCurrentUser().getEmail());
        purpose = findViewById(R.id.purpose);
        purpose.setText(op.getPurpose());
        destination=findViewById(R.id.destination);
        destination.setText(op.getGoing());
        vehicle=findViewById(R.id.vehicle);
        vehicle.setText(op.getVehicle());
        date1=findViewById(R.id.date1);
        date1.setText(op.getDate());
        time1=findViewById(R.id.time1);
        time1.setText(op.getTime());

        b=findViewById(R.id.home);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),navbar.class));
                finish();
            }
            });

    }
}
