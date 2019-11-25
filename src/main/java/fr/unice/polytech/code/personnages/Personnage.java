package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bots.*;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public abstract class Personnage {

    protected int numero;
    protected String nom;

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

}