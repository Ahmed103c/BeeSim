package com.beesim.models;
import com.beesim.State.*;
public abstract class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected int score;
    protected EtatAbeille etatActuel;


    public Abeille(int x, int y ,int  score){
        this.x = x;
        this.y = y;
        this.score = score;
        this.etatActuel=new Repos();
    }


    public void setEtat(EtatAbeille nouvelEtat)
    {
        this.etatActuel=nouvelEtat;
    }


    public void agir()
    {
        etatActuel.agir(this);
    }


    //public  boolean estSurFleur();

    public void collecterNectar() 
    {
        System.out.println("Collecte du nectar...");
        capaciteNectarPrise += 1; // Augmente le nectar
    }


    public EtatAbeille getEtat()
    {
        return etatActuel;
    }

    int getX() {
        return x;
    }
    void setX(int x) {
        this.x = x;
    }
    int getY() {
        return y;
    }
    void setY(int y) {
        this.y = y;
    }
    int getCapaciteNectarPrise() {
        return capaciteNectarPrise;
    }
    void setCapaciteNectarPrise(int capaciteNectarPrise) {
        this.capaciteNectarPrise = capaciteNectarPrise;
    }
    int getScore(){
        return score;
    }
    void setScore(int score){
        this.score = score;
    }
    



}
