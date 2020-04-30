package com.example.outpass2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private static final String TAG = "TAG";
    private FirebaseFirestore db;
    private ProgressBar pgCheckInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pgCheckInfo = findViewById(R.id.pgcheckinfo);
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            pgCheckInfo.setVisibility(View.VISIBLE);
            String x=auth.getCurrentUser().getEmail();
            x=x.substring(3,10);
            try{
                int x1=Integer.parseInt(x);
                db = FirebaseFirestore.getInstance();

                DocumentReference docRef = db.collection(auth.getCurrentUser().getEmail()).document("Details");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                finish();
                                pgCheckInfo.setVisibility(View.GONE);
                                startActivity(new Intent(LoginActivity.this, navbar.class));
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                pgCheckInfo.setVisibility(View.GONE);

                                startActivity(new Intent(LoginActivity.this, signup.class));
                                finish();

                            }
                        } else {
                            pgCheckInfo.setVisibility(View.GONE);
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
            catch (NumberFormatException e){
//                flag =1;
                pgCheckInfo.setVisibility(View.GONE);
                startActivity(new Intent(LoginActivity.this, option_hostel.class));
                finish();
            }

        }
        else{
            pgCheckInfo.setVisibility(View.GONE);
        }

        setContentView(R.layout.activity_login);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FragmentContainer,new LoginFragment());
        fragmentTransaction.commit();

    }
}
