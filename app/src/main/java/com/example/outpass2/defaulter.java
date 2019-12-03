package com.example.outpass2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

public class defaulter extends Fragment {

    RecyclerView recyclerView;
    adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view= inflater.inflate(R.layout.fragment_history,container,false);


//                recyclerView = view.findViewById(R.id.recycle);
//                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                adapter = new adapter(getContext(),hs);
//                recyclerView.setAdapter(adapter);

        return view;
    }
}
