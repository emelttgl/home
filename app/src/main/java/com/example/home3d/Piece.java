package com.example.home3d;


public class Piece{

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