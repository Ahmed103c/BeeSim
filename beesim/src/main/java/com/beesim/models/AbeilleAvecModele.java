package com.beesim.models;
import java.util.*;
import com.beesim.State.EtatAbeille;

public class AbeilleAvecModele extends Abeille{
    private static HashMap<String, Fleur> memoireCollective = new HashMap<>();
    private boolean rechercheTerminee = false; // Indique si la recherche est terminée
    private boolean collecteTerminee = false; // Indique si la collecte est terminée
    private int capacite;

    public AbeilleAvecModele(int x, int y, int capacite) {
        super(x, y, 0); // Le score commence à 0
        this.capacite = capacite;
    }

    // Ajouter une fleur à la mémoire collective
    public static void ajouterFleurMemoire(Fleur fleur) {
        if (fleur == null || fleur.estVide()) return;
        String cle = fleur.getX() + "," + fleur.getY();
        memoireCollective.put(cle, fleur);
        System.out.println("Fleur ajoutée : " + cle + " avec nectar " + fleur.getNectar());
    }

    // Obtenir la liste des fleurs triée par nectar décroissant
    private static List<Fleur> obtenirFleursTriees() {
        List<Fleur> fleurs = new ArrayList<>(memoireCollective.values());
        fleurs.sort((f1, f2) -> Integer.compare(f2.getNectar(), f1.getNectar())); // Tri décroissant
        return fleurs;
    }
    public void rechercherNectar(int tailleGrille, boolean partieSuperieure) {
        int limite = tailleGrille / 2;

        if (partieSuperieure) {
            // Abeille 1 : Parcourt la moitié supérieure de la grille
            for (int i = 0; i < limite; i++) {
                for (int j = 0; j < tailleGrille; j++) {
                    this.x = i;
                    this.y = j;

                    // Simuler la découverte d'une fleur
                    Fleur fleur = environnementSimule(i, j);
                    if (fleur != null && !fleur.estVide()) {
                        ajouterFleurMemoire(fleur);
                    }
                }
            }
        } else {
            // Abeille 2 : Parcourt la moitié inférieure de la grille (inverse de l'abeille 1)
            for (int i = limite; i < tailleGrille; i++) {
                for (int j = tailleGrille - 1; j >= 0; j--) {
                    this.x = i;
                    this.y = j;

                    // Simuler la découverte d'une fleur
                    Fleur fleur = environnementSimule(i, j);
                    if (fleur != null && !fleur.estVide()) {
                        ajouterFleurMemoire(fleur);
                    }
                }
            }
        }

        rechercheTerminee = true;
    }
    public void collecterNectar(boolean prioriteMax) {
        List<Fleur> fleursTriees = obtenirFleursTriees();

        // Sélectionner la fleur appropriée
        if (fleursTriees.isEmpty()) {
            collecteTerminee = true;
            return;
        }
        Fleur cible = prioriteMax ? fleursTriees.get(0) : (fleursTriees.size() > 1 ? fleursTriees.get(1) : null);

        if (cible != null) {
            int nectarCollecte = Math.min(cible.getNectar(), capacite);
            cible.setNectar(cible.getNectar() - nectarCollecte);
            this.score += nectarCollecte;

            // Mettre à jour la mémoire collective
            String cle = cible.getX() + "," + cible.getY();
            if (cible.getNectar() <= 0) {
                memoireCollective.remove(cle);
            } else {
                memoireCollective.put(cle, cible);
            }
            System.out.println("Collecte terminée : " + nectarCollecte + " nectar pris à " + cle);
        }
        collecteTerminee = true;
    }
    @Override
    public void agir() {
        if (!rechercheTerminee) {
            System.out.println("Abeille cherche des fleurs...");
            rechercherNectar(10, this.x < 5); //justeUnExemple
        } else if (!collecteTerminee) {
            System.out.println("Abeille collecte du nectar...");
            collecterNectar(true);
        } else {
            System.out.println("Abeille retourne à la ruche.");
        }
    }

    public static HashMap<String, Fleur> getMemoireCollective() {
        return memoireCollective;
    }
}
