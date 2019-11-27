package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Observatoire extends CarteCitadellesAvecPouvoir {
    public Observatoire(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(CarteCitadellesAvecPouvoir carte , Bot joueur,PiocheCartesCitadelles piocheCartesCitadelles) {

        CarteCitadelles cartechoisie;
        CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee3 = piocheCartesCitadelles.piocher();
        if(cartePiochee1.getPoint()<cartePiochee2.getPoint()){
            if(cartePiochee2.getPoint()<cartePiochee3.getPoint()){
                cartechoisie=cartePiochee3;
            }
            else {
                cartechoisie=cartePiochee2;}

        }
        else if(cartePiochee1.getPoint()<cartePiochee3.getPoint()){
            cartechoisie=cartePiochee3;
        }
        else {
            cartechoisie=cartePiochee1;}
    }
}

