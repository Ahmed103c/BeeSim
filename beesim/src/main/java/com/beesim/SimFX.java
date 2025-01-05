package com.beesim;

import com.beesim.Factory.AbeilleAvecModeleFactory;
import com.beesim.Factory.AbeilleSansModeleFactory;
import com.beesim.Mediateur.*;


import com.beesim.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SimFX extends Application {
    @Override
    public void start(Stage primaryStage) {
      
        
    /************************************************************************
     * 
     * 
     * 
     *                     Creation du button démarrer Simulation 
     *                        
     * 
     * 
     * 
     * 
     ************************************************************************/  

    Label labelSansModele = new Label("BeeSim" );
    labelSansModele.setStyle("-fx-font-size: 36px; -fx-text-fill: red; -fx-font-weight: bold;"); 
    
    Button startButton = new Button("Démarrer la simulation");
    startButton.setOnAction(event -> {
        
        afficherSceneSimulation(primaryStage);
    });

  
    VBox introLayout = new VBox(20);
    introLayout.setStyle("-fx-background-color: lightgray; -fx-padding: 20px; -fx-alignment: center;"); 
    introLayout.setAlignment(Pos.CENTER); 
    introLayout.getChildren().addAll(labelSansModele,startButton);
    Scene introScene = new Scene(introLayout, 450, 450);
    primaryStage.setTitle("Introduction");
    primaryStage.setScene(introScene);
    primaryStage.show();

    }

    private void afficherSceneSimulation(Stage primaryStage)
    {
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
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: lightgray; ");
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(400, 400);
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

        }));  

 
        int dureeSimulationEnSecondes = 30;
        int cycleCount = dureeSimulationEnSecondes * 1000 / 500; // Nombre de cycles
        timeline.setCycleCount(cycleCount);

        timeline.setOnFinished(event -> {
            afficherScores(primaryStage, rucheSansModele.getScore(), rucheAvecModele.getScore());
        });

        timeline.play();

        /************************************************************************
         * 
         * 
         * 
         *                Button Arreter 
         * 
         * 
         * 
         * 
         ************************************************************************/  
             
        Button stopButton = new Button("Arrêter la simulation");
       
        stopButton.setMinWidth(400);
        stopButton.setOnAction(event -> {
            timeline.stop(); 
            afficherScores(primaryStage, rucheSansModele.getScore(), rucheAvecModele.getScore());
        });

        layout.setTop(gridPane);
    
        layout.setCenter(stopButton);

        
        Scene scene = new Scene(layout, 450,450);
        primaryStage.setTitle("Simulation d'abeille 2D");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /************************************************************************
     * 
     * 
     * 
     *                      Cette Fonction affiche dernier Scene 
     *                          Scores dans les ruches 
     * 
     * 
     * 
     * 
     ************************************************************************/  

    private void afficherScores(Stage primaryStage, int scoreSansModele, int scoreAvecModele) {

        VBox vbox = new VBox(30); 
        vbox.setStyle("-fx-background-color: lightgray; -fx-padding: 20px; -fx-alignment: center;"); 
        vbox.setPrefSize(400, 200); 

        
        Label labelSansModele = new Label("Score de la ruche sans modèle : " + scoreSansModele);
        labelSansModele.setStyle("-fx-font-size: 24px; -fx-text-fill: #ff6347; -fx-font-weight: bold;"); 

        Label labelAvecModele = new Label("Score de la ruche avec modèle : " + scoreAvecModele);
        labelAvecModele.setStyle("-fx-font-size: 24px; -fx-text-fill: #4682b4; -fx-font-weight: bold;"); 

      
        vbox.getChildren().addAll(labelSansModele, labelAvecModele);

        
        Scene scoreScene = new Scene(vbox, 450, 450);

        
        scoreScene.setFill(Color.LIGHTYELLOW); 

      
        primaryStage.setTitle("Scores finaux");
        primaryStage.setScene(scoreScene);
    }


    public static void main(String[] args) 
    {
        launch(args); 
    }
}
