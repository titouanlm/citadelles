package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.*;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;


public class Moteur {

    private PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
    private ArrayList<Bot> listeJoueurs;

    Moteur(ArrayList<Bot> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    void lancerUnePartie() {
        piocheCartesCitadelles.implementerCartesCitadelles();
        piocheCartesPersonnage.implementerCartesPersonnage();
        this.initialiserPartie();
        this.commencerPartie();
    }

    private void initialiserPartie() {
        piocheCartesCitadelles.melanger();
        piocheCartesPersonnage.melanger();

        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
        }
    }

    void commencerPartie() {
        boolean finPartie=false;
        long choix;
        int cptTour=1;

        while (true) {
            System.out.println("******** Tour " + cptTour + " ********");
            this.attributionCarteCouronne();
            int indiceJoueurPossedantCouronne = this.obtenirIndiceJoueurCourrone();
            this.attributionPersonnageAChaqueJoueur(indiceJoueurPossedantCouronne);

                for (int i=1; i < 9 ; i++) {

                    if (piocheCartesCitadelles.nbCartesRestantes()>0){
                        choix = Math.round(Math.random());
                    }else{
                        choix=1;
                    }

                    for (Bot joueur : listeJoueurs) {
                        if(joueur.getPersonnageACeTour().getNumero()==i) {
                            if (choix==1){
                                joueur.ajouterPiece(2);
                            }else{
                                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            }
                            System.out.println(joueur.getNom() +" possède " + joueur.getNbPiece() + " pièces.");
                            joueur.strategieConstruitDesQuilPeut();
                        }
                    }
                }

                for (Bot joueur : listeJoueurs) {
                    if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8) {
                        finPartie = true;
                        break;
                    }
                }

                if (finPartie){
                    break;
                }else{
                    piocheCartesPersonnage.reinitialiser();
                    piocheCartesPersonnage.melanger();
                    cptTour++;
                }
            }
        }

    private void attributionCarteCouronne() {
        int indiceJoueurPossedantCouronne = (int)(Math.random()*(listeJoueurs.size()));
        listeJoueurs.get(indiceJoueurPossedantCouronne).setPossedeCouronne(true);
    }

    private int obtenirIndiceJoueurCourrone() {
        int indiceJoueurPossedantCouronne = 0;
        for(int i= 0; i<listeJoueurs.size(); i++){
            if(listeJoueurs.get(i).possedeCouronne()){
                indiceJoueurPossedantCouronne=i;
                break;
            }
        }
        return indiceJoueurPossedantCouronne;
    }

    private void attributionPersonnageAChaqueJoueur(int indiceJoueurPossedantCouronne) {
        for(int i=indiceJoueurPossedantCouronne; i<listeJoueurs.size(); i++){
            listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoire());
        }
        for(int i=0; i<indiceJoueurPossedantCouronne; i++){
            listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoire());
        }
    }

}
