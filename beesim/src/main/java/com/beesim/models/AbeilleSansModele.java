package com.beesim.models;
import com.beesim.State.EtatAbeille;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.synth.SynthEditorPaneUI;

public class AbeilleSansModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;

   
    public AbeilleSansModele(Environnement environnement,int x, int y, int capaciteNectarPrise ,Ruche rucha) {
        super(x, y,capaciteNectarPrise,rucha);
        this.environnement=environnement;
        environnement.set_Abeille_Sans_Modèle_Par_Défaults(x, y, true);
    }

    
    public void deplacer() {
    Random random = new Random();
    int newX = x + random.nextInt(3) - 1; // Mouvement aléatoire entre -1 et +1
    int newY = y + random.nextInt(3) - 1;

    // Vérifie si la position est dans les limites
    if (newX >= 0 && newX < environnement.cells.length && newY >= 0 && newY < environnement.cells[0].length)
    {
            environnement.mettreAJourCellule_Abeille(x, y, false); // Vide l'ancienne position
            // Met à jour les coordonnées
            x = newX;
            y = newY;
            // Vérifie si la nouvelle poisition de la abeille trouve une fleur
            if (environnement.getCellBool(x, y)) {
                System.out.println("Abeille est sur une fleur ");
            }
            System.out.println("Abeille déplacée à : (" + newX + ", " + newY + ")");
            System.out.println("Contenu de la cellule : " + environnement.cells[newX][newY].getFill());
            environnement.mettreAJourCellule_Abeille(x, y, true); // Remplit la nouvelle position
        }
    }
}


// utiliser min entre capacite abeille et quantite nectar dans fleur 