package com.beesim.Factory;

import com.beesim.models.AbeilleSansModele;
import com.beesim.models.Environnement;
import com.beesim.models.Ruche;

public class AbeilleSansModeleFactory extends AbeilleFactory {
    @Override
    public AbeilleSansModele creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche)
    {
        return new AbeilleSansModele(environnement, x, y, capaciteNectarPriseMax,ruche);
    }
    
}
