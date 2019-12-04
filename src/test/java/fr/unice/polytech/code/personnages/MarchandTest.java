package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotAleatoire;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarchandTest {
    Affichage affichage  = new Affichage(1);

    @Test
    void effectuerSpecialiteMarchandTest(){
        Bot bot1 = new BotAleatoire("Bot 1", "\033[32m", null);
        Bot bot2 = new BotAleatoire("Bot 2","\033[33m", null);

        bot1.setPersonnageACeTour(new Marchand(affichage));
        bot2.setPersonnageACeTour(new Marchand(affichage));

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
}
