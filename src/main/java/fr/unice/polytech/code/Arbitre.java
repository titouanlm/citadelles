package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;

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
        ArrayList<String> couleurs = new ArrayList<>();
        couleurs.add("BLEU");
        couleurs.add("JAUNE");
        couleurs.add("VERT");
        couleurs.add("ROUGE");
        couleurs.add("VIOLET");

        for (CarteCitadelles batiment : joueur.getVilleDuBot().getBatimentsConstruits() ){
            for(int i=0;i<couleurs.size();i++){
                if (batiment.getCouleur().toString().equals(couleurs.get(i))) {
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