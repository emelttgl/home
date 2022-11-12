package com.example.home3d;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    protected Activity activity;
    protected ArrayList<Piece> pieces;
    protected int checkedPos=-1;



    public Adapter(Activity activity, ArrayList<Piece> pieces) {
        this.pieces=new ArrayList<>();
        this.activity = activity;
        this.pieces = pieces;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_piece, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Piece piece = pieces.get(position);
        holder.textView1.setText(piece.getNom());


    }

    @Override
    public int getItemCount() {
        return pieces.size();
    }


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView textView1;
    protected LinearLayout linearLayout;
    protected ImageButton supprimerPiece;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        this.textView1 = itemView.findViewById(R.id.textview1);
        this.linearLayout = itemView.findViewById(R.id.constraint);
        this.supprimerPiece=itemView.findViewById(R.id.imageButton);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelection(getAdapterPosition());
                Intent intent=new Intent( activity,ImageActivity.class);
                activity.startActivity(intent);
                }

        });

        supprimerPiece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieces.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());

            }
        });


    }

    @Override
    public void onClick(View v) {
        int position=getAdapterPosition();
        Intent intent=new Intent(activity,ImageActivity.class);
        //activity.startActivity(intent);
    }
}
    public void setSelection(int adapterPosition){
        if(adapterPosition==RecyclerView.NO_POSITION)return;

        notifyItemChanged(checkedPos);
        checkedPos=adapterPosition;
        notifyItemChanged(checkedPos);
    }
}



