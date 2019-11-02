package fr.unice.polytech.code;

import java.util.ArrayList;

class Arbitre {
    private Bot joueurGagnant;

    Arbitre(){
        joueurGagnant = null;
    }

    public Bot getJoueurGagnant() {
        return joueurGagnant;
    }

    void compteLesPoints(ArrayList<Bot> listeJoueurs){
        //System.out.println("\033[0m" + "******** Résutats " + " ********\n");
        for (Bot joueur : listeJoueurs) {
            joueur.setNbPoint(joueur.getVilleDuBot().getNbTotalPoint());
            this.testBonusPremierJoueurAFinir(joueur);
            this.testBonusAConstruit8CesQuartiers(joueur);
            this.testBonusPossede5CouleursDeQuartierDifferentes(joueur);

            //System.out.println(joueur.getCouleur() + joueur.getNom() + " : " + joueur.getNbPoint() + " points \n");
        }
    }

    void testBonusPremierJoueurAFinir(Bot joueur){
        if(joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(4);
        }
    }

    void testBonusAConstruit8CesQuartiers(Bot joueur){
        if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8 && !joueur.estPremierJoueurAFinir()){
            joueur.setNbPoint(2);
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
        return "\033[1;32m" + joueurGagnant.getNom() + " gagne la partie ! ";
    }

}
