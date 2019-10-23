package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Roi extends Personnage {

    public Roi(){
        this.numero =4;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du roi");
    }
}
