package com.example.home3d.outils;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.home3d.R;
import com.example.home3d.monde.Batiment;
import com.example.home3d.monde.Piece;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    final static public String BATIMENT_KEY="Envoyer";
    final static public String PIECES_KEY="Recuperer";

    protected Button Construction;
    protected Button Visualisation;
    protected ActivityResultLauncher<Intent> launcher;
    protected ActivityResultLauncher<Intent> launcherp;
    protected Batiment batiment;
    protected ArrayList<Piece> pieces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieces = new ArrayList<Piece>();
        Construction = findViewById(R.id.construction);
        Visualisation=findViewById(R.id.visualisation);
        Log.i("MainActivity", "Création batiment");
        batiment = new Batiment();


        Construction.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConstructionActivity.class);
            startActivity(intent);
            //intent.putExtra(PIECES_KEY,pieces);
            //launcher.launch(intent);

        });

        Visualisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VisualisationActivity.class);
                startActivity(intent);
            }
        });
       /* launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            assert result.getData() != null;
           // pieces.add(result.getData().getSerializableExtra(PIECES_KEY, Piece.class));
            if (result.getResultCode()== Activity.RESULT_OK)
               pieces.add((Piece) result.getData().getSerializableExtra(BATIMENT_KEY));

        });

        this.launcherp = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            assert result.getData() != null;
            pieces.add((Piece) result.getData().getSerializableExtra(BATIMENT_KEY));
        });*/
    }

}