package com.beesim.Mediateeur;
import com.beesim.models.*;

public interface  mediateur {
    void enregistrerAbeillePourFleur(Fleur fleur, Abeille abeille);
    void notifierAbeilles(Fleur fleur);
}
