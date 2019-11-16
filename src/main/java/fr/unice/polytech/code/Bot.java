package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

public abstract class Bot {
    String nom;
    String couleur;
    int nbPiece;
    ArrayList<CarteCitadelles> cartesCitadellesEnMain;
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
        this.cartesCitadellesEnMain.add(cartesCitadellesAAjouter);
    }

    public boolean contientDansSaMain(CarteCitadelles carteATester){
        for(CarteCitadelles c : cartesCitadellesEnMain){
            if(c.getNom().equals(carteATester.getNom())){
                return true;
            }
        }
        return false;
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

    public abstract void strategie(PiocheCartesCitadelles piocheCartesCitadelles);

    public abstract void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible);

    public abstract void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles);
}
