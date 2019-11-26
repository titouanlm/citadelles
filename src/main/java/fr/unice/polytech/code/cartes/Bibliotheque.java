package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Bibliotheque extends CarteCitadellesAvecPouvoir {
    public Bibliotheque(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(int numero, CouleurCarteCitadelles couleur, String nom, int point, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {

    }
}
