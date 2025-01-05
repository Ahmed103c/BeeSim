package com.beesim.Factory;

import com.beesim.models.AbeilleAvecModele;
import com.beesim.models.Environnement;
import com.beesim.models.Ruche;

public class AbeilleAvecModeleFactory extends AbeilleFactory{
    @Override
    public AbeilleAvecModele creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche)
    {
        return new AbeilleAvecModele(environnement,x, y, capaciteNectarPriseMax,ruche);
    }
    
}
