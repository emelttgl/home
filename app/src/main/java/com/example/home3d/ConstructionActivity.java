package com.example.home3d;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ConstructionActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected ArrayList<Piece> pieces;
    protected Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

        pieces=new ArrayList<>();

    }


    public void ajouterPiece(View view) {
        String s = Integer.toString(pieces.size());
        Piece piece= new Piece(s);
        pieces.add(piece);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new Adapter(this,pieces);
        recyclerView.setAdapter(adapter);

    }
}