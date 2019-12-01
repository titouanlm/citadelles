package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Observatoire extends CarteCitadellesAvecPouvoir {
    public Observatoire(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(CarteCitadellesAvecPouvoir carte , Bot joueur,PiocheCartesCitadelles piocheCartesCitadelles) {


    }
}

