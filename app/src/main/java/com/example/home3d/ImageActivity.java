package com.example.home3d;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageActivity extends AppCompatActivity {

    protected ActivityResultLauncher<Intent> launcherN;
    protected ActivityResultLauncher<Intent> launcherO;
    protected ActivityResultLauncher<Intent> launcherE;
    protected ActivityResultLauncher<Intent> launcherS;
    protected ImageView ouest,nord,sud,est;
    protected Button valider;
    protected ImageButton Ouest,Nord,Sud,Est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        valider=findViewById(R.id.buttonVal);

        Ouest=findViewById(R.id.imageButtonOuest);
        Nord=findViewById(R.id.imageButtonNord);
        Sud=findViewById(R.id.imageButtonSud);
        Est=findViewById(R.id.imageButtonEst);

        ouest = findViewById(R.id.imageView7);
        nord = findViewById(R.id.imageView);
        est = findViewById(R.id.imageView6);
        sud = findViewById(R.id.imageView8);


        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageActivity.this.finish();


            }
        });



        sud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcherS.launch(i);
            }
        });
        launcherS = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        Toast.makeText(ImageActivity.this,"taille"+bitmap.getHeight() ,Toast.LENGTH_SHORT).show();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("imageS.data", MODE_PRIVATE);
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
        est.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcherE.launch(i);
            }
        });
        launcherE = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        Toast.makeText(ImageActivity.this,"taille"+bitmap.getHeight() ,Toast.LENGTH_SHORT).show();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("imageE.data", MODE_PRIVATE);
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
        nord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcherN.launch(i);
            }
        });


        launcherN = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        Toast.makeText(ImageActivity.this,"taille"+bitmap.getHeight() ,Toast.LENGTH_SHORT).show();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("imageN.data", MODE_PRIVATE);
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
        recupImageO();
        recupImageE();
        recupImageN();
        recupImageS();
        prendrePhotoO();
    }

    public void recupImageO() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("imageO.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.ouest.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void recupImageS() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("imageS.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.sud.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void recupImageN() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("imageN.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.nord.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void recupImageE() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("imageE.data");
            Bitmap bm = BitmapFactory.decodeStream(fis);
            this.est.setImageBitmap(bm);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void prendrePhotoN(){

    }

    public void prendrePhotoE(){

    }

    public void prendrePhotoS(){

    }

    public void prendrePhotoO(){
        Ouest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcherO.launch(i);
            }
        });
        launcherO = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                        Toast.makeText(ImageActivity.this,"taille image : "+bitmap.getHeight() ,Toast.LENGTH_SHORT).show();
                        FileOutputStream fos = null;
                        try {
                            fos = openFileOutput("imageO.data", MODE_PRIVATE);
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

    public void modifierPhoto(){

    }
}
