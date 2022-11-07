package com.example.home3d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Piece extends AppCompatActivity {

    protected Mur mur1, mur2, mur3, mur4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece);
    }
}