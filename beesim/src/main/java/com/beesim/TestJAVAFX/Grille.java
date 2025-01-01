package com.beesim.TestJAVAFX;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grille {
    private int rows;
    private int cols;
    Rectangle[][] cells;

    public Grille(int rows, int cols, GridPane gridPane) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Rectangle[rows][cols];

        // Initialisation des cellules de la grille
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle cell = new Rectangle(40, 40, Color.LIGHTGRAY); // Taille et couleur initiale
                cell.setStroke(Color.BLACK); // Bordure des cellules
                cells[i][j] = cell;
                gridPane.add(cell, j, i); // Ajout à la grille graphique
            }
        }
    }

    // Met à jour la couleur d'une cellule
    public void mettreAJourCellule_Abeille(int x, int y, boolean occupe) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            cells[x][y].setFill(occupe ? Color.YELLOW : Color.LIGHTGRAY);
        }
    }
        public void set_Fleur(int x, int y, boolean occupe) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            cells[x][y].setFill(occupe ? Color.PINK : Color.LIGHTGRAY);
        }
    }
        public void set_Fleur_occupe(int x, int y, boolean occupe) {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            cells[x][y].setFill(occupe ? Color.GREEN : Color.PINK);
        }
    }
}