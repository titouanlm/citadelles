package fr.unice.polytech.code;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VilleTest {
    private Ville ville = new Ville();
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
        Bot bot1 = new BotSimpliste("Bot 1", "\033[32m");
        Bot bot2 = new BotSimpliste("Bot 2","\033[33m");

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
}
