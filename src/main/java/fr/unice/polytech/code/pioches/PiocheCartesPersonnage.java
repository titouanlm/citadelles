package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.Personnage;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesPersonnage {
    private ArrayList<Personnage> piocheCP;

    PiocheCartesPersonnage() {
        this.piocheCP = new ArrayList<>();
    }

    ArrayList<Personnage> getPiocheCP() {
        return piocheCP;
    }

    void ajouterCartePersonnage(Personnage cp) {
        this.piocheCP.add(cp);
    }

    void melanger(){
        Collections.shuffle(this.piocheCP);
    }
}
