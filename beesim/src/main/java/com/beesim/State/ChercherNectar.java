package com.beesim.State;



import com.beesim.models.Abeille;
import com.beesim.models.AbeilleSansModele;

public class ChercherNectar extends EtatAbeille{
    public ChercherNectar(Abeille abeille)
    {
        String couleurRouge = "\u001B[31m"; // Rouge
        String couleurVert = "\u001B[32m";
        String couleurJaune = "\u001B[33m";


        String resetCouleur = "\u001B[0m"; // Réinitialisation de la couleur

        if (abeille instanceof AbeilleSansModele) {
            
            System.out.println(couleurRouge + "Abeille sans Modele "+couleurJaune +"en train de chercher une fleur"+ resetCouleur);
        }
        else
        {
            System.out.println(couleurVert+"Abeille avec Modele"+couleurJaune+"en train de chercher une fleur"+ resetCouleur);
        }
    }
}
