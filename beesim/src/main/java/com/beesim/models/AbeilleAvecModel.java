package com.beesim.models;

import com.beesim.State.*;
import java.util.*;

public class AbeilleAvecModel extends Abeille {
    private static Map<String, Fleur> memoireCollective = new HashMap<>(); // Mémoire partagée entre abeilles avec modèle
    private Map<Fleur, Integer> modeleInterne; // Mémoire individuelle de l'abeille
    public AbeilleAvecModel(int x, int y, int capaciteNectarPrise, Ruche ruche , Environnement environnement) {
        super(x, y, capaciteNectarPrise, ruche);
        this.modeleInterne = new HashMap<>();
    }

}
