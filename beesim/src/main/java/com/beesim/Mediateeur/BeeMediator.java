package com.beesim.Mediateeur;

import com.beesim.models.Abeille;
import com.beesim.models.Fleur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeeMediator implements mediateur {
    private final Map<Fleur, Set<Abeille>> abeillesParFleur = new HashMap<>();

    @Override
    public void enregistrerAbeillePourFleur(Fleur fleur, Abeille abeille) {
        abeillesParFleur.computeIfAbsent(fleur, f -> new HashSet<>()).add(abeille);
        System.out.println("Abeille a la position :(" + abeille.getX() + " enregistrée pour surveiller la fleur a la position :(" + fleur.getX()+","+fleur.getY()+")");
    }

    @Override
    public void notifierAbeilles(Fleur fleur) {
        if (abeillesParFleur.containsKey(fleur)) {
            for (Abeille abeille : abeillesParFleur.get(fleur)) {
                abeille.recevoirNotification(fleur);
            }
        }
    }
}
