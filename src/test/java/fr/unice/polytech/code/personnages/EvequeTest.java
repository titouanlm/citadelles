package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvequeTest {


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
}
