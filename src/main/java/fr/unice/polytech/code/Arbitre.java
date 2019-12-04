package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesAvecPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.CourDesMiracles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

class Arbitre {
    private Bot joueurGagnant;
    private Affichage affichage;

    Arbitre(Affichage affichage){
        joueurGagnant = null;
        this.affichage = affichage;
    }

    public Bot getJoueurGagnant() {
        return joueurGagnant;
    }

    void compteLesPoints(ArrayList<Bot> listeJoueurs){
        for (Bot joueur : listeJoueurs) {
            joueur.setNbPoint(joueur.getVilleDuBot().getNbTotalPoint());
            this.testBonusPremierJoueurAFinir(joueur);
            this.testBonusAConstruit8CesQuartiers(joueur);
            for(CarteCitadelles c : joueur.getVilleDuBot().getBatimentsConstruits()){
                if(c instanceof CourDesMiracles){
                    PiocheCartesCitadelles piocheCartesCitadelles = null;
                    ((CourDesMiracles) c).effectuerSpecialite((CarteCitadellesAvecPouvoir) c, joueur, piocheCartesCitadelles);
                }
            }
            this.testBonusPossede5CouleursDeQuartierDifferentes(joueur);
        }
    }

    void testBonusPremierJoueurAFinir(Bot joueur){
        if(joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(4);
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" +joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " est le premier joueur ayant posé son huitième quartier ");
        }
    }

    void testBonusAConstruit8CesQuartiers(Bot joueur){
        if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8 && !joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(2);
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a huit quartiers ");
        }
    }

    void testBonusPossede5CouleursDeQuartierDifferentes(Bot joueur){
        ArrayList<CouleurCarteCitadelles> couleurs = new ArrayList<>();
        couleurs.add(CouleurCarteCitadelles.BLEU);
        couleurs.add(CouleurCarteCitadelles.JAUNE);
        couleurs.add(CouleurCarteCitadelles.VERT);
        couleurs.add(CouleurCarteCitadelles.ROUGE);
        couleurs.add(CouleurCarteCitadelles.VIOLET);

        for (CarteCitadelles batiment : joueur.getVilleDuBot().getBatimentsConstruits() ){
            for(int i=0;i<couleurs.size();i++){
                if (batiment.getCouleur().equals(couleurs.get(i))) {
                    couleurs.remove(i);
                }
            }
        }

        if(couleurs.size()==0){
            joueur.setNbPoint(3);
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a des quartiers de cinq couleurs différentes dans sa cité ");
        }
        affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " : " + "\u001B[21m" + "\u001B[0m" + joueur.getNbPoint() + " points");
    }

    void determineJoueurGagnant(ArrayList<Bot> listeJoueurs) {
        for (Bot joueur : listeJoueurs) {
            if(joueurGagnant==null){
                joueurGagnant = joueur;
            }else if(joueur.getNbPoint() > joueurGagnant.getNbPoint()) {
                joueurGagnant = joueur;
            }
        }
        affichage.afficherDetails(toString());
    }

    @Override
    public String toString() {
        return "\u001B[1m" + "\u001B[34m"  + joueurGagnant.getNom() + " gagne la partie ! " + "\u001B[0m" +"\n";
    }
}