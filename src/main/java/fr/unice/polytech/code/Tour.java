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

    public void appelerJoueursDansLOrdre() {
        for (int i=1; i < 9 ; i++) {
            for (Bot joueur : listeJoueurs) {
                if(joueur.getPersonnageACeTour().getNumero()==i) {
                    if(i==4){
                        this.setJoueurAyantLeRoi(joueur);
                    }else if(i==6){
                        joueur.ajouterPiece(1);
                    }

                    joueur.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);

                    /*
                    System.out.println(joueur.getCouleur() + joueur.getNom() + " possède " + joueur.getNbPiece() + " pièces.");
                    String cartesEnMain = "";
                    for (CarteCitadelles carteEnMain : joueur.getCartesCitadellesEnMain()) {
                        cartesEnMain += carteEnMain.getNom() + ", ";
                    }
                    System.out.println(joueur.getNom() + " possède les cartes " + cartesEnMain + " dans sa main.");
                    */

                    joueur.strategie();
                    this.estJoueurAyantFinisEnPremier(joueur);
                    break;
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
        piocheCartesPersonnage.piocherPersonnageAleatoire();
        Personnage personnageDefausseVisible= piocheCartesPersonnage.piocherPersonnageAleatoire();

        while(personnageDefausseVisible.getNumero()==4){
            piocheCartesPersonnage.ajouterCartePersonnage(personnageDefausseVisible);
            personnageDefausseVisible = piocheCartesPersonnage.piocherPersonnageAleatoire();
        }

        //System.out.println(personnageDefausseVisible.getNom() + " ne peut être choisit pour ce tour.\n");
        this.setPersonnageDefausseVisible(personnageDefausseVisible);
    }

    // Peut être déterminée par le joueur
    public void attributionPersonnageAChaqueJoueur() {
        for(int i=this.indiceJoueurPossedantCouronne; i<listeJoueurs.size(); i++){
            listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoire());
            //listeJoueurs.get(i).choixDuPersonnagePourLeTour();
        }
        for(int i=0; i<this.indiceJoueurPossedantCouronne; i++){
            listeJoueurs.get(i).setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoire());
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
