package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.personnages.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PersonnageTest {
    PiocheCartesCitadelles piocheCartesCitadelles;
/*
    @Test
    void effectuerSpecialiteAssassinTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m");

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
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");
        Bot bot3 = new BotAleatoire("Bot 3","\033[35m");
        Bot bot4 = new BotAleatoire("Bot 4","\033[35m");

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
    }*/

    @Test
    void effectuerSpecialiteRoiTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Roi());
        bot2.setPersonnageACeTour(new Roi());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(18, CouleurCarteCitadelles.JAUNE, "Château", 4));

        if(bot1.getPersonnageACeTour() instanceof Roi){
            ((Roi) bot1.getPersonnageACeTour()).effectuerSpecialiteRoi(bot1);
        }
        if(bot2.getPersonnageACeTour() instanceof Roi){
            ((Roi) bot2.getPersonnageACeTour()).effectuerSpecialiteRoi(bot2);
        }

        assertEquals(1,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteEvequeTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Eveque());
        bot2.setPersonnageACeTour(new Eveque());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(8,CouleurCarteCitadelles.BLEU, "Monastère", 3 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        if(bot1.getPersonnageACeTour() instanceof Eveque){
            ((Eveque) bot1.getPersonnageACeTour()).effectuerSpecialiteEveque(bot1);
        }
        if(bot2.getPersonnageACeTour() instanceof Eveque){
            ((Eveque) bot2.getPersonnageACeTour()).effectuerSpecialiteEveque(bot2);
        }

        assertEquals(1,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteMarchandTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Marchand());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Echoppe", 2));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));

        if(bot1.getPersonnageACeTour() instanceof Marchand){
            ((Marchand) bot1.getPersonnageACeTour()).effectuerSpecialiteMarchand(bot1);
        }
        if(bot2.getPersonnageACeTour() instanceof Marchand){
            ((Marchand) bot2.getPersonnageACeTour()).effectuerSpecialiteMarchand(bot2);
        }

        assertEquals(1,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

    @Test
    void effectuerSpecialiteCondottiereTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Condottiere());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).effectuerSpecialiteCondottiere(bot1);
        }
        if(bot2.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot2.getPersonnageACeTour()).effectuerSpecialiteCondottiere(bot2);
        }

        assertEquals(1,bot1.getNbPiece());
        assertEquals(2,bot2.getNbPiece());
    }

}