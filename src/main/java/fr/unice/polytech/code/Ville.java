package fr.unice.polytech.code;

import fr.unice.polytech.code.cartes.CarteCitadelles;

import java.util.ArrayList;

public class Ville {

    public ArrayList<CarteCitadelles> batimentsConstruits = new ArrayList<>();
    private int nbBatimentsConstruits;
    private int nbTotalPoint;

    public Ville(){
        nbBatimentsConstruits = 0;
        nbTotalPoint=0;
    }

    public ArrayList<CarteCitadelles> getBatimentsConstruits() {
        return batimentsConstruits;
    }

    public void construireBatiment(CarteCitadelles batimentAConstruire) {
        if(!this.contient(batimentAConstruire)){
            this.batimentsConstruits.add(batimentAConstruire);
            this.nbTotalPoint+=batimentAConstruire.getPoint();
            this.nbBatimentsConstruits+=1;
        }
    }

    public int getNbBatimentsConstruits() {
        return nbBatimentsConstruits;
    }

    public void setNbBatimentsConstruits(int nbBatimentsConstruits) {
        this.nbBatimentsConstruits = nbBatimentsConstruits;
    }

    public int getNbTotalPoint() {
        return nbTotalPoint;
    }

    public boolean contient(CarteCitadelles carteCitadelles) {
        for(CarteCitadelles batiment: batimentsConstruits){
            if(batiment.getNom().equals(carteCitadelles.getNom())){
                return true;
            }
        }
        return false;
    }

    public int compterNbQuartiersRouge(){
        int nbQuartiersRouge=0;
        for(CarteCitadelles quartier : batimentsConstruits){
            if(quartier.getCouleur().toString()=="ROUGE"){
                nbQuartiersRouge++;
            }
        }
        return nbQuartiersRouge;
    }
}
