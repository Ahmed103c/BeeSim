package com.beesim.State;
import com.beesim.models.*;

public class Repos implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("Abeille en mode Repos.");
        try {
            Thread.sleep(5000); // Pause pour simuler le repos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Après repos, revient à ChercherNectar
        abeille.rechargerEnergie();
        // Détermine le nouvel état en fonction du type d'abeille
        if (abeille instanceof AbeilleAvecModel) {
            System.out.println("AbeilleAvecModel : Passage à l'état de CollecterNectar.");
            abeille.setEtatActuel(new CollecterNectar());
        } else if (abeille instanceof AbeilleSansModele) {
            System.out.println("AbeilleSansModel : Retour à l'état de ChercherNectar.");
            abeille.setEtatActuel(new ChercherNectar());
        } else {
            System.out.println("Type d'abeille inconnu, état par défaut : ChercherNectar.");
            abeille.setEtatActuel(new ChercherNectar());
        }
    }
}

