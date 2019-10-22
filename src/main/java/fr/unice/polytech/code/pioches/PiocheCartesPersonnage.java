package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.Personnage;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesPersonnage {
    private ArrayList<Personnage> piocheCP;

    public PiocheCartesPersonnage() {
        this.piocheCP = new ArrayList<>();
    }

    ArrayList<Personnage> getPiocheCP() {
        return piocheCP;
    }

    public void ajouterCartePersonnage(Personnage cp) {
        this.piocheCP.add(cp);
    }

    public void melanger(){
        Collections.shuffle(this.piocheCP);
    }
}
