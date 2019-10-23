package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Marchand extends Personnage {

    public Marchand(){
        this.numero =6;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du marchand");
    }
}
