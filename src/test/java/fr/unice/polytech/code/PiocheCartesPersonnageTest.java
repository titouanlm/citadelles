package fr.unice.polytech.code;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PiocheCartesPersonnageTest {
    private Affichage affichage =new Affichage(1);
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage(affichage);

    @Test
    void implementerCartesPersonnageTest(){
        assertEquals(0, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

    @Test
    void piocherPersonnageAleatoireTest(){
        assertNull(piocheCartesPersonnage.piocherPersonnageAleatoirement());
        piocheCartesPersonnage.implementerCartesPersonnage();
        assertNotNull(piocheCartesPersonnage.piocherPersonnageAleatoirement());
    }

    @Test
    void reinitialiserTest(){
        piocheCartesPersonnage.implementerCartesPersonnage();
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        piocheCartesPersonnage.piocherPersonnageAleatoirement();
        assertEquals(6, piocheCartesPersonnage.getPiocheCP().size());
        piocheCartesPersonnage.reinitialiser();
        assertEquals(8, piocheCartesPersonnage.getPiocheCP().size());
    }

}
