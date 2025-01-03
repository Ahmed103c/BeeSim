package com.beesim.models;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Environnement {
    private int rows;
    private int cols;
    Rectangle[][] cells;
    private Fleur[][] cellscontent;
    private List<Fleur> fleurFixes;

    Image fleurImage2 = new Image(getClass().getResource("fleur.png").toExternalForm());
    public Environnement(int rows, int cols, GridPane gridPane) 
    {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Rectangle[rows][cols];
        this.cellscontent = new Fleur[rows+1][cols+1];

        // Initialisation des cellules de la grille :
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle cell = new Rectangle(40, 40, Color.LIGHTGRAY); // Taille et couleur initiale
                cell.setStroke(Color.BLACK);                             // Bordure des cellules
                cells[i][j] = cell;
                cellscontent[i][j]=null;
                gridPane.add(cell, j, i);                                // Ajout à la grille graphique
            }
        }
    }
    public Fleur getCell(int x,int y)
    {
        return cellscontent[x][y];
    }
    public boolean getCellBool(int x,int y)
    {
        boolean resultat =true;
        if (getCell(x,y)==null) {
            resultat=false;
        }
        return resultat;
    }
    /*************************************************************************
     * 
     * 
     * 
     *              Création des Fleurs et dessiner les fleurs 
     * 
     * 
     * 
     * 
     ***********************************************************************/
    public List<Fleur> genererFleurs() {
        List<Fleur> Fleurs = new ArrayList<>();
        Set<String> usedPositions = new HashSet<>(); 
        Random random = new Random();

        // Générer 10 positions uniques
        while (Fleurs.size() < 10) {
            int x = random.nextInt(10) + 1; 
            int y = random.nextInt(10) + 1; 
         
            String position = x + "," + y;

            // Vérifier si cette position est déjà utilisée
            if (!usedPositions.contains(position)) {
                usedPositions.add(position); 
                Fleurs.add(new Fleur(x, y, 10));  
                System.out.println("Fleur ajoutée en : (" + x + ", " + y + ")");
                cellscontent[x][y] = new Fleur(x, y, 10);  
            }
        }
        return Fleurs;
    }
    public void set_Couleur_Fleur_Par_Défault(int x, int y, boolean occupe) 
        {
            if (x >= 0 && x < rows && y >= 0 && y < cols) 
            {
                if (occupe) {
                    // Charger l'image de fleur
                    Image fleurImage = new Image(getClass().getResource("fleur.png").toExternalForm()); // Assurez-vous que l'image est accessible depuis le chemin
                    cells[x][y].setFill(new ImagePattern(fleurImage));                                  // Appliquer l'image comme motif
                } else {
                    cells[x][y].setFill(Color.LIGHTGRAY);                                               // Couleur par défaut si pas de fleur
                }
            }
        }
    public void DessinerFleur()
    {
        fleurFixes =this.genererFleurs();
        for (Fleur fleur : fleurFixes) {
            System.out.println("Fleur ajoutée en : (" + fleur.getX() + ", " + fleur.getY() + ")");
            this.set_Couleur_Fleur_Par_Défault(fleur.getX(), fleur.getY(), true);
        }
    }    
    




    /*************************************************************************
     * 
     * 
     * 
     *              Gestion des Abeilles 
     * 
     * 
     * 
     * 
     ***********************************************************************/
    public void set_Abeille_Sans_Modèle_Par_Défaults(int x,int y,boolean occupe)
    {
        if (x >= 0 && x < rows && y >= 0 && y < cols) 
        {
            if (occupe) {
                // Charger l'image de fleur
                Image abeilleImage = new Image(getClass().getResource("bee.png").toExternalForm()); // Assurez-vous que l'image est accessible depuis le chemin
                cells[x][y].setFill(new ImagePattern(abeilleImage)); // Appliquer l'image comme motif
            } else {
                System.out.println("Erreur dans la position de la fleur");
                cells[x][y].setFill(Color.LIGHTGRAY); // Couleur par défaut si pas de fleur
            }
        }
    }
    public void mettreAJourCellule_Abeille(int x, int y, boolean occupe)
    {
        if (x >= 0 && x < rows && y >= 0 && y < cols) {
            if (occupe) {
                Image abeilleImage = new Image(getClass().getResource("bee.png").toExternalForm()); // Assurez-vous que l'image est accessible depuis le chemin
                cells[x][y].setFill(new ImagePattern(abeilleImage)); // Appliquer l'image comme motif
            }
            else
            {
                if(this.getCellBool(x, y))
                {
                    this.set_Couleur_Fleur_Par_Défault(x, y, true);
                }
                else{

                    cells[x][y].setFill(Color.LIGHTGRAY);
                }
            }
        }
    }

}










