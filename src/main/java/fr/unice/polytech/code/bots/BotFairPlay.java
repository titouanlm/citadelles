package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend les meilleurs décisions possibles dans le jeu en appliquant les vraies règles
 */
public class BotFairPlay extends Bot {

    public BotFairPlay(String nom, String couleur, Affichage affichage) {
        super(nom, couleur,affichage);
    }

    @Override
    public void strategieConstruction() { //Construit le plus gros batiment qu'il peut
        CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
        if (carteEnMainDePlusHauteValeur != null) {
            this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
            this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
            this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
            affichage.afficherDetails(this.getCouleur() + "Construit le quartier " + carteEnMainDePlusHauteValeur.getCouleur() + carteEnMainDePlusHauteValeur.getNom() + "\u001B[0m" + this.getCouleur() + " dans sa ville.");
        }
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(this));
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
            affichage.afficherDetails("Choisit de prendre 2 pièces.");
        } else {
            CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
            CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();
            if(this.getVilleDuBot().contient("Observatoire")){
                CarteCitadelles cartePiochee3 = piocheCartesCitadelles.piocher();
                CarteCitadelles carteChoisie =  this.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);
                CarteCitadelles carteChoisieFinal =  this.choixCartesPiochees(piocheCartesCitadelles, cartePiochee3,carteChoisie );
                this.ajouterCartesCitadellesDansMain(carteChoisieFinal);
            }else{
                CarteCitadelles carteChoisie =  this.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);
                this.ajouterCartesCitadellesDansMain(carteChoisie);
                if(carteChoisie!=null){
                    affichage.afficherDetails("Choisit de prendre : " + carteChoisie.getNom());
                }
            }

        }
    }

    @Override
    public CarteCitadelles choixCartesPiochees(PiocheCartesCitadelles piocheCartesCitadelles,CarteCitadelles cartePiochee1,CarteCitadelles cartePiochee2) {
        CarteCitadelles carteChoisie;
        if(cartePiochee2!=null){
            affichage.afficherDetails("Pioche 2 cartes : " + cartePiochee1.getNom() + " et " + cartePiochee2.getNom());

            if(this.getVilleDuBot().contient("Bibliothèque")) {
                this.ajouterCartesCitadellesDansMain(cartePiochee1);
                this.ajouterCartesCitadellesDansMain(cartePiochee2);
                return null;
            }else if (!this.getVilleDuBot().contient(cartePiochee1) && !this.getVilleDuBot().contient(cartePiochee2) &&
                    !this.contientDansSaMain(cartePiochee1) && !this.contientDansSaMain(cartePiochee2)) {
                if (cartePiochee1.getCouleur().equals(CouleurCarteCitadelles.VIOLET) && cartePiochee2.getCouleur().equals(CouleurCarteCitadelles.VIOLET)) {
                    carteChoisie = cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                } else if (cartePiochee1.getCouleur().equals(CouleurCarteCitadelles.VIOLET)) { //Prend la première carte si elle est violette
                    carteChoisie = cartePiochee1;
                } else if (cartePiochee2.getCouleur().equals(CouleurCarteCitadelles.VIOLET)) {//Prend la deuxième carte si elle est violette
                    carteChoisie = cartePiochee2;
                } else {
                    carteChoisie = cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                }
            } else if (!this.getVilleDuBot().contient(cartePiochee1) && !this.contientDansSaMain(cartePiochee1)) { //Prend la première carte s'il possède déjà la 2ème
                carteChoisie = cartePiochee1;
            } else if (!this.getVilleDuBot().contient(cartePiochee2) && !this.contientDansSaMain(cartePiochee2)) { //Prend la 2ème carte s'il possède déjà la première
                carteChoisie = cartePiochee2;
            } else { //Prend la première carte si il possède déjà les deux
                carteChoisie = cartePiochee1;
            }
            //On remet dans la pioche la carte que l'on a pas choisi
            if (carteChoisie == cartePiochee1) {
                piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
            } else {
                piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
            }
        }else{
            carteChoisie=cartePiochee1;
        }
        return carteChoisie;
    }

    @Override
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int indicePersonnageAssassiner;
        do{
            indicePersonnageAssassiner = ((int)(Math.random()*8))+1;
        }while(indicePersonnageAssassiner == this.getPersonnageACeTour().getNumero() || indicePersonnageAssassiner==personnageDefausse.getNumero());

        Personnage p = this.getPersonnageACeTour();
        ((Assassin) p).effectuerSpecialiteAssassin( this.listePersonnages(indicePersonnageAssassiner), listeJoueurs);
    }

    @Override
    public void strategieVoleur(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int indicePersonnageAVoler;
        do{
            indicePersonnageAVoler = ((int)(Math.random()*8))+1;
        }while(indicePersonnageAVoler == this.getPersonnageACeTour().getNumero() || indicePersonnageAVoler==personnageDefausse.getNumero());
        Personnage p = this.getPersonnageACeTour();
        ((Voleur) p).effectuerSpecialiteVoleur(this, this.listePersonnages(indicePersonnageAVoler), listeJoueurs);
    }

    @Override
    public void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles pioche) {
        Personnage magicien = this.getPersonnageACeTour();
        int nombreCarteMaxMainPersonne = -1;
        int b=0;
        ArrayList<CarteCitadelles> cartesAEchanger = new ArrayList<>();
        for (int i =0 ; i < listeJoueurs.size(); i++) {
            if (listeJoueurs.get(i) != this) {
                if (listeJoueurs.get(i).getCartesCitadellesEnMain().size() > nombreCarteMaxMainPersonne) {
                    nombreCarteMaxMainPersonne=listeJoueurs.get(i).getCartesCitadellesEnMain().size();
                    b = i;
                }
            }
        }
        for (int p = 0; p < this.getCartesCitadellesEnMain().size(); p++) {
            if (this.getCartesCitadellesEnMain().get(p).getPoint() < 4) {
                cartesAEchanger.add(this.getCartesCitadellesEnMain().get(p));
            }
        }
        if (nombreCarteMaxMainPersonne > 2) {
            ((Magicien) magicien).echangerCartesAvecUnJoueur(this, listeJoueurs.get(b));
        }
        else {
            ((Magicien) magicien).echangerCartesAvecPioche(this, pioche, cartesAEchanger);
        }
    }

    @Override
    public void strategieRoi() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Roi){
            ((Roi) personnageJoueur).effectuerSpecialiteRoi(this);
        }
    }

    @Override
    public void strategieEveque() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Eveque){
            ((Eveque) personnageJoueur).effectuerSpecialiteEveque(this);
        }
    }

    @Override
    public void strategieMarchand() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Marchand){
            ((Marchand) personnageJoueur).effectuerSpecialiteMarchand(this);
        }
    }

    @Override
    public void strategieArchitecte() {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Architecte){
            ((Architecte) personnageJoueur).effectuerSpecialiteArchitecte(this);
        }
    }

    @Override
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Condottiere){
            ((Condottiere) personnageJoueur).effectuerSpecialiteCondottiere(this);
            int nbPointMax=0;
            Bot victime=null;
            for(Bot b : listeJoueurs){
                if(b!=this && b.getVilleDuBot().getNbTotalPoint()>nbPointMax){
                    nbPointMax=b.getVilleDuBot().getNbTotalPoint();
                    victime=b;
                }
            }
            ((Condottiere) personnageJoueur).detruirePlusGrosQuartierEnemie(this, victime, piocheCartesCitadelles, listeJoueurs);
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0 && getNbPiece() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }
}
