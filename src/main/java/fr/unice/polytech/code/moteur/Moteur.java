package fr.unice.polytech.code.moteur;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;


public class Moteur {
    private static PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage ;
    private ArrayList<Bot> listeJoueurs;
    private ArrayList<Tour> listeTours;
    private Affichage affichage;

    public Moteur(ArrayList<Bot> listeJoueurs, Affichage affichage) {
        this.listeJoueurs = listeJoueurs;
        this.affichage=affichage;
        listeTours = new ArrayList<>();
        piocheCartesPersonnage = new PiocheCartesPersonnage(this.affichage);
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
        affichage.afficherDetails("--------------------------------------------------");
        affichage.afficherDetails( "\t\t\t-- Début de la partie --");
        affichage.afficherDetails("--------------------------------------------------");
        affichage.afficherDetails("\t\t\t Initialisation du jeu " +"\n" );
        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            affichage.afficherDetails(joueur.getCouleur() + joueur.getNom() +  " reçoit 2 pièces. " + "\u001B[0m");
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
            affichage.afficherDetails(joueur.getCouleur() + joueur.getNom() +" reçoit les cartes Citadelles suivantes : "+ "\u001B[0m" );
            affichage.afficherDetails( joueur.cartesEnMainToString() + "\n");
        }
    }

    public void commencerPartie() {
        int cptTour = 1;
        while (true) {
            affichage.afficherDetails("\u001B[1m"+"*********************"  + " Tour " + cptTour + " ********************\n"+ "\u001B[0m");
            listeTours.add(new Tour(cptTour, piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs,affichage));
            if (!listeTours.get(cptTour - 1).lancerTour()) {
                cptTour++;
            } else {
                break;
            }
        }
    }
}