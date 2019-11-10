package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

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
        if (this.verifierFinPartie()){
            return true;
        }else{
            piocheCartesPersonnage.reinitialiser();
            piocheCartesPersonnage.melanger();
            if(listeJoueurs.get(this.indiceJoueurPossedantCouronne)!=this.joueurAyantLeRoi || this.joueurAyantLeRoi==null){
                listeJoueurs.get(this.indiceJoueurPossedantCouronne).setPossedeCouronne(false);
            }
            return false;
        }
    }

    public void appelerJoueursDansLOrdre() { //
        for (int i=1; i < 9 ; i++) {
            for (Bot joueur : listeJoueurs) {
                if (joueur.getPersonnageACeTour() != null) {
                    if (joueur.getPersonnageACeTour().getNumero() == i) {
                        if (i == 4) {
                            this.setJoueurAyantLeRoi(joueur);
                        } else if (i == 6) {
                            joueur.ajouterPiece(1);
                        }
                        //joueur.strategie0(piocheCartesPersonnage);
                        joueur.strategie(piocheCartesCitadelles);


                        this.estJoueurAyantFinisEnPremier(joueur); //besoin d'explication sur ce point
                        break;
                    }
                }
            }
        }
    }



    public void setIndiceJoueurPossedantCourrone() {
        int indiceJoueurPossedantCouronne = 10;
        for(int i= 0; i<listeJoueurs.size(); i++){
            if(listeJoueurs.get(i).possedeCouronne()){
                indiceJoueurPossedantCouronne=i;
                break;
            }
        }
        if(indiceJoueurPossedantCouronne==10){
            indiceJoueurPossedantCouronne = (int)(Math.random()*(listeJoueurs.size()));
            listeJoueurs.get(indiceJoueurPossedantCouronne).setPossedeCouronne(true);
        }
         this.indiceJoueurPossedantCouronne = indiceJoueurPossedantCouronne;
    }

    public void defausserCartesPersonnagePourLeTour() {
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        Personnage personnageDefausseVisible= piocheCartesPersonnage.piocherPersonnageAleatoirement();

        while(personnageDefausseVisible.getNumero()==4){
            piocheCartesPersonnage.ajouterCartePersonnage(personnageDefausseVisible);
            personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        }

        //System.out.println(personnageDefausseVisible.getNom() + " ne peut être choisit pour ce tour.\n");
        this.setPersonnageDefausseVisible(personnageDefausseVisible);
    }


    // Peut être déterminée par le joueur  | faire 2 méthodes stratégie : l'une pour le choix du personnage l'autre pour le reste

    public void attributionPersonnageAChaqueJoueur() {

        for(int i=this.indiceJoueurPossedantCouronne; i<listeJoueurs.size(); i++){
            if (setUpTypeBot < listeJoueurs.size()){
                listeJoueurs.get(i).setUpTypeBot();
                setUpTypeBot+=1;
            }
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
                //listeJoueurs.get(i).choixDuPersonnagePourLeTour();
            }
            else {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement());
            }
        }
        for(int i=0; i<this.indiceJoueurPossedantCouronne; i++){
            if (listeJoueurs.get(i).getTypedubot() == "Bête") {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoirement());
                //listeJoueurs.get(i).choixDuPersonnagePourLeTour();
            }
            else {
                listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement());
            }
        }
    }

    public boolean estJoueurAyantFinisEnPremier(Bot joueur) {
        for (Bot j : listeJoueurs) {
            if(j.estPremierJoueurAFinir()){
                return false;
            }
        }
        if(joueur.getVilleDuBot().getNbBatimentsConstruits()==8){
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
}
