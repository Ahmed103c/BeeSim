package com.beesim.State;
import com.beesim.models.*;

public class CollecterNectar implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("Abeille en mode CollecterNectar.");
        Fleur cible = abeille.choisirFleurPourCollecte();

        if (cible != null && !cible.estVide()) {
            abeille.seDeplacerVers(cible.getX(), cible.getY());
            if (abeille instanceof AbeilleAvecModel){
                ((AbeilleAvecModel) abeille).collecterNectar();
            }

            else{
                int nectarCollecte = Math.min(cible.getNectar(), abeille.getNectarTransporté());
                cible.reduireNectar(nectarCollecte);
                abeille.ajouterNectarTransporté(nectarCollecte);

                abeille.setEtatActuel(new RetourRuche());
            }
            // Si l'énergie est basse, passe à l'état Repos
            if (abeille.getEnergie() <= 0) {
                abeille.setEtatActuel(new Repos());
            } else {
                abeille.setEtatActuel(new CollecterNectar()); // Retourne à la ruche
            }
        } else {
            System.out.println("Fleur vide ou introuvable !");
            abeille.setEtatActuel(new RetourRuche());
        }
    }
}


