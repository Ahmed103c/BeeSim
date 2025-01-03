package com.beesim.models;
import com.beesim.State.*;
public abstract class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected int nectarTransporte;
    protected int energie;
    protected Ruche ruche;
    protected EtatAbeille etatActuel;
    public Abeille(int x, int y ,int  capaciteNectarPrise ,Ruche ruche){
        this.x = x;
        this.y = y;
        this.capaciteNectarPrise = capaciteNectarPrise;
        this.energie = 100;
        this.nectarTransporte=0;
        this.ruche = ruche;
        this.etatActuel=new ChercherNectar();
    }
    public void agir(){
        etatActuel.agir(this);
    }
    public void setEtat(EtatAbeille nouvelEtat) {
        this.etatActuel = nouvelEtat;
    }
    public void seDeplacerVers(int cibleX, int cibleY) {
        while (x != cibleX || y != cibleY) {
            if (x < cibleX) x++;
            else if (x > cibleX) x--;
            if (y < cibleY) y++;
            else if (y > cibleY) y--;

            System.out.println("Position actuelle : [" + x + ", " + y + "]");
            energie--; // Réduire l'énergie pour chaque déplacement
        }
    }
    public void ajouterNectarTransporté(int quantité) {
        nectarTransporte += quantité;
    }
    public void viderNectarTransporté() {
        nectarTransporte = 0;
    }
    public void rechargerEnergie() {
        energie = 100;
    }
    public abstract Fleur choisirFleur();
    public abstract Fleur choisirFleurPourCollecte();
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
    public int getCapaciteNectarPrise() {
        return capaciteNectarPrise;
    }
    public void setCapaciteNectarPrise(int capaciteNectarPrise) {
        this.capaciteNectarPrise = capaciteNectarPrise;
    }
    public int getEnergie() {
        return energie;
    }

    public int getNectarTransporté() {
        return nectarTransporte;
    }

    public Ruche getRuche() {
        return ruche;
    }
}
