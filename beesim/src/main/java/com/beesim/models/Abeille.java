package com.beesim.models;
import com.beesim.State.*;
public abstract class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected int score;
    protected EtatAbeille Etat;
    // public Abeille(){
    //     this.Etat=new Repos();
    // }
    int getScore(){
        return score;
    }
    void setScore(int score){
        this.score = score;
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

}
