package com.beesim.State;

import com.beesim.models.*;

public class CollecterNectar extends EtatAbeille{
    public CollecterNectar(Abeille abeille)
    {
        String couleurRouge = "\u001B[31m"; // Rouge
        String couleurBleu = "\u001B[34m"; // Bleu
        String resetCouleur = "\u001B[0m"; // Réinitialisation de la couleur
        String couleurVert = "\u001B[32m";
        String couleurJaune = "\u001B[33m";
        String couleurMagenta = "\u001B[35m";
        String  couleurCyan = "\u001B[36m";
        if (abeille instanceof AbeilleSansModele) {
            
            System.out.println(couleurRouge +"Abeille Sans Modele"+couleurBleu+" est sur une fleur en train de collecter du nectar"+ resetCouleur);
        }
        else
        {
            System.out.println(couleurVert+"Abeille Avec Modele"+ couleurBleu +" sur une fleur en train de collecter du nectar"+ resetCouleur);
        }
    }
}
