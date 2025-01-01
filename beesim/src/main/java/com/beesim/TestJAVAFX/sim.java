package com.beesim.TestJAVAFX;

import com.beesim.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class sim extends Application {
    @Override
    public void start(Stage primaryStage) {
        int rows = 10;
        int cols = 10;

        // Créer une grille graphique
        GridPane gridPane = new GridPane();
        Grille grille = new Grille(rows, cols, gridPane);

        // Créer une abeille au centre de la grille
        Abeille2 abeille_0 = new Abeille2(grille, rows / 2, cols / 2);
        Abeille2 abeille_1 = new Abeille2(grille, rows / 5, cols / 5);    
        //Creer une fleur
        Fleur fleur =new Fleur(grille,rows / 3,rows / 3,10);

        // Créer une animation pour déplacer l'abeille toutes les 500ms
        
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            abeille_0.deplacer(); // Déplace l'abeille
            abeille_1.deplacer();
            fleur.setOccupee(false, abeille_1, fleur);
            fleur.setOccupee(false, abeille_0, fleur);
        }));
        
        timeline.setCycleCount(Timeline.INDEFINITE); // Boucle infinie
        timeline.play();
        

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