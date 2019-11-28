package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArbitreTest {
    CarteCitadelles[] cc = new CarteCitadelles[65];
    Affichage affichage  = new Affichage(1);

    @Test
    void compteLesPointsTest(){
        Arbitre arbitre = new Arbitre(affichage);
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new BotAleatoire("Bot1","\033[36m",affichage));
        listeJoueurs.add(new BotAleatoire("Bot2","\033[36m",affichage));
        listeJoueurs.add(new BotAleatoire("Bot3","\033[36m",affichage));

        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(2, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(14, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(26, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(0).setPremierJoueurAFinir(true);

        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(3, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(25, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(56, CouleurCarteCitadelles.VIOLET, "Donjon", 3));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(18, CouleurCarteCitadelles.JAUNE, "Château", 4));

        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(8, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(9, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(10, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(61, CouleurCarteCitadelles.VIOLET, "Cimitière", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(62, CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(6, CouleurCarteCitadelles.BLEU, "Eglise", 2));

        arbitre.compteLesPoints(listeJoueurs);
        assertEquals(10, listeJoueurs.get(0).getNbPoint()); // 6 + 4 (BonusPremierJoueurAFinir)
        assertEquals(18, listeJoueurs.get(1).getNbPoint()); // 15 + 3 (BonusPossede5CouleursDeQuartierDifferentes)
        assertEquals(21, listeJoueurs.get(2).getNbPoint()); // 21
    }

    @Test
    void testBonusPremierJoueurAFinirTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Bot bot2 = new BotAleatoire("Bot2","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);

        bot1.setPremierJoueurAFinir(true);
        arbitre.testBonusPremierJoueurAFinir(bot1);
        arbitre.testBonusPremierJoueurAFinir(bot2);
        assertEquals(4, bot1.getNbPoint());
        assertEquals(0, bot2.getNbPoint());
    }

    @Test
    void testBonusAConstruit8CesQuartiersTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Bot bot2 = new BotAleatoire("Bot2","\033[36m",affichage);
        Bot bot3 = new BotAleatoire("Bot3","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);

        bot1.setPremierJoueurAFinir(true);
        bot1.getVilleDuBot().setNbBatimentsConstruits(8);
        bot2.getVilleDuBot().setNbBatimentsConstruits(8);

        arbitre.testBonusAConstruit8CesQuartiers(bot1);
        arbitre.testBonusAConstruit8CesQuartiers(bot2);
        arbitre.testBonusAConstruit8CesQuartiers(bot3);

        assertEquals(0, bot1.getNbPoint());
        assertEquals(2, bot2.getNbPoint());
        assertEquals(0, bot3.getNbPoint());
    }

    @Test
    void testBonusPossede5CouleursDeQuartierDifferentesTest(){
        Bot bot1 = new BotAleatoire("Bot1","\033[36m",affichage);
        Bot bot2 = new BotAleatoire("Bot2","\033[36m",affichage);
        Arbitre arbitre = new Arbitre(affichage);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(45, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot2);

        assertEquals(0, bot1.getNbPoint());
        assertEquals(3, bot2.getNbPoint());
    }

    @Test
    void determineJoueurGagnantTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new BotAleatoire("Bot1","\033[36m",affichage));
        listeJoueurs.add(new BotAleatoire("Bot2","\033[36m",affichage));
        listeJoueurs.add(new BotAleatoire("Bot3","\033[36m",affichage));

        listeJoueurs.get(0).setNbPoint(16);
        listeJoueurs.get(1).setNbPoint(24);
        listeJoueurs.get(2).setNbPoint(18);

        Arbitre arbitre= new Arbitre(affichage);
        assertNull(arbitre.getJoueurGagnant());
        arbitre.determineJoueurGagnant(listeJoueurs);
        assertEquals("Bot2",arbitre.getJoueurGagnant().getNom());
    }

}

