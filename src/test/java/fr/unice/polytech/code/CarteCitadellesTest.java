package fr.unice.polytech.code;

import fr.unice.polytech.code.cartes.CarteCitadelles;
import fr.unice.polytech.code.cartes.CarteCitadellesSansPouvoir;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CarteCitadellesTest {

    private CarteCitadelles cc1;
    private CarteCitadelles cc2;

    @BeforeEach
    public void setUp() {
        cc1 = new CarteCitadellesSansPouvoir(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3);
        cc2 = new CarteCitadellesSansPouvoir(58, CouleurCarteCitadelles.VIOLET, "Laboratoire", 5);
    }

    @Test
    public void compareNbPointsTest(){
        assertSame(cc1.compareNbPoints(cc2), cc2);
        assertNotSame(cc2.compareNbPoints(cc1), cc1);
        assertSame(cc2.compareNbPoints(new CarteCitadellesSansPouvoir(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5)), cc2);
    }
}
