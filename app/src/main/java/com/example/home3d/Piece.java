package com.example.home3d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Piece{

    protected String nom;
    protected Mur mur1, mur2, mur3, mur4;

    public Piece(String s){
        this.nom=s;
    }

    public String getNom (){
        return nom;
    }
}