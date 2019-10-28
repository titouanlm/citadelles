
package fr.unice.polytech.code;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PiocheCartesCitadellesTest {
    PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();

    @Test
    void piocherTest(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadelles(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadelles(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        piocheCartesCitadelles.ajouterCarteCitadelles(cc[0]);
        assertEquals(cc[0],piocheCartesCitadelles.piocher());
    }

    @Test
    void piocherTestFaulse(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadelles(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadelles(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        piocheCartesCitadelles.ajouterCarteCitadelles(cc[0]);
        assertNotEquals(cc[1],piocheCartesCitadelles.piocher());
    }

    @Test
    void melangerTest(){
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadelles(1,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadelles(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[2] = new CarteCitadelles(3,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[3] = new CarteCitadelles(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[4] = new CarteCitadelles(5,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        for(int i=0; i<5;i++){
            piocheCartesCitadelles.ajouterCarteCitadelles(cc[i]);
        }
        piocheCartesCitadelles.melanger();
        assertNotEquals(cc,piocheCartesCitadelles.getPiocheCC());


    }




}

