package com.beesim.models;

public class AbeilleAvecModeleFactory extends AbeilleFactory{
    @Override
    public AbeilleAvecModele creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche)
    {
        return new AbeilleAvecModele(environnement,x, y, capaciteNectarPriseMax,ruche);
    }
    
}
