package com.beesim.models;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class simulationAvecModel extends Application {
    @Override
    public void start(Stage primaryStage) {
        /************************
         *
         *
         *
         *                Création de l'environnement Vide
         *
         *
         *
         *
         ************************/
        int rows = 10;
        int cols = 10;
        GridPane gridPane = new GridPane();
        Environnement grille = new Environnement(rows, cols, gridPane);
        /************************
         *
         *
         *
         *                Création des fleurs dans l'environnement
         *
         *
         *
         *
         ************************/
        grille.DessinerFleur();
        System.out.println("salama azbi");
        /************************
         *
         *
         *
         *                Création des ruches dans l'environnement
         *
         *
         *
         *
         ************************/
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
        // Création des abeilles
        AbeilleAvecModel bee1 = new AbeilleAvecModel(2, 1, rucheAvecModele, grille); // Partie supérieure
        AbeilleAvecModel bee2 = new AbeilleAvecModel(2, 2, rucheSansModele, grille); // Partie inférieure

        // Simulation avec Timeline
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            bee1.rechercherNectar(rows, cols, true); // Parcours de la partie supérieure
            bee2.rechercherNectar(rows, cols, false); // Parcours de la partie inférieure
        }));

        timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie pour simuler
        timeline.play();

        // Affichage de la scène
        Scene scene = new Scene(gridPane, 825, 825);
        primaryStage.setTitle("Simulation d'abeille 2D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
