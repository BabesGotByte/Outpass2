package com.example.outpass2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feedback extends AppCompatActivity {

    //DatabaseReference b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_feedback);
        Button b1 = findViewById(R.id.send_button);
        //b = FirebaseDatabase.getInstance().getReference("feedback");
        final EditText t1 = findViewById(R.id.editText);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //b.push().setValue(t1.getText().toString());
                finish();
            }
        });
    }
}
