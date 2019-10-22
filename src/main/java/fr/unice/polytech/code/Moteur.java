package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

public class Moteur {

    private PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();

    Moteur(){}

    void lancerUnePartie(ArrayList<Bot> listeJoueurs) {
        this.implementerCartesCitadelles();
        this.implementerCartesPersonnage();
        this.initialiserPartie(listeJoueurs);

    }

    private void implementerCartesCitadelles() {
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadelles(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadelles(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[2] = new CarteCitadelles(3,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[3] = new CarteCitadelles(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        //... pour les 65 cartes

        for(int i=0; i<65;i++){
            piocheCartesCitadelles.ajouterCarteCitadelles(cc[i]);
        }
    }

    private void implementerCartesPersonnage() {
        Personnage[] cp = new Personnage[8];
        cp[0] = new Assassin();
        cp[1] = new Voleur();
        cp[2] = new Magicien();
        cp[3] = new Roi();
        cp[4] = new Eveque();
        cp[5] = new Marchand();
        cp[6] = new Architecte();
        cp[7] = new Condottiere();

        for(int i=0; i<8;i++){
            piocheCartesPersonnage.ajouterCartePersonnage(cp[i]);
        }
    }

    private void initialiserPartie(ArrayList<Bot> listeJoueurs) {
        piocheCartesCitadelles.melanger();
        piocheCartesPersonnage.melanger();

        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
            }
        }

        this.attributionCarteCouronneDebutPartie(listeJoueurs);

    }

    private void attributionCarteCouronneDebutPartie(ArrayList<Bot> listeJoueurs) {

    }


}
