package com.example.outpass2;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class adapter_request extends RecyclerView.Adapter<myholder_request>{

    Context c;
    ArrayList<OutpassInfo> models;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    public  adapter_request(Context c, ArrayList<OutpassInfo> models){
        this.c=c;
        this.models=models;

    }
    public myholder_request onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request,null);
        return new myholder_request(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder_request myholder_request, final int i) {
        myholder_request.roll.setText(models.get(i).getEmail());
        myholder_request.going.setText(models.get(i).getGoing());
        myholder_request.purpose.setText(models.get(i).getPurpose());
        myholder_request.date1.setText(models.get(i).getDate());
        myholder_request.time.setText(models.get(i).getTime());
        myholder_request.vehicle.setText(models.get(i).getVehicle());

        String[] d = models.get(i).getDate().split("/");
        String a="";
        for(int ii=0;ii<d.length;ii++){
            a=a+d[ii]+"-";
        }
        final String k =models.get(i).getGoing()+"."+a+"."+models.get(i).getTime();
        final String x = models.get(i).getEmail()+"."+k;

        myholder_request.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                models.get(i).setStatus("Accepted");
                auth=FirebaseAuth.getInstance();
                models.get(i).setApprovedBy(auth.getCurrentUser().getEmail());
                db
                        .collection(models.get(i).getEmail())
                        .document("History")
                        .collection("Outpasses")
                        .document(k)
                        .update("status","Accepted","approvedBy",auth.getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            db.collection(models.get(i).getFirstTimeRegistration().getHostel()).document(x)
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(c, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(c, "Failed Successfully", Toast.LENGTH_SHORT).show();                                        }
                                    });

                            db.collection("Outsiders").document(x).set(models.get(i))
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(c, "Outsiders added", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
//                                            pg.setVisibility(View.GONE);
                                            Toast.makeText(c, "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            models.remove(myholder_request.getAdapterPosition());
                            notifyItemRemoved(myholder_request.getAdapterPosition());
                            notifyItemRangeChanged(myholder_request.getAdapterPosition(), models.size());
                        }
                        else {
                            Toast.makeText(c, "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        myholder_request.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(c, "Bad Caretaker", Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(c,PopupActivity.class);

//                intent.putExtra("email",models.get(i).getEmail());
//                intent.putExtra("k",k);
//                intent.putExtra("x",x);
//                intent.putExtra("hostel",models.get(i).getFirstTimeRegistration().getHostel());

//                c.startActivity(intent);

                db
                        .collection(models.get(i).getEmail())
                        .document("History")
                        .collection("Outpasses")
                        .document(k)
                        .update("status","Rejected","approvedBy",auth.getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            db.collection(models.get(i).getFirstTimeRegistration().getHostel()).document(x)
                                    .delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(c, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(c, "Failed Successfully", Toast.LENGTH_SHORT).show();                                        }
                                    });

                            db.collection("Rejecteds").document(x).set(models.get(i))
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(c, "Rejecteds added", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
//                                            pg.setVisibility(View.GONE);
                                            Toast.makeText(c, "Some error occurred : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });

                            models.remove(myholder_request.getAdapterPosition());
                            notifyItemRemoved(myholder_request.getAdapterPosition());
                            notifyItemRangeChanged(myholder_request.getAdapterPosition(), models.size());
                        }
                        else {
                            Toast.makeText(c, "Failed to update", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




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
