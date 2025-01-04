package com.beesim.models;

public class AbeilleAvecModeleFactory extends AbeilleFactory{
    @Override
    public AbeilleAvecModel creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche)
    {
        return new AbeilleAvecModel(x, y, capaciteNectarPriseMax,ruche ,environnement);
    }
    
}
