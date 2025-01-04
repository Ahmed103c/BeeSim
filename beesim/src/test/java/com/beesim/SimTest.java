package com.beesim;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Test;



import com.beesim.models.*;
import com.beesim.models.Fleur;

public class SimTest 
{

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
    
}
