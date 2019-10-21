package fr.unice.polytech.code;

import java.util.ArrayList;
import java.util.Collections;

class PiocheCartesCitadelles {
    private ArrayList<CarteCitadelles> piocheCC;

    PiocheCartesCitadelles() {
        this.piocheCC = new ArrayList<>();
    }

    ArrayList<CarteCitadelles> getPiocheCC() {
        return piocheCC;
    }

    void setPiocheCC(CarteCitadelles cc) {
        this.piocheCC.add(cc);
    }

    void melanger(){
        Collections.shuffle(this.piocheCC);
    }

}
