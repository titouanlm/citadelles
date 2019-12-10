package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Laboratoire extends CarteCitadellesAvecPouvoir {

    public Laboratoire(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(CarteCitadellesAvecPouvoir carte, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        for (CarteCitadelles  carteADefausser :joueur.getCartesCitadellesEnMain()){
            if(carteADefausser.getPoint()==1){
                joueur.getCartesCitadellesEnMain().remove(carteADefausser);
                piocheCartesCitadelles.ajouterCarteCitadelles(carteADefausser);
                joueur.ajouterPiece(1);
                break;
            }
        }
    }

}
