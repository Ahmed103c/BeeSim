package com.beesim.State;
import com.beesim.models.*;

public class ChercherNectar implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("Abeille en mode ChercherNectar.");
        Fleur cible = abeille.choisirFleur();

        if (cible != null) {
            abeille.seDeplacerVers(cible.getX(), cible.getY());
            abeille.setEtat(new RetourRuche()); // Passe à l'état suivant
        } else {
            System.out.println("Aucune fleur trouvée !");
        }
    }
}

