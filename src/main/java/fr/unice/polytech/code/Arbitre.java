package fr.unice.polytech.code;

import java.util.ArrayList;

class Arbitre {
    private Bot joueurGagnant;

    Arbitre(){
        joueurGagnant = null;
    }

    void compteLesPoints(ArrayList<Bot> listeJoueurs) {
        for (Bot joueur : listeJoueurs) {
            int points = joueur.getVilleDuBot().getNbTotalPoint();
            System.out.println(joueur.getNom() + " : " + points + " points");
        }
    }

    void determineJoueurGagnant(ArrayList<Bot> listeJoueurs) {
        for (Bot joueur : listeJoueurs) {
            if(joueurGagnant==null){
                joueurGagnant = joueur;
            }else if(joueur.getVilleDuBot().getNbTotalPoint() > joueurGagnant.getVilleDuBot().getNbTotalPoint()) {
                joueurGagnant = joueur;
            }
        }
    }

    @Override
    public String toString() {
        return joueurGagnant.getNom() + " gagne la partie ! ";
    }

}
