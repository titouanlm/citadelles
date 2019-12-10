package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesAvecPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.CourDesMiracles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

public class Arbitre {
    private Bot joueurGagnant;

    public Arbitre(){
        joueurGagnant = null;
    }

    public Bot getJoueurGagnant() {
        return joueurGagnant;
    }

    public void compteLesPoints(ArrayList<Bot> listeJoueurs){
        for (Bot joueur : listeJoueurs) {
            joueur.setNbPoint(joueur.getVilleDuBot().getNbTotalPoint());
            this.testBonusPremierJoueurAFinir(joueur);
            this.testBonusAConstruit8CesQuartiers(joueur);
            this.testBonusPossede5CouleursDeQuartierDifferentes(joueur);
        }
    }

    public void testBonusPremierJoueurAFinir(Bot joueur){
        if(joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(4);
        }
    }

    void testBonusAConstruit8CesQuartiers(Bot joueur){
        if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8 && !joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(2);
        }
    }

    public void testBonusPossede5CouleursDeQuartierDifferentes(Bot joueur){
        ArrayList<CouleurCarteCitadelles> couleurs = new ArrayList<>();
        couleurs.add(CouleurCarteCitadelles.BLEU);
        couleurs.add(CouleurCarteCitadelles.JAUNE);
        couleurs.add(CouleurCarteCitadelles.VERT);
        couleurs.add(CouleurCarteCitadelles.ROUGE);
        couleurs.add(CouleurCarteCitadelles.VIOLET);


        for(CarteCitadelles c : joueur.getVilleDuBot().getBatimentsConstruits()){
            if(c instanceof CourDesMiracles){
                ((CourDesMiracles) c).effectuerSpecialite((CarteCitadellesAvecPouvoir) c, joueur, null);
            }
        }

        for (CarteCitadelles batiment : joueur.getVilleDuBot().getBatimentsConstruits() ){
            for(int i=0;i<couleurs.size();i++){
                if (batiment.getCouleur().equals(couleurs.get(i))) {
                    couleurs.remove(i);
                }
            }
        }

        if(couleurs.size()==0){
            joueur.setNbPoint(3);
        }
    }

    void determineJoueurGagnant(ArrayList<Bot> listeJoueurs) {
        for (Bot joueur : listeJoueurs) {
            if(joueurGagnant==null){
                joueurGagnant = joueur;
            }else if(joueur.getNbPoint() > joueurGagnant.getNbPoint()) {
                joueurGagnant = joueur;
            }
        }
    }

    @Override
    public String toString() {
        return "\u001B[1m" + "\u001B[34m"  + joueurGagnant.getNom() + " gagne la partie ! " + "\u001B[0m" +"\n";
    }
}