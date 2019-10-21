package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Voleur extends Personnage {

    Voleur(){
        this.numero =2;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du voleur");
    }
}
