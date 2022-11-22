package com.example.home3d.outils;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.home3d.R;
import com.example.home3d.monde.Batiment;
import com.example.home3d.monde.Piece;

import java.util.ArrayList;

public class ConstructionActivity extends AppCompatActivity {
    final static public String BATIMENT_KEY="Envoyer";
    final static public String PIECES_KEY="Recuperer";
    protected RecyclerView recyclerView;
    protected ArrayList<Piece> pieces;
    protected Adapter adapter;
    protected Button ajouter;
    protected Button sauvegarder;
    protected ActivityResultLauncher<Intent>launcher;
    protected SharedPreferences sharedPreferences;
    protected Batiment batiment;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        batiment = (Batiment) getIntent().getSerializableExtra(PIECES_KEY);
        pieces = batiment.getPieces();
        ajouter = findViewById(R.id.ajouter);
        recyclerView = findViewById(R.id.recyclerview);
        sauvegarder = findViewById(R.id.save);
        buildRecyclerView();

        sauvegarder.setOnClickListener(v -> {
            batiment.setPieces(pieces);
            Intent i = new Intent();
            i.putExtra(BATIMENT_KEY,batiment);
            setResult(Activity.RESULT_OK, i);
            finish();
        });

        launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
            batiment=(Batiment) getIntent().getSerializableExtra(PIECES_KEY);
        });
        
        ajouter.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ConstructionActivity.this);
            dialog.setTitle("Nom de la pièce");

            final EditText editText = new EditText(ConstructionActivity.this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            dialog.setView(editText);

            dialog.setPositiveButton("ok", (dialog1, which) -> {
                String name = editText.getText().toString();
                Toast.makeText(ConstructionActivity.this, "Nouvelle pièce ajoutée: " + name, Toast.LENGTH_LONG).show();
                Piece piece = new Piece(name, false);
                pieces.add(piece);
                buildRecyclerView();

            });

            dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            dialog.show();

        });
    }

    public void buildRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(ConstructionActivity.this));
        adapter = new Adapter(ConstructionActivity.this, pieces);
        recyclerView.setAdapter(adapter);
    }

}