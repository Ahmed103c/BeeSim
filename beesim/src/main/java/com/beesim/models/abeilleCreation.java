package com.beesim.models;

public class abeilleCreation {
    public static final String AVEC_MODELE = "AvecModel";
    public static final String SANS_MODELE = "SansModel";
    public Abeille creerAbeille(String type, int x, int y, int score) {
        switch (type) {
            case AVEC_MODELE:
                return new AbeilleAvecModel(x, y, score);
            case SANS_MODELE:
                return new AbeilleSansModel(x, y, score);
            default:
                throw new IllegalArgumentException("Type d'abeille non reconnu : " + type);
        }
    }
    

}
