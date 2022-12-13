package com.example.home3d.monde;



import android.widget.ImageView;

import com.example.home3d.monde.Acces;

import java.io.Serializable;
import java.util.ArrayList;

public class Mur implements Serializable {

    protected String image;

    protected ArrayList<Acces>acces;

    public Mur(String image, ArrayList<Acces> acces) {
        this.image = image;
        this.acces = acces;
    }
    public Mur(String image) {
        this.image=image;
        this.acces= new ArrayList<>();
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }


}