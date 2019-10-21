package fr.unice.polytech.code;

import java.util.ArrayList;

public class Ville {

    private ArrayList<CarteCitadelles> batimentsConstruits = new ArrayList<>();
    private int nbBatimentsConstruits;
    private int nbTotalPoint;

    Ville(){
        nbBatimentsConstruits = 0;
        nbTotalPoint=0;
    }

    public ArrayList<CarteCitadelles> getBatimentsConstruits() {
        return batimentsConstruits;
    }

    public void construireBatiment(CarteCitadelles batimentAConstruire) {
        this.batimentsConstruits.add(batimentAConstruire);
        this.nbTotalPoint+=batimentAConstruire.getPoint();
        this.nbBatimentsConstruits+=1;
    }

    public int getNbBatimentsConstruits() {
        return nbBatimentsConstruits;
    }

    public int getNbTotalPoint() {
        return nbTotalPoint;
    }

}
