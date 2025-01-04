package com.beesim.models;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;

import com.beesim.State.*;
public abstract class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected int capaciteNectarPriseMaximale;
    protected Ruche ruche;
    protected int energie;    
    protected EtatAbeille etatActuel;
    

    public Abeille(int x, int y ,int  capaciteNectarPriseMaximale ,Ruche ruche) {
        this.x = x;
        this.y = y;
        this.capaciteNectarPriseMaximale = capaciteNectarPriseMaximale;
        this.capaciteNectarPrise=0;
        this.energie = 100;
        this.ruche = ruche;
        this.etatActuel=new ChercherNectar();
    }


    public void setEtat(EtatAbeille etatAbeille)
    {
        this.etatActuel=etatAbeille;
    }

    int getCapaciteNectarPrise() {
        return capaciteNectarPrise;
    }
    int getCapaciteNectarPriseMaximale() {
        return capaciteNectarPriseMaximale;
    }
    int getCapaciteNectarDisponible(){
        return capaciteNectarPriseMaximale-capaciteNectarPrise;
    }
    void setCapaciteNectarPrise(int capaciteNectarPrise) {
        this.capaciteNectarPrise = capaciteNectarPrise;
    }
    public Ruche getRuche() {
        return ruche;
    }
}
