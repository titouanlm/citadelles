package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.*;

import java.util.ArrayList;

/**
 * Prend toutes ses décisions aléatoirement
 */
public class BotAleatoire extends Bot {

    public BotAleatoire(String nom, String couleur, Affichage affichage) {
        super(nom, couleur, affichage);
    }

    @Override
    public void strategieConstruction() { //Construit le premier batiment qu'il peut construire
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)) {
                retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
                affichage.afficherDetails(this.getCouleur() + "Construit le quartier " + carteEnMain.getCouleur() + carteEnMain.getNom() + "\u001B[0m" + this.getCouleur() + " dans sa ville.");
                break;
            }
        }
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        Personnage pChoisi = piocheCartesPersonnage.piocherPersonnageAleatoirement();
        this.setPersonnageACeTour(pChoisi);
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) { //Non aléatoire
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
            affichage.afficherDetails("Choisit de prendre 2 pièces.");
        } else {
            CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
            CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();

            affichage.afficherDetails("Pioche 2 cartes : " + cartePiochee1.getNom() + " et " + cartePiochee2.getNom());
            CarteCitadelles carteChoisie;

            if(cartePiochee2!=null){
                int choix = (int) (Math.random() * 2);
                if(choix==0){
                    carteChoisie = cartePiochee1;
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
                }else{
                    carteChoisie = cartePiochee2;
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
                }
            }else{
                carteChoisie=cartePiochee1;
            }

            this.ajouterCartesCitadellesDansMain(carteChoisie);
            affichage.afficherDetails("Choisit de prendre : " + carteChoisie.getNom());
        }
    }

    @Override
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int personnageAssassiner;
        do{
            personnageAssassiner = (int)(Math.random()*8);
        }while(personnageAssassiner == this.getPersonnageACeTour().getNumero());

        Personnage p = this.getPersonnageACeTour();
        ((Assassin) p).effectuerSpecialiteAssassin( this.listePersonnages(personnageAssassiner), listeJoueurs);
    }

    @Override
    public void strategieVoleur(ArrayList<Bot> listeJoueurs, Personnage personnageDefausse) {
        int indicePersonnageAVoler;
        do{
            indicePersonnageAVoler = (int)(Math.random()*8);
        }while(indicePersonnageAVoler == this.getPersonnageACeTour().getNumero() || indicePersonnageAVoler == 0); // 0 numéro Associé à l'Assassin

        Personnage p = this.getPersonnageACeTour();
        ((Voleur) p).effectuerSpecialiteVoleur( this, this.listePersonnages(indicePersonnageAVoler), listeJoueurs);
    }

    @Override
    public void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles pioche) {
        Personnage magicien = this.getPersonnageACeTour();
        if ((int) (Math.random() * 2) == 0) {
            ((Magicien) magicien).echangerCartesAvecUnJoueur(this, listeJoueurs.get((int) (Math.random() * listeJoueurs.size())));
        }else {
            int nombreCartesAleatoireAEchanger= (int) (Math.random() * this.getCartesCitadellesEnMain().size());
            ArrayList<CarteCitadelles> cartesAEchanger = new ArrayList<>();
            for (int i=0; i<nombreCartesAleatoireAEchanger;i++){
                cartesAEchanger.add(cartesCitadellesEnMain.get(i));
            }
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
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Condottiere){
            ((Condottiere) personnageJoueur).effectuerSpecialiteCondottiere(this);

            int indiceBotVictime;
            do{
                indiceBotVictime = (int)(Math.random()*listeJoueurs.size());
            }while(listeJoueurs.get(indiceBotVictime) == this);
            Bot victime = listeJoueurs.get(indiceBotVictime);
            ((Condottiere) personnageJoueur).detruireQuartierAleatoireEnemie(this, victime);
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0) {
            return (int) Math.round(Math.random());
        } else {
            return 1;
        }
    }
}




