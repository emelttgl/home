package com.example.home3d;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView textView1;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            textView1=itemView.findViewById(R.id.textview1);

    }
}
