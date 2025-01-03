package com.beesim.State;

import com.beesim.models.Abeille;

public class ChercherNectar extends EtatAbeille{
    @Override
    public void agir(Abeille abeille) {
        System.out.println("L'abeille cherche du nectar...");
        // Si l'abeille trouve une fleur, passe à l'état CollecterNectar
        if (abeille.estSurFleur()) {
            abeille.setEtat(new CollecterNectar());
        }
    }
}
