package com.beesim.models;

import java.util.*;

public class BeeEnvironement {
    private int lignes ;
    private int colonnes ;
    private int [][] grille;
    private List<Fleur> fleurs;
    private List<Ruche> ruches;
    int nombreDeFleurs;

    public BeeEnvironement(int lignes, int colonnes, int nbrFleur,AbeilleFactory factory) {
        this.lignes = lignes;
        this.colonnes = colonnes;
        this.grille = new int [lignes][colonnes];
        this.fleurs = new ArrayList<>();
        this.ruches = new ArrayList<>();
        initialiserRuches(factory);
    }

    private void initialiserRuches(AbeilleFactory factory) {
        Set<String> positions = new HashSet<>();
        int x = 0, y = 0;
        int x2=lignes, y2=colonnes;
        positions.add(x+","+y);
        positions.add(x2+","+y2);
        ruches.add(new Ruche(x,y,factory));
        ruches.add(new Ruche(x2,y2,factory)); //il faut voir avec chatGPt une autre fois putain
    }
}
public void genererFleurs(int nombre) {
    Random random = new Random();
    Set<String> positions = new HashSet<>();

    // Ajouter les fleurs avec des positions uniques
    while (fleurs.size() < nombre) {
        int x = random.nextInt(taille);
        int y = random.nextInt(taille);

        if (!positions.contains(x + "," + y)) {
            positions.add(x + "," + y);
            fleurs.add(new Fleur(x, y, random.nextInt(100) + 1)); // Nectar entre 1 et 100
        }
    }
}

// Getter pour les fleurs
public List<Fleur> getFleurs() {
    return fleurs;
}

// Getter pour la taille de la grille
public int getTaille() {
    return taille;
}
