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
        }
    }

    void compteLespointsFinal(ArrayList<Bot> listeJoueurs){
        for (Bot joueur : listeJoueurs) {
            int points = joueur.getVilleDuBot().getNbTotalPoint();
            if(joueur==joueurGagnant){
                points=points+4;
            }
            if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8 && joueur!=joueurGagnant){
                points=points+2;
            }
            ArrayList<String> couleurs = new ArrayList<>();
            couleurs.add("BLEU");
            couleurs.add("JAUNE");
            couleurs.add("VERT");
            couleurs.add("ROUGE");
            couleurs.add("VIOLET");

            for (CarteCitadelles batiment :joueur.getVilleDuBot().batimentsConstruits ){
                for(int i=0;i<couleurs.size();i++){
                    if(batiment.getCouleur().toString().equals(couleurs.get(i))==true ){
                        couleurs.remove(i);
                    }
                }


            }
            System.out.println(couleurs.size());
            if(couleurs.size()==0){
                points=points+3;
            }

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
