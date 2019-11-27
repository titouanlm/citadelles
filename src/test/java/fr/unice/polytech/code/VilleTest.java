package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VilleTest {
    private Ville ville = new Ville(null);
    private CarteCitadelles cc1 = new CarteCitadellesSansPouvoir(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
    private CarteCitadelles cc2 = new CarteCitadellesSansPouvoir(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 );
    private CarteCitadelles cc3 = new CarteCitadellesSansPouvoir(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 );

    @Test
    void construireBatimentTest(){
        assertEquals(0, ville.getNbBatimentsConstruits());
        assertEquals(0, ville.getNbTotalPoint());

        ville.construireBatiment(cc1);
        assertEquals(1, ville.getNbBatimentsConstruits());
        assertEquals(3, ville.getNbTotalPoint());

        ville.construireBatiment(cc2);
        assertEquals(2, ville.getNbBatimentsConstruits());
        assertEquals(8, ville.getNbTotalPoint());
    }

    @Test
    void contientTest(){
        assertFalse(ville.contient(cc3));
        ville.construireBatiment(cc3);
        assertTrue(ville.contient(cc3));
        assertFalse(ville.contient(cc1));
    }

    @Test
    void compterNbQuartiersRougeTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.getVilleDuBot().construireBatiment( new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment( new CarteCitadellesSansPouvoir(53,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));
        bot2.getVilleDuBot().construireBatiment( new CarteCitadellesSansPouvoir(54,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));

        assertEquals(1, bot1.getVilleDuBot().compterNbQuartiersRouge());
        assertNotEquals(0,bot2.getVilleDuBot().compterNbQuartiersRouge());
        assertEquals(1, bot2.getVilleDuBot().compterNbQuartiersRouge());
    }


    @Test
    void compterNbQuartiersJauneTest() {
        Bot bot1 = new BotAleatoire("Bot 1", "\033[35m",null);
        Bot bot2 = new BotAleatoire("Bot 2", "\033[35m",null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(21, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(31, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(19, CouleurCarteCitadelles.JAUNE, "Château", 4));

        assertNotEquals(5,bot1.getVilleDuBot().compterNbQuartiersJaune());
        assertEquals(2, bot1.getVilleDuBot().compterNbQuartiersJaune());
        assertNotEquals(0, bot2.getVilleDuBot().compterNbQuartiersJaune());
        assertEquals(1, bot2.getVilleDuBot().compterNbQuartiersJaune());
    }

    @Test
    void compterNbQuartiersBleuTest(){

        Bot bot1 = new BotAleatoire("Bot 1", "\033[35m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m",null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(10,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(21, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(19, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 ));

        assertNotEquals(7,bot1.getVilleDuBot().compterNbQuartiersBleu());
        assertEquals(3, bot1.getVilleDuBot().compterNbQuartiersBleu());
        assertNotEquals(3, bot2.getVilleDuBot().compterNbQuartiersBleu());
        assertEquals(0, bot2.getVilleDuBot().compterNbQuartiersBleu());

    }

    @Test
    void compterNbQuartiersVertTest(){

        Bot bot1 = new BotAleatoire("Bot 2", "\033[35m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m",null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(19, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(31, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(27,CouleurCarteCitadelles.VERT, "Taverne", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(29,CouleurCarteCitadelles.VERT, "Échoppe", 2 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(39,CouleurCarteCitadelles.VERT, "Port", 4 ));

        assertNotEquals(9,bot1.getVilleDuBot().compterNbQuartiersVert());
        assertEquals(2, bot1.getVilleDuBot().compterNbQuartiersVert());
        assertNotEquals(0, bot2.getVilleDuBot().compterNbQuartiersVert());
        assertEquals(3, bot2.getVilleDuBot().compterNbQuartiersVert());

    }

    @Test
    void quartiersVilleToStringTest(){

        Bot bot1 = new BotAleatoire("Bot 1", "\033[35m",null);
        Bot bot2 = new BotAleatoire("Bot 2", "\033[35m",null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(21, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(31, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(19, CouleurCarteCitadelles.JAUNE, "Château", 4));

        assertEquals("Manoir, Château, Échoppe, ",bot1.getVilleDuBot().quartiersVilleToString());
        assertEquals("Forteresse, Taverne, Château, ",bot2.getVilleDuBot().quartiersVilleToString());



    }

    @Test
    void detruireQuartierTest(){
        Bot bot1 = new BotAleatoire("Bot 2", "\033[35m",null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m",null);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(19, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(31, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(27,CouleurCarteCitadelles.VERT, "Taverne", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(29,CouleurCarteCitadelles.VERT, "Échoppe", 2 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(39,CouleurCarteCitadelles.VERT, "Port", 4 ));

        bot1.getVilleDuBot().detruireQuartier(bot1.getVilleDuBot().getBatimentsConstruits().get(0));
        bot1.getVilleDuBot().detruireQuartier(bot1.getVilleDuBot().getBatimentsConstruits().get(1));
        bot2.getVilleDuBot().detruireQuartier(bot2.getVilleDuBot().getBatimentsConstruits().get(0));


        assertEquals(7, bot1.getVilleDuBot().getNbTotalPoint());
        assertEquals(6, bot2.getVilleDuBot().getNbTotalPoint());
        assertEquals(2,bot1.getVilleDuBot().getNbBatimentsConstruits());
        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

}

