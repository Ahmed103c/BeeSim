package com.beesim.State;

import com.beesim.models.Abeille;

public class CollecterNectar extends EtatAbeille{
    @Override
    public void agir(Abeille abeille) {
        System.out.println("L'abeille collecte du nectar...");
        abeille.collecterNectar(); // Réduit le nectar de la fleur, augmente celui de l'abeille
        abeille.setEtat(new RetourRuche()); // Transition vers RetourRuche
    }
}
