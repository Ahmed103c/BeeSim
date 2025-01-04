package com.beesim;



import com.beesim.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimFX extends Application {
    @Override
    public void start(Stage primaryStage) {
        /************************************************************************
         * 
         * 
         * 
         *                Création de l'environnement Vide  
         * 
         * 
         * 
         * 
         ************************************************************************/
        int rows = 10;
        int cols = 10;        
        GridPane gridPane = new GridPane();
        Environnement grille = new Environnement(rows, cols, gridPane);
        /************************************************************************
         * 
         * 
         * 
         *                Création des fleurs dans l'environnement 
         * 
         * 
         * 
         * 
         ************************************************************************/
        grille.DessinerFleur();
        /************************************************************************
         * 
         * 
         * 
         *                Création des ruches dans l'environnement 
         * 
         * 
         * 
         * 
         ************************************************************************/
        Ruche rucheSansModele=grille.getRuches().get(0);
        Ruche rucheAvecModele=grille.getRuches().get(1);
        grille.DessinerRuche(gridPane);
        /************************************************************************
         * 
         * 
         * 
         *                Gestion des abeilles ; 
         * 
         * 
         * 
         * 
         ************************************************************************/  
        
        
        AbeilleSansModeleFactory abeilleSansModelFactory = new AbeilleSansModeleFactory();
        AbeilleSansModele abeilleTest =abeilleSansModelFactory.creerAbeille(grille,2,1,20,rucheSansModele);



        AbeilleAvecModeleFactory abeilleAvecModelFactory = new AbeilleAvecModeleFactory();
        AbeilleAvecModele abeilleTest2 =abeilleAvecModelFactory.creerAbeille(grille,15,15,20,rucheAvecModele);
        

   
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            
            abeilleTest.deplacer();
            abeilleTest2.deplacerVersFleur();
            //System.out.println("Quantité Nectar dans Ruche : "+rucheAvecModele.getScore());
            //System.out.println("Quantité Nectar dans Ruche : "+rucheSansModele.getScore());

        }));  
        timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie
        timeline.play();
        
        // Afficher la scène
        Scene scene = new Scene(gridPane, 825, 825);
        primaryStage.setTitle("Simulation d'abeille 2D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) 
    {
        launch(args); // Lance l'application JavaFX
    }
}