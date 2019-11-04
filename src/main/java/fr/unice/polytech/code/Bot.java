package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

public abstract class Bot {
    private String nom;
    private String couleur;
    private int nbPiece;
    private ArrayList<CarteCitadelles> cartesCitadellesEnMain;
    private Ville villeDuBot;
    private Personnage personnageACeTour;
    private boolean possedeCouronne;
    private int nbPoint;
    private boolean premierJoueurAFinir;

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

    public void strategieConstruitDesQuilPeut() {
        boolean aConstruit =false;
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain.getNom())){
                retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                //System.out.println(this.nom + " a construit le batiment " + carteEnMain.getNom() + " dans sa ville.");
                cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                aConstruit = true;
                break;
            }
        }
        if(!aConstruit){
            //System.out.println(this.nom + " n'a pas pu construire.");
        }
        //this.getPersonnageACeTour().effectuerSpecialite();
    }

    public abstract void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage);

    public abstract void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles);

    public abstract void strategie();

}
