package com.example.outpass2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class myholder_request extends RecyclerView.ViewHolder {

    TextView roll,date1,time,going,purpose,vehicle;
    Button accept,reject;

    public myholder_request(@NonNull View itemView) {
        super(itemView);
        this.roll= itemView.findViewById(R.id.roll);
        this.time= itemView.findViewById(R.id.time);
        this.date1= itemView.findViewById(R.id.date);
        this.going= itemView.findViewById(R.id.going);
        this.purpose= itemView.findViewById(R.id.purpose);
        this.vehicle= itemView.findViewById(R.id.vehicle);
        this.accept=itemView.findViewById(R.id.accept);
        this.reject=itemView.findViewById(R.id.reject);
    }
}
