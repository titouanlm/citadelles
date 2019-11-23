package fr.unice.polytech.code.bots;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

/**
 * Prend les meilleurs décisions possibles dans le jeu en appliquant les vraies règles
 */
public class BotFairPlay extends Bot {

    public BotFairPlay(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategieConstruction(PiocheCartesCitadelles piocheCartesCitadelles) { //Construit le plus gros batiment qu'il peut
        int i = 1;
        if (this.personnageACeTour instanceof Architecte) {
            i = 3;
        }
        while (i > 0) {
            CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
            if (carteEnMainDePlusHauteValeur != null) {
                this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
                this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
                this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
            }
            i--;
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
        } else {
            CarteCitadelles carteChoisie;
            CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
            CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();

            if(cartePiochee2!=null){
                // On teste si le joueur n'a pas déjà construit les quartiers ou les possède déjà dans sa main
                if(!this.getVilleDuBot().contient(cartePiochee1) && !this.getVilleDuBot().contient(cartePiochee2) &&
                        !this.contientDansSaMain(cartePiochee1) && !this.contientDansSaMain(cartePiochee2)){

                    if(cartePiochee1.getCouleur().toString().equals("VIOLET") && cartePiochee2.getCouleur().toString().equals("VIOLET")){
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }else if(cartePiochee1.getCouleur().toString().equals("VIOLET")){ //Prend la première carte si elle est violette
                        carteChoisie=cartePiochee1;
                    }else if(cartePiochee2.getCouleur().toString().equals("VIOLET")){//Prend la deuxième carte si elle est violette
                        carteChoisie=cartePiochee2;
                    }else{
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }
                }else if(!this.getVilleDuBot().contient(cartePiochee1) && !this.contientDansSaMain(cartePiochee1)){ //Prend la première carte s'il possède déjà la 2ème
                    carteChoisie=cartePiochee1;
                }else if(!this.getVilleDuBot().contient(cartePiochee2) && !this.contientDansSaMain(cartePiochee2)){ //Prend la 2ème carte s'il possède déjà la première
                    carteChoisie=cartePiochee2;
                }else{ //Prend la première carte si il possède déjà les deux
                    carteChoisie=cartePiochee1;
                }

                this.ajouterCartesCitadellesDansMain(carteChoisie);

                //On remet dans la pioche la carte que l'on a pas choisi
                if(carteChoisie==cartePiochee1){
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
                }else{
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
                }
            }else{
                carteChoisie=cartePiochee1;
                this.ajouterCartesCitadellesDansMain(carteChoisie);
            }
        }
    }

    @Override // A implémenter (Aléatoire pour l'instant)
    public void strategieAssassin(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {
        int indiceBotQueLonVaAssassiner=(int)(Math.random()*listeJoueurs.size());
        Bot botQueLonVaAssassiner = listeJoueurs.get(indiceBotQueLonVaAssassiner);
        Personnage assassin = this.getPersonnageACeTour();
        assassin.effectuerSpecialite(this, botQueLonVaAssassiner);
    }

    @Override // A implémenter (Aléatoire pour l'instant)
    public void strategieVoleur(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {
        int indiceBotAVoler = (int) (Math.random() * listeJoueurs.size());
        Bot botAVoler = listeJoueurs.get(indiceBotAVoler);
        this.getPersonnageACeTour().effectuerSpecialite(this,botAVoler);
    }

    @Override // A implémenter
    public void strategieMagicien(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {

    }

    @Override
    public void strategieRoi(PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage roi = this.getPersonnageACeTour();
        roi.effectuerSpecialite(this, null);
    }

    @Override
    public void strategieEveque(PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage eveque = this.getPersonnageACeTour();
        eveque.effectuerSpecialite(this, null);
    }

    @Override
    public void strategieMarchand(PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage marchand = this.getPersonnageACeTour();
        marchand.effectuerSpecialite(this, null);
    }

    @Override
    public void strategieArchitecte(PiocheCartesCitadelles piocheCartesCitadelles) {
        Personnage architecte = this.getPersonnageACeTour();
        architecte.effectuerSpecialite(this, null);
    }

    @Override // A implémenter (Aléatoire pour l'instant)
    public void strategieCondottiere(ArrayList<Bot> listeJoueurs, PiocheCartesCitadelles piocheCartesCitadelles) {
        int indiceBotVictime = (int)(Math.random()*listeJoueurs.size());
        Bot victime = listeJoueurs.get(indiceBotVictime);
        Personnage condottiere = this.getPersonnageACeTour();
        condottiere.effectuerSpecialite(this, victime);
    }


    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0 && getNbPiece() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }
}
