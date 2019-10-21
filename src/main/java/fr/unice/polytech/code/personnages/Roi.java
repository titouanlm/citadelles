package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Roi extends Personnage {

    Roi(){
        this.numero =4;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du roi");
    }
}
