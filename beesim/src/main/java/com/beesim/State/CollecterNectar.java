package com.beesim.State;
import com.beesim.models.*;

public class CollecterNectar implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("Abeille en mode CollecterNectar.");
        Fleur cible = abeille.choisirFleurPourCollecte();

        if (cible != null && !cible.estVide()) {
            abeille.seDeplacerVers(cible.getX(), cible.getY());

            // Collecter le nectar
            int nectarCollecte = Math.min(cible.getNectar(), abeille.getCapaciteNectar());
            cible.consommerNectar(nectarCollecte);
            abeille.ajouterNectarTransporté(nectarCollecte);

            // Si l'énergie est basse, passe à l'état Repos
            if (abeille.getEnergie() <= 0) {
                abeille.setEtat(new Repos());
            } else {
                abeille.setEtat(new RetourRuche()); // Retourne à la ruche
            }
        } else {
            System.out.println("Fleur vide ou introuvable !");
            abeille.setEtat(new Repos());
        }
    }
}


