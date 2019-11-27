package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.Ville;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

public abstract class Bot {
    String nom;
    String couleur;
    public int nbPiece;
    public ArrayList<CarteCitadelles> cartesCitadellesEnMain;
    Ville villeDuBot;
    Personnage personnageACeTour;
    boolean possedeCouronne;
    int nbPoint;
    boolean premierJoueurAFinir;

    public Bot(String nom, String couleur) {
        this.nom = nom;
        this.nbPiece = 0;
        this.cartesCitadellesEnMain = new ArrayList<>();
        this.villeDuBot = new Ville();
        this.personnageACeTour = null;
        this.possedeCouronne = false;
        this.nbPoint = 0;
        this.premierJoueurAFinir= false;
        this.couleur = couleur;
    }

    public String getNom() {
        return nom;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void ajouterPiece(int nbPiece) {
        this.nbPiece += nbPiece;
    }

    public void retirerPiece(int nbPiece) {
        if(this.nbPiece-nbPiece <0){
            this.nbPiece=0;
        }else{
            this.nbPiece-=nbPiece;
        }
    }

    public Ville getVilleDuBot() {
        return villeDuBot;
    }

    public ArrayList<CarteCitadelles> getCartesCitadellesEnMain() {
        return cartesCitadellesEnMain;
    }

    public void ajouterCartesCitadellesDansMain(CarteCitadelles cartesCitadellesAAjouter) {
        if(cartesCitadellesAAjouter!=null){
            this.cartesCitadellesEnMain.add(cartesCitadellesAAjouter);
        }else{
            System.out.println("Impossible : vous ajoutez une carte null en main");
        }
    }

    public boolean contientDansSaMain(CarteCitadelles carteATester){
        for(CarteCitadelles c : cartesCitadellesEnMain){
            if(c.getNom().equals(carteATester.getNom())){
                return true;
            }
        }
        return false;
    }

    public String getCouleur() {
        return couleur;
    }

    public int getNbPoint() {
        return nbPoint;
    }

    public void setNbPoint(int nbPoint) {
        this.nbPoint += nbPoint;
    }

    public boolean estPremierJoueurAFinir() {
        return premierJoueurAFinir;
    }

    public void setPremierJoueurAFinir(boolean premierJoueurAFinir) {
        this.premierJoueurAFinir = premierJoueurAFinir;
    }

    public Personnage getPersonnageACeTour() {
        return personnageACeTour;
    }

    public void setPersonnageACeTour(Personnage personnageACeTour) {
        this.personnageACeTour = personnageACeTour;
    }

    public boolean possedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(boolean possedeCouronne) {
        this.possedeCouronne = possedeCouronne;
    }

    public String cartesEnMainToString(){
        String cartes="";
        for(CarteCitadelles c : cartesCitadellesEnMain){
            cartes+= c.getNom()+", ";
        }
        return cartes;
    }

    //Recherche le batiment de plus grande valeur qu'il peut construire
    public CarteCitadelles rechercheCartePlusHauteValeurConstruisable() {
        int valeurCartePlusHauteEnMain = 0;
        CarteCitadelles quartierAConstruire = null;
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)
                    && valeurCartePlusHauteEnMain < carteEnMain.getPoint()) {
                valeurCartePlusHauteEnMain = carteEnMain.getPoint();
                quartierAConstruire = carteEnMain;
            }
        }
        return quartierAConstruire;
    }

    public abstract void strategieConstruction(PiocheCartesCitadelles piocheCartesCitadelles);

    public abstract void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible);

    public abstract void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles);

    public abstract void strategieAssassin(ArrayList<Bot> listeJoueurs);

    public abstract void strategieVoleur(ArrayList<Bot> listeJoueurs,Bot botVoleur);

    public abstract void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles pioche);

    public abstract void strategieRoi();

    public abstract void strategieEveque();

    public abstract void strategieMarchand();

    public abstract void strategieArchitecte(PiocheCartesCitadelles piocheCartesCitadelles);

    public abstract void strategieCondottiere(ArrayList<Bot> listeJoueurs);
}
