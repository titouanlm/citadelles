package fr.unice.polytech.code;

import fr.unice.polytech.code.bot.Bot;
import fr.unice.polytech.code.bot.BotSimpliste;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArbitreTest {
    CarteCitadelles[] cc = new CarteCitadelles[65];

    @Test
    void compteLesPointsTest(){
        Arbitre arbitre = new Arbitre();
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new BotSimpliste("Bot1","\033[36m"));
        listeJoueurs.add(new BotSimpliste("Bot2","\033[36m"));
        listeJoueurs.add(new BotSimpliste("Bot3","\033[36m"));

        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(2, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(14, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(new CarteCitadelles(26, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(0).setPremierJoueurAFinir(true);

        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(3, CouleurCarteCitadelles.BLEU, "Temple", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(25, CouleurCarteCitadelles.VERT, "Taverne", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(56, CouleurCarteCitadelles.VIOLET, "Donjon", 3));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Château", 4));

        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(8, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(9, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monastère", 3));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(61, CouleurCarteCitadelles.VIOLET, "Cimitière", 5));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(62, CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6));
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(new CarteCitadelles(6, CouleurCarteCitadelles.BLEU, "Eglise", 2));

        arbitre.compteLesPoints(listeJoueurs);
        assertEquals(10, listeJoueurs.get(0).getNbPoint()); // 6 + 4 (BonusPremierJoueurAFinir)
        assertEquals(18, listeJoueurs.get(1).getNbPoint()); // 15 + 3 (BonusPossede5CouleursDeQuartierDifferentes)
        assertEquals(21, listeJoueurs.get(2).getNbPoint()); // 21
    }

    @Test
    void testBonusPremierJoueurAFinirTest(){
        Bot bot1 = new BotSimpliste("Bot1","\033[36m");
        Bot bot2 = new BotSimpliste("Bot2","\033[36m");
        Arbitre arbitre = new Arbitre();

        bot1.setPremierJoueurAFinir(true);
        arbitre.testBonusPremierJoueurAFinir(bot1);
        arbitre.testBonusPremierJoueurAFinir(bot2);
        assertEquals(4, bot1.getNbPoint());
        assertEquals(0, bot2.getNbPoint());
    }

    @Test
    void testBonusAConstruit8CesQuartiersTest(){
        Bot bot1 = new BotSimpliste("Bot1","\033[36m");
        Bot bot2 = new BotSimpliste("Bot2","\033[36m");
        Bot bot3 = new BotSimpliste("Bot3","\033[36m");
        Arbitre arbitre = new Arbitre();

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
        Bot bot1 = new BotSimpliste("Bot1","\033[36m");
        Bot bot2 = new BotSimpliste("Bot2","\033[36m");
        Arbitre arbitre = new Arbitre();

        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(45, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot2);

        assertEquals(0, bot1.getNbPoint());
        assertEquals(3, bot2.getNbPoint());
    }

    @Test
    void determineJoueurGagnantTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new BotSimpliste("Bot1","\033[36m"));
        listeJoueurs.add(new BotSimpliste("Bot2","\033[36m"));
        listeJoueurs.add(new BotSimpliste("Bot3","\033[36m"));

        listeJoueurs.get(0).setNbPoint(16);
        listeJoueurs.get(1).setNbPoint(24);
        listeJoueurs.get(2).setNbPoint(18);

        Arbitre arbitre= new Arbitre();
        assertNull(arbitre.getJoueurGagnant());
        arbitre.determineJoueurGagnant(listeJoueurs);
        assertEquals("Bot2",arbitre.getJoueurGagnant().getNom());
    }

}

