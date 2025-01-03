package com.beesim.models;
import com.beesim.State.EtatAbeille;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AbeilleSansModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;
   


    public AbeilleSansModele(Environnement environnement,int x, int y, int score) {
        super(x, y, score);
        this.environnement=environnement;
        environnement.set_Abeille_Sans_Modèle_Par_Défaults(x, y, true);
    }
    @Override
    public void agir() {
        //bah selon les etats
    }
    public void deplacer() {
        Random random = new Random();
        int newX = x + random.nextInt(3) - 1; // Mouvement aléatoire entre -1 et +1
        int newY = y + random.nextInt(3) - 1;

        // Vérifie si la position est dans les limites
        if (newX >= 0 && newX < environnement.cells.length && newY >= 0 && newY < environnement.cells[0].length) {
            environnement.mettreAJourCellule_Abeille(x, y, false); // Vide l'ancienne position
            x = newX;
            y = newY;
            environnement.mettreAJourCellule_Abeille(x, y, true); // Remplit la nouvelle position
        }
    }





}
