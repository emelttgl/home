package com.example.home3d;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageActivity extends AppCompatActivity {

    protected ActivityResultLauncher<Intent> launcher;
    protected ImageView ouest,nord,sud,est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        ouest = findViewById(R.id.imageView7);
        nord = findViewById(R.id.imageView);
        est = findViewById(R.id.imageView6);
        sud = findViewById(R.id.imageView8);

        ouest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(i);
            }
        });
        FileInputStream fis = null;
        try {
            fis = openFileInput("image.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            ouest.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        sud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(i);
            }
        });
        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(i);
            }
        });
        nord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcher.launch(i);
            }
        });
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        Toast.makeText(ImageActivity.this,"taille"+bitmap.getHeight() ,Toast.LENGTH_SHORT).show();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("image.data", MODE_PRIVATE);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                        } catch (FileNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            fos.flush();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
        );

    }

/*
        FileInputStream fis = null;
        try {
            fis = openFileInput("image.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            nord.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        FileInputStream fis2 = null;
        try {
            fis = openFileInput("image.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            sud.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/
    }
