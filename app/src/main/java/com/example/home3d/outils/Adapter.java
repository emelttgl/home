package com.example.home3d.outils;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.home3d.R;
import com.example.home3d.monde.Piece;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    protected Activity activity;
    protected ArrayList<Piece> pieces;
    protected int checkedPos;

    public Adapter(Activity activity, ArrayList<Piece> pieces) {
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
        checkedPos=-1;
        this.textView1 = itemView.findViewById(R.id.textview1);
        this.linearLayout = itemView.findViewById(R.id.constraint);
        this.supprimerPiece=itemView.findViewById(R.id.imageButton);

        linearLayout.setOnClickListener(v -> {
                    setSelection(getAdapterPosition());
                    Intent intent = new Intent(activity, ImageActivity.class);
                    activity.startActivity(intent);
        });

        supprimerPiece.setOnClickListener(v -> {
            pieces.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        });

    }

    @Override
    public void onClick(View v) {

    }
}
    public void setSelection(int adapterPosition){
        if(adapterPosition==RecyclerView.NO_POSITION)return;
        notifyItemChanged(checkedPos);
        checkedPos=adapterPosition;
        notifyItemChanged(checkedPos);
    }


}



