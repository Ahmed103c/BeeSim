package com.beesim.models;
import com.beesim.State.EtatAbeille;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.synth.SynthEditorPaneUI;

public class AbeilleSansModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;

   
    public AbeilleSansModele(Environnement environnement,int x, int y, int capaciteNectarPriseMax ,Ruche ruche) {
        super(x, y,capaciteNectarPriseMax,ruche);
        this.environnement=environnement;
        environnement.set_Abeille_Sans_Modèle_Par_Défaults(x, y, true);
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
/* 
    public void deplacer() {
        Random random = new Random();
        int newX = x;
        int newY = y;
        boolean positionValide = false;
        int tentatives = 0;                      
        if (this.getCapaciteNectarPrise() >= this.getCapaciteNectarPriseMaximale()) 
        {
            System.out.println("On Atteint ou dépasse capacité maximale ");
            this.retournerAuRuche(this.ruche);
            return;
        }
        else
        {
            System.out.println("On est encore sous capacité maximale ");
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

        //ce code permet de retourner false si l'abeille quitte la fleur
        if (environnement.getCellBool(x, y))
        {
            Fleur fleur = environnement.getCell(x, y);
            fleur.setOccupe(false);
        }

        if (positionValide) {
            environnement.mettreAJourCellule_Abeille(x, y, false);      // Vide l'ancienne position
            x = newX;
            y = newY;

            // Vérifie si la nouvelle position contient une fleur
            if (environnement.getCellBool(x, y)) {
                System.out.println("Abeille est sur une fleur ");
                Fleur fleur = environnement.getCell(x, y);
                fleur.setOccupe(true);
                this.collecterNectar(fleur);
                

            }

            environnement.mettreAJourCellule_Abeille(x, y, true);     // Remplit la nouvelle position
        } else {
            System.out.println("Aucune position valide trouvée pour l'abeille après " + tentatives + " tentatives.");
        }
    }
        */
/*
 * 
 public void retournerAuRuche(Ruche ruche) {
    System.out.println("Abeille retourne à la ruche.");
    int rucheX = 1; // Coordonnées fixes de la ruche
    int rucheY = 1;
    
    
    while (x != rucheX || y != rucheY) {
        
    // Sauvegarder l'ancienne position
    int xAncien = x;
    int yAncien = y;
    
    // Calculer la nouvelle position
    if (x < rucheX) x++;
    else if (x > rucheX) x--;
    
    if (y < rucheY) y++;
    else if (y > rucheY) y--;
    
    // Mettre à jour l'environnement : vider l'ancienne cellule
    environnement.mettreAJourCellule_Abeille(xAncien, yAncien, false);
    
    // Remplir la nouvelle cellule
    environnement.mettreAJourCellule_Abeille(x, y, true);
}

// Une fois arrivé à la ruche : 
ruche.ajouterNectar(this.capaciteNectarPrise);
this.capaciteNectarPrise = 0; // Réinitialiser la capacité de nectar
}


*/
/* 
public void retournerAuRuche(Ruche ruche) {
    System.out.println("Abeille retourne à la ruche.");
    int rucheX = 1; // Coordonnées fixes de la ruche
    int rucheY = 1;

    Timeline timeline = new Timeline();
    timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie pour l'animation

    KeyFrame keyFrame = new KeyFrame(javafx.util.Duration.millis(500), event -> {
        // Déplacement progressif vers la ruche
        if (x != rucheX || y != rucheY) {
            // Sauvegarder l'ancienne position
            int xAncien = x;
            int yAncien = y;

            // Calculer la nouvelle position (un pas à chaque itération)
            if (x < rucheX) x++;
            else if (x > rucheX) x--;

            if (y < rucheY) y++;
            else if (y > rucheY) y--;

            // Mettre à jour l'environnement : vider l'ancienne cellule
            environnement.mettreAJourCellule_Abeille(xAncien, yAncien, false);

            // Remplir la nouvelle cellule
            environnement.mettreAJourCellule_Abeille(x, y, true);
        } else {
            // Une fois arrivé à la ruche : arrêter l'animation
            ruche.ajouterNectar(this.capaciteNectarPrise);
            this.capaciteNectarPrise = 0; // Réinitialiser la capacité de nectar
            timeline.stop(); // Arrêter l'animation
        }
    });

    timeline.getKeyFrames().add(keyFrame);
    timeline.play(); // Démarrer l'animation
}
*/
// Ajouter un flag pour s'assurer que l'abeille ne se déplace pas en même temps que son retour.
private boolean isReturning = false;

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
        environnement.mettreAJourCellule_Abeille(x, y, false);
        x = newX;
        y = newY;

        if (environnement.getCellBool(x, y)) {
            System.out.println("Abeille est sur une fleur ");
            Fleur fleur = environnement.getCell(x, y);
            fleur.setOccupe(true);
            this.collecterNectar(fleur);
        }

        environnement.mettreAJourCellule_Abeille(x, y, true);
    } else {
        System.out.println("Aucune position valide trouvée pour l'abeille après " + tentatives + " tentatives.");
    }
}

public void retournerAuRuche(Ruche ruche) {
    System.out.println("Abeille retourne à la ruche.");
    int rucheX = 1; // Coordonnées fixes de la ruche
    int rucheY = 1;

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

            environnement.mettreAJourCellule_Abeille(xAncien, yAncien, false);
            environnement.mettreAJourCellule_Abeille(x, y, true);
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


}