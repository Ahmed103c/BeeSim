package com.beesim.models;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Environnement {
    private int rows;
    private int cols;
    Rectangle[][] cells;

    public Environnement(int rows, int cols, GridPane gridPane) {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Rectangle[rows][cols];

        // Initialisation des cellules de la grille :
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle cell = new Rectangle(40, 40, Color.LIGHTGRAY); // Taille et couleur initiale
                cell.setStroke(Color.BLACK);                             // Bordure des cellules
                cells[i][j] = cell;
                gridPane.add(cell, j, i);                                // Ajout à la grille graphique
            }
        }
    }
    // Couleur par Défaut des fleurs :
    // public void set_Couleur_Fleur_Par_Défault(int x, int y, boolean occupe) {
    //     if (x >= 0 && x < rows && y >= 0 && y < cols) {
    //         cells[x][y].setFill(occupe ? Color.RED : Color.LIGHTGRAY);
    //     }
    // }

    public void set_Couleur_Fleur_Par_Défault(int x, int y, boolean occupe) {
    if (x >= 0 && x < rows && y >= 0 && y < cols) {
        if (occupe) {
            // Charger l'image de fleur
            Image fleurImage = new Image(getClass().getResource("fleur.png").toExternalForm()); // Assurez-vous que l'image est accessible depuis le chemin
            cells[x][y].setFill(new ImagePattern(fleurImage)); // Appliquer l'image comme motif
        } else {
            cells[x][y].setFill(Color.LIGHTGRAY); // Couleur par défaut si pas de fleur
        }
    }
}







}

