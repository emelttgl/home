package com.example.home3d.outils;

public class FabriqueId {
    private static FabriqueId instance = new FabriqueId();
    private int id_piece;

    public static FabriqueId getInstance() {
        return instance;
    }

    public FabriqueId() {
        id_piece = 0;
    }

    public int getId_piece(){
        int res = id_piece;
        id_piece++;
        return res;
    }
}
