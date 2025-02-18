package com.beesim.models;

import com.beesim.Mediateur.*;

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
    private Fleur[][] matriceFleur;
    private List<Fleur> fleurs;
    private List<Ruche> ruches;
    private beeMediator mediateurr;


    public Environnement(int rows, int cols, GridPane gridPane,beeMediator mediateur)
    {
        this.rows = rows;
        this.cols = cols;
        this.cells = new Rectangle[rows][cols];
        this.matriceFleur = new Fleur[rows+1][cols+1];
        this.fleurs=new ArrayList<>();
        this.ruches=new ArrayList<>();
        this.mediateurr=mediateur;
        initialiserRuches();
        // Initialisation des cellules de la grille :
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Rectangle cell = new Rectangle(40, 40, Color.LIGHTGRAY); // Taille et couleur initiale
                cell.setStroke(Color.BLACK);                             // Bordure des cellules
                cells[i][j] = cell;
                matriceFleur[i][j]=null;
                gridPane.add(cell, j, i);                                // Ajout à la grille graphique
            }
        }

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


    public List<Fleur> getFleurs() {
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
                Fleurs.add(new Fleur(x, y, 50));  
                System.out.println("Fleur ajoutée en : (" + x + ", " + y + ")");
                matriceFleur[x][y] = new Fleur(x, y, 10);  
            }
        }
        return Fleurs;
    }
    public Fleur getCell(int x,int y)
    {
        return matriceFleur[x][y];
    }
    public boolean getCellBool(int x,int y)
    {
        boolean resultat =true;
        if (getCell(x,y)==null) {
            resultat=false;
        }
        return resultat;
    }
    public void set_Couleur_Fleur_Par_Défault(int x, int y, boolean occupe) 
        {
            if (x >= 0 && x < rows && y >= 0 && y < cols) 
            {
                if (occupe) {
                    // Charger l'image de fleur
                    Image fleurImage = new Image(getClass().getResource("../images/fleur.png").toExternalForm()); 
                    cells[x][y].setFill(new ImagePattern(fleurImage));                                  // Appliquer l'image comme motif
                } else {
                    cells[x][y].setFill(Color.LIGHTGRAY);                                               // Couleur par défaut si pas de fleur
                }
            }
        }
    public void DessinerFleur()
    {
        fleurs =this.getFleurs();
        for (Fleur fleur : fleurs) {
            System.out.println("Fleur ajoutée en : (" + fleur.getX() + ", " + fleur.getY() + ")");
            this.set_Couleur_Fleur_Par_Défault(fleur.getX(), fleur.getY(), true);
            fleur.setMediateur(mediateurr);
        }
    }   
    public List<Fleur> getFleursFixes(){
        return fleurs;
    }


    /*************************************************************************
     * 
     * 
     * 
     *              Création des Fleurs et Ruches 
     * 
     * 
     * 
     * 
     ***********************************************************************/
    

    public void initialiserRuches() {
        Set<String> positions = new HashSet<>();
        int x = 0, y = 0;
        int x2 = rows-2, y2 = cols-2;
        positions.add(x + "," + y);
        positions.add(x2 + "," + y2);
        ruches.add(new Ruche(x, y,0));
        ruches.add(new Ruche(x2, y2,0)); //il faut voir avec chatGPt une autre fois putain
    }
    public List<Ruche> getRuches()
    {
        initialiserRuches();
        return ruches;
    }
    public void DessinerRuche(GridPane gridPane)
    {
        Rectangle rucheCell = new Rectangle(80, 80);   // Taille de la zone fusionnée
        rucheCell.setStroke(Color.BLACK);              // Bordure pour la cellule fusionnée
        Image rucheImage = new Image(getClass().getResource("../images/rucheSansModele.png").toExternalForm()); 
        rucheCell.setFill(new ImagePattern(rucheImage));

        // Supprimer les anciennes cellules si nécessaire (facultatif, selon votre logique)
        gridPane.getChildren().removeAll(cells[0][0], cells[0][1], cells[1][0], cells[1][1]);

        // Ajouter le rectangle fusionné
        gridPane.add(rucheCell, 0, 0);         // Position (0,0) comme point de départ
        GridPane.setColumnSpan(rucheCell, 2);  // Fusionner 2 colonnes
        GridPane.setRowSpan(rucheCell, 2);     // Fusionner 2 lignes*


        Rectangle rucheCell2 = new Rectangle(80, 80); 
        rucheCell2.setStroke(Color.BLACK);            
        Image rucheImage2 = new Image(getClass().getResource("../images/rucheAvecModele.png").toExternalForm());  
        rucheCell2.setFill(new ImagePattern(rucheImage2));

   
        gridPane.getChildren().removeAll(cells[rows-1][cols-1], cells[rows-2][cols-1], cells[rows-1][cols-2], cells[rows-2][cols-2]);

       
        gridPane.add(rucheCell2, rows-2, cols-2); 
        GridPane.setColumnSpan(rucheCell2, 2); 
        GridPane.setRowSpan(rucheCell2, 2);    
    }
    


    /*************************************************************************
     * 
     * 
     * 
     *              Dessiner  les Abeilles 
     * 
     * 
     * 
     * 
     ***********************************************************************/


    public void set_Abeille_Sans_Modèle_Par_Défaults(int x,int y,boolean occupe)
    {
        if (x >=2 && x < rows-2 && y >=2 && y < cols-2) 
        {
            if (occupe) //si abeille existe sur la cellule
            {
                Image abeilleImage = new Image(getClass().getResource("../images/beeSansModele.png").toExternalForm()); 
                cells[x][y].setFill(new ImagePattern(abeilleImage)); 
            } else {
                System.out.println("Erreur dans la position de la fleur");
                cells[x][y].setFill(Color.LIGHTGRAY); 
            }
        }
    }

    public void set_Abeille_Avec_Modèle_Par_Défaults(int x,int y,boolean occupe)
    {
        if (x >=2 && x < rows-2 && y >=2 && y < cols-2) 
        {
            if (occupe) //si abeille existe sur la cellule
            {
                Image abeilleImage = new Image(getClass().getResource("../images/beeAvecModele.png").toExternalForm()); 
                cells[x][y].setFill(new ImagePattern(abeilleImage)); 
            } else {
                System.out.println("Erreur dans la position de la fleur");
                cells[x][y].setFill(Color.LIGHTGRAY); 
            }
        }
    }

    public void mettreAJourCellule_Abeille(int x, int y, boolean occupe)
    {
        
        if (x >=0 && x < rows && y >=0 && y < cols) {
            if (occupe) 
            {
                Image abeilleImage = new Image(getClass().getResource("../images/beeSansModele.png").toExternalForm()); 
                cells[x][y].setFill(new ImagePattern(abeilleImage)); 
            }
            else // Abeille n'est pas sur la grille 
            {
                if(this.getCellBool(x, y))
                {
                    this.set_Couleur_Fleur_Par_Défault(x, y, true);
                }
                else
                {
                    cells[x][y].setFill(Color.LIGHTGRAY);
                }
            }
        }
        else
        {

        }
    }

    public void mettreAJourCellule_AbeilleAvecModele(int x, int y, boolean occupe)
    {
        
        if (x >=0 && x < rows && y >=0 && y < cols) {
            if (occupe) 
            {
                Image abeilleImage = new Image(getClass().getResource("../images/beeAvecModele.png").toExternalForm()); 
                cells[x][y].setFill(new ImagePattern(abeilleImage)); 
            }
            else // Abeille n'est pas sur la grille 
            {
                if(this.getCellBool(x, y))
                {
                    this.set_Couleur_Fleur_Par_Défault(x, y, true);
                }
                else
                {
                    cells[x][y].setFill(Color.LIGHTGRAY);
                }
            }
        }
        else
        {

        }
    }
























    public boolean isCellPartOfRuche(int x, int y) 
    {
    for (Ruche ruche : ruches) {
        int rucheX = ruche.getPositionX();
        int rucheY = ruche.getPositionY();
        int rucheWidth = 2; // Largeur de la ruche (par exemple 2x2)
        int rucheHeight = 2; // Hauteur de la ruche

        // Vérifie si (x, y) est dans les limites de la ruche
        if (x >= rucheX && x < rucheX + rucheWidth && y >= rucheY && y < rucheY + rucheHeight) {
            return true;
        }
    }
    return false;
    }


}










