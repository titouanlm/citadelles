package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.bots.BotFairPlay;
import fr.unice.polytech.code.cartes.*;
import fr.unice.polytech.code.moteur.Moteur;
import fr.unice.polytech.code.personnages.Condottiere;
import fr.unice.polytech.code.personnages.Roi;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CarteCitadellesAvecPouvoirTest {
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();

    @Test
    void carteUniversitéEtDracopertTest(){
         Ville ville = new Ville();
        CarteCitadellesAvecPouvoir carte1 = new Universite(64,CouleurCarteCitadelles.VIOLET, "Université", 6 );
        CarteCitadellesAvecPouvoir carte2 = new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 6 );
        CarteCitadelles cc1 = new CarteCitadellesSansPouvoir(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
        CarteCitadelles cc2 = new CarteCitadellesSansPouvoir(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 );

        ville.construireBatiment(cc1);
        ville.construireBatiment(carte1);
        ville.construireBatiment(carte2);
        ville.construireBatiment(cc2);

        assertEquals(4, ville.getNbBatimentsConstruits());
        assertEquals(24, ville.getNbTotalPoint());


    }
    @Test
    void carteCourDesMiracles(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m");
        Arbitre arbitre = new Arbitre();

        Ville ville = new Ville();
        bot1.getVilleDuBot().construireBatiment(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(37,CouleurCarteCitadelles.VERT, "Comptoir", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(20,CouleurCarteCitadelles.JAUNE, "Château", 4 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(46,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        assertEquals(3, bot1.getNbPoint());
    }

    @Test
    void carteEcoleDeMagie(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        bot1.setPersonnageACeTour(new Roi());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new EcoleDeMagie(63,CouleurCarteCitadelles.VIOLET, "École de magie", 6 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        for(CarteCitadelles c : bot1.getVilleDuBot().getBatimentsConstruits()){
            if(c instanceof EcoleDeMagie){
                ((EcoleDeMagie) c).effectuerSpecialite((CarteCitadellesAvecPouvoir) c, bot1, piocheCartesCitadelles);
            }
        }

        ((Roi) bot1.getPersonnageACeTour()).effectuerSpecialiteRoi(bot1);

        assertEquals(2,bot1.getNbPiece());
    }

    @Test
    void carteBibliothèque(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m");
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new Bibliotheque(62,CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot1.ajouterPiece(8);
        piocheCartesCitadelles.implementerCartesCitadelles();


        bot1.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);

        assertEquals(6,bot1.cartesCitadellesEnMain.size());




    }

}