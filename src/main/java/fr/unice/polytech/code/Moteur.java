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

    private void commencerPartie() {
        long choix;
        int cptTour=1;
        boolean roiPresent=false;
        Bot joueurRoi=null;

        while (true) {
            System.out.println("******** Tour " + cptTour + " ********");
            int indiceJoueurPossedantCouronne = this.obtenirIndiceJoueurPossedantCourrone();

            piocheCartesPersonnage.piocherPersonnageAleatoire();
            Personnage personnageDefausseVisible= piocheCartesPersonnage.piocherPersonnageAleatoire();
            while(personnageDefausseVisible.getNumero()==4){
                piocheCartesPersonnage.ajouterCartePersonnage(personnageDefausseVisible);
                personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoire();
            }
            System.out.println(personnageDefausseVisible.getNom() + " ne peut être choisit pour ce tour.\n");

            this.attributionPersonnageAChaqueJoueur(indiceJoueurPossedantCouronne);

                for (int i=1; i < 9 ; i++) {

                    if (piocheCartesCitadelles.nbCartesRestantes()>0){
                        choix = Math.round(Math.random());
                    }else{
                        choix=1;
                    }

                    for (Bot joueur : listeJoueurs) {
                        if(joueur.getPersonnageACeTour().getNumero()==i) {
                            if (choix == 1) {
                                joueur.ajouterPiece(2);
                            } else {
                                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            }
                            System.out.println(joueur.getNom() + " possède " + joueur.getNbPiece() + " pièces.");
                            String cartesEnMain = "";
                            for (CarteCitadelles carteEnMain : joueur.getCartesCitadellesEnMain()) {
                                cartesEnMain += carteEnMain.getNom() + ", ";
                            }
                            System.out.println(joueur.getNom() + " possède les cartes " + cartesEnMain + " dans sa main.");
                            joueur.strategieConstruitDesQuilPeut();
                            if(i==4){
                                joueurRoi=joueur;
                                roiPresent=true;
                            }
                        }
                    }
                }

                if (this.verifierFinPartie()){
                    break;
                }else{
                    piocheCartesPersonnage.reinitialiser();
                    piocheCartesPersonnage.melanger();
                    listeJoueurs.get(indiceJoueurPossedantCouronne).setPossedeCouronne(false);
                    if(roiPresent){
                        joueurRoi.setPossedeCouronne(true);
                        roiPresent=false;
                    }
                    cptTour++;
                }
            }
        }

    private int obtenirIndiceJoueurPossedantCourrone() {
        int indiceJoueurPossedantCouronne = 10;
        for(int i= 0; i<listeJoueurs.size(); i++){
            if(listeJoueurs.get(i).possedeCouronne()){
                indiceJoueurPossedantCouronne=i;
                break;
            }
        }
        if(indiceJoueurPossedantCouronne==10){
            indiceJoueurPossedantCouronne = (int)(Math.random()*(listeJoueurs.size()));
            listeJoueurs.get(indiceJoueurPossedantCouronne).setPossedeCouronne(true);
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

    private boolean verifierFinPartie() {
        for (Bot joueur : listeJoueurs) {
            if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8) {
                return true;
            }
        }
        return false;
    }

}
