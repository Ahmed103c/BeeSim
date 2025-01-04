package com.beesim.models;
import com.beesim.Mediateeur.*;

public class Fleur {
    private int x;
    private int y;
    private boolean occupe;
    private int nectar;
    private mediateur mediateur;
    
    public Fleur(int x,int y,int nectar)
    {
        this.x=x;
        this.y=y;
        this.nectar=nectar;
        this.occupe=false;
    }
    public Fleur(int x,int y,int nectar,mediateur mediateur)
    {
        this(x,y,nectar);
        this.mediateur=mediateur;

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
    public mediateur getMediateur() {
        return mediateur;
    }
    public void setMediateur(mediateur mediateur) {
        this.mediateur = mediateur;
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
    public void setOccupe(boolean occupe) {
        this.occupe=occupe;
    }
    public void reduireNectar(int quantite) {
        if (!occupe && quantite <= nectar) {
            this.nectar -= quantite;
            if (this.nectar <= 0) {
                this.nectar = 0;
                this.occupe = true;
                System.out.println("Fleur de la position :(" + x +"," +y+") est épuisée !");
                mediateur.notifierAbeilles(this);
            }
        }
    }
    @Override
    public String toString() {
        return "Fleur [x=" + x + ", y=" + y + ", nectar=" + nectar + ", estAccessible=" + !occupe + "]";
    }
}