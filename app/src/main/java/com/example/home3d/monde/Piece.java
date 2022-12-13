package com.example.home3d.monde;


import com.example.home3d.outils.FabriqueId;

import java.io.Serializable;
import java.util.ArrayList;

public class Piece implements Serializable {

    protected String nom;
    protected Mur mur1, mur2, mur3, mur4;
    protected boolean isSelected;
    protected int id;

    public Piece(String s, boolean isSelected){
        this.nom=s;
        this.isSelected=isSelected;
        id= FabriqueId.getInstance().getId_piece();
    }

    public int getId() {
        return id;
    }

    public Piece() {
        mur1 = null;
        mur2 = null;
        mur3 = null;
        mur4 = null;
        nom= "";
        id= FabriqueId.getInstance().getId_piece();
    }

    public void setMur1(Mur mur1) {
        this.mur1 = mur1;
    }

    public void setMur2(Mur mur2) {
        this.mur2 = mur2;
    }

    public void setMur3(Mur mur3) {
        this.mur3 = mur3;
    }

    public void setMur4(Mur mur4) {
        this.mur4 = mur4;
    }

    public Piece(String nom, Mur mur1, Mur mur2, Mur mur3, Mur mur4) {
        this.nom = nom;
        this.mur1 = mur1;
        this.mur2 = mur2;
        this.mur3 = mur3;
        this.mur4 = mur4;
        id= FabriqueId.getInstance().getId_piece();
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
    public void setPiece(Piece p){
        this.nom = p.nom;
        this.mur1 = p.mur1;
        this.mur2 = p.mur2;
        this.mur3 = p.mur3;
        this.mur4 = p.mur4;
    }

    public Mur getMur1() {
        return mur1;
    }

    public Mur getMur2() {
        return mur2;
    }

    public Mur getMur3() {
        return mur3;
    }

    public Mur getMur4() {
        return mur4;
    }
}