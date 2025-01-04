package com.beesim.models;

import com.beesim.State.*;
import java.util.*;

public class AbeilleAvecModel extends Abeille {
    private static Map<String, Fleur> memoireCollective = new HashMap<>(); // Mémoire partagée entre abeilles avec modèle
    private Map<Fleur, Integer> modeleInterne; // Mémoire individuelle de l'abeille
    private int id;
    public AbeilleAvecModel(int x, int y, Ruche ruche, Environnement environnement,int id) {
        super(x, y, ruche, environnement);
        this.modeleInterne = new HashMap<>();
        this.id = id;

    }
    public AbeilleAvecModel(int x, int y, Ruche ruche, Environnement environnement) {
        super(x, y, ruche, environnement);
        this.modeleInterne = new HashMap<>();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Implémentation de la méthode abstraite `choisirFleur`.
     * Cette méthode est utilisée pour choisir une fleur lors de la recherche de nectar.
     */
    @Override
    public Fleur choisirFleur() {
        List<Fleur> fleursTriees = obtenirFleursTrieesParNectar();
        if (fleursTriees.isEmpty()) {
            System.out.println("Aucune fleur disponible pour choisir.");
            return null;
        }
        // Retourne la fleur avec le maximum de nectar (priorité max)
        return fleursTriees.get(0);
    }

    /**
     * Implémentation de la méthode abstraite `choisirFleurPourCollecte`.
     * Cette méthode est utilisée pour choisir une fleur pour la collecte (par exemple, la 2ème meilleure).
     */
    @Override
    public Fleur choisirFleurPourCollecte() {
        List<Fleur> fleursTriees = obtenirFleursTrieesParNectar();
        if (fleursTriees.size() < 2) {
            System.out.println("Moins de deux fleurs disponibles pour choisir.");
            return fleursTriees.isEmpty() ? null : fleursTriees.get(0);
        }
        // Retourne la deuxième fleur avec le maximum de nectar (priorité secondaire)
        return fleursTriees.get(1);
    }
    @Override
    public void interagirAvecFleur(Fleur fleur){
        ajouterFleurMemoireCollective(fleur);
    }
    /**
     * Ajoute une fleur à la mémoire collective.
     */
    public static void ajouterFleurMemoireCollective(Fleur fleur) {
        if (fleur == null || fleur.getNectar() <= 0) return;
        String cle = fleur.getX() + "," + fleur.getY();
        memoireCollective.put(cle, fleur);
        System.out.println("Fleur ajoutée à la mémoire collective : " + cle + " avec nectar " + fleur.getNectar());
    }

    /**
     * Obtient les fleurs triées par quantité de nectar (ordre décroissant).
     */
    public static List<Fleur> obtenirFleursTrieesParNectar() {
        List<Fleur> fleurs = new ArrayList<>(memoireCollective.values());
        fleurs.sort((f1, f2) -> Integer.compare(f2.getNectar(), f1.getNectar())); // Nectar décroissant
        return fleurs;
    }

    /**
     * Recherche du nectar dans la zone attribuée à l'abeille.
     */
    @Override
    public void rechercherNectar(int rows, int cols) {
        boolean partieSuperieure=(id==0);
        int limiteLignes = rows / 2; // Division horizontale
        int limiteColonnes = cols / 2; // Division verticale (utile pour plus tard)

        Environnement environnement = getEnvironnement(); // Récupère l'environnement

        if (environnement == null) {
            System.out.println("Erreur : aucun environnement associé à l'abeille.");
            return;
        }

        // Déplacement selon la partie assignée
        if (partieSuperieure) {
            // Partie supérieure de la grille
            for (int i = 0; i < limiteLignes; i++) {
                for (int j = 0; j < cols; j++) {
                    seDeplacerVers(i, j);
                    Fleur fleur = environnement.environnementSimule(i, j);
                    if (fleur != null && fleur.getNectar() > 0) {
                        ajouterFleurMemoireCollective(fleur);
                        modeleInterne.put(fleur, fleur.getNectar());
                    }
                }
            }
        } else {
            // Partie inférieure de la grille
            for (int i = limiteLignes; i < rows; i++) {
                for (int j = cols - 1; j >= 0; j--) {
                    seDeplacerVers(i, j);
                    Fleur fleur = environnement.environnementSimule(i, j);
                    if (fleur != null && fleur.getNectar() > 0) {
                        ajouterFleurMemoireCollective(fleur);
                        modeleInterne.put(fleur, fleur.getNectar());
                    }
                }
            }
        }

        // Vérifier si toutes les fleurs ont été explorées
        if (environnement.toutesFleursExplorees()) {
            setEtatActuel(new RetourRuche()); // Retour à la ruche
            agir();
        }
    }

    /**
     * Collecte du nectar selon le modèle interne et la mémoire collective.
     */
    public void collecterNectar() {
        List<Fleur> fleursTriees = obtenirFleursTrieesParNectar();

        if (fleursTriees.isEmpty()) {
            System.out.println("Aucune fleur disponible pour la collecte.");
            setEtatActuel(new Repos()); // Passe en repos si aucune fleur
            return;
        }

        setEtatActuel(new CollecterNectar());
        Fleur cible = fleursTriees.get(0); // Fleur avec le plus de nectar
        seDeplacerVers(cible.getX(), cible.getY());

        int nectarCollecte = Math.min(cible.getNectar(), getCapaciteNectarPrise());
        cible.reduireNectar(nectarCollecte);
        ajouterNectarTransporté(nectarCollecte);

        // Met à jour la mémoire collective et le modèle interne
        String cle = cible.getX() + "," + cible.getY();
        if (cible.getNectar() <= 0) {
            memoireCollective.remove(cle);
            modeleInterne.remove(cible);
        } else {
            modeleInterne.put(cible, cible.getNectar());
        }

        System.out.println("Collecte de " + nectarCollecte + " nectar sur la fleur à " + cle);
        setEtatActuel(new RetourRuche()); // Retourne à la ruche après la collecte
    }

    /**
     * Affiche le modèle interne de l'abeille.
     */
    public void afficherModeleInterne() {
        System.out.println("Modèle interne de l'abeille avec modèle :");
        for (Map.Entry<Fleur, Integer> entry : modeleInterne.entrySet()) {
            Fleur fleur = entry.getKey();
            System.out.println("Fleur (" + fleur.getX() + ", " + fleur.getY() + ") - Nectar : " + entry.getValue());
        }
    }

    /**
     * Agit en fonction de l'état actuel.
     */
    @Override
    public void agir() {
        etatActuel.agir(this);
    }
    @Override
    public void retour(){
        seDeplacerVers(0,0);
    }
    public static Map<String, Fleur> getMemoireCollective() {
        return memoireCollective;
    }
    @Override
    public void recevoirNotification(Fleur fleur) {
        System.out.println("Abeille a la position:( " +getX()+","+getY()+") a été notifiée que la fleur " + fleur.getX() + "," + fleur.getY() + " est vide.");

        // Si l'abeille est en état de recherche
        if (etatActuel instanceof ChercherNectar) {
            if (modeleInterne.containsKey(fleur)) {
                System.out.println("Mise à jour de la mémoire collective pour la fleur vide.");
                modeleInterne.remove(fleur); // Suppression de la fleur de la mémoire collective
            } else {
                System.out.println("La fleur n'était pas dans la mémoire collective.");
            }
        }

        // Si l'abeille est en état de collecte
        else if (etatActuel instanceof CollecterNectar) {
            Fleur cible = ((CollecterNectar) etatActuel).getFleurCible();
            if (cible != null && cible.equals(fleur)) {
                System.out.println("La fleur cible est vide, retour à la ruche.");
                modeleInterne.remove(fleur); // Mise à jour de la mémoire collective
                setEtatActuel(new RetourRuche()); // Changer d'état
            } else {
                System.out.println("Mise à jour de la mémoire collective uniquement.");
                modeleInterne.remove(fleur); // Supprimer la fleur vide
            }
        }
    }
}
