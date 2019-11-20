package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.bot.Bot;
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

    public abstract void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction , PiocheCartesCitadelles piocheCartesCitadelles);
}
