package fr.unice.polytech.code.moteur;

import fr.unice.polytech.code.Affichage;
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
    private Affichage affichage;


    public Tour(int numero, PiocheCartesCitadelles piocheCartesCitadelles, PiocheCartesPersonnage piocheCartesPersonnage, ArrayList<Bot> listeJoueurs,Affichage affichage) {
        this.numero = numero;
        this.piocheCartesCitadelles = piocheCartesCitadelles;
        this.piocheCartesPersonnage = piocheCartesPersonnage;
        this.listeJoueurs = listeJoueurs;
        this.indiceJoueurPossedantCouronne = 0;
        this.personnageDefausseVisible = null;
        this.joueurAyantLeRoi = null;
        this.affichage = affichage;
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
        affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + "Le " + listeJoueurs.get(indiceJoueurPossedantCouronne).getNom() + "\u001B[21m" + "\u001B[0m" + " possède la carte courounne \n" );
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
        for (int numeroAppele = 1; numeroAppele < 9; numeroAppele++) {
            for (Bot joueur : listeJoueurs) {
                if (joueur.getPersonnageACeTour() != null) {
                    if (joueur.getPersonnageACeTour().getNumero() == numeroAppele) {
                        if (numeroAppele == 4) { //Roi
                            this.setJoueurAyantLeRoi(joueur);
                        } else if (numeroAppele == 6) { //Marchand
                            joueur.ajouterPiece(1);
                        }else if(numeroAppele == 7){ //Architecte
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                        }
                        joueur.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
                        joueur.strategieConstruction(piocheCartesCitadelles);
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
        affichage.afficherDetails(personnageDefausseVisible.getNom() + " ne peut pas être choisit pour ce tour.\n");
    }

    public void attributionPersonnageAChaqueJoueur() {
        for (int i = this.indiceJoueurPossedantCouronne; i < listeJoueurs.size(); i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + listeJoueurs.get(i).getNom() + " :" + "\u001B[21m" + "\u001B[0m" +" a le personnage " + listeJoueurs.get(i).getPersonnageACeTour().getNom() + " à ce tour");
        }
        for (int i = 0; i < this.indiceJoueurPossedantCouronne; i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + listeJoueurs.get(i).getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " a le personnage " + listeJoueurs.get(i).getPersonnageACeTour().getNom() + " à ce tour");
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
                affichage.afficherDetails("--------------------------------------------------");
                affichage.afficherDetails("\u001B[1m" + "\u001B[31m"  + "\t\t\t La partie est terminée" + "\u001B[0m" );
                affichage.afficherDetails("--------------------------------------------------");
                return true;
            }
        }
        return false;
    }

    public void strategieEffectuerSpecialite(Bot joueur) {
        if (joueur.getPersonnageACeTour() instanceof Assassin) {
            joueur.strategieAssassin(listeJoueurs, personnageDefausseVisible);
            affichage.afficherDetails("L'assassin effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Voleur) {
            joueur.strategieVoleur(listeJoueurs, personnageDefausseVisible);
            affichage.afficherDetails("Le voleur effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Magicien) {
            joueur.strategieMagicien(listeJoueurs, piocheCartesCitadelles);
            affichage.afficherDetails("Le magicien effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Roi) {
            joueur.strategieRoi();
            affichage.afficherDetails("Le roi effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Eveque) {
            joueur.strategieEveque();
            affichage.afficherDetails("L'évèque effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Marchand) {
            joueur.strategieMarchand();
            affichage.afficherDetails("Le marchand effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Architecte) {
            joueur.strategieArchitecte(piocheCartesCitadelles);
            affichage.afficherDetails("L'architecte effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }else if (joueur.getPersonnageACeTour() instanceof Condottiere) {
            joueur.strategieCondottiere(listeJoueurs);
            affichage.afficherDetails("Le condottiere effectue sa spécialité");
            if (joueur.getVilleDuBot().getNbBatimentsConstruits()!=0)
                affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + "\u001B[21m" + "\u001B[0m" + " a construit : " + joueur.getVilleDuBot().quartiersVilleToString());
            affichage.afficherDetails("\u001B[1m" + "\u001B[32m" + joueur.getNom() + " :" + "\u001B[21m" + "\u001B[0m" + " possède " + joueur.getNbPiece() + " pièces" + "\n");
        }
    }


}