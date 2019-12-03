package com.example.outpass2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.ArrayList;

public class adapter_request extends RecyclerView.Adapter<myholder_request>{

    Context c;
    ArrayList<OutpassInfo> models;

    public  adapter_request(Context c, ArrayList<OutpassInfo> models){
        this.c=c;
        this.models=models;

    }
    public myholder_request onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request,null);
        return new myholder_request(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder_request myholder_request, int i) {
        myholder_request.roll.setText(models.get(i).getEmail());
        myholder_request.going.setText(models.get(i).getGoing());
        myholder_request.purpose.setText(models.get(i).getPurpose());
        myholder_request.date1.setText(models.get(i).getDate());
        myholder_request.time.setText(models.get(i).getTime());
        myholder_request.vehicle.setText(models.get(i).getVehicle());
        myholder_request.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        myholder_request.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Animation animation = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
        myholder_request.itemView.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
