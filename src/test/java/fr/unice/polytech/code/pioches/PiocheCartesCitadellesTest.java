
package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PiocheCartesCitadellesTest {
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();

    @Test
    void piocherTest(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadellesSansPouvoir(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        assertNull(piocheCartesCitadelles.piocher());
        piocheCartesCitadelles.ajouterCarteCitadelles(cc[0]);
        assertEquals(1, piocheCartesCitadelles.nbCartesRestantes());
        assertEquals(cc[0],piocheCartesCitadelles.piocher());
        assertEquals(0, piocheCartesCitadelles.nbCartesRestantes());
    }

    @Test
    void piocherTestFaulse(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadellesSansPouvoir(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadellesSansPouvoir(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        piocheCartesCitadelles.ajouterCarteCitadelles(cc[0]);
        assertNotEquals(cc[1],piocheCartesCitadelles.piocher());
    }

    @Test
    void ajouterCarteCitadellesTest(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadellesSansPouvoir(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        assertEquals(0, piocheCartesCitadelles.nbCartesRestantes());
        piocheCartesCitadelles.ajouterCarteCitadelles(cc[0]);
        assertEquals(1, piocheCartesCitadelles.nbCartesRestantes());
        assertEquals(cc[0], piocheCartesCitadelles.getPiocheCC().get(0));
    }

    @Test
    void implementerCartesCitadellesTest(){
        assertEquals(0, piocheCartesCitadelles.nbCartesRestantes());
        piocheCartesCitadelles.implementerCartesCitadelles();
        assertEquals(65, piocheCartesCitadelles.nbCartesRestantes());
    }
}

