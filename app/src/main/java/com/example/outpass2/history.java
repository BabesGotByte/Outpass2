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
import android.widget.TextView;
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
import java.util.Comparator;
import java.util.Iterator;

import static android.support.constraint.Constraints.TAG;

class Sort implements Comparator<OutpassInfo>
{
    public int compare(OutpassInfo a, OutpassInfo b)
    {
        int dateCompare = b.getDate().compareTo(a.getDate());
        int timeCompare = b.getTime().compareTo(a.getTime());

        if (dateCompare == 0) {
            return ((timeCompare == 0) ? dateCompare : timeCompare);
        } else {
            return dateCompare;
        }

    }
}



public class history extends Fragment {

    private ArrayList<OutpassInfo> hs=new ArrayList<>();
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private CollectionReference historyOutpass ;
    private ProgressBar pg;

    RecyclerView recyclerView;
    adapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view= inflater.inflate(R.layout.fragment_history,container,false);

        db=FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        historyOutpass = db.collection(auth.getCurrentUser().getEmail()).document("History").collection("Outpasses");


        //final TextView tv=view.findViewById(R.id.textView3);
        //pg=view.findViewById(R.id.pgHistory);
        historyOutpass.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                pg.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        OutpassInfo h = document.toObject(OutpassInfo.class);
                        hs.add(h);
                    }

                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }




                if(hs==null)
                    Toast.makeText(getContext(), "empty view", Toast.LENGTH_SHORT).show();


//                pg.setVisibility(View.GONE);

//
//                String x="";
//                for(int i=0;i<hs.size();i++){
//                    x=x+hs.get(i).toString()+"\n\n";
//                }
//                tv.setText(x);


                Collections.sort(hs, new Sort());

                recyclerView = view.findViewById(R.id.recycle);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter = new adapter(getContext(),hs);
                recyclerView.setAdapter(adapter);

            }
        });

        return view;
    }
}
