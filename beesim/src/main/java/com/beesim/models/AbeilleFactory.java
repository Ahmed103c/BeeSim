package com.beesim.models;

public class AbeilleFactory {
    public static final String AVEC_MODELE = "AvecModel";
    public static final String SANS_MODELE = "SansModel";
    public Abeille creerAbeille(String type, int x, int y, int capacite) {
        switch (type) {
            case AVEC_MODELE:
                return new AbeilleAvecModele(x, y, capacite);
            case SANS_MODELE:
                return new AbeilleSansModele(x, y, capacite);
            default:
                throw new IllegalArgumentException("Type d'abeille non reconnu : " + type);
        }
    }
    

}
