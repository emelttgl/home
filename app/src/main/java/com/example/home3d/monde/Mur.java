package com.example.home3d.monde;



import android.widget.ImageView;

import com.example.home3d.monde.Acces;

import java.io.Serializable;
import java.util.ArrayList;

public class Mur implements Serializable {

    protected ImageView image;

    protected ArrayList<Acces>acces;

    public Mur(ImageView image, ArrayList<Acces> acces) {
        this.image = image;
        this.acces = acces;
    }
}