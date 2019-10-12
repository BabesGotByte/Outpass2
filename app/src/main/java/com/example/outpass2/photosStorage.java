package com.example.outpass2;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class photosStorage {
    public static ArrayList<Bitmap> photos;

    public photosStorage(ArrayList<Bitmap> photos) {
        this.photos = photos;
    }

    public ArrayList<Bitmap> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Bitmap> photos) {
        this.photos = photos;
    }
}
