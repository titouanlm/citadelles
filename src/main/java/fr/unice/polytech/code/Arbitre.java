package fr.unice.polytech.code;

import java.util.ArrayList;

class Arbitre {

    void compteLesPoints(ArrayList<Bot> listeJoueurs) {
        int pts = 0;
        for (int i = 0; i < 2; i++) {
            pts = listeJoueurs.get(i).getVilleDuBot().getNbTotalPoint();
            System.out.println("Le nombre total des points de" + listeJoueurs.get(i).getNom() + "est : " + pts);
        }
    }


    String afficherGagnant(ArrayList<Bot> listeJoueurs) {
        String gagnant = null;
        for (int i = 0; i < 2; i++) {
            if (listeJoueurs.get(i).getVilleDuBot().getNbTotalPoint() > listeJoueurs.get(i + 1).getVilleDuBot().getNbTotalPoint()) {
                gagnant = listeJoueurs.get(i).getNom();
            } else if (listeJoueurs.get(i).getVilleDuBot().getNbTotalPoint() < listeJoueurs.get(i + 1).getVilleDuBot().getNbTotalPoint()) {
                gagnant = listeJoueurs.get(i + 1).getNom();
            }
        }
        return gagnant;
    }


    @Override
    public String toString() {
        ArrayList<Bot> listeJoueurs;
        return "Le joueur gagnant est : " + this.afficherGagnant(listeJoueurs);
    }

}
