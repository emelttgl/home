package com.example.home3d.outils;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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

public class MainActivity extends AppCompatActivity {
    final static public String BATIMENT_KEY="Envoyer";
    final static public String PIECES_KEY="Recuperer";

    protected Button Construction;
    protected Button Visualisation;
    protected ActivityResultLauncher<Intent> launcher;
    protected Batiment batiment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Construction = findViewById(R.id.construction);
        Visualisation=findViewById(R.id.visualisation);
        Log.i("MainActivity", "Création batiment");
        batiment = new Batiment();

        Construction.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ConstructionActivity.class);
            intent.putExtra(PIECES_KEY,batiment);
           launcher.launch(intent);
        });
        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            assert result.getData() != null;
            if (result.getResultCode()== Activity.RESULT_OK)
                batiment = (Batiment) result.getData().getSerializableExtra(BATIMENT_KEY);

        });
    }
}