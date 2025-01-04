package com.beesim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



import com.beesim.models.*;

import javafx.scene.layout.GridPane;

public class SimTest 
{

    @Test
    public void testCreationAbeilleSansModele() {
    AbeilleSansModele abeille = new AbeilleSansModele(null,2, 1, 20, null);
    assertEquals("Position X de l'abeille incorrecte",2, abeille.getX());
    assertEquals("Position Y de l'abeille incorrecte",1, abeille.getY());
    }   

    @Test
    public void testCreationAbeilleAvecModele() {
    AbeilleAvecModele abeille = new AbeilleAvecModele(null,2, 1, 20, null);
    assertEquals("Position X de l'abeille incorrecte",2, abeille.getX());
    assertEquals("Position Y de l'abeille incorrecte",1, abeille.getY());
    }   


    /****************************************************************
     * 
     * 
     *    Ces 2 tests visent a tester Fonction Collecter Nectar()
     * 
     * 
     * 
     ****************************************************************/
    
    
    @Test
    public void testCollecterNectarSuffisant_AbeilleSansModel() {
        AbeilleSansModele abeilleSansModele = new AbeilleSansModele(10);
        Fleur fleur =  new Fleur(15);
        abeilleSansModele.collecterNectar(fleur);
        assertEquals(10, abeilleSansModele.getCapaciteNectarPrise());
        assertEquals(5, fleur.getNectar());
    }

    
    @Test
    public void testCollecterNectarSuffisant_AbeilleAvecModel() {
        AbeilleAvecModele abeilleAvecModele = new AbeilleAvecModele(10);
        Fleur fleur =  new Fleur(15);
        abeilleAvecModele.collecterNectar(fleur);
        assertEquals(10, abeilleAvecModele.getCapaciteNectarPrise());
        assertEquals(5, fleur.getNectar());
    }
    

    @Test
    public void testEnvironnementCreation() {
    Environnement grille = new Environnement(10, 10, new GridPane());
    //grille.DessinerFleur();
    assertNotNull( "Les fleurs devraient être créées",grille.getFleurs());
    assertTrue("Il devrait y avoir des fleurs dans l'environnement",grille.getFleurs().size() > 0 );
    }

}
