package fr.unice.polytech.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ArbitreTest {
    CarteCitadelles[] cc = new CarteCitadelles[65];

    @BeforeEach
    void setUp() {
        cc[0] = new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1);
        cc[1] = new CarteCitadelles(2, CouleurCarteCitadelles.BLEU, "Temple", 1);
        cc[2] = new CarteCitadelles(3, CouleurCarteCitadelles.BLEU, "Temple", 1);
        cc[3] = new CarteCitadelles(4, CouleurCarteCitadelles.BLEU, "Eglise", 2);
        cc[4] = new CarteCitadelles(5, CouleurCarteCitadelles.BLEU, "Eglise", 2);
        cc[5] = new CarteCitadelles(6, CouleurCarteCitadelles.BLEU, "Eglise", 2);
        cc[6] = new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2);
        cc[7] = new CarteCitadelles(8, CouleurCarteCitadelles.BLEU, "Monastère", 3);
        cc[8] = new CarteCitadelles(9, CouleurCarteCitadelles.BLEU, "Monastère", 3);
        cc[9] = new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Monastère", 3);
        cc[10] = new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5);
        cc[11] = new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5);

        cc[12] = new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3);
        cc[13] = new CarteCitadelles(14, CouleurCarteCitadelles.JAUNE, "Manoir", 3);
        cc[14] = new CarteCitadelles(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3);
        cc[15] = new CarteCitadelles(16, CouleurCarteCitadelles.JAUNE, "Manoir", 3);
        cc[16] = new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoir", 3);
        cc[17] = new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Château", 4);
        cc[18] = new CarteCitadelles(19, CouleurCarteCitadelles.JAUNE, "Château", 4);
        cc[19] = new CarteCitadelles(20, CouleurCarteCitadelles.JAUNE, "Château", 4);
        cc[20] = new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Château", 4);
        cc[21] = new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5);
        cc[22] = new CarteCitadelles(23, CouleurCarteCitadelles.JAUNE, "Palais", 5);

        cc[23] = new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1);
        cc[24] = new CarteCitadelles(25, CouleurCarteCitadelles.VERT, "Taverne", 1);
        cc[25] = new CarteCitadelles(26, CouleurCarteCitadelles.VERT, "Taverne", 1);
        cc[26] = new CarteCitadelles(27, CouleurCarteCitadelles.VERT, "Taverne", 1);
        cc[27] = new CarteCitadelles(28, CouleurCarteCitadelles.VERT, "Taverne", 1);
        cc[28] = new CarteCitadelles(29, CouleurCarteCitadelles.VERT, "Échoppe", 2);
        cc[29] = new CarteCitadelles(30, CouleurCarteCitadelles.VERT, "Échoppe", 2);
        cc[30] = new CarteCitadelles(31, CouleurCarteCitadelles.VERT, "Échoppe", 2);
        cc[31] = new CarteCitadelles(32, CouleurCarteCitadelles.VERT, "Marché", 2);
        cc[32] = new CarteCitadelles(33, CouleurCarteCitadelles.VERT, "Marché", 2);
        cc[33] = new CarteCitadelles(34, CouleurCarteCitadelles.VERT, "Marché", 2);
        cc[34] = new CarteCitadelles(35, CouleurCarteCitadelles.VERT, "Marché", 2);
        cc[35] = new CarteCitadelles(36, CouleurCarteCitadelles.VERT, "Comptoir", 3);
        cc[36] = new CarteCitadelles(37, CouleurCarteCitadelles.VERT, "Comptoir", 3);
        cc[37] = new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3);
        cc[38] = new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4);
        cc[39] = new CarteCitadelles(40, CouleurCarteCitadelles.VERT, "Port", 4);
        cc[40] = new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4);
        cc[41] = new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5);
        cc[42] = new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville", 5);

        cc[43] = new CarteCitadelles(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1);
        cc[44] = new CarteCitadelles(45, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1);
        cc[45] = new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1);
        cc[46] = new CarteCitadelles(47, CouleurCarteCitadelles.ROUGE, "Prison", 2);
        cc[47] = new CarteCitadelles(48, CouleurCarteCitadelles.ROUGE, "Prison", 2);
        cc[48] = new CarteCitadelles(49, CouleurCarteCitadelles.ROUGE, "Prison", 2);
        cc[49] = new CarteCitadelles(50, CouleurCarteCitadelles.ROUGE, "Caserne", 3);
        cc[50] = new CarteCitadelles(51, CouleurCarteCitadelles.ROUGE, "Caserne", 3);
        cc[51] = new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3);
        cc[52] = new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5);
        cc[53] = new CarteCitadelles(54, CouleurCarteCitadelles.ROUGE, "Forteresse", 5);

        cc[54] = new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2);
        cc[55] = new CarteCitadelles(56, CouleurCarteCitadelles.VIOLET, "Donjon", 3);
        cc[56] = new CarteCitadelles(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3);
        cc[57] = new CarteCitadelles(58, CouleurCarteCitadelles.VIOLET, "Laboratoire", 5);
        cc[58] = new CarteCitadelles(59, CouleurCarteCitadelles.VIOLET, "Manufacture", 5);
        cc[59] = new CarteCitadelles(60, CouleurCarteCitadelles.VIOLET, "Observatoire", 5);
        cc[60] = new CarteCitadelles(61, CouleurCarteCitadelles.VIOLET, "Cimitière", 5);
        cc[61] = new CarteCitadelles(62, CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6);
        cc[62] = new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6);
        cc[63] = new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8);
        cc[64] = new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8);
    }


    @Test
    void compteLesPointsTest(){
        Arbitre arbitre = new Arbitre();
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new Bot("Bot1","\033[36m"));
        listeJoueurs.add(new Bot("Bot2","\033[36m"));
        listeJoueurs.add(new Bot("Bot3","\033[36m"));

        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[0]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[12]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[23]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[43]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[44]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[1]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[13]);
        listeJoueurs.get(0).getVilleDuBot().construireBatiment(cc[25]);
        listeJoueurs.get(0).setPremierJoueurAFinir(true);

        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[2]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[14]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[24]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[45]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[55]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[6]);
        listeJoueurs.get(1).getVilleDuBot().construireBatiment(cc[17]);

        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[7]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[8]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[9]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[10]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[11]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[60]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[61]);
        listeJoueurs.get(2).getVilleDuBot().construireBatiment(cc[5]);

        arbitre.compteLesPoints(listeJoueurs);
        assertEquals(16, listeJoueurs.get(0).getNbPoint()); // 12 + 4
        assertEquals(18, listeJoueurs.get(1).getNbPoint()); // 15 + 3
        assertEquals(34, listeJoueurs.get(2).getNbPoint()); // 34 + 2
    }

    @Test
    void testBonusPremierJoueurAFinirTest(){
        Bot bot1 = new Bot("Bot1","\033[36m");
        Bot bot2 = new Bot("Bot2","\033[36m");
        Arbitre arbitre = new Arbitre();

        bot1.setPremierJoueurAFinir(true);
        arbitre.testBonusPremierJoueurAFinir(bot1);
        arbitre.testBonusPremierJoueurAFinir(bot2);
        assertEquals(4, bot1.getNbPoint());
        assertEquals(0, bot2.getNbPoint());
    }

    @Test
    void testBonusAConstruit8CesQuartiersTest(){
        Bot bot1 = new Bot("Bot1","\033[36m");
        Bot bot2 = new Bot("Bot2","\033[36m");
        Bot bot3 = new Bot("Bot3","\033[36m");
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
        Bot bot1 = new Bot("Bot1","\033[36m");
        Bot bot2 = new Bot("Bot2","\033[36m");
        Arbitre arbitre = new Arbitre();

        bot1.getVilleDuBot().construireBatiment(cc[0]);
        bot1.getVilleDuBot().construireBatiment(cc[12]);
        bot1.getVilleDuBot().construireBatiment(cc[23]);
        bot1.getVilleDuBot().construireBatiment(cc[43]);
        bot1.getVilleDuBot().construireBatiment(cc[44]);

        bot2.getVilleDuBot().construireBatiment(cc[0]);
        bot2.getVilleDuBot().construireBatiment(cc[12]);
        bot2.getVilleDuBot().construireBatiment(cc[23]);
        bot2.getVilleDuBot().construireBatiment(cc[43]);
        bot2.getVilleDuBot().construireBatiment(cc[54]);

        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot1);
        arbitre.testBonusPossede5CouleursDeQuartierDifferentes(bot2);

        assertEquals(0, bot1.getNbPoint());
        assertEquals(3, bot2.getNbPoint());
    }

    @Test
    void determineJoueurGagnantTest(){
        ArrayList<Bot> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(new Bot("Bot1","\033[36m"));
        listeJoueurs.add(new Bot("Bot2","\033[36m"));
        listeJoueurs.add(new Bot("Bot3","\033[36m"));

        listeJoueurs.get(0).setNbPoint(16);
        listeJoueurs.get(1).setNbPoint(24);
        listeJoueurs.get(2).setNbPoint(18);

        Arbitre arbitre= new Arbitre();
        assertNull(arbitre.getJoueurGagnant());
        arbitre.determineJoueurGagnant(listeJoueurs);
        assertEquals("Bot2",arbitre.getJoueurGagnant().getNom());
    }

}

