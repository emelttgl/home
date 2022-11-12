package com.example.home3d;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ConstructionActivity extends AppCompatActivity {

    protected RecyclerView recyclerView;
    protected ArrayList<Piece> pieces;
    protected Adapter adapter;
    protected Button ajouter;


    protected LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction);

        ajouter=findViewById(R.id.ajouter);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);

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
                       Toast.makeText(ConstructionActivity.this, "Nouvelle pièce ajoutée: "+name,Toast.LENGTH_LONG).show();
                        Piece piece= new Piece(name,false);
                        pieces.add(piece);
                        recyclerView.setLayoutManager(new LinearLayoutManager(ConstructionActivity.this));
                        adapter=new Adapter(ConstructionActivity.this,pieces);
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
        pieces=new ArrayList<>();
    }


}