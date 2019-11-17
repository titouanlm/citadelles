package fr.unice.polytech.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CarteCitadellesTest {

    CarteCitadelles cc1;
    CarteCitadelles cc2;

    @BeforeEach
    public void setUp() {
        cc1 = new CarteCitadelles(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3);
        cc2 = new CarteCitadelles(58, CouleurCarteCitadelles.VIOLET, "Laboratoire", 5);
    }

    @Test
    public void compareNbPointsTest(){
        assertTrue(cc1.compareNbPoints(cc2)==cc2);
        assertTrue(cc2.compareNbPoints(cc1)!=cc1);
        assertTrue(cc2.compareNbPoints(new CarteCitadelles(12,CouleurCarteCitadelles.BLEU, "Cathédrale", 5 ))==cc2);
    }
}
