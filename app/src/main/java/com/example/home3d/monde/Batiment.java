package com.example.home3d.monde;


import java.io.Serializable;
import java.util.ArrayList;

public class Batiment implements Serializable {

    protected ArrayList<Piece>pieces;

    public Batiment() {
        pieces = new ArrayList<>();
    }


    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }
}