package com.beesim.models;

import com.beesim.State.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import java.util.*;
public class AbeilleAvecModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;

   
    public AbeilleAvecModele(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche) {
        super(x, y,capaciteNectarPriseMax,ruche);
        this.environnement=environnement;
        environnement.set_Abeille_Avec_Modèle_Par_Défaults(x, y, true);
        
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

    private boolean isReturning = false;
    private boolean isgoing = false;

    public void deplacer() {
        if (isReturning) {
            return; // Si l'abeille est en train de retourner à la ruche, on ne fait rien
        }

        Random random = new Random();
        int newX = x;
        int newY = y;
        boolean positionValide = false;
        int tentatives = 0;

        if (this.getCapaciteNectarPrise() >= this.getCapaciteNectarPriseMaximale()) {
            System.out.println("On atteint ou dépasse la capacité maximale ");
            this.retournerAuRuche(this.ruche);
            isReturning = true;  // Mettre le flag à true pour bloquer les autres déplacements pendant le retour
            return;
        }

        while (!positionValide && tentatives < 10) {

            newX = x + random.nextInt(5) - 2;   // Mouvement aléatoire entre -1 et +1
            newY = y + random.nextInt(5) - 2;

            if (newX >= 0 && newX < environnement.cells.length 
                && newY >= 0 && newY < environnement.cells[0].length
                && !environnement.isCellPartOfRuche(newX, newY)) {
                positionValide = true;
            }

            tentatives++; 
        }

        if (environnement.getCellBool(x, y)) {
            Fleur fleur = environnement.getCell(x, y);
            fleur.setOccupe(false);
        }

        if (positionValide) {
            environnement.mettreAJourCellule_AbeilleAvecModele(x, y, false);
            x = newX;
            y = newY;

            if (environnement.getCellBool(x, y)) {
                System.out.println("Abeille est sur une fleur ");
                Fleur fleur = environnement.getCell(x, y);
                fleur.setOccupe(true);
                this.collecterNectar(fleur);
            }

            environnement.mettreAJourCellule_AbeilleAvecModele(x, y, true);
        } else {
            System.out.println("Aucune position valide trouvée pour l'abeille après " + tentatives + " tentatives.");
        }
    }
/* 
    public void retournerAuRuche(Ruche ruche) {
        System.out.println("Abeille retourne à la ruche.");
        int rucheX = ruche.getPositionX(); // Coordonnées fixes de la ruche
        int rucheY = ruche.getPositionY();

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie pour l'animation

        KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.millis(500), event -> {
            if (x != rucheX || y != rucheY) {
                int xAncien = x;
                int yAncien = y;

                if (x < rucheX) x++;
                else if (x > rucheX) x--;

                if (y < rucheY) y++;
                else if (y > rucheY) y--;

                environnement.mettreAJourCellule_AbeilleAvecModele(xAncien, yAncien, false);
                environnement.mettreAJourCellule_AbeilleAvecModele(x, y, true);
            } else {
                ruche.ajouterNectar(this.capaciteNectarPrise);
                this.capaciteNectarPrise = 0;
                timeline.stop();
                isReturning = false;  // Réinitialiser le flag pour permettre un nouveau déplacement
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

            if (x < cibleX) x++;
            else if (x > cibleX) x--;

            if (y < cibleY) y++;
            else if (y > cibleY) y--;

            environnement.mettreAJourCellule_AbeilleAvecModele(xAncien, yAncien, false);
            environnement.mettreAJourCellule_AbeilleAvecModele(x, y, true);
        } else {
            timeline.stop();
            isgoing=false;
            if (actionFinale != null) {
                actionFinale.run(); // Exécute une action lorsque la cible est atteinte
            }
        }
    });

    timeline.getKeyFrames().add(keyFrame);
    timeline.play(); // Démarrer l'animation
    }

    public void deplacerVersFleur() {
        if (isgoing) {
            return;
        }
        // if (this.getCapaciteNectarPrise() >= this.getCapaciteNectarPriseMaximale()) {
        //     System.out.println("On atteint ou dépasse la capacité maximale ");
        //     this.retournerAuRuche(this.ruche);
        //     isgoing = true;  // Mettre le flag à true pour bloquer les autres déplacements pendant le retour
        //     return;
        // }
        Random random = new Random();
        List<Fleur> fleurs =environnement.getFleursFixes();
        Fleur fleurCible = fleurs.get(random.nextInt(fleurs.size()));
        deplacerVersCible(fleurCible.getX(), fleurCible.getY(), () -> {
        System.out.println("Abeille a atteint la fleur : (" + fleurCible.getX() + ", " + fleurCible.getY() + ")");
        this.collecterNectar(fleurCible);
           if (this.getCapaciteNectarPrise() >= this.getCapaciteNectarPriseMaximale()) {
            System.out.println("On atteint ou dépasse la capacité maximale ");
            this.retournerAuRuche(this.ruche);
            isgoing = true;  // Mettre le flag à true pour bloquer les autres déplacements pendant le retour
            return;
        }
        //isReturning = true; // Une fois le nectar collecté, l'abeille retourne à la ruche
        //retournerAuRuche(this.ruche);
    });

}
    */

public void retournerAuRuche(Ruche ruche) {
    System.out.println("Abeille retourne à la ruche.");
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
            isReturning = false; // Permettre de redéplacer l'abeille
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







