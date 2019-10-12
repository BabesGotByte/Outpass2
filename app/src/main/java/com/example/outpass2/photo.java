package com.example.outpass2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class photo extends AppCompatActivity {

    ArrayList<Bitmap> photos=new ArrayList<>();
    File photoFile = null;
    String currentPhotoPath;
    ImageView i1;
    TextView t1;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void func(){
        int i;
        for(i=0;i<3;i++){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 0);

            if (intent.resolveActivity(getPackageManager()) != null) {
                try {
                    photoFile = createImageFile();

                    finish();
                }
                catch (IOException ex) {
                }
            }
        }
//        if(i==2) {
//            startActivity(new Intent(photo.this, navbar.class));
//        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        func();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if(photos.size()==3){
                    startActivity(new Intent(photo.this, navbar.class));
                }
            }
        }, 5000);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        photos.add(bitmap);
        if(photos.size()==3){
            startActivity(new Intent(photo.this, navbar.class));
        }
        //imageview.setImageBitmap(bitmap);
    }
}
