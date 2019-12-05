package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend les meilleurs décisions possibles en trichant
 */
public class BotTricheur extends Bot {

    public BotTricheur(String nom, String couleur, Affichage affichage) {
        super(nom, couleur,affichage);
    }

    @Override
    public void strategieConstruction() { //Construit le plus gros batiment qu'il peut
        CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
        if (carteEnMainDePlusHauteValeur != null) {
            this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
            this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
            this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
            affichage.afficherDetails(this.getCouleur() + "Construit le quartier " + carteEnMainDePlusHauteValeur.getCouleur() + carteEnMainDePlusHauteValeur.getNom() + "\u001B[0m" + this.getCouleur() + " dans sa ville.");
        }
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(this));
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
        } else {
            this.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());// Peut être déterminée par le joueur
        }
    }

    @Override // Assassine le joueur ayant le plus de point
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        Bot botQueLonVaAssassiner= null;
        int joueurMaxPoint = -1;
        for(Bot b : listeJoueurs){
            if (b.getNbPoint() > joueurMaxPoint && b != this) {
                joueurMaxPoint = b.getNbPoint();
                botQueLonVaAssassiner = b;
            }
        }
        Personnage personnageJoueur = this.getPersonnageACeTour();
        ((Assassin) personnageJoueur).effectuerSpecialiteAssassin(botQueLonVaAssassiner.getPersonnageACeTour(), listeJoueurs);
    }

    @Override // Vole le joueur ayant le plus de pièce
    public void strategieVoleur(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        Bot botAVoler = null;
        int nombreDePieceMax = 0;
        for (Bot botVictime : listeJoueurs) {
            if (botVictime.getNbPiece()  >= nombreDePieceMax && botVictime.getPersonnageACeTour() != null &&
                    botVictime != this && !(botVictime.getPersonnageACeTour() instanceof Assassin)) {
                nombreDePieceMax = botVictime.getNbPiece();
                botAVoler=botVictime;
            }
        }
        Personnage personnageJoueur = this.getPersonnageACeTour();
        ((Voleur) personnageJoueur).effectuerSpecialiteVoleur(this, botAVoler.getPersonnageACeTour(), listeJoueurs);
    }

    @Override
    public void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles pioche) {
        Personnage magicien = this.getPersonnageACeTour();
        int nombrePointsCarteMaxMainPersonne = 0;
        int nombrePointsCarteMainPersonne =0;
        int b=0;
        ArrayList<CarteCitadelles> cartesAEchanger = new ArrayList<>();
        for (int i =0 ; i < listeJoueurs.size(); i++) {
            for (int c = 0; c < listeJoueurs.get(i).getCartesCitadellesEnMain().size(); c++) {
                nombrePointsCarteMainPersonne+=listeJoueurs.get(i).getCartesCitadellesEnMain().get(c).getPoint();
            }
            if (nombrePointsCarteMainPersonne > nombrePointsCarteMaxMainPersonne){
                nombrePointsCarteMaxMainPersonne=nombrePointsCarteMainPersonne;
            }
        }
        for (int p = 0; p < this.getCartesCitadellesEnMain().size(); p++) {
            if (this.getCartesCitadellesEnMain().get(p).getPoint() < 4) {
                cartesAEchanger.add(this.getCartesCitadellesEnMain().get(p));
            }
        }
        if (nombrePointsCarteMaxMainPersonne > 10) {
            ((Magicien) magicien).echangerCartesAvecUnJoueur(this, listeJoueurs.get(b));
        }
        else {
            ((Magicien) magicien).echangerCartesAvecPioche(this, pioche, cartesAEchanger);
        }
    }

    @Override
    public void strategieRoi() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Roi){
            ((Roi) personnageJoueur).effectuerSpecialiteRoi(this);
        }
    }

    @Override
    public void strategieEveque() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Eveque){
            ((Eveque) personnageJoueur).effectuerSpecialiteEveque(this);
        }
    }

    @Override
    public void strategieMarchand() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Marchand){
            ((Marchand) personnageJoueur).effectuerSpecialiteMarchand(this);
        }
    }

    @Override
    public void strategieArchitecte() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Architecte){
            ((Architecte) personnageJoueur).effectuerSpecialiteArchitecte(this);
        }
    }

    @Override
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Condottiere){
            ((Condottiere) personnageJoueur).effectuerSpecialiteCondottiere(this);
            int nbPointMax=0;
            Bot victime=null;
            for(Bot b : listeJoueurs){
                if(b!=this && b.getVilleDuBot().getNbTotalPoint()>nbPointMax){
                    nbPointMax=b.getVilleDuBot().getNbTotalPoint();
                    victime=b;
                }
            }
            ((Condottiere) personnageJoueur).detruirePlusGrosQuartierEnemie(this, victime);
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0 && getNbPiece() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }
}
