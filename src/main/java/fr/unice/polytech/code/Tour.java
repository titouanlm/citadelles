package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
    private int numero;
    private int indiceJoueurPossedantCouronne;
    private Personnage personnageDefausseVisible;
    private Bot joueurAyantLeRoi;
    private PiocheCartesCitadelles piocheCartesCitadelles;
    private PiocheCartesPersonnage piocheCartesPersonnage;
    private ArrayList<Bot> listeJoueurs;
    private int setUpTypeBot;

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
        //System.out.println("\033[0m" + "******** Tour " + this.getNumero() + " ********");

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

    public void appelerJoueursDansLOrdre() { //
        for (int i = 1; i < 9; i++) {
            Collections.shuffle(listeJoueurs);
            for (Bot joueur : listeJoueurs) {
                if (joueur.getPersonnageACeTour() != null) {
                    if (joueur.getPersonnageACeTour().getNumero() == i) {
                        if (i == 4) {
                            this.setJoueurAyantLeRoi(joueur);
                        } else if (i == 6) {
                            joueur.ajouterPiece(1);
                        }
                        joueur.strategie(piocheCartesCitadelles);
                        strategieEffectuerSpecialité();

                        this.estJoueurAyantFinisEnPremier(joueur); //besoin d'explication sur ce point
                        break;
                    }
                }
            }
        }
    }


    public void setIndiceJoueurPossedantCourrone() {
        int indiceJoueurPossedantCouronne = 10;
        for (int i = 0; i < listeJoueurs.size(); i++) {
            if (listeJoueurs.get(i).possedeCouronne()) {
                indiceJoueurPossedantCouronne = i;
                break;
            }
        }
        if (indiceJoueurPossedantCouronne == 10) {
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
        int économie=0;
        for (int i = this.indiceJoueurPossedantCouronne; i < listeJoueurs.size(); i++) {
            if (setUpTypeBot < listeJoueurs.size()) {
                listeJoueurs.get(i).setUpTypeBot();
                setUpTypeBot += 1;
            }
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
            } else {
                économie=listeJoueurs.get(i).nbPiece;
                ArrayList<CarteCitadelles> carteEnMain = listeJoueurs.get(i).getCartesCitadellesEnMain();
                Ville villeDuBot=listeJoueurs.get(i).villeDuBot;
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(économie, carteEnMain,villeDuBot));
            }
        }
        for (int i = 0; i < this.indiceJoueurPossedantCouronne; i++) {
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
            } else {
                économie=listeJoueurs.get(i).nbPiece;
                ArrayList<CarteCitadelles> carteEnMain = listeJoueurs.get(i).getCartesCitadellesEnMain();
                Ville villeDuBot=listeJoueurs.get(i).villeDuBot;
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(économie, carteEnMain, villeDuBot));
            }
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

    public void strategieEffectuerSpecialité() {
        for (int i = 0; i < listeJoueurs.size(); i++) {
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Assassin) {
                strategieAssassin(i);
                break;
            }
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Architecte) {
                strategieArchitecte(i);
                break;
            }
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Voleur) {
                strategieVoleur(i);
                break;
            }
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Roi){
                strategieRoi(i);
                break;
            }
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Marchand){
                strategieMarchand(i);
                break;
            }
            if (listeJoueurs.get(i).getPersonnageACeTour() instanceof Magicien){
                strategieMagicien(i);
                break;
            }
        }
    }

    public void strategieMagicien(int botQuiPossèdeLeMagicien){
        int botQueLonVaDétruire=(int) (Math.random()*listeJoueurs.size());
        int nombreCarteMaxMain = 0;
        if (listeJoueurs.get(botQuiPossèdeLeMagicien).getTypedubot()== "Intelligent"){
            for (int i=0;i<listeJoueurs.size();i++){
                if (nombreCarteMaxMain <= listeJoueurs.get(i).getCartesCitadellesEnMain().size()){
                    nombreCarteMaxMain=listeJoueurs.get(i).getCartesCitadellesEnMain().size();
                    botQueLonVaDétruire=i;
                }
            }
        }
        Bot joueur = listeJoueurs.get(botQuiPossèdeLeMagicien);
        Personnage personnage = listeJoueurs.get(botQuiPossèdeLeMagicien).getPersonnageACeTour();
        Bot victime = listeJoueurs.get(botQueLonVaDétruire);
        personnage.effectuerSpecialite(joueur, victime, piocheCartesCitadelles);
    }

    public void strategieMarchand(int botQuiPossèdeLeMarchand){
        Bot joueur = listeJoueurs.get(botQuiPossèdeLeMarchand);
        Personnage personnage = listeJoueurs.get(botQuiPossèdeLeMarchand).getPersonnageACeTour();
        personnage.effectuerSpecialite(joueur, null, piocheCartesCitadelles);
    }

    public void strategieRoi(int botQuiPossèdeLeRoi){
        Bot joueur = listeJoueurs.get(botQuiPossèdeLeRoi);
        Personnage personnage = listeJoueurs.get(botQuiPossèdeLeRoi).getPersonnageACeTour();
        personnage.effectuerSpecialite(joueur, null, piocheCartesCitadelles);
    }

    public void strategieVoleur(int personnequidoitvoler){
        int botQueLonVaDétruire=(int) (Math.random()*listeJoueurs.size());
        int nombreDePieceMax=0;
        if (listeJoueurs.get(personnequidoitvoler).getTypedubot()== "Intelligent"){
            for (int i=0;i<listeJoueurs.size();i++){
                if (listeJoueurs.get(i).nbPiece>=nombreDePieceMax && listeJoueurs.get(botQueLonVaDétruire).getPersonnageACeTour()==null){
                    nombreDePieceMax=listeJoueurs.get(i).nbPiece;
                    botQueLonVaDétruire=i;
                }
            }
        }
        else {
            while (botQueLonVaDétruire==personnequidoitvoler | listeJoueurs.get(botQueLonVaDétruire).getPersonnageACeTour()==null){
                botQueLonVaDétruire= (int) (Math.random()*listeJoueurs.size());
            }
        }
        /** Les lignes ci-dessous sont là pour simplifier le code lors de l'appel de la fonction effectuer spécialité */
        Bot victime = listeJoueurs.get(botQueLonVaDétruire);
        Personnage personnage = listeJoueurs.get(personnequidoitvoler).getPersonnageACeTour();
        Bot joueur = listeJoueurs.get(personnequidoitvoler);
        personnage.effectuerSpecialite(joueur, victime, piocheCartesCitadelles);
    }

    public void strategieArchitecte(int personneQuiDoitConstruire){
        Personnage personnage = listeJoueurs.get(personneQuiDoitConstruire).getPersonnageACeTour();
        Bot joueur = listeJoueurs.get(personneQuiDoitConstruire);
        personnage.effectuerSpecialite(joueur, null, piocheCartesCitadelles);
    }

    public void strategieAssassin(int botQuiVaFairePleurerQuelquun) {
        int botQueLonVaDétruire = -1;
        if (listeJoueurs.get(botQuiVaFairePleurerQuelquun).getTypedubot() == "Intelligent") {
            int joueurmaxpoint = -1;
            for (int i = 0; i < listeJoueurs.size(); i++) {
                if (listeJoueurs.get(i).getNbPoint() > joueurmaxpoint) {
                    joueurmaxpoint = listeJoueurs.get(i).getNbPoint();
                    botQueLonVaDétruire = i;
                }
            }
        }
        else {
            botQueLonVaDétruire= (int) Math.random() * listeJoueurs.size();
        }
        Personnage personnage = listeJoueurs.get(botQuiVaFairePleurerQuelquun).getPersonnageACeTour();
        Bot joueur = listeJoueurs.get(botQuiVaFairePleurerQuelquun);
        Bot victime = listeJoueurs.get(botQueLonVaDétruire);
        personnage.effectuerSpecialite(joueur, victime, piocheCartesCitadelles);
    }

}
