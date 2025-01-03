package com.beesim.models;

public class AbeilleFactory {
    public enum TypeAbeille {
        AVEC_MODELE,
        SANS_MODELE
    }
    public Abeille creerAbeille(TypeAbeille type, int x, int y, int capacite, Ruche ruche, Environnement environnement) {
        switch (type) {
            case AVEC_MODELE:
                return new AbeilleAvecModele(x, y, capacite, ruche, environnement);
            case SANS_MODELE:
                return new AbeilleSansModele(x, y, capacite, ruche);
            default:
                throw new IllegalArgumentException("Type d'abeille non reconnu : " + type);
        }
    }
}
