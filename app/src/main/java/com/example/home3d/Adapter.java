package com.example.home3d;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    protected Activity activity;
    protected ArrayList<Piece> pieces;


    public Adapter(Activity activity, ArrayList<Piece> list) {
        pieces=new ArrayList<>();
        this.activity=activity;
        this.pieces=list;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_piece,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        Piece piece=pieces.get(position);
        holder.textView1.setText(piece.getNom());
    }

    @Override
    public int getItemCount() {
        return pieces.size();
    }


}
