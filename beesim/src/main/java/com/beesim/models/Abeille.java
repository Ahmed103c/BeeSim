package com.beesim.models;
import com.beesim.State.*;
public abstract class Abeille {
    protected int x;
    protected int y;
    protected int capaciteNectarPrise;
    protected Ruche ruche;
    protected int nectarTransporte;
    protected int energie;
    protected EtatAbeille etatActuel;
    private Environnement environnement;

    public Environnement getEnvironnement() {
        return environnement;
    }
    public void setEnvironnement(Environnement environnement) {
        this.environnement = environnement;
    }


    public Abeille(int x, int y ,Ruche ruche,Environnement environnement) {
        this.x = x;
        this.y = y;
        this.environnement = environnement;
        this.energie = 100;
        this.capaciteNectarPrise = 30;
        this.nectarTransporte=0;
        this.ruche = ruche;
        this.etatActuel=new ChercherNectar();
    }
    public abstract void interagirAvecFleur(Fleur fleur);
    public void agir()
    {
        etatActuel.agir(this);
    }
    public void seDeplacerVers(int cibleX, int cibleY) {
        int x1,y1;
        while (x != cibleX || y != cibleY) {
            x1=x;
            y1=y;
            if (x < cibleX) {x++;}
            else if (x > cibleX) x--;
            if (y < cibleY) y++;
            else if (y > cibleY) y--;
            environnement.mettreAJourCellule_Abeille(x1,y1,false);
            if (environnement.getCellBool(x, y)) {
                System.out.println("Abeille est sur une fleur ");
                Fleur fleur = environnement.getCell(x, y);
                interagirAvecFleur(fleur);
                // Si l'abeille est pleine, elle retourne à la ruche

            }

            // System.out.println("Abeille déplacée à : (" + x + ", " + y + ")");
            environnement.mettreAJourCellule_Abeille(x, y, true);
            System.out.println("Position actuelle : [" + x + ", " + y + "]");
            energie--; // Réduire l'énergie pour chaque déplacement
        }
    }
    public abstract void retour();
    //public  boolean estSurFleur();

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
    public abstract void rechercherNectar(int rows,int cols); // Méthode abstraite
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
    public int getEnergie() {
        return energie;
    }

    public int getNectarTransporté() {
        return nectarTransporte;
    }

    public Ruche getRuche() {
        return ruche;
    }
    public void setEtatActuel(EtatAbeille etatActuel) {
        this.etatActuel = etatActuel;
    }
    public boolean estDansLimites(int x, int y) {
        return x >= 0 && x < environnement.getRows() && y >= 0 && y < environnement.getCols();
    }

}