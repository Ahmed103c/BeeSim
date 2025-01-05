package com.beesim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;



import com.beesim.models.*;

import javafx.scene.layout.GridPane;

public class SimTest 
{

    /****************************************************************
     * 
     * 
     *    Test pour vérifier la création d'une abeille sans modèle et 
     *      l'initialisation correcte de ses coordonnées
     * 
     * 
     * 
     ****************************************************************/
    @Test
    public void testCreationAbeilleSansModele() {
    AbeilleSansModele abeille = new AbeilleSansModele(null,2, 1, 20, null);
    assertEquals("Position X de l'abeille incorrecte",2, abeille.getX());
    assertEquals("Position Y de l'abeille incorrecte",1, abeille.getY());
    }   

    
    /****************************************************************
     * 
     * 
     *    Test pour vérifier la création d'une abeille avec modèle et 
     *      l'initialisation correcte de ses coordonnées
     * 
     * 
     * 
     ****************************************************************/

    @Test
    public void testCreationAbeilleAvecModele() {
    AbeilleAvecModele abeille = new AbeilleAvecModele(null,2, 1, 20, null);
    assertEquals("Position X de l'abeille incorrecte",2, abeille.getX());
    assertEquals("Position Y de l'abeille incorrecte",1, abeille.getY());
    }   


    /****************************************************************
     * 
     * 
     *      Les 2 tests suivants vérifient le comportement de la méthode 
     *          collecterNectar() pour deux types 
     *          d'abeilles : avec et sans modèle.
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
    
    /****************************************************************
     * 
     * 
     *    Test pour vérifier que l'environnement est correctement 
     *          créé avec des fleurs
     * 
     * 
     * 
     ****************************************************************/

    @Test
    public void testEnvironnementCreation() {
    Environnement grille = new Environnement(10, 10, new GridPane(),null);
    assertNotNull( "Les fleurs devraient être créées",grille.getFleurs());
    assertTrue("Il devrait y avoir des fleurs dans l'environnement",grille.getFleurs().size() > 0 );
    }

}
