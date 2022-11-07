package com.example.home3d;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Boussole extends AppCompatActivity {

    protected String Nord, Sud, Ouest, Est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boussole);
    }
}