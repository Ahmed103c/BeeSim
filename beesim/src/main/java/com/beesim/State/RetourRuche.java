package com.beesim.State;

import com.beesim.models.*;

public class RetourRuche extends EtatAbeille{
    public RetourRuche(Abeille abeille) {
        // Codes ANSI pour les couleurs
        String couleurRouge = "\u001B[31m"; // Rouge
        String couleurBleu = "\u001B[34m"; // Bleu
        String resetCouleur = "\u001B[0m"; // Réinitialisation de la couleur
        String couleurVert = "\u001B[32m";
        String couleurJaune = "\u001B[33m";
        String couleurMagenta = "\u001B[35m";
        String  couleurCyan = "\u001B[36m";
        if (abeille instanceof AbeilleSansModele) {
            // Afficher en rouge
            System.out.println(couleurRouge + "Abeille sans modèle"+couleurMagenta+" en train de retourner à la ruche" + resetCouleur);
        } else {
            // Afficher en bleu
            System.out.println(couleurVert + "Abeille avec modèle"+couleurMagenta +" en train de retourner à la ruche" + resetCouleur);
        }
    }
}
