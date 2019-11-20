package fr.unice.polytech.code.moteur;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;


import java.util.ArrayList;

public class Tour {
    private int numero;
    private int indiceJoueurPossedantCouronne;
    private Personnage personnageDefausseVisible;
    private Bot joueurAyantLeRoi;
    private PiocheCartesCitadelles piocheCartesCitadelles;
    private PiocheCartesPersonnage piocheCartesPersonnage;
    private ArrayList<Bot> listeJoueurs;

    public Tour(int numero, PiocheCartesCitadelles piocheCartesCitadelles, PiocheCartesPersonnage piocheCartesPersonnage, ArrayList<Bot> listeJoueurs) {
        this.numero = numero;
        this.piocheCartesCitadelles = piocheCartesCitadelles;
        this.piocheCartesPersonnage = piocheCartesPersonnage;
        this.listeJoueurs = listeJoueurs;
        this.indiceJoueurPossedantCouronne = 0;
        this.personnageDefausseVisible = null;
        this.joueurAyantLeRoi = null;
    }

    public int getNumero() {
        return numero;
    }

    public int getIndiceJoueurPossedantCouronne() {
        return indiceJoueurPossedantCouronne;
    }

    public Personnage getPersonnageDefausseVisible() {
        return personnageDefausseVisible;
    }

    public void setPersonnageDefausseVisible(Personnage personnageDefausseVisible) {
        this.personnageDefausseVisible = personnageDefausseVisible;
    }

    public Bot getJoueurAyantLeRoi() {
        return joueurAyantLeRoi;
    }

    public void setJoueurAyantLeRoi(Bot joueurAyantLeRoi) {
        this.joueurAyantLeRoi = joueurAyantLeRoi;
        joueurAyantLeRoi.setPossedeCouronne(true);
    }

    public boolean lancerTour() {
        this.defausserCartesPersonnagePourLeTour();
        this.setIndiceJoueurPossedantCourrone();
        this.attributionPersonnageAChaqueJoueur();
        this.appelerJoueursDansLOrdre();

        return this.finDuTour();
    }

    public boolean finDuTour() {
        if (this.verifierFinPartie()) {
            return true;
        } else {
            piocheCartesPersonnage.reinitialiser();
            piocheCartesPersonnage.melanger();
            if (listeJoueurs.get(this.indiceJoueurPossedantCouronne) != this.joueurAyantLeRoi || this.joueurAyantLeRoi == null) {
                listeJoueurs.get(this.indiceJoueurPossedantCouronne).setPossedeCouronne(false);
            }
            return false;
        }
    }

    public void appelerJoueursDansLOrdre() {
        for (int numeroAppeler = 1; numeroAppeler < 9; numeroAppeler++) {
            for (Bot joueur : listeJoueurs) {
                if (joueur.getPersonnageACeTour() != null) {
                    if (joueur.getPersonnageACeTour().getNumero() == numeroAppeler) {
                        System.out.println("\n" + joueur.getCouleur() + joueur.getNom() + " : " + joueur.getPersonnageACeTour().getNom());
                        if (numeroAppeler == 4) { //Roi
                            this.setJoueurAyantLeRoi(joueur);
                        } else if (numeroAppeler == 6) { //Marchand
                            joueur.ajouterPiece(1);
                        }else if(numeroAppeler == 7){ //Architecte
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                        }
                        joueur.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
                        joueur.strategie(piocheCartesCitadelles);
                        this.strategieEffectuerSpecialite(joueur);
                        this.estJoueurAyantFinisEnPremier(joueur);
                        break;
                    }
                }
            }
        }
    }


    public void setIndiceJoueurPossedantCourrone() {
        int indiceJoueurPossedantCouronne = -1;
        for (int i = 0; i < listeJoueurs.size(); i++) {
            if (listeJoueurs.get(i).possedeCouronne()) {
                indiceJoueurPossedantCouronne = i;
                break;
            }
        }
        if (indiceJoueurPossedantCouronne == -1) {
            indiceJoueurPossedantCouronne = (int) (Math.random() * (listeJoueurs.size()));
            listeJoueurs.get(indiceJoueurPossedantCouronne).setPossedeCouronne(true);
        }
        this.indiceJoueurPossedantCouronne = indiceJoueurPossedantCouronne;
    }

    public void defausserCartesPersonnagePourLeTour() {
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        Personnage personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoirement();

        while (personnageDefausseVisible.getNumero() == 4) {
            piocheCartesPersonnage.ajouterCartePersonnage(personnageDefausseVisible);
            personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        }
        this.setPersonnageDefausseVisible(personnageDefausseVisible);
    }

