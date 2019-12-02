package com.example.outpass2;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class myholder extends RecyclerView.ViewHolder {

    ImageView log;
    TextView date1,time,going,purpose,vehicle;

    public myholder(@NonNull View itemView) {
        super(itemView);
        this.log=itemView.findViewById(R.id.log);
        this.date1= itemView.findViewById(R.id.date);
        this.time= itemView.findViewById(R.id.time);
        this.going= itemView.findViewById(R.id.going);
        this.purpose= itemView.findViewById(R.id.purpose);
        this.vehicle= itemView.findViewById(R.id.vehicle);
    }
}
