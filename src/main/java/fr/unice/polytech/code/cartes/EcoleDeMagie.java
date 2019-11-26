package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.personnages.Condottiere;
import fr.unice.polytech.code.personnages.Eveque;
import fr.unice.polytech.code.personnages.Marchand;
import fr.unice.polytech.code.personnages.Roi;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class EcoleDeMagie extends CarteCitadellesAvecPouvoir {
    public EcoleDeMagie(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(int numero, CouleurCarteCitadelles couleur, String nom, int point, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        if(joueur.getPersonnageACeTour() instanceof Roi){
            this.setCouleur(CouleurCarteCitadelles.JAUNE);
        }
        else if(joueur.getPersonnageACeTour() instanceof Eveque){
            this.setCouleur(CouleurCarteCitadelles.BLEU);
        }
        else if(joueur.getPersonnageACeTour() instanceof Marchand){
            this.setCouleur(CouleurCarteCitadelles.VERT);
        }
        else if(joueur.getPersonnageACeTour() instanceof Condottiere){
            this.setCouleur(CouleurCarteCitadelles.ROUGE);
        }

    }
}
