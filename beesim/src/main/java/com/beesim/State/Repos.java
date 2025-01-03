package com.beesim.State;

import com.beesim.models.Abeille;

public class Repos extends EtatAbeille{
    @Override
    public void agir(Abeille abeille)
    {
        System.out.println("L'abeille est au repos .");
        abeille.setEtat(new ChercherNectar());
    }
}
