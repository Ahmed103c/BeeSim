package com.beesim.TestJAVAFX;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Fleur {
    private int x;
    private int y;
    private Grille grille;
    private int nectar; // Quantité de nectar (entre 0 et 100)
    private boolean estOccupee; // Indique si une abeille est sur la fleur

    public Fleur(Grille grille, int x, int y,int nectar) {
        this.grille = grille;
        this.x = x;
        this.y = y;
        this.nectar = 100; // Par défaut, nectar est à 100
        this.estOccupee = false;
        grille.set_Fleur(x, y, true); // Mettre à jour la cellule de la grille();
    }


    // Méthode pour définir si une abeille est sur la fleur
    public void setOccupee(boolean occupee, Abeille2 abeille2, Fleur fleur) {
        if(abeille2.getX() == x && abeille2.getY() == y) {
            this.estOccupee = true;
        }
        else
        {
             this.estOccupee = false;
        }
        grille.set_Fleur_occupe(x, y, estOccupee);
    }

    // Getter pour vérifier la quantité de nectar
    public int getNectar() {
        return nectar;
    }
}
