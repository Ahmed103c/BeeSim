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
        abeille.setEtat(new ChercherNectar());
    }
}

