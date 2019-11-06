package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotIntelligent extends Bot {

    public BotIntelligent(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage) {
        this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageAleatoire());
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
        } else {
            this.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());// Peut être déterminée par le joueur
        }
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
        this.strategieConstruit();


    }


    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes()>0 && getNbPiece()>=5){
            return 0/*(int) Math.round(Math.random())*/;
        }else{
            return 1;
        }
    }

    public CarteCitadelles rechercheCarteALaPlusHauteValeurConstruisable() { //Construire le plus gros batiment d'un coup
        int quartierALaPlusHauteValeur=0;
        CarteCitadelles quartierAConstruire=null;
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain.getNom())
                    && quartierALaPlusHauteValeur<carteEnMain.getPoint()) {
                quartierALaPlusHauteValeur=carteEnMain.getPoint();
                quartierAConstruire=carteEnMain;
            }
        }
        return quartierAConstruire;
    }

    public void strategieConstruit() { //Construire le plus gros batiment d'un coup
        boolean aConstruit =false;
        CarteCitadelles carteEnMainDePlusGrande = this.rechercheCarteALaPlusHauteValeurConstruisable();
        if (carteEnMainDePlusGrande!=null){
                retirerPiece(carteEnMainDePlusGrande.getPoint()); //on retire les pieces
                villeDuBot.construireBatiment(carteEnMainDePlusGrande); //on ajoute la carte dans la ville
                //System.out.println(this.nom + " a construit le batiment " + quartierÀConstruire.getNom() + " dans sa ville.");
                cartesCitadellesEnMain.remove(carteEnMainDePlusGrande); //on retire la carte de la main
                aConstruit = true;
        }

        if(!aConstruit){
            //System.out.println(this.nom + " n'a pas pu construire.");
        }
        //this.getPersonnageACeTour().effectuerSpecialite();
    }

}
