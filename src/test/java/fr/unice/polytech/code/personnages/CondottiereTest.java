package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.cartes.Donjon;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CondottiereTest {

    @Test
    void effectuerSpecialiteCondottiereTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Condottiere());

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
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


    @Test
    void detruirePlusGrosQuartierEnemieTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 2 ));
        bot2.getVilleDuBot().construireBatiment(new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(4, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(2,bot1.getNbPiece());
        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 2 )));
        assertEquals(1,bot1.getNbPiece());
        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);
        }
        assertEquals(1,bot1.getNbPiece());
        assertEquals(1, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);
        }
        assertEquals(1,bot1.getNbPiece());
        assertEquals(1, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    void detruirePlusGrosQuartierEnemieEvequeTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Eveque());

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusGrosQuartierEnemie(bot1, bot2);
        }

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4)));
        assertEquals(5,bot1.getNbPiece());
    }

    @Test
    void detruireQuartierAleatoireEnemieTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruireQuartierAleatoireEnemie(bot1, bot2);
        }

        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

    @Test
    void detruirePlusPetitQuartierEnemieTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m");
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m");

        bot1.setPersonnageACeTour(new Condottiere());
        bot2.setPersonnageACeTour(new Marchand());

        bot1.ajouterPiece(5);

        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44, CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Château", 4));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot2.getVilleDuBot().construireBatiment(new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 ));

        assertTrue(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 )));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(3, bot2.getVilleDuBot().getNbBatimentsConstruits());

        if(bot1.getPersonnageACeTour() instanceof Condottiere){
            ((Condottiere) bot1.getPersonnageACeTour()).detruirePlusPetitQuartierEnemie(bot1, bot2);
        }

        assertFalse(bot2.getVilleDuBot().contient(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 )));
        assertEquals(5,bot1.getNbPiece());
        assertEquals(2, bot2.getVilleDuBot().getNbBatimentsConstruits());
    }

}
