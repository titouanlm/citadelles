package fr.unice.polytech.code;

public abstract class Personnage {

    protected int numero;
    protected String nom;

    public abstract int getNumero();

    public abstract String getNom();

    public abstract void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction);
}
