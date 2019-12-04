package com.example.outpass2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class PopupActivity extends AppCompatActivity {

    private EditText reasonEt;
    private Button bUpdateRegect;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String email;
    private String k;
    private String x,hostel;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        reasonEt = (EditText)findViewById(R.id.reasonEt);
        bUpdateRegect =(Button)findViewById(R.id.bRegectUpdate);

        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
         email = intent.getStringExtra("email");
         k = intent.getStringExtra("k");
         x = intent.getStringExtra("x");
        hostel = intent.getStringExtra("hostel");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        bUpdateRegect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateRegectInfo();
            }
        });



    }

    private void updateRegectInfo() {
        String res = reasonEt.getText().toString().trim();
        if(TextUtils.isEmpty(res)){
            reasonEt.setError("Necessary field to proceed");
            return;
        }
        //firebase work
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

        TempClass.reason=res;

        finish();


    }
}
