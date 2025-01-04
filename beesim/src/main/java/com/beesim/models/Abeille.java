package com.beesim.models;


import com.beesim.State.*;
public abstract  class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected int capaciteNectarPriseMaximale;
    protected Ruche ruche;   
    protected EtatAbeille etatActuel;
    

    public Abeille(int x, int y ,int  capaciteNectarPriseMaximale ,Ruche ruche) {
        this.x = x;
        this.y = y;
        this.capaciteNectarPriseMaximale = capaciteNectarPriseMaximale;
        this.capaciteNectarPrise=0;
        this.ruche = ruche;
        this.etatActuel=new ChercherNectar();
    }
    public Abeille(int capaciteNectarPriseMaximale )
    {
         this(1, 1, capaciteNectarPriseMaximale, null);
    }


    public void setEtat(EtatAbeille etatAbeille)
    {
        this.etatActuel=etatAbeille;
    }

    public int getCapaciteNectarPrise() {
        return capaciteNectarPrise;
    }
    public int getCapaciteNectarPriseMaximale() {
        return capaciteNectarPriseMaximale;
    }
    public int getCapaciteNectarDisponible(){
        return capaciteNectarPriseMaximale-capaciteNectarPrise;
    }
    public void setCapaciteNectarPrise(int capaciteNectarPrise) {
        this.capaciteNectarPrise = capaciteNectarPrise;
    }
    public Ruche getRuche() {
        return ruche;
    }
}
