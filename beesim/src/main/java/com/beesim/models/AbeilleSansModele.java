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

public void collecterNectar(Fleur fleur)
{
        int nectarCollecte = Math.min(fleur.getNectar(), this.getCapaciteNectarPrise());
        fleur.reduireNectar(nectarCollecte);
        this.ajouterNectarTransporté(nectarCollecte);
}

public void deplacer() {
    Random random = new Random();
    int newX = x;
    int newY = y;
    boolean positionValide = false;
    int tentatives = 0;                    // Compteur pour éviter une boucle infinie et ne pas avoir une grande Complexité 

    while (!positionValide && tentatives < 10) {
        newX = x + random.nextInt(5) - 2; // Mouvement aléatoire entre -1 et +1
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
            // System.out.println("Abeille est sur une fleur ");
            Fleur fleur = environnement.getCell(x, y);
            fleur.setOccupe(true);
            this.collecterNectar(fleur);
        }

        // System.out.println("Abeille déplacée à : (" + x + ", " + y + ")");
        environnement.mettreAJourCellule_Abeille(x, y, true);     // Remplit la nouvelle position
    } else {
        System.out.println("Aucune position valide trouvée pour l'abeille après " + tentatives + " tentatives.");
    }
}


}