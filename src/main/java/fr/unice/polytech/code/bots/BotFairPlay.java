package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesAvecPouvoir;
import fr.unice.polytech.code.cartes.Observatoire;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend les meilleurs décisions possibles dans le jeu en appliquant les vraies règles
 */
public class BotFairPlay extends Bot {

    public BotFairPlay(String nom, String couleur, Affichage affichage) {
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

    @Override
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int indicePersonnageAssassiner;
        do{
            indicePersonnageAssassiner = (int)(Math.random()*8);
        }while(indicePersonnageAssassiner == this.getPersonnageACeTour().getNumero() || (indicePersonnageAssassiner+1)==personnageDefausse.getNumero());

        Personnage p = this.getPersonnageACeTour();
        ((Assassin) p).effectuerSpecialiteAssassin( this.listePersonnages(indicePersonnageAssassiner), listeJoueurs);
    }

    @Override
    public void strategieVoleur(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int indicePersonnageAVoler;
        do{
            indicePersonnageAVoler = (int)(Math.random()*8);
        }while(indicePersonnageAVoler == this.getPersonnageACeTour().getNumero() || indicePersonnageAVoler == 0 || (indicePersonnageAVoler+1)==personnageDefausse.getNumero());
        Personnage p = this.getPersonnageACeTour();
        ((Voleur) p).effectuerSpecialiteVoleur(this, this.listePersonnages(indicePersonnageAVoler), listeJoueurs);
    }

    @Override
    public void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles pioche) {
        Personnage magicien = this.getPersonnageACeTour();
        int nombreCarteMaxMainPersonne = -1;
        int b=0;
        ArrayList<CarteCitadelles> cartesAEchanger = new ArrayList<>();
        for (int i =0 ; i < listeJoueurs.size(); i++) {
            if (listeJoueurs.get(i) != this) {
                if (listeJoueurs.get(i).getCartesCitadellesEnMain().size() > nombreCarteMaxMainPersonne) {
                    nombreCarteMaxMainPersonne=listeJoueurs.get(i).getCartesCitadellesEnMain().size();
                    b = i;
                }
            }
        }
        for (int p = 0; p < this.getCartesCitadellesEnMain().size(); p++) {
            if (this.getCartesCitadellesEnMain().get(p).getPoint() < 4) {
                cartesAEchanger.add(this.getCartesCitadellesEnMain().get(p));
            }
        }
        if (nombreCarteMaxMainPersonne > 10) {
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

    @Override // A implémenter (Aléatoire pour l'instant) DETRUIT LE BAT D'UN JOUEUR
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
