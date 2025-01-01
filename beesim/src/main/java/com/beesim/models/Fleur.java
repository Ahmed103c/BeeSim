package com.beesim.models;

public class Fleur {
    private int x;
    private int y;
    private boolean occupe;
    private int nectar;
    private Environnement environnement;

    public Fleur(Environnement environnement,int x,int y,int nectar)
    {
        this.environnement=environnement;
        this.x=x;
        this.y=y;
        this.nectar=nectar;
        this.occupe=false;
        environnement.set_Couleur_Fleur_Par_Défault(x, y, true);
    }
    

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getNectar() {
        return nectar;
    }
    public void setNectar(int nectar) {
        this.nectar = nectar;
    }
    public boolean estVide(){
        return  nectar==0;
    }
    public boolean isOccupe() {
        return occupe;
    }
    /*il faut ajouter les fonction d'occupation*/



}
