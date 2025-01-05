package com.beesim.State;

import com.beesim.models.*;

public class RetourRuche extends EtatAbeille{
    public RetourRuche(Abeille abeille) {
        // Codes ANSI pour les couleurs
        String couleurRouge = "\u001B[31m"; // Rouge
        String resetCouleur = "\u001B[0m"; // Réinitialisation de la couleur
        String couleurVert = "\u001B[32m";
        String couleurMagenta = "\u001B[35m";

        if (abeille instanceof AbeilleSansModele) {
            // Afficher en rouge
            System.out.println(couleurRouge + "Abeille sans modèle"+couleurMagenta+" en train de retourner à la ruche" + resetCouleur);
        } else {
            // Afficher en bleu
            System.out.println(couleurVert + "Abeille avec modèle"+couleurMagenta +" en train de retourner à la ruche" + resetCouleur);
        }
    }
}
