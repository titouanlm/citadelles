package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.CarteCitadelles;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesCitadelles {
    private ArrayList<CarteCitadelles> piocheCC;

    public PiocheCartesCitadelles() {
        this.piocheCC = new ArrayList<>();
    }

    public ArrayList<CarteCitadelles> getPiocheCC() {
        return piocheCC;
    }

    public void ajouterCarteCitadelles(CarteCitadelles cc) {
        this.piocheCC.add(cc);
    }

    public void melanger(){
        Collections.shuffle(this.piocheCC);
    }

    public CarteCitadelles piocher(){
        CarteCitadelles cartePiochee = piocheCC.get(0);
        piocheCC.remove(0);
        return cartePiochee;
    }

}
