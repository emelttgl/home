package com.example.home3d.monde;


import com.example.home3d.monde.Mur;

import java.io.Serializable;

public class Piece implements Serializable {

    protected String nom;
    protected Mur mur1, mur2, mur3, mur4;
    protected boolean isSelected;

    public Piece(String s, boolean isSelected){
        this.nom=s;
        this.isSelected=isSelected;
    }

    public String getNom (){
        return nom;
    }

    public void setNom(String nom){this.nom=nom;}

    public boolean isSelected(){ return isSelected();}

    public void setSelected(boolean selected) { isSelected=selected;}

    public String toString(){
        return "Piece{"+"nom='"+nom+'\''+", isSelected="+isSelected+'}';
    }


}