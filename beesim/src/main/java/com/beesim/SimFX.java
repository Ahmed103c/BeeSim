package com.beesim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
        
        AbeilleSansModele AbeilleSansModele_1 =new AbeilleSansModele(grille,2,1,25,rucheSansModele);
        AbeilleSansModele AbeilleSansModele_2 =new AbeilleSansModele(grille,2,2,25,rucheSansModele);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            AbeilleSansModele_1.deplacer(); 
            AbeilleSansModele_2.deplacer();
            System.out.println("Nectar sur l'abeille 0 : "+AbeilleSansModele_1.getNectarTransporté());
            System.out.println("Nectar sur l'abeille 1 : "+AbeilleSansModele_2.getNectarTransporté());
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