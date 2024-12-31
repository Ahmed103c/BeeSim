package com.beesim.models;
import com.beesim.State.*;

public class AbeilleSansModele extends Abeille{
    
    public AbeilleSansModele() {
        this.Etat=new Repos();  
    }
    public void getEtat(){
        this.Etat.donnerEtat();
    }
}
