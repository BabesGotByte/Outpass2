package com.example.outpass2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class myholder_outside extends RecyclerView.ViewHolder {

    TextView name,roll,date1,time,going,purpose,vehicle;

    public myholder_outside(@NonNull View itemView) {
        super(itemView);
        this.name=itemView.findViewById(R.id.name_approved);
        this.roll= itemView.findViewById(R.id.roll);
        this.time= itemView.findViewById(R.id.time);
        this.date1= itemView.findViewById(R.id.date);
        this.going= itemView.findViewById(R.id.going);
        this.purpose= itemView.findViewById(R.id.purpose);
        this.vehicle= itemView.findViewById(R.id.vehicle);
    }
}
