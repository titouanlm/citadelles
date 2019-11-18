package fr.unice.polytech.code;

import java.util.ArrayList;

public class Ville {

    public ArrayList<CarteCitadelles> batimentsConstruits = new ArrayList<>();
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
        if(!this.contient(batimentAConstruire)){
            this.batimentsConstruits.add(batimentAConstruire);
            this.nbTotalPoint+=batimentAConstruire.getPoint();
            this.nbBatimentsConstruits+=1;
            System.out.println("Construit le quartier " + batimentAConstruire.getNom() + " dans sa ville." );
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

    public String quartiersVilleToString(){
        String quartiers="";
        for(CarteCitadelles c : batimentsConstruits){
            quartiers+= c.getNom()+", ";
        }
        return quartiers;
    }

    public void detruireQuartier(CarteCitadelles quartierADetruire) {
        if(this.contient(quartierADetruire)){
            this.batimentsConstruits.remove(quartierADetruire);
            this.nbTotalPoint-=quartierADetruire.getPoint();
            this.nbBatimentsConstruits-=1;
        }
    }
}
