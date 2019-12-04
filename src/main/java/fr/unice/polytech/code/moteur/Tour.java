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
                        affichage.afficherDetails("\n" +joueur.getCouleur()+joueur.getNom());
                        affichage.afficherDetails(("Personnage à ce tour : " + joueur.getPersonnageACeTour().getNom()));
                        if (numeroAppele == 4) { //Roi
                            this.setJoueurAyantLeRoi(joueur);
                        } else if (numeroAppele == 6) { //Marchand
                            joueur.ajouterPiece(1);
                            affichage.afficherDetails("+1 pièce car il est le Marchand ce tour-ci!");
                        }else if(numeroAppele == 7){ //Architecte
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
                            affichage.afficherDetails("Pioche 2 cartes car il est l'Architecte ce tour-ci!");
                        }
                        affichage.afficherDetails("Possède " + joueur.getNbPiece() + " pièces.\n" + "Cartes dans sa main : \n"+ joueur.cartesEnMainToString() +  "\u001B[0m"+ joueur.getCouleur());

                        joueur.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
                        joueur.strategieConstruction(piocheCartesCitadelles);
                        this.strategieEffectuerSpecialite(joueur);
                        this.estJoueurAyantFinisEnPremier(joueur);

                        affichage.afficherDetails(joueur.getCouleur() + "Quartiers construits dans sa ville : ");
                        affichage.afficherDetails(joueur.getVilleDuBot().quartiersVilleToString());
                        affichage.afficherDetails(joueur.getCouleur() +"Possède " + joueur.getNbPiece() + " pièces.\n" + "\u001B[0m");
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
        affichage.afficherDetails(listeJoueurs.get(indiceJoueurPossedantCouronne).getCouleur() + listeJoueurs.get(indiceJoueurPossedantCouronne).getNom() + " possède la carte couronne. \n" );

    }

    public void defausserCartesPersonnagePourLeTour() {
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        Personnage personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoirement();

        while (personnageDefausseVisible.getNumero() == 4) {
            piocheCartesPersonnage.ajouterCartePersonnage(personnageDefausseVisible);
            personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        }
        this.setPersonnageDefausseVisible(personnageDefausseVisible);
        affichage.afficherDetails("\u001B[1m" + personnageDefausseVisible.getNom()+ "\u001B[0m"+ " ne peut pas être choisi pour ce tour.");
    }

    public void attributionPersonnageAChaqueJoueur() {
        for (int i = this.indiceJoueurPossedantCouronne; i < listeJoueurs.size(); i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);

            affichage.afficherDetails(listeJoueurs.get(i).getCouleur() +  listeJoueurs.get(i).getNom() + " a choisi le personnage " + "\u001B[1m" +listeJoueurs.get(i).getPersonnageACeTour().getNom() + ".\u001B[0m");
        }
        for (int i = 0; i < this.indiceJoueurPossedantCouronne; i++) {
            listeJoueurs.get(i).choixDuPersonnagePourLeTour(piocheCartesPersonnage, personnageDefausseVisible);
            affichage.afficherDetails(listeJoueurs.get(i).getCouleur() +  listeJoueurs.get(i).getNom() + " a choisi le personnage " + "\u001B[1m" +listeJoueurs.get(i).getPersonnageACeTour().getNom()  +".\u001B[0m");
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
        }else if (joueur.getPersonnageACeTour() instanceof Voleur) {
            joueur.strategieVoleur(listeJoueurs, personnageDefausseVisible);
        }else if (joueur.getPersonnageACeTour() instanceof Magicien) {
            joueur.strategieMagicien(listeJoueurs, piocheCartesCitadelles);
            affichage.afficherDetails("Cartes dans sa main : \n"+ joueur.cartesEnMainToString() +  "\u001B[0m"+ joueur.getCouleur());
        }else if (joueur.getPersonnageACeTour() instanceof Roi) {
            joueur.strategieRoi();
        }else if (joueur.getPersonnageACeTour() instanceof Eveque) {
            joueur.strategieEveque();
        }else if (joueur.getPersonnageACeTour() instanceof Marchand) {
            joueur.strategieMarchand();
        }else if (joueur.getPersonnageACeTour() instanceof Architecte) {
            joueur.strategieArchitecte(piocheCartesCitadelles);
        }else if (joueur.getPersonnageACeTour() instanceof Condottiere) {
            joueur.strategieCondottiere(listeJoueurs);
        }
    }

}