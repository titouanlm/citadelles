package fr.unice.polytech.code;

/** La stratégie des bots pour l'instant est de construire le plus vite possible */

import java.util.ArrayList;

public class Bot {
    private String nom;
    private int nbPiece;
    private ArrayList<CarteCitadelles> cartesCitadellesEnMain;
    private Ville villeDuBot;
    private Personnage personnageACeTour;
    private boolean possedeCouronne;

    public Bot(String nom) {
        this.nom = nom;
        this.nbPiece = 0;
        this.cartesCitadellesEnMain = new ArrayList<>();
        this.villeDuBot = new Ville();
        this.personnageACeTour = null;
        this.possedeCouronne = false;
    }

    public Bot(String nom, Ville villeDuBot) {
        this.nom = nom;
        this.nbPiece = 0;
        this.cartesCitadellesEnMain = new ArrayList<>();
        this.villeDuBot = villeDuBot;
        this.personnageACeTour = null;
        this.possedeCouronne = false;
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
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain.getNom())){ //regarde pour acheter
                retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                System.out.println(this.nom + " a construit le batiment " + carteEnMain.getNom() + " dans sa ville.");
                cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                aConstruit = true;
                break;
            }
        }
        if(aConstruit==false){
            System.out.println(this.nom + " n'a pas pu construire.");
        }
        this.getPersonnageACeTour().effectuerSpecialite();
    }
}
