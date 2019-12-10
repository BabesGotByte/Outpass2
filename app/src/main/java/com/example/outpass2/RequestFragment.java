package com.example.outpass2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

import static android.support.constraint.Constraints.TAG;

import java.util.ArrayList;
import java.util.Collections;


public class RequestFragment extends Fragment {

    private View view;
    private String hostel;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private CollectionReference RequestOutpass;
    private ArrayList<OutpassInfo> reqList=new ArrayList<>();

//    private ArrayList<model_outside> temp=new ArrayList<>();

    RecyclerView recyclerView;
    adapter_request adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_request, container, false);
        Intent intent=getActivity().getIntent();
        hostel= intent.getStringExtra("hostel");
        db=FirebaseFirestore.getInstance();

        RequestOutpass = db.collection(hostel);


        RequestOutpass.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                pg.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        OutpassInfo h = document.toObject(OutpassInfo.class);
                        reqList.add(h);
                    }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }




                if(reqList==null)
                    Toast.makeText(getContext(), "empty view", Toast.LENGTH_SHORT).show();

                recyclerView = view.findViewById(R.id.recycle_request);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new adapter_request(getContext(),reqList);
                recyclerView.setAdapter(adapter);





            }
        });

        return view;


    }

}
