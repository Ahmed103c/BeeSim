package com.beesim.State;
import com.beesim.models.*;

public class ChercherNectar implements EtatAbeille {
    @Override
    public void agir(Abeille abeille) {
        System.out.println("État : Recherche de nectar...");

        abeille.rechercherNectar(abeille.getEnvironnement().getRows(),abeille.getEnvironnement().getRows());

        // Si l'énergie est faible, passer à l'état de repos
        if (abeille.getEnergie() <= 0) {
            abeille.setEtatActuel(new RetourRuche());
            abeille.setEtatActuel(new Repos());
            System.out.println("Énergie faible, l'abeille passe en repos.");
            return;
        }

        // Si l'environnement est parcouru, retour à la ruche
        if (abeille.getEnvironnement().toutesFleursExplorees()) {
            abeille.setEtatActuel(new RetourRuche());
            System.out.println("Toutes les fleurs explorées, retour à la ruche.");
            //istanceof
            if (abeille instanceof AbeilleSansModele){
                System.out.println("AbeilleSansModele est en train de collecter le nectar");
                abeille.setEtatActuel(new CollecterNectar());
            } else if (abeille instanceof AbeilleAvecModel) {
                System.out.println("L'abeille exploratrice retourne a la ruche pour informer la bituneuse");
                abeille.setEtatActuel(new RetourRuche());
            }
        }
    }
}
