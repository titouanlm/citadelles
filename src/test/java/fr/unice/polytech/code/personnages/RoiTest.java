package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoiTest {
    Affichage affichage  = new Affichage(1);

    @Test
    void effectuerSpecialiteRoiTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m", null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", null);

        bot1.setPersonnageACeTour(new Roi(affichage));
        bot2.setPersonnageACeTour(new Roi(affichage));

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
}
