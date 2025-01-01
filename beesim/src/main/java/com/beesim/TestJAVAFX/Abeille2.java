package com.beesim.TestJAVAFX;

import java.util.Random;

public class Abeille2 {
    private int x;
    private int y;
    private Grille grille;
    int getX() {
        return x;
    }
    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }
    void setY(int y) {
        this.y = y;
    }

    public Abeille2(Grille grille, int startX, int startY) {
        this.grille = grille;
        this.x = startX;
        this.y = startY;
        grille.mettreAJourCellule_Abeille(x, y, true); // Place l'abeille visuellement
    }

    // Méthode pour déplacer l'abeille
    public void deplacer() {
        Random random = new Random();
        int newX = x + random.nextInt(3) - 1; // Mouvement aléatoire entre -1 et +1
        int newY = y + random.nextInt(3) - 1;

        // Vérifie si la position est dans les limites
        if (newX >= 0 && newX < grille.cells.length && newY >= 0 && newY < grille.cells[0].length) {
            grille.mettreAJourCellule_Abeille(x, y, false); // Vide l'ancienne position
            x = newX;
            y = newY;
            grille.mettreAJourCellule_Abeille(x, y, true); // Remplit la nouvelle position
        }
    }
}
