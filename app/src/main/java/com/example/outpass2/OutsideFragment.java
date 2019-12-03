package com.example.outpass2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class OutsideFragment extends Fragment {

    private ArrayList<model_outside> temp=new ArrayList<>();

    RecyclerView recyclerView;
    adapter_outside adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view= inflater.inflate(R.layout.fragment_outside,container,false);


        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));
        temp.add(new model_outside("anshul","iwm2017008","cl","shopping","23/05/2019","12:07","ola"));

        recyclerView = view.findViewById(R.id.recycle1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new adapter_outside(getContext(),temp);
        recyclerView.setAdapter(adapter);


        return view;
    }
}
