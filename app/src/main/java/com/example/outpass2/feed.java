package com.example.outpass2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class feed extends Fragment {

    EditText feedbk;
    Button b1;
    FirebaseAuth auth;
    DatabaseReference dbreference;
    String feed_value;


    private GifImageView gifImageView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        auth=FirebaseAuth.getInstance();

        final String email = auth.getCurrentUser().getEmail();


        gifImageView = view.findViewById(R.id.gimage);
        b1=view.findViewById(R.id.send_button);
        feedbk = view.findViewById(R.id.feedback_hint);

        feed_value=feedbk.getText().toString().trim();

        try {
            InputStream inputStream = getActivity().getAssets().open("like.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException ex) {

        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbreference= FirebaseDatabase.getInstance().getReference("Feedback");
                String id = dbreference.push().getKey();
                F f = new F(email,feedbk.getText().toString());
                dbreference.child(id).setValue(f);

                FragmentTransaction fr=getFragmentManager().beginTransaction();
                fr.replace(R.id.NavFragmentContainer,new HomeFragment());
                fr.commit();
            }
        });

        return view;

    }
}
