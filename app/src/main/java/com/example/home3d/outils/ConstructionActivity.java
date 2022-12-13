package com.example.home3d.outils;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.home3d.R;
import com.example.home3d.monde.Piece;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ConstructionActivity extends AppCompatActivity {
    final static public String BATIMENT_KEY="Envoyer";
    final static public String PIECES_KEY="Recuperer";
    final static public String Piece ="Piece";
    protected RecyclerView recyclerView;
    protected ArrayList<Piece> pieces;
    protected Adapter adapter;
    protected Adapter.ViewHolder view;
    protected Button ajouter;
    protected Button sauvegarder;
    protected ActivityResultLauncher<Intent>launcher;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        pieces = new ArrayList<>();
        //batiment = (Batiment) getIntent().getSerializableExtra(PIECES_KEY);
      // pieces = (ArrayList<Piece>) getIntent().getSerializableExtra(Piece);
        //pieces = pieces.getPieces();
        ajouter = findViewById(R.id.ajouter);
        recyclerView = findViewById(R.id.recyclerview);
        sauvegarder = findViewById(R.id.save);
        Piece piece= new Piece();
        piece= (com.example.home3d.monde.Piece) getIntent().getSerializableExtra(BATIMENT_KEY);
        if(piece!=null)
             pieces.set(adapter.pieceselected,piece);
        SharedPreferences sharedPreference = getSharedPreferences("DATA", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreference.getString("studata", null);
        Type type = new TypeToken<ArrayList<Piece>>(){}.getType();
        pieces=gson.fromJson(json,type);

        if(pieces==null){
          pieces=new ArrayList<>();
        }

        buildRecyclerView();

        ajouter.setOnClickListener(v -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(ConstructionActivity.this);
            dialog.setTitle("Nom de la pièce");

            final EditText editText = new EditText(ConstructionActivity.this);
            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            dialog.setView(editText);

            dialog.setPositiveButton("ok", (dialog1, which) -> {
                String name = editText.getText().toString();
                Toast.makeText(ConstructionActivity.this, "Nouvelle pièce ajoutée: " + name, Toast.LENGTH_LONG).show();
               //int nextid=adapter.getItemCount();

               //adapter.setSelection(nextid++);
                Piece piece2 = new Piece(name, null, null, null, null);
                pieces.add(piece2);
                buildRecyclerView();

            });

            dialog.setNegativeButton("cancel", (dialog2, which) -> dialog2.cancel());
            dialog.show();

        });
        sauvegarder.setOnClickListener(v -> {
            //pieces.setPieces(pieces);

            //finish();*/
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();

            for (int i = 0; i < pieces.size(); i++) {
                System.out.println("------------------>>> "+pieces.get(i).getMur1());
                System.out.println("------------------>>> "+pieces.get(i).getMur2());
                System.out.println("------------------>>> "+pieces.get(i).getMur3());
                System.out.println("------------------>>> "+pieces.get(i).getMur4());
            }

            Gson gsonn = new Gson();
           // pieces.add(new Piece());
            String jsonn= gsonn.toJson(pieces);
            editor.putString("studata",jsonn);
            editor.apply();
            Toast.makeText(this, "Pieces enregistrées", Toast.LENGTH_SHORT).show();
            finish();
        });

       /*Intent i = new Intent();
        i.putExtra(BATIMENT_KEY,pieces);
        setResult(Activity.RESULT_OK, i);*/
       /*launcher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result -> {
           Piece piece= new Piece() ;
           piece= (com.example.home3d.monde.Piece) getIntent().getSerializableExtra(BATIMENT_KEY);
        });*/
        }


    public void buildRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(ConstructionActivity.this));
        adapter = new Adapter(ConstructionActivity.this, pieces);
        recyclerView.setAdapter(adapter);
    }

}