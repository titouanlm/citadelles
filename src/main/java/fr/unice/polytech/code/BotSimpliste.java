package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotSimpliste extends Bot {

    public BotSimpliste(String nom, String couleur) {
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
        this.strategieConstruitDesQuilPeut();

    }

    // Aléatoire
    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes()>0){
            return (int) Math.round(Math.random());
        }else{
            return 1;
        }
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


}
