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

    public BotAleatoire(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategieConstruction() { //Construit le premier batiment qu'il peut construire
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)) {
                retirerPiece(carteEnMain.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMain); //on ajoute la carte dans la ville
                cartesCitadellesEnMain.remove(carteEnMain); //on retire la carte de la main
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
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
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
            }
        }
    }

    @Override
    public CarteCitadelles choixCartesPiochees(PiocheCartesCitadelles piocheCartesCitadelles, CarteCitadelles cartePiochee1,CarteCitadelles cartePiochee2) {
        CarteCitadelles carteChoisie;
        if(cartePiochee2!=null){
            int choix = (int) (Math.random() * 2);
            if(this.getVilleDuBot().contient("Bibliothèque")) {
                this.ajouterCartesCitadellesDansMain(cartePiochee1);
                this.ajouterCartesCitadellesDansMain(cartePiochee2);
                return null;
            }else if(choix==0){
                carteChoisie = cartePiochee1;
                piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
            }else{
                carteChoisie = cartePiochee2;
                piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
            }
        }else{
            carteChoisie=cartePiochee1;
        }
        return carteChoisie;
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
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage personnageJoueur = this.getPersonnageACeTour();
        if(personnageJoueur instanceof Condottiere){
            ((Condottiere) personnageJoueur).effectuerSpecialiteCondottiere(this);

            int indiceBotVictime;
            do{
                indiceBotVictime = (int)(Math.random()*listeJoueurs.size());
            }while(listeJoueurs.get(indiceBotVictime) == this);
            Bot victime = listeJoueurs.get(indiceBotVictime);
            ((Condottiere) personnageJoueur).detruireQuartierAleatoireEnemie(this, victime, piocheCartesCitadelles, listeJoueurs);
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




