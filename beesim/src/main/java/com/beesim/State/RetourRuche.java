package com.beesim.State;
import com.beesim.models.*;

public class RetourRuche implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("Abeille retourne à sa ruche.");
        Ruche ruche = abeille.getRuche();
        abeille.seDeplacerVers(ruche.getPositionX(), ruche.getPositionY());
        // Mise à jour du score ou du nectar stocké dans la ruche
        ruche.ajouterNectar(abeille.getNectarTransporté());
        abeille.viderNectarTransporté();

        abeille.setEtatActuel(new CollecterNectar()); // Passe à l'état suivant
    }
}

