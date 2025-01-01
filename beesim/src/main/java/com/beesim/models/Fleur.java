package com.beesim.models;

public class Fleur {
    private int nectar;
    private int x;
    private int y;
    private boolean occupe;

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
