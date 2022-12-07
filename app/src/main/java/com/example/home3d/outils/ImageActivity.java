package com.example.home3d.outils;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.home3d.R;
import com.example.home3d.monde.Mur;
import com.example.home3d.monde.Piece;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {
    public final static String BATIMENT_KEY="Envoyer";
    public final static String PIECES_KEY="Recuperer";
    protected ActivityResultLauncher<Intent> launcherN;
    protected ActivityResultLauncher<Intent> launcherO;
    protected ActivityResultLauncher<Intent> launcherE;
    protected ActivityResultLauncher<Intent> launcherS;
    protected ActivityResultLauncher<Intent> launcher;
    protected ImageView ouest,nord,sud,est;
    protected Button valider;
    protected ImageButton Ouest,Nord,Sud,Est,modifO,modifS,modifN,modifE;
    protected Piece piece;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        piece = new Piece();
        valider=findViewById(R.id.buttonVal);

        Ouest=findViewById(R.id.imageButtonOuest);
        Nord=findViewById(R.id.imageButtonNord);
        Sud=findViewById(R.id.imageButtonSud);
        Est=findViewById(R.id.imageButtonEst);

        modifE=findViewById(R.id.imageButtonEst2);
        modifS=findViewById(R.id.imageButtonSud2);
        modifN=findViewById(R.id.imageButtonNord2);
        modifO=findViewById(R.id.imageButtonOuest2);

        ouest = findViewById(R.id.imageView7);
        nord = findViewById(R.id.imageView);
        est = findViewById(R.id.imageView6);
        sud = findViewById(R.id.imageView8);




        recupImageO();
        recupImageE();
        recupImageN();
        recupImageS();
        prendrePhotoO();
        prendrePhotoS();
        prendrePhotoE();
        prendrePhotoN();
        modifierPhotoN();
        modifierPhotoE();
        modifierPhotoS();
        modifierPhotoO();

        valider.setOnClickListener(v -> {
            //pieces.setPieces(pieces);
            Intent i = new Intent();
            i.putExtra(BATIMENT_KEY,piece);
            setResult(Activity.RESULT_OK, i);
            finish();
        });

        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            piece.setPiece(new Piece(piece.getNom(), new Mur(ouest, null),new Mur(nord, null),new Mur(sud, null),new Mur(est, null)));
            piece= (Piece) getIntent().getSerializableExtra(PIECES_KEY);
        });

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
        Nord.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcherN.launch(i);
        });

        launcherN = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
                    recupImageN();
                }
        );
    }

    public void prendrePhotoE(){
        Est.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcherE.launch(i);
        });
        launcherE = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
                    recupImageE();
                }
        );
    }

    public void prendrePhotoS(){
        Sud.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcherS.launch(i);
        });
        launcherS = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
                    recupImageS();
                }
        );
    }

    public void prendrePhotoO(){
        Ouest.setOnClickListener(v -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            launcherO.launch(i);
        });
        launcherO = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
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
                    recupImageO();
                }
        );
    }

    public void modifierPhotoO(){
        modifO.setOnClickListener(v -> {
            Intent intent = new Intent(ImageActivity.this, AccesOuestActivity.class);
            startActivity(intent);            });
    }

    public void modifierPhotoN(){
        modifN.setOnClickListener(v -> {
            Intent intent = new Intent(ImageActivity.this, AccessNorActivity.class);
            startActivity(intent);
        });
    }
    public void modifierPhotoS(){
        modifS.setOnClickListener(v -> {
            Intent intent = new Intent(ImageActivity.this, AccessSudActivity.class);
            startActivity(intent);
        });
    }
    public void modifierPhotoE(){
        modifE.setOnClickListener(v -> {
            Intent intent = new Intent(ImageActivity.this, AccesEstActivity.class);
            startActivity(intent);
        });
    }
}
