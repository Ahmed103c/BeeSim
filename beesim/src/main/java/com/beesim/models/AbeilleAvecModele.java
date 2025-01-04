package com.beesim.models;

import com.beesim.State.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.*;
public class AbeilleAvecModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;
    private boolean isgoing = false;
   
    public AbeilleAvecModele(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche) {
        super(x, y,capaciteNectarPriseMax,ruche);
        this.environnement=environnement;
        //environnement.set_Abeille_Avec_Modèle_Par_Défaults(x, y, true);
        
    }
    //Ce constructeur sera utile pour les tests
    public AbeilleAvecModele(int capaciteNectarPriseMaximale )
    {
         this(null,1, 1, capaciteNectarPriseMaximale, null);
    }

    public void collecterNectar(Fleur fleur) {
        int nectarCollecte = Math.min(fleur.getNectar(), this.getCapaciteNectarPriseMaximale() - this.capaciteNectarPrise);

        if (nectarCollecte > 0) // Ne collecter que si la capacité permet
        { 
            this.capaciteNectarPrise += nectarCollecte; 
            fleur.reduireNectar(nectarCollecte);

            System.out.println("Nectar collecté : " + nectarCollecte);
            System.out.println("Capacité actuelle : " + this.capaciteNectarPrise + "/" + this.getCapaciteNectarPriseMaximale());
        } else {
            System.out.println("Abeille pleine ! Aucun nectar supplémentaire collecté.");
        }
    }

    public void retournerAuRuche(Ruche ruche) {
    System.out.println("Abeille retourne à la ruche.");
    setEtat(new RetourRuche());
    int rucheX = ruche.getPositionX(); // Coordonnées fixes de la ruche
    int rucheY = ruche.getPositionY();

    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie pour l'animation

    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.millis(500), event -> {
        if (x != rucheX || y != rucheY) {
            int xAncien = x;
            int yAncien = y;

            // Calcul du déplacement vers la ruche
            if (x < rucheX) x++;
            else if (x > rucheX) x--;

            if (y < rucheY) y++;
            else if (y > rucheY) y--;

            // Mise à jour de l'environnement
            environnement.mettreAJourCellule_AbeilleAvecModele(xAncien, yAncien, false);
            environnement.mettreAJourCellule_AbeilleAvecModele(x, y, true);
        } else {
            // Actions une fois arrivé à la ruche
            ruche.ajouterNectar(this.capaciteNectarPrise);
            this.capaciteNectarPrise = 0; // Réinitialise le nectar transporté
            timeline.stop();
            //isReturning = false; // Permettre de redéplacer l'abeille
            System.out.println("Abeille a atteint la ruche et déposé le nectar.");
            isgoing = false; // Autoriser l'abeille à repartir
            deplacerVersFleur(); // Relancer la recherche de fleurs
        }
    });

    timeline.getKeyFrames().add(keyFrame);
    timeline.play(); // Démarrer l'animation
}

    public void deplacerVersCible(int cibleX, int cibleY, Runnable actionFinale) {
    System.out.println("Abeille se déplace vers la cible : (" + cibleX + ", " + cibleY + ")");

    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie pour l'animation

    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.millis(500), event -> {
        if (x != cibleX || y != cibleY) {
            int xAncien = x;
            int yAncien = y;

            // Calcul du déplacement vers la cible
            if (x < cibleX) x++;
            else if (x > cibleX) x--;

            if (y < cibleY) y++;
            else if (y > cibleY) y--;

            // Mise à jour de l'environnement
            environnement.mettreAJourCellule_AbeilleAvecModele(xAncien, yAncien, false);
            environnement.mettreAJourCellule_AbeilleAvecModele(x, y, true);
        } else {
            timeline.stop();
            isgoing = false; // Permettre un nouveau déplacement
            if (actionFinale != null) {
                actionFinale.run(); // Exécute une action lorsqu’on atteint la cible
            }
        }
    });

    timeline.getKeyFrames().add(keyFrame);
    timeline.play(); // Démarrer l'animation
}

    public void deplacerVersFleur() {
    if (isgoing) {
        return; // Empêche le déplacement si une animation est déjà en cours
    }
    setEtat(new ChercherNectar());
    Random random = new Random();
    List<Fleur> fleurs = environnement.getFleursFixes();

    if (fleurs.isEmpty()) {
        System.out.println("Aucune fleur disponible.");
        return;
    }

    Fleur fleurCible = fleurs.get(random.nextInt(fleurs.size()));
    isgoing = true; // Bloque d'autres déplacements pendant cette animation

    deplacerVersCible(fleurCible.getX(), fleurCible.getY(), () -> {
        System.out.println("Abeille a atteint la fleur : (" + fleurCible.getX() + ", " + fleurCible.getY() + ")");
        setEtat(new CollecterNectar());
        this.collecterNectar(fleurCible);

        // Vérifie si l'abeille doit retourner à la ruche
        if (this.getCapaciteNectarPrise() >= this.getCapaciteNectarPriseMaximale()) {
            System.out.println("Capacité maximale atteinte, retour à la ruche.");
            retournerAuRuche(this.ruche);
            isgoing = true; // Bloque les déplacements pendant le retour
        } else {
            isgoing = false; // Permettre un autre déplacement si la ruche n’est pas nécessaire
        }
    });
}

} 







