package com.beesim.models;
import com.beesim.State.EtatAbeille;

import java.util.HashMap;
import java.util.Map;

public class AbeilleSansModele extends Abeille {

    public AbeilleSansModele(int x, int y, int score) {
        super(x, y, score);
        this.capaciteNectarPrise=20;
        this.etatActuel=new EtatAbeille();
    }
    @Override
    public void agir() {
        //bah selon les etats
    }
}
