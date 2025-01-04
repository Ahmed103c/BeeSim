package com.beesim.Mediateur;
import com.beesim.models.*;

import java.util.*;

public class beeMediator implements mediator {
    private List<Abeille> abeilles; // Liste des abeilles abonnées au médiateur

    public beeMediator() {
        abeilles = new ArrayList<>();
    }

    // Méthode pour enregistrer une abeille
    public void enregistrerAbeille(Abeille abeille) {
        abeilles.add(abeille);
    }

    // Méthode pour notifier toutes les abeilles
    public void notifierAbeilles(Fleur fleur) {
        System.out.println("Médiateur : Fleur " + fleur.getX() + ", " + fleur.getY() + " est vide de nectar !");
        for (Abeille abeille : abeilles) {
            abeille.recevoirNotification(fleur);
        }
    }
}
