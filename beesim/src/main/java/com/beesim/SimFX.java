package com.beesim;

import com.beesim.Factory.AbeilleAvecModeleFactory;
import com.beesim.Factory.AbeilleSansModeleFactory;
import com.beesim.Mediateur.*;


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
        beeMediator mediateur = new beeMediator();
        Environnement grille = new Environnement(rows, cols, gridPane,mediateur);
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
        for (Fleur fleur : grille.getFleurs()) {
            fleur.setMediateur(mediateur); // Associe chaque fleur au médiateur
        }
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
        AbeilleSansModele abeillesansmodele_1 =abeilleSansModelFactory.creerAbeille(grille,2,1,20,rucheSansModele);
        AbeilleSansModele abeillesansmodele_2 =abeilleSansModelFactory.creerAbeille(grille,3,1,20,rucheSansModele);
        AbeilleSansModele abeillesansmodele_3 =abeilleSansModelFactory.creerAbeille(grille,4,1,20,rucheSansModele);



        AbeilleAvecModeleFactory abeilleAvecModelFactory = new AbeilleAvecModeleFactory();
        AbeilleAvecModele abeilleavecmodele_1 =abeilleAvecModelFactory.creerAbeille(grille,15,15,20,rucheAvecModele);
        AbeilleAvecModele abeilleavecmodele_2 =abeilleAvecModelFactory.creerAbeille(grille,15,15,20,rucheAvecModele);
        AbeilleAvecModele abeilleavecmodele_3 =abeilleAvecModelFactory.creerAbeille(grille,15,15,20,rucheAvecModele);
   
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            
            abeillesansmodele_1.deplacer();
            abeillesansmodele_2.deplacer();
            abeillesansmodele_3.deplacer();
            abeilleavecmodele_1.deplacerVersFleur();
            abeilleavecmodele_2.deplacerVersFleur();
            abeilleavecmodele_3.deplacerVersFleur();

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
