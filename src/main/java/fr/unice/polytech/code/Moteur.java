package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;


public class Moteur {
    private PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
    private ArrayList<Bot> listeJoueurs;
    private ArrayList<Tour> listeTours;

    Moteur(ArrayList<Bot> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
        listeTours = new ArrayList<>();
        piocheCartesCitadelles.implementerCartesCitadelles();
        piocheCartesPersonnage.implementerCartesPersonnage();
    }

    public PiocheCartesCitadelles getPiocheCartesCitadelles() {
        return piocheCartesCitadelles;
    }

    public PiocheCartesPersonnage getPiocheCartesPersonnage() {
        return piocheCartesPersonnage;
    }

    public ArrayList<Tour> getListeTours() {
        return listeTours;
    }

    void lancerUnePartie() {
        this.initialiserPartie();
        this.commencerPartie();
    }

    public void initialiserPartie() {
        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
        }
    }

    public void commencerPartie() {
        int cptTour = 1;
        while (true) {
            listeTours.add(new Tour(cptTour, piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs));
            if (!listeTours.get(cptTour-1).lancerTour()) {
                cptTour++;
            } else {
                break;
            }
        }
    }

}
