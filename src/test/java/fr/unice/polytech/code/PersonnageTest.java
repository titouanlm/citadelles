package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotSimpliste;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest {
    PiocheCartesCitadelles piocheCartesCitadelles;

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

        bot1.getPersonnageACeTour().effectuerSpecialite(bot1,bot1,piocheCartesCitadelles);
        bot1.getPersonnageACeTour().effectuerSpecialite(bot1,bot2,piocheCartesCitadelles);

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

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot1,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot2,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot3,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot4,piocheCartesCitadelles);

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

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);

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

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);

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

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);
        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,null,piocheCartesCitadelles);

        assertEquals(0,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteMagicienTest(){
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");
        ArrayList<CarteCitadelles> test = new ArrayList<>() ;

        bot1.setPersonnageACeTour(new Eveque());
        bot2.setPersonnageACeTour(new Magicien());


        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.ajouterCartesCitadellesDansMain(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadelles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        test.add(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        test.add(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        test.add(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        test.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        bot2.getPersonnageACeTour().effectuerSpecialite(bot2,bot1,piocheCartesCitadelles);



        assertEquals(bot1.cartesCitadellesEnMain.size(),test.size());
        assertEquals(bot1.cartesCitadellesEnMain.get(0).getNom(),test.get(0).getNom());
        assertEquals(bot1.cartesCitadellesEnMain.get(0).getNumero(),test.get(0).getNumero());
    }
    //Le test marche si on force la méthode à prendre la valeur value=1 pour choisir d'échanger les cartes en main

}
