package com.example.outpass2;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
//            int flag=0;
            String x=auth.getCurrentUser().getEmail();
            x=x.substring(3,10);
            try{
                int x1=Integer.parseInt(x);
                startActivity(new Intent(LoginActivity.this, signup.class));
                finish();
            }
            catch (NumberFormatException e){
//                flag =1;
                startActivity(new Intent(LoginActivity.this, CaretakerActivity.class));
                finish();
            }

        }

        setContentView(R.layout.activity_login);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FragmentContainer,new LoginFragment());
        fragmentTransaction.commit();



    }
}
