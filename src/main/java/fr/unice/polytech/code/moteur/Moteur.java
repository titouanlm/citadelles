package fr.unice.polytech.code.moteur;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;


public class Moteur {
    private static PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
    private ArrayList<Bot> listeJoueurs;
    private ArrayList<Tour> listeTours;
    private Affichage affichage;

    public Moteur(ArrayList<Bot> listeJoueurs, Affichage affichage) {
        this.listeJoueurs = listeJoueurs;
        this.affichage=affichage;
        listeTours = new ArrayList<>();
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
        affichage.afficherDetails( "\u001B[1m" + "\u001B[31m"  + "\t\t\t-- Debut de partie --" + "\u001B[0m");
        affichage.afficherDetails("--------------------------------------------------");
        affichage.afficherDetails("\u001B[1m" + "\u001B[34m"  + "\t\t\t Initialisation du jeu " +"\u001B[0m" +"\n" );
        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            affichage.afficherDetails( "\u001B[1m" + "\u001B[32m" + "Le " + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " reçoit 2 pièces ");
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + "Le " + joueur.getNom() + "\u001B[21m" + "\u001B[0m" +" reçoit les cartes de Quartier suivantes : ");
            affichage.afficherDetails( joueur.cartesEnMainToString() + "\n");
        }
    }

    public void commencerPartie() { //ici c'est plus le déroulement de tout les tours
        int cptTour = 1;
        while (true) {
            affichage.afficherDetails("--------------------------------------------------");
            affichage.afficherDetails("\u001B[1m" + "\u001B[34m"  + "\t\t\t\t Tour  " + cptTour + ":\t\t\t\t"+ "\u001B[0m" );
            affichage.afficherDetails("--------------------------------------------------");
            listeTours.add(new Tour(cptTour, piocheCartesCitadelles, piocheCartesPersonnage, listeJoueurs,affichage));
            if (!listeTours.get(cptTour - 1).lancerTour()) {
                cptTour++;
            } else {
                break;
            }
        }
    }
}