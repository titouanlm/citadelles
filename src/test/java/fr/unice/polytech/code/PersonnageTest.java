package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest {

    @Test
    void effectuerSpecialiteAssassinTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");
        Bot bot3 = new BotSimpliste("Bot 3","\033[35m");

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Voleur());
        bot3.setPersonnageACeTour(new Architecte());

        assertTrue(bot1.getPersonnageACeTour()instanceof Assassin);
        assertTrue(bot2.getPersonnageACeTour()instanceof Voleur);
        assertTrue(bot3.getPersonnageACeTour()instanceof Architecte);

        bot1.getPersonnageACeTour().effectuerSpecialite(bot1,bot1);
        bot1.getPersonnageACeTour().effectuerSpecialite(bot1,bot2);

        assertNotNull(bot1.getPersonnageACeTour());
        assertNull(bot2.getPersonnageACeTour());
        assertNotNull(bot3.getPersonnageACeTour());
    }

    @Test
    void effectuerSpecialiteVoleurTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");
        Bot bot3 = new BotSimpliste("Bot 3","\033[35m");
        Bot bot4 = new BotSimpliste("Bot 4","\033[35m");

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Voleur());
        bot3.setPersonnageACeTour(new Architecte());
        bot4.setPersonnageACeTour(null);

        assertTrue(bot1.getPersonnageACeTour()instanceof Assassin);
        assertTrue(bot2.getPersonnageACeTour()instanceof Voleur);
        assertTrue(bot3.getPersonnageACeTour()instanceof Architecte);
        assertNull(bot4.getPersonnageACeTour());

        bot1.ajouterPiece(10);
        bot2.ajouterPiece(10);
        bot3.ajouterPiece(10);
        bot4.ajouterPiece(10);

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot1);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot2);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot3);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot4);

        assertEquals(10,bot1.getNbPiece());
        assertEquals(20,bot2.getNbPiece());
        assertEquals(0,bot3.getNbPiece());
        assertEquals(10,bot4.getNbPiece());
    }

    @Test
    void effectuerSpecialiteRoiTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Roi());

        assertTrue(bot1.getPersonnageACeTour()instanceof Assassin);
        assertTrue(bot2.getPersonnageACeTour()instanceof Roi);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        bot2.getPersonnageACeTour().effectuerSpecialite(bot1,null);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null);

        assertEquals(0,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteEvequeTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Eveque());

        assertTrue(bot1.getPersonnageACeTour()instanceof Assassin);
        assertTrue(bot2.getPersonnageACeTour()instanceof Eveque);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        bot2.getPersonnageACeTour().effectuerSpecialite(bot1,null);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null);

        assertEquals(0,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteMarchandTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Assassin());
        bot2.setPersonnageACeTour(new Marchand());

        assertTrue(bot1.getPersonnageACeTour()instanceof Assassin);
        assertTrue(bot2.getPersonnageACeTour()instanceof Marchand);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));

        bot2.getPersonnageACeTour().effectuerSpecialite(bot1,null);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null);

        assertEquals(0,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }
}
