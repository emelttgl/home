package com.example.home3d;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.JsonToken;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ConstructionActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected ArrayList<Piece> pieces;
    protected Adapter adapter;
    protected Button ajouter;
    protected Button sauvegarder;
    protected ActivityResultLauncher<Intent>launcher;
    protected SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        pieces = new ArrayList<>();
        ajouter = findViewById(R.id.ajouter);
        recyclerView = findViewById(R.id.recyclerview);
        sauvegarder = findViewById(R.id.save);


        sauvegarder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
               SharedPreferences.Editor editor = sharedPreferences.edit();

               //editor.putInt();
               editor.apply();
            }
        });
        
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ConstructionActivity.this);
                dialog.setTitle("Nom de la pièce");

                final EditText editText = new EditText(ConstructionActivity.this);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog.setView(editText);

                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText.getText().toString();
                        Toast.makeText(ConstructionActivity.this, "Nouvelle pièce ajoutée: " + name, Toast.LENGTH_LONG).show();
                        Piece piece = new Piece(name, false);
                        pieces.add(piece);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ConstructionActivity.this));
                        adapter = new Adapter(ConstructionActivity.this, pieces);
                        recyclerView.setAdapter(adapter);

                    }
                });

                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog.show();

            }
        });
    }

}