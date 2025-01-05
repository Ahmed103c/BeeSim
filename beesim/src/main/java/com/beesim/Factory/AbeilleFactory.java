package com.beesim.Factory;

import com.beesim.models.Abeille;
import com.beesim.models.Environnement;
import com.beesim.models.Ruche;

abstract class AbeilleFactory {

    protected abstract Abeille creerAbeille(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche);

}