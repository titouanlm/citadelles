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

    public void achatBatiment(){
        for (int i = 0; i< cartesCitadellesEnMain.size(); i++) {

            if (nbPiece >= cartesCitadellesEnMain.get(i).getPoint()){ //regarde pour acheter

                retirerPiece(cartesCitadellesEnMain.get(i).getPoint()); //on retire les pieces

                villeDuBot.construireBatiment(cartesCitadellesEnMain.get(i)); //on ajoute la carte dans la ville

                cartesCitadellesEnMain.remove(cartesCitadellesEnMain.get(i)); //on retire la carte de la main

            }
        }
    }

    public void godstrat() {
        achatBatiment();
    }
}
