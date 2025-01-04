package com.beesim.models;

public class AbeilleSansModeleFactory extends AbeilleFactory {
    @Override
    public AbeilleSansModele creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche)
    {
        return new AbeilleSansModele(environnement, x, y, capaciteNectarPriseMax,ruche);
    }
    
}
