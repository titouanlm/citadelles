package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Manufacture extends CarteCitadellesAvecPouvoir {
    public Manufacture(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(int numero, CouleurCarteCitadelles couleur, String nom, int point, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        joueur.retirerPiece(3);
        for(int i=0;i<3;i++){
            joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());

        }

    }
}

