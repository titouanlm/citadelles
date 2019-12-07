package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Affichage;
import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.bots.BotFairPlay;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArchitecteTest {

    Affichage affichage  = new Affichage(1);
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();

    @Test
    void effectuerSpecialiteArchitecteTest(){
        Bot bot1 = new BotFairPlay("Bot 1", "\033[32m", affichage);

        bot1.setPersonnageACeTour(new Architecte(affichage));
        bot1.ajouterPiece(12);

        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 ));
        bot1.ajouterCartesCitadellesDansMain(new CarteCitadellesSansPouvoir(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 ));
        bot1.ajouterCartesCitadellesDansMain((new CarteCitadellesSansPouvoir(13, CouleurCarteCitadelles.JAUNE, "Manoir", 3)));
        bot1.ajouterCartesCitadellesDansMain((new CarteCitadellesSansPouvoir(15, CouleurCarteCitadelles.BLEU, "Cathédrale", 5)));

        if(bot1.getPersonnageACeTour() instanceof Architecte){
            ((Architecte) bot1.getPersonnageACeTour()).effectuerSpecialiteArchitecte(bot1);
        }

        assertTrue(bot1.getVilleDuBot().getNbBatimentsConstruits()==0 || bot1.getVilleDuBot().getNbBatimentsConstruits()==1
                ||bot1.getVilleDuBot().getNbBatimentsConstruits()==2 || bot1.getVilleDuBot().getNbBatimentsConstruits()==3); // L'architecte peut bâtir jusqu'à trois quartiers
    }
}
