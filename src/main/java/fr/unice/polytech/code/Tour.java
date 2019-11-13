package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.Assassin;
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

    public ArrayList<Bot> getListeJoueurs() {
        return listeJoueurs;
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

        for (int i = this.indiceJoueurPossedantCouronne; i < listeJoueurs.size(); i++) {
            if (setUpTypeBot < listeJoueurs.size()) {
                listeJoueurs.get(i).setUpTypeBot();
                setUpTypeBot += 1;
            }
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
            } else {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement());
            }
        }
        for (int i = 0; i < this.indiceJoueurPossedantCouronne; i++) {
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
            } else {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement());
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
        }
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
        /** Les futures lignes sont là pour simplifier le code lors de l'appel de la fonction effecteur spécialté */
        Personnage personnage = listeJoueurs.get(botQuiVaFairePleurerQuelquun).getPersonnageACeTour();
        Bot joueur = listeJoueurs.get(botQuiVaFairePleurerQuelquun);
        Bot victime = listeJoueurs.get(botQueLonVaDétruire);
        personnage.effectuerSpecialite(joueur, victime, piocheCartesCitadelles);
    }
}
