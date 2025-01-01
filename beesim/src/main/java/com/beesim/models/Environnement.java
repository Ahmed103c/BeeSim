package com.beesim.models;
import java.util.ArrayList;
import java.util.List;

public class Environnement {
    private int[][] grille;              // La grille de l'environnement
    private List<Fleur> fleurs;          // Liste des fleurs
    private List<Ruche> ruches;
    public Environnement(int lignes, int colognes) {
        grille = new int[lignes][colognes];
        fleurs = new ArrayList<>();
        ruches = new ArrayList<>();
    }
    public void ajouterFleur(int positionFleurX,int positionFleurY, Fleur fleur) {
        if (positionFleurY>=0 && positionFleurX>=0 && positionFleurX<=grille.length && positionFleurY<=grille[0].length){
            fleurs.add(fleur);
            grille[positionFleurX][positionFleurY]=1;
            //le 1 fait reference pour la fleur pour le moment (apres on va voir sur l'interface graphique
        }
        else{
            System.out.println("Position invalide pour la fleur resseyer");
        }
    }
    public void ajouterRuche(int positionRucheX,int positionRucheY, Ruche ruche) {
        if (positionRucheX>=0 && positionRucheY>=0 && positionRucheX<=grille.length && positionRucheY<=grille[0].length){
            ruches.add(ruche);
            grille[positionRucheX][positionRucheY]=5;
            //le 5 fait reference pour la ruche pour le moment (apres on va voir sur l'interface graphique)
        }
        else{
            System.out.println("Position invalide pour la ruche resseyer");
        }
    }
    public void afficherGrille() {
        for (int[] ints : grille) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}

