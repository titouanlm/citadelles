package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Laboratoire extends CarteCitadellesAvecPouvoir {

    public Laboratoire(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(int numero, CouleurCarteCitadelles couleur, String nom, int point, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        CarteCitadelles carteAdefausser=joueur.getCartesCitadellesEnMain().get(0);
        for (CarteCitadelles  carte :joueur.getCartesCitadellesEnMain()){
            if(carte.getPoint()<carteAdefausser.getPoint()){
                carteAdefausser=carte;
            }
        }
        joueur.getCartesCitadellesEnMain().remove(carteAdefausser);
        joueur.ajouterPiece(1);

    }

}
