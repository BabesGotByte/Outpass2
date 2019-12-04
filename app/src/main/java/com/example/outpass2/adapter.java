package com.example.outpass2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<myholder>{

    Context c;
    ArrayList<OutpassInfo> models;
    String status;
    public  adapter(Context c, ArrayList<OutpassInfo> models){
        this.c=c;
        this.models=models;

    }
    public myholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,null);
        return new myholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myholder myholder, final int i) {

        if(models.get(i).getStatus().equals("Accepted")){
            myholder.log.setImageResource(R.drawable.tick14);
        }
        else if(models.get(i).getStatus().equals("Rejected")){
            myholder.log.setImageResource(R.drawable.x13);
        }
        else{
            myholder.log.setImageResource(R.drawable.pending_still_image);
        }

        myholder.date1.setText(models.get(i).getDate());
        myholder.going.setText(models.get(i).getGoing());
        myholder.time.setText(models.get(i).getTime());
        myholder.log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TempClass.op= models.get((i));
                status=models.get(i).getStatus();
                Intent intent = new Intent(c, StatusActivity.class);
                intent.putExtra("status", status);
                c.startActivity(intent);


            }
        });
        myholder.vehicle.setText(models.get(i).getVehicle());
        myholder.purpose.setText(models.get(i).getPurpose());

        Animation animation = AnimationUtils.loadAnimation(c,android.R.anim.slide_in_left);
        myholder.itemView.setAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
