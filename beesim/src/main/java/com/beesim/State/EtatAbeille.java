package com.beesim.State;
import com.beesim.models.*;

/*
public class EtatAbeille {
    public void donnerEtat(){};


}
 */
public interface EtatAbeille {
    void agir(Abeille abeille);
}

