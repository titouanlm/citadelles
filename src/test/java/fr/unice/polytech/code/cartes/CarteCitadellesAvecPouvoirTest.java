package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.Arbitre;
import fr.unice.polytech.code.Ville;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.bots.BotFairPlay;
import fr.unice.polytech.code.bots.BotTricheur;
import fr.unice.polytech.code.cartes.*;
import fr.unice.polytech.code.moteur.Tour;
import fr.unice.polytech.code.personnages.Condottiere;
import fr.unice.polytech.code.personnages.Marchand;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.personnages.Roi;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CarteCitadellesAvecPouvoirTest {
    private PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private Affichage affichage  = new Affichage(1);

    @Test
    void carteUniversiteTest(){
        Ville ville = new Ville(affichage);
        CarteCitadellesAvecPouvoir univ = new Universite(64,CouleurCarteCitadelles.VIOLET, "Université", 6 );

        ville.construireBatiment(univ);

        assertEquals(1, ville.getNbBatimentsConstruits());
        assertEquals(8, ville.getNbTotalPoint());
    }

    @Test
    void carteDracopertTest(){
        Ville ville = new Ville(affichage);
        CarteCitadellesAvecPouvoir draco = new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 6);

        ville.construireBatiment(draco);

        assertEquals(1, ville.getNbBatimentsConstruits());
        assertEquals(8, ville.getNbTotalPoint());
    }

    @Test
    void carteCourDesMiraclesTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);

        bot1.getVilleDuBot().construireBatiment(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(20,CouleurCarteCitadelles.JAUNE, "Château", 4 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(54,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));
        bot1.getVilleDuBot().construireBatiment(new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 6));

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        assertEquals(CouleurCarteCitadelles.VERT, bot1.getVilleDuBot().getBatimentsConstruits().get(0).getCouleur());
        assertEquals(3, bot1.getNbPoint());
    }


    @Test
    void carteCourDesMiraclesTest2(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(20,CouleurCarteCitadelles.JAUNE, "Château", 4 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(54,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));
        bot1.getVilleDuBot().construireBatiment(new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 6));
        bot1.getVilleDuBot().construireBatiment(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        assertEquals(CouleurCarteCitadelles.VIOLET, bot1.getVilleDuBot().getBatimentsConstruits().get(7).getCouleur());
        assertEquals(0, bot1.getNbPoint());
    }

    @Test
    void carteDonjonTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Bot bot2 = new BotFairPlay("Bot2","\033[35m",affichage);
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        bot2.setPersonnageACeTour(new Condottiere(affichage));

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        bot1.getVilleDuBot().construireBatiment(new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 ));

        assertEquals(3, bot1.getVilleDuBot().getNbBatimentsConstruits());

        Condottiere c =new Condottiere(affichage);
        c.detruirePlusGrosQuartierEnemie(bot2, bot1, piocheCartesCitadelles, listeJoueurs);
        assertEquals(2, bot1.getVilleDuBot().getNbBatimentsConstruits());
        assertTrue(bot1.getVilleDuBot().contient(new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 )));
    }

    @Test
    void carteLaboratoireTest(){
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Tour t = new Tour(1,piocheCartesCitadelles,null,null,affichage );

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));

        bot1.getVilleDuBot().construireBatiment(new Laboratoire(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 ));

        assertEquals(2, bot1.getCartesCitadellesEnMain().size());
        assertEquals(0, bot1.getNbPiece());
        assertEquals(0 , piocheCartesCitadelles.getPiocheCC().size());
        t.strategieLaboratoire(bot1);
        assertEquals(1, bot1.getCartesCitadellesEnMain().size());
        assertEquals(1, bot1.getNbPiece());
        assertEquals(1, piocheCartesCitadelles.getPiocheCC().size());
    }


    @Test
    void carteLaboratoireTest2(){
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Tour t = new Tour(1,piocheCartesCitadelles,null,null,affichage);

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.ajouterCartesCitadellesDansMain(new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));

        bot1.getVilleDuBot().construireBatiment(new Laboratoire(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 ));

        assertEquals(2, bot1.getCartesCitadellesEnMain().size());
        assertEquals(0, bot1.getNbPiece());
        assertEquals(0 , piocheCartesCitadelles.getPiocheCC().size());
        t.strategieLaboratoire(bot1);
        assertEquals(2, bot1.getCartesCitadellesEnMain().size());
        assertEquals(0, bot1.getNbPiece());
        assertEquals(0 , piocheCartesCitadelles.getPiocheCC().size());
    }

    @Test
    void carteManufactureTest(){
        PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
        piocheCartesCitadelles.implementerCartesCitadelles();

        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Tour t = new Tour(1,piocheCartesCitadelles,null,null,affichage);
        bot1.ajouterPiece(3);

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2));
        bot1.getVilleDuBot().construireBatiment(new Manufacture(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 ));
        t.strategieManufacture(bot1);

        assertEquals(4,bot1.getCartesCitadellesEnMain().size());
        assertEquals(0, bot1.getNbPiece());
        assertEquals(62, piocheCartesCitadelles.getPiocheCC().size());
    }


    @Test
    void carteEcoleDeMagieTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",affichage);
        bot1.setPersonnageACeTour(new Roi(affichage));
        Tour t = new Tour(1,piocheCartesCitadelles,null,null,affichage);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new EcoleDeMagie(63,CouleurCarteCitadelles.VIOLET, "École de magie", 6 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        assertEquals(0,bot1.getNbPiece());
        t.strategieEcoleDeMagie(bot1);
        ((Roi) bot1.getPersonnageACeTour()).effectuerSpecialiteRoi(bot1);
        assertEquals(2,bot1.getNbPiece());
    }

    @Test
    void carteBibliothequeTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",affichage);
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 ));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));

        assertEquals(0 , bot1.getCartesCitadellesEnMain().size());
        assertEquals(2 , piocheCartesCitadelles.nbCartesRestantes());

        CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
        CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();

        bot1.getVilleDuBot().construireBatiment(new Bibliotheque(62,CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6 ));
        bot1.choixCartesPiochees(piocheCartesCitadelles, cartePiochee1, cartePiochee2);

        assertEquals(2, bot1.getCartesCitadellesEnMain().size());
        assertEquals(0 , piocheCartesCitadelles.nbCartesRestantes());
        assertEquals("Temple" , bot1.getCartesCitadellesEnMain().get(0).getNom());
        assertEquals("Eglise" , bot1.getCartesCitadellesEnMain().get(1).getNom());
    }

    @Test
    void carteCimetiereTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);
        Bot bot2 = new BotFairPlay("Bot 2", "\033[32m", affichage);
        Bot bot3 = new BotFairPlay("Bot 3", "\033[32m", affichage);
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(bot1);
        listeJoueurs.add(bot2);
        listeJoueurs.add(bot3);

        bot1.ajouterPiece(2);
        bot1.getVilleDuBot().construireBatiment(new Cimitiere(61,CouleurCarteCitadelles.VIOLET, "Cimetière", 5 ));

        bot2.ajouterPiece(20);
        bot2.setPersonnageACeTour(new Condottiere(affichage));

        bot3.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 6));
        bot3.getVilleDuBot().construireBatiment(new Manufacture(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 ));

        assertEquals(0 , bot1.getCartesCitadellesEnMain().size());
        assertEquals(2, bot1.getNbPiece());
        assertEquals(20, bot2.getNbPiece());
        assertEquals(2 , bot3.getVilleDuBot().getNbBatimentsConstruits());
        bot2.strategieCondottiere(listeJoueurs, piocheCartesCitadelles);

        assertEquals(1 , bot1.getCartesCitadellesEnMain().size());
        assertEquals(1, bot1.getNbPiece());
        assertEquals(15, bot2.getNbPiece());
        assertEquals(1 , bot3.getVilleDuBot().getNbBatimentsConstruits());
    }



    @Test
    void carteObservatoireTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);
        bot1.ajouterPiece(50);
        bot1.getVilleDuBot().construireBatiment(new Observatoire(60,CouleurCarteCitadelles.VIOLET, "Observatoire", 5));

        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 6));
        piocheCartesCitadelles.ajouterCarteCitadelles(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 ));
        piocheCartesCitadelles.ajouterCarteCitadelles(new Manufacture(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 ));

        assertEquals(0 , bot1.getCartesCitadellesEnMain().size());
        assertEquals(3, piocheCartesCitadelles.nbCartesRestantes());

        bot1.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);

        assertEquals(1, bot1.getCartesCitadellesEnMain().size());
        assertEquals("Manufacture" , bot1.getCartesCitadellesEnMain().get(0).getNom());
        assertEquals(2, piocheCartesCitadelles.nbCartesRestantes());
    }

}
