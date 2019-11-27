package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend les meilleurs décisions possibles en trichant
 */
public class BotTricheur extends Bot {

    public BotTricheur(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategieConstruction(PiocheCartesCitadelles piocheCartesCitadelles) { //Construit le plus gros batiment qu'il peut
        CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
        if (carteEnMainDePlusHauteValeur != null) {
            this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
            this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
            this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
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
            CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
            CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();

            if(cartePiochee2!=null){
                CarteCitadelles carteChoisie;

                // On teste si le joueur n'a pas déjà construit les quartiers ou les possède déjà dans sa main
                if(!this.getVilleDuBot().contient(cartePiochee1) && !this.getVilleDuBot().contient(cartePiochee2) &&
                        !this.contientDansSaMain(cartePiochee1) && !this.contientDansSaMain(cartePiochee2)){

                    if(cartePiochee1.getCouleur().toString().equals("VIOLET") && cartePiochee2.getCouleur().toString().equals("VIOLET")){
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }else if(cartePiochee1.getCouleur().toString().equals("VIOLET")){ //Prend la première carte si elle est violette
                        carteChoisie=cartePiochee1;
                    }else if(cartePiochee2.getCouleur().toString().equals("VIOLET")){//Prend la deuxième carte si elle est violette
                        carteChoisie=cartePiochee2;
                    }else{
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }
                }else if(!this.getVilleDuBot().contient(cartePiochee1) && !this.contientDansSaMain(cartePiochee1)){ //Prend la première carte s'il possède déjà la 2ème
                    carteChoisie=cartePiochee1;
                }else if(!this.getVilleDuBot().contient(cartePiochee2) && !this.contientDansSaMain(cartePiochee2)){ //Prend la 2ème carte s'il possède déjà la première
                    carteChoisie=cartePiochee2;
                }else{ //Prend la première carte si il possède déjà les deux
                    carteChoisie=cartePiochee1;
                }

                this.ajouterCartesCitadellesDansMain(carteChoisie);

                //On remet dans la pioche la carte que l'on a pas choisi
                if(carteChoisie==cartePiochee1){
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
                }else{
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
                }
            }else{
                this.ajouterCartesCitadellesDansMain(cartePiochee1);
            }
        }
    }

    @Override // Assassine le joueur ayant le plus de point
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        Bot botQueLonVaAssassiner= null;
        int joueurMaxPoint = -1;
        for(Bot b : listeJoueurs){
            if (b.getNbPoint() > joueurMaxPoint) {
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
            ((Magicien) magicien).echangerCartesAvecUnPersonnage(this, listeJoueurs.get(b));
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
