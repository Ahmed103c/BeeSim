package com.beesim.models;

public class Fleur {
    private int x;
    private int y;
    private boolean occupe;
    private int nectar;
    

    public Fleur(int x,int y,int nectar)
    {
        this.x=x;
        this.y=y;
        this.nectar=nectar;
        this.occupe=false;
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
    public void setOccupe(boolean occup)
    {
        this.occupe=occup;
    }
    public boolean isOccupe() {
        return occupe;
    }
    public void reduireNectar(int quantite) {
        if (!occupe && quantite <= nectar) {
            this.nectar -= quantite;
            if (this.nectar <= 0) {
                this.nectar = 0;
                this.occupe = true; // La fleur devient inaccessible si tout le nectar est collecté
            }
        }
    }
    @Override
    public String toString() {
        return "Fleur [x=" + x + ", y=" + y + ", nectar=" + nectar + ", estAccessible=" + !occupe + "]";
    }
    /*il faut ajouter les fonction d'occupation*/



}
