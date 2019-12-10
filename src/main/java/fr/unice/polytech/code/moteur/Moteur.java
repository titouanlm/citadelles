package fr.unice.polytech.code.moteur;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;


public class Moteur {
    private static PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage ;
    private ArrayList<Bot> listeJoueurs;
    private ArrayList<Tour> listeTours;

    public Moteur(ArrayList<Bot> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
        listeTours = new ArrayList<>();
        piocheCartesPersonnage = new PiocheCartesPersonnage();
        piocheCartesCitadelles.implementerCartesCitadelles();
        piocheCartesPersonnage.implementerCartesPersonnage();
    }

    public static PiocheCartesCitadelles getPiocheCartesCitadelles() {
        return piocheCartesCitadelles;
    }

    public PiocheCartesPersonnage getPiocheCartesPersonnage() {
        return piocheCartesPersonnage;
    }

    public ArrayList<Tour> getListeTours() {
        return listeTours;
    }

    public void lancerUnePartie() {
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
            if (!listeTours.get(cptTour - 1).lancerTour()) {
                cptTour++;
            } else {
                break;
            }
        }
    }
}