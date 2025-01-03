package com.beesim.models;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // --- Création d'une ruche ---
        Ruche ruche = new Ruche(5, 5, 0);
        System.out.println("Position de la ruche : (" + ruche.getPositionX() + ", " + ruche.getPositionY() + ")");
        System.out.println("Score initial de la ruche : " + ruche.getScore());

        // --- Ajout de nectar à la ruche ---
        ruche.ajouterNectar(10);
        System.out.println("Score après ajout de nectar : " + ruche.getScore());

        // --- Création de fleurs ---
        List<Fleur> fleurs = new ArrayList<>();
        fleurs.add(new Fleur(1, 1, 15)); // Fleur avec 15 unités de nectar
        fleurs.add(new Fleur(2, 3, 20)); // Fleur avec 20 unités de nectar
        fleurs.add(new Fleur(4, 4, 0));  // Fleur sans nectar (vide)

        // --- Affichage des informations des fleurs ---
        System.out.println("Fleurs créées :");
        for (Fleur fleur : fleurs) {
            System.out.println(fleur);
        }

        // --- Réduction du nectar de la première fleur ---
        Fleur fleur1 = fleurs.get(0);
        System.out.println("\nRéduction du nectar de la première fleur de 5 unités...");
        fleur1.reduireNectar(5);
        System.out.println(fleur1);

        // --- Réduction complète du nectar de la première fleur ---
        System.out.println("\nRéduction complète du nectar de la première fleur...");
        fleur1.reduireNectar(10); // Réduction supérieure au nectar restant
        System.out.println(fleur1);

        // --- Vérification des états des fleurs ---
        System.out.println("\nVérification des fleurs :");
        for (Fleur fleur : fleurs) {
            System.out.println("Fleur en (" + fleur.getX() + ", " + fleur.getY() + ") est vide : " + fleur.estVide());
        }
    }
}

