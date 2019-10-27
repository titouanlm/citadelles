package fr.unice.polytech.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArbitreTest {
    @Test
    void testAfficherGagnant(){
        Arbitre arbitre= new Arbitre();
        CarteCitadelles c1 = new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1);
        CarteCitadelles c2 = new CarteCitadelles(5,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        CarteCitadelles c3 = new CarteCitadelles(8,CouleurCarteCitadelles.BLEU, "Menastère", 3 );
        CarteCitadelles c4= new CarteCitadelles(9,CouleurCarteCitadelles.BLEU, "Menastère", 3 );

        Ville v1 = new Ville();
        Ville v2 = new Ville();

        //PiocheCartesCitadelles p1= new PiocheCartesCitadelles();
        //p1.ajouterCarteCitadelles(c1);
        // p1.ajouterCarteCitadelles(c2);
        //PiocheCartesCitadelles p2= new PiocheCartesCitadelles();
        //p2.ajouterCarteCitadelles(c3);
        //p2.ajouterCarteCitadelles(c4);

        ArrayList<CarteCitadelles> listeCartes1 = new ArrayList<>();
        listeCartes1.add(c1);
        listeCartes1.add(c2);

        ArrayList<CarteCitadelles> listeCartes2 = new ArrayList<>();
        listeCartes2.add(c3);
        listeCartes2.add(c4);


        Bot bot1 = new Bot("Joueur1", 5,listeCartes1,v1,null,false);
        Bot bot2 = new Bot ("Joueur2",6,listeCartes2,v2,null,false);

        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);

        //assertEquals("Joueur1", arbitre.afficherGagnant(listeJoueurs));



    }


}