    public void attributionPersonnageAChaqueJoueur() {
        for (int i = this.indiceJoueurPossedantCouronne; i < listeJoueurs.size(); i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);
        }
        for (int i = 0; i < this.indiceJoueurPossedantCouronne; i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);
        }
    }

    public boolean estJoueurAyantFinisEnPremier(Bot joueur) {
        for (Bot j : listeJoueurs) {
            if (j.estPremierJoueurAFinir()) {
                return false;
            }
        }
        if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8) {
            joueur.setPremierJoueurAFinir(true);
            return true;
        }
        return false;
    }

    public boolean verifierFinPartie() {
        for (Bot joueur : listeJoueurs) {
            if (joueur.getVilleDuBot().getNbBatimentsConstruits() == 8) {
                return true;
            }
        }
        return false;
    }

    public void strategieEffectuerSpecialite(Bot joueur) {
        if (joueur.getPersonnageACeTour() instanceof Assassin) {
            strategieAssassin(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Architecte) {
            strategieArchitecte(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Voleur) {
            strategieVoleur(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Roi) {
            strategieRoi(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Marchand) {
            strategieMarchand(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Magicien) {
            strategieMagicien(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Eveque) {
            strategieEveque(joueur);
        }
        if (joueur.getPersonnageACeTour() instanceof Condottiere) {
            strategieCondottiere(joueur);
        }
    }
    //Vrai pour le bot tricheur
    public void strategieCondottiere(Bot botQuiPossedeLeCondottiere) {
        int joueurAvecLePlusDePoint = 0;
        int botQueLonVaDetruire = (int)(Math.random() * listeJoueurs.size());
        while (listeJoueurs.get(botQueLonVaDetruire) == botQuiPossedeLeCondottiere) {
            botQueLonVaDetruire = (int)(Math.random()* listeJoueurs.size());
        }
        if (botQuiPossedeLeCondottiere instanceof BotTricheur) {
            for (int i = 0; i < listeJoueurs.size(); i++) {
                if (joueurAvecLePlusDePoint <= listeJoueurs.get(i).getNbPoint()) {
                    joueurAvecLePlusDePoint = listeJoueurs.get(i).getNbPoint();
                    botQueLonVaDetruire = i;
                }
            }
        }
        Bot victime = listeJoueurs.get(botQueLonVaDetruire);
        Personnage condottiere = botQuiPossedeLeCondottiere.getPersonnageACeTour();
        condottiere.effectuerSpecialite(botQuiPossedeLeCondottiere, victime, piocheCartesCitadelles);
    }

    public void strategieEveque(Bot botQuiPossedeLEveque) {
        Personnage eveque = botQuiPossedeLEveque.getPersonnageACeTour();
        eveque.effectuerSpecialite(botQuiPossedeLEveque, null, piocheCartesCitadelles);
    }

    //Marche pour bot tricheur
    public void strategieMagicien(Bot botQuiPossedeLeMagicien) {
        int indiceBotCible = (int)(Math.random() * listeJoueurs.size());
        Bot botCible = listeJoueurs.get(indiceBotCible);
        int nombreCarteMaxMain = 0;
        if (botQuiPossedeLeMagicien instanceof BotTricheur) {
            for(Bot b : listeJoueurs){
                if (nombreCarteMaxMain <= b.getCartesCitadellesEnMain().size()) {
                    nombreCarteMaxMain = b.getCartesCitadellesEnMain().size();
                    botCible = b;
                }
            }
        }
        Personnage magicien = botQuiPossedeLeMagicien.getPersonnageACeTour();
        magicien.effectuerSpecialite(botQuiPossedeLeMagicien, botCible, piocheCartesCitadelles);
    }

    public void strategieMarchand(Bot botQuiPossedeLeMarchand) {
        Personnage marchand = botQuiPossedeLeMarchand.getPersonnageACeTour();
        marchand.effectuerSpecialite(botQuiPossedeLeMarchand, null, piocheCartesCitadelles);
    }

    public void strategieRoi(Bot botQuiPossedeLeRoi) {
        Personnage roi = botQuiPossedeLeRoi.getPersonnageACeTour();
        roi.effectuerSpecialite(botQuiPossedeLeRoi, null, piocheCartesCitadelles);
    }

    //Vrai pour le voleur
    public void strategieVoleur(Bot botVoleur) {
        Bot botAVoler = null;
        if (botVoleur instanceof BotTricheur) {
            int nombreDePieceMax = 0;
            for (Bot botVictime : listeJoueurs) {
                if (botVictime.getNbPiece()  >= nombreDePieceMax && botVictime.getPersonnageACeTour() != null &&
                        botVictime != botVoleur && !(botVictime.getPersonnageACeTour() instanceof Assassin)) {
                    nombreDePieceMax = botVictime.getNbPiece();
                    botAVoler = botVictime;
                }
            }
        } else {
            int indiceBotAVoler = (int) (Math.random() * listeJoueurs.size());
            botAVoler = listeJoueurs.get(indiceBotAVoler);
        }
        botVoleur.getPersonnageACeTour().effectuerSpecialite(botVoleur,botAVoler, piocheCartesCitadelles);
    }

    public void strategieArchitecte(Bot botArchitecte){
        Personnage architecte = botArchitecte.getPersonnageACeTour();
        architecte.effectuerSpecialite(botArchitecte, null, piocheCartesCitadelles);
    }

    // Vrai pour bot voleur
    public void strategieAssassin(Bot botAssassin) {
        Bot botQueLonVaAssassiner= null;
        if (botAssassin instanceof BotTricheur) {
            int joueurMaxPoint = -1;
            for(Bot b : listeJoueurs){
                if (b.getNbPoint() > joueurMaxPoint) {
                    joueurMaxPoint = b.getNbPoint();
                    botQueLonVaAssassiner = b;
                }
            }
        }else{
            int indiceBotQueLonVaAssassiner=(int)(Math.random()*listeJoueurs.size());
            botQueLonVaAssassiner = listeJoueurs.get(indiceBotQueLonVaAssassiner);
        }
        Personnage assassin = botAssassin.getPersonnageACeTour();
        assassin.effectuerSpecialite(botAssassin, botQueLonVaAssassiner, piocheCartesCitadelles);
    }

}
