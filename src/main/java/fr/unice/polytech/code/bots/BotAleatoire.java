package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend toutes ses décisions aléatoirement
 */
public class BotAleatoire extends Bot {

    public BotAleatoire(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategieConstruction(PiocheCartesCitadelles piocheCartesCitadelles) { //Construit le premier batiment qu'il peut construire
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)) {
                retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                break;
            }
        }
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        Personnage pChoisi = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        this.setPersonnageACeTour(pChoisi);
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) { //Non aléatoire
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
        } else {
            CarteCitadelles cartePrise = piocheCartesCitadelles.piocher();
            this.ajouterCartesCitadellesDansMain(cartePrise);
        }
    }

    /** Changer le fonctionnement pour viser un personnage et non pas un joueur */

    @Override
    public void strategieAssassin(ArrayList<Bot> listeJoueurs) {
        /*int indiceBotAAssassiner;
        do{
            indiceBotAAssassiner = (int)(Math.random()*listeJoueurs.size());
        }while(listeJoueurs.get(indiceBotAAssassiner) == this);

        Bot botAAssassiner = listeJoueurs.get(indiceBotAAssassiner);
        Personnage assassin = this.getPersonnageACeTour();
        assassin.effectuerSpecialite(this, botAAssassiner);*/
    }

    @Override
    public void strategieVoleur(ArrayList<Bot> listeJoueurs) {
        /*int indiceBotAVoler;
        do{
            indiceBotAVoler = (int)(Math.random()*listeJoueurs.size());
        }while(listeJoueurs.get(indiceBotAVoler) == this);

        Bot botAVoler = listeJoueurs.get(indiceBotAVoler);
        Personnage voleur = this.getPersonnageACeTour();
        voleur.effectuerSpecialite(this, botAVoler);*/
    }

    @Override //A implémenter
    public void strategieMagicien(ArrayList<Bot> listeJoueurs) {

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
    public void strategieArchitecte(PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Architecte){
            ((Architecte) personnageJoueur).effectuerSpecialiteArchitecte(this, piocheCartesCitadelles);
        }
    }

    @Override
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Condottiere){
            ((Condottiere) personnageJoueur).effectuerSpecialiteCondottiere(this);

            int indiceBotVictime;
            do{
                indiceBotVictime = (int)(Math.random()*listeJoueurs.size());
            }while(listeJoueurs.get(indiceBotVictime) == this);
            Bot victime = listeJoueurs.get(indiceBotVictime);
            ((Condottiere) personnageJoueur).detruireQuartierAleatoireEnemie(this, victime);
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0) {
            return (int) Math.round(Math.random());
        } else {
            return 1;
        }
    }
}