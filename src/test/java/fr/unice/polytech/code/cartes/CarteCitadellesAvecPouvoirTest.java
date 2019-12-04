package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.Arbitre;
import fr.unice.polytech.code.Ville;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.bots.BotFairPlay;
import fr.unice.polytech.code.cartes.*;
import fr.unice.polytech.code.personnages.Condottiere;
import fr.unice.polytech.code.personnages.Marchand;
import fr.unice.polytech.code.personnages.Roi;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class CarteCitadellesAvecPouvoirTest {
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    Affichage affichage  = new Affichage(1);

    @Test
    void carteUniversitéEtDracopertTest(){
         Ville ville = new Ville(affichage);
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
    void carteCourDesMiraclesTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);

        Ville ville = new Ville(null);
        bot1.getVilleDuBot().construireBatiment(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(20,CouleurCarteCitadelles.JAUNE, "Château", 4 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(46,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 6));

        /*for(CarteCitadelles c : bot1.getVilleDuBot().getBatimentsConstruits()){
            if(c instanceof CourDesMiracles){
                ((CourDesMiracles) c).effectuerSpecialite((CarteCitadellesAvecPouvoir) c, bot1, piocheCartesCitadelles);
            }
        }*/

        arbitre.compteLesPoints(listeJoueurs);
        assertEquals(26, bot1.getNbPoint());
    }

    @Test
    void carteEcoleDeMagieTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",affichage);
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
    void carteBibliothèqueTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m",affichage);
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new Bibliotheque(62,CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot1.ajouterPiece(8);
        piocheCartesCitadelles.implementerCartesCitadelles();
        bot1.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);

        assertEquals(6,bot1.cartesCitadellesEnMain.size());

    }

    @Test
    void detruirePlusGrosQuartierEnemieTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", affichage);

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(15);
        bot2.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.ajouterCartesCitadellesDansMain(new Cimitiere(61,CouleurCarteCitadelles.VIOLET, "Cimitière", 5 ));




        ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);


        assertEquals(1,bot2.getVilleDuBot().getNbBatimentsConstruits());
        assertEquals(4,bot2.getCartesCitadellesEnMain().size());
        assertEquals(4,bot2.getNbPiece());
    }

    @Test
    void detruireQuartierAleatoireEnemieTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", affichage);

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(15);
        bot2.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.ajouterCartesCitadellesDansMain(new Cimitiere(61,CouleurCarteCitadelles.VIOLET, "Cimitière", 5 ));




        ((Condottiere) bot1.getPersonnageACeTour()).detruireQuartierAleatoireEnemie(bot1, bot2);


        assertEquals(1,bot2.getVilleDuBot().getNbBatimentsConstruits());
        assertEquals(4,bot2.getCartesCitadellesEnMain().size());
        assertEquals(4,bot2.getNbPiece());
    }

    @Test
    void detruirePlusPetitQuartierEnemieTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", affichage);

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(15);
        bot2.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));




        ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusPetitQuartierEnemie(bot1, bot2);


        assertEquals(1,bot2.getVilleDuBot().getNbBatimentsConstruits());
        assertEquals(2,bot2.getCartesCitadellesEnMain().size());
        assertEquals(5,bot2.getNbPiece());
    }

    @Test
    void carteObservatoireTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m",affichage);
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new Observatoire(60,CouleurCarteCitadelles.VIOLET, "Observatoire", 5 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot1.ajouterPiece(8);
        piocheCartesCitadelles.implementerCartesCitadelles();
        bot1.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);

        assertEquals(5,bot1.cartesCitadellesEnMain.size());

    }

}
