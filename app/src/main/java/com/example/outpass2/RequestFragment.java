package com.example.outpass2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;


public class RequestFragment extends Fragment {


    private ArrayList<model_outside> temp=new ArrayList<>();

    RecyclerView recyclerView;
    adapter_request adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view= inflater.inflate(R.layout.fragment_request,container,false);


        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));

        recyclerView = view.findViewById(R.id.recycle_request);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new adapter_request(getContext(),temp);
        recyclerView.setAdapter(adapter);


        return view;
    }

}
