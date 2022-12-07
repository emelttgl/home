package com.example.home3d.monde;


import android.widget.ImageView;

import java.io.Serializable;

public class Acces implements Serializable {

    protected Piece piece;
    protected String nom;
    protected ImageView imageView;
    protected boolean selection;

    public Acces (){
        piece = new Piece(nom,selection);

    }

}