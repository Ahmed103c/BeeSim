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

        int rows = 20;
        int cols = 20;        
        GridPane gridPane = new GridPane();
        Environnement grille = new Environnement(rows, cols, gridPane);

        /************************************************************************
         * 
         * 
         * 
         *                Création les fleurs dans l'environnement 
         * 
         * 
         * 
         * 
         ************************************************************************/
        // Utiliser un Set pour garantir que les nombres sont uniques
        Set<Integer> liste_X_Fleur_Set = new HashSet<>();
        Set<Integer> liste_Y_Fleur_Set = new HashSet<>();
        Random random = new Random();

        // Générer des nombres aléatoires jusqu'à en avoir 10 différents
        while (liste_X_Fleur_Set.size() < 10) {
            int num = random.nextInt(19) + 1;
            liste_X_Fleur_Set.add(num);
        }
        while (liste_Y_Fleur_Set.size() < 10) {
            int num = random.nextInt(19) + 1; 
            liste_Y_Fleur_Set.add(num);
        }

        List<Integer> liste_X_Fleur =new ArrayList<>(liste_X_Fleur_Set);
        List<Integer> liste_Y_Fleur =new ArrayList<>(liste_Y_Fleur_Set);
        List<Fleur> Fleurs = new ArrayList<>();
        for (int index = 0; index < liste_X_Fleur.size(); index++) {
            Fleur fleur = new Fleur(grille, liste_X_Fleur.get(index), liste_Y_Fleur.get(index),10);
            Fleurs.add(fleur);
        }

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
        


        // Afficher la scène
        Scene scene = new Scene(gridPane, 825, 825);
        primaryStage.setTitle("Simulation d'abeille 2D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Lance l'application JavaFX
    }
}