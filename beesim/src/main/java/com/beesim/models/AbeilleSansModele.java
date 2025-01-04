package com.beesim.models;
import com.beesim.State.*;
import com.beesim.State.EtatAbeille;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.plaf.synth.SynthEditorPaneUI;

public class AbeilleSansModele extends Abeille {

    private int x;
    private int y;
    private Environnement environnement;


    public AbeilleSansModele(Environnement environnement,int x, int y ,Ruche ruche) {
        super(x, y,ruche,environnement);
        environnement.set_Abeille_Sans_Modèle_Par_Défaults(x, y, true);
    }
    /*
        public void collecterNectar(Fleur fleur)
        {
                int nectarCollecte = Math.min(fleur.getNectar(), this.getCapaciteNectarDisponible());
                fleur.reduireNectar(nectarCollecte);
                this.ajouterNectarTransporté(nectarCollecte);
        }
    */
    public void collecterNectar(Fleur fleur) {
        int nectarCollecte = Math.min(fleur.getNectar(), this.capaciteNectarPrise);

        if (nectarCollecte > 0) { // Ne collecter que si la capacité permet
            this.capaciteNectarPrise += nectarCollecte;
            fleur.reduireNectar(nectarCollecte);

            System.out.println("Nectar collecté : " + nectarCollecte);
            System.out.println("Capacité actuelle : " + this.capaciteNectarPrise );
        } else {
            System.out.println("Abeille pleine ! Aucun nectar supplémentaire collecté.");
        }
    }
    public void deplacer() {
        int newX = x;
        int newY = y;
        boolean positionValide = false;
        int tentatives = 0;                     // Compteur pour éviter une boucle infinie et ne pas avoir une grande Complexité
        while (!positionValide && tentatives < 10) {



            if (newX >= 0 && newX < environnement.cells.length
                    && newY >= 0 && newY < environnement.cells[0].length
                    && !environnement.isCellPartOfRuche(newX, newY)) {
                positionValide = true;
            }

            tentatives++;
        }

        //ce code permet de retourner false si l'abeille quitte la fleur
        if (environnement.getCellBool(x, y))
        {
            Fleur fleur = environnement.getCell(x, y);
            fleur.setOccupe(false);
        }

        if (positionValide) {
            environnement.mettreAJourCellule_Abeille(x, y, false);      // Vide l'ancienne position
            x = newX;
            y = newY;

            // Vérifie si la nouvelle position contient une fleur
            if (environnement.getCellBool(x, y)) {
                System.out.println("Abeille est sur une fleur ");
                Fleur fleur = environnement.getCell(x, y);
                fleur.setOccupe(true);
                this.collecterNectar(fleur);
                // Si l'abeille est pleine, elle retourne à la ruche

            }

            // System.out.println("Abeille déplacée à : (" + x + ", " + y + ")");
            environnement.mettreAJourCellule_Abeille(x, y, true);     // Remplit la nouvelle position
        } else {
            System.out.println("Aucune position valide trouvée pour l'abeille après " + tentatives + " tentatives.");
        }
    }
    @Override
    public void interagirAvecFleur(Fleur fleur) {
        System.out.println("salut");
    }

    public void retournerAuRuche(Ruche ruche) {
        System.out.println("Abeille retourne à la ruche.");
        int rucheX = 1; // Coordonnées fixes de la ruche
        int rucheY = 1;

        while (x != rucheX || y != rucheY) {
            // Sauvegarder l'ancienne position
            int xAncien = x;
            int yAncien = y;

            // Calculer la nouvelle position
            if (x < rucheX) x++;
            else if (x > rucheX) x--;

            if (y < rucheY) y++;
            else if (y > rucheY) y--;

            // Mettre à jour l'environnement : vider l'ancienne cellule
            environnement.mettreAJourCellule_Abeille(xAncien, yAncien, false);

            // Remplir la nouvelle cellule
            environnement.mettreAJourCellule_Abeille(x, y, true);
        }

        // Une fois arrivé à la ruche
        ruche.ajouterNectar(this.capaciteNectarPrise);
        this.capaciteNectarPrise = 0; // Réinitialiser la capacité de nectar
    }
    @Override
    public Fleur choisirFleur() {
        Fleur fleur =new Fleur(1,1,100);
        return fleur;
    }
    @Override
    public Fleur choisirFleurPourCollecte(){
        Fleur fleur =new Fleur(1,1,100);
        return fleur;
    }
    @Override
    public void agir() {
        etatActuel.agir(this);
    }
    @Override
    public void retour(){
        seDeplacerVers(0,0);
    }
    @Override
    public void rechercherNectar(int taille,int m){
        System.out.println("ChercherNectar");
    }
    @Override
    public void recevoirNotification(Fleur fleur) {
        System.out.println("Abeille a la position: (" + getX()+","+getY() + ") a été notifiée que la fleur " + fleur.getX() + "," + fleur.getY() + " est vide.");

        // Si l'abeille est en état de collecte et la fleur vide est la cible
        if (etatActuel instanceof CollecterNectar) {
            Fleur cible = ((CollecterNectar) etatActuel).getFleurCible();
            if (cible != null && cible.equals(fleur)) {
                System.out.println("La fleur cible est vide, retour à la ruche.");
                setEtatActuel(new RetourRuche());
            } else {
                System.out.println("Abeille sans modèle : pas d'action requise.");
            }
        }
    }


}