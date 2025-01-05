package com.beesim.Factory;

import com.beesim.models.Abeille;
import com.beesim.models.Environnement;
import com.beesim.models.Ruche;

abstract class AbeilleFactory {

    protected abstract Abeille creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche);
    // public Abeille creerAbeille(TypeAbeille type, Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche) {
    //     switch (type) {
    //         case AVEC_MODELE:
    //             return new AbeilleAvecModel(x, y, capaciteNectarPriseMax,ruche ,environnement);
    //         case SANS_MODELE:
    //             return new AbeilleSansModele(environnement,x, y,capaciteNectarPriseMax,ruche) ;
    //         default:
    //             throw new IllegalArgumentException("Type d'abeille non reconnu : " + type);
    //     }
    // }
}