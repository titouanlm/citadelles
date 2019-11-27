package fr.unice.polytech.code;

import fr.unice.polytech.code.cartes.CarteCitadelles;

import java.util.ArrayList;

public class Ville {

    public ArrayList<CarteCitadelles> batimentsConstruits = new ArrayList<>();
    private int nbBatimentsConstruits;
    private int nbTotalPoint;
    private Affichage affichage;

    public Ville(Affichage affichage){
        nbBatimentsConstruits = 0;
        nbTotalPoint=0;
        this.affichage = affichage;
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
            if(quartier.getCouleur().toString().equals("ROUGE")){
                nbQuartiersRouge++;
            }
        }
        return nbQuartiersRouge;
    }

    public int compterNbQuartiersJaune(){
        int nbQuartiersJaune=0;
        for(CarteCitadelles quartier : batimentsConstruits){
            if(quartier.getCouleur().toString().equals("JAUNE")){
                nbQuartiersJaune++;
            }
        }
        return nbQuartiersJaune;
    }

    public int compterNbQuartiersBleu(){
        int nbQuartiersBleu=0;
        for(CarteCitadelles quartier : batimentsConstruits){
            if(quartier.getCouleur().toString().equals("BLEU")){
                nbQuartiersBleu++;
            }
        }
        return nbQuartiersBleu;
    }

    public int compterNbQuartiersVert(){
        int nbQuartiersVert=0;
        for(CarteCitadelles quartier : batimentsConstruits){
            if(quartier.getCouleur().toString().equals("VERT")){
                nbQuartiersVert++;
            }
        }
        return nbQuartiersVert;
    }

    public int compterNbQuartiersViolet(){
        int nbQuartiersViolet=0;
        for(CarteCitadelles quartier : batimentsConstruits){
            if(quartier.getCouleur().toString().equals("VIOLET")){
                nbQuartiersViolet++;
            }
        }
        return nbQuartiersViolet;
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
            affichage.afficherDetails("Le joueur détruit le quartier " + toString() + "\n");
            this.nbTotalPoint-=quartierADetruire.getPoint();
            this.nbBatimentsConstruits-=1;
        }
    }
}