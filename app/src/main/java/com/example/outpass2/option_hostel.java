package com.example.outpass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class option_hostel extends AppCompatActivity {

    Spinner spinner;
    Button b1;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_hostel);
        auth=FirebaseAuth.getInstance();
        String email;
        if(auth.getCurrentUser()!=null)
            email = auth.getCurrentUser().getEmail();

        spinner = findViewById(R.id.spinner2);
        b1=findViewById(R.id.button);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(option_hostel.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.hostel));
        myadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadapter);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hostel=spinner.getSelectedItem().toString();

                Intent intent = new Intent(option_hostel.this, CaretakerActivity.class);
                intent.putExtra("hostel", hostel);
                startActivity(intent);
            }
        });


    }
}
