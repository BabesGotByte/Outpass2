package com.example.outpass2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.felipecsl.gifimageview.library.GifImageView;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class feed extends Fragment {

    EditText feedback;
    FirebaseAuth auth;

    private GifImageView gifImageView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        auth=FirebaseAuth.getInstance();
        String email = auth.getCurrentUser().getEmail();


        gifImageView = view.findViewById(R.id.gimage);

        feedback = view.findViewById(R.id.editText);

        try {
            InputStream inputStream = getActivity().getAssets().open("like.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException ex) {

        }




        return view;

    }
}
