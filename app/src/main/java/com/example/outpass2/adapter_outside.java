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

public class adapter_outside extends RecyclerView.Adapter<myholder_outside>{

    Context c;
    ArrayList<model_outside> models;

    public  adapter_outside(Context c, ArrayList<model_outside> models){
        this.c=c;
        this.models=models;

    }
    public myholder_outside onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_outside,null);
        return new myholder_outside(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder_outside myholder_outside, int i) {
        myholder_outside.name.setText(models.get(i).getName());
        myholder_outside.roll.setText(models.get(i).getRoll());
        myholder_outside.going.setText(models.get(i).getGoing());
        myholder_outside.date1.setText(models.get(i).getDate());
        myholder_outside.time.setText(models.get(i).getTime());
        myholder_outside.purpose.setText(models.get(i).getPurpose());
        myholder_outside.vehicle.setText(models.get(i).getVehicle());

        Animation animation = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
        myholder_outside.itemView.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
