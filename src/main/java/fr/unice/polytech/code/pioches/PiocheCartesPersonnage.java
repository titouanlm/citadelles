package fr.unice.polytech.code;

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

    void setPiocheCP(Personnage cp) {
        this.piocheCP.add(cp);
    }

    void melanger(){
        Collections.shuffle(this.piocheCP);
    }
}
