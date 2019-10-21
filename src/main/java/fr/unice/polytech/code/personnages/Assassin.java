package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Assassin extends Personnage {

    Assassin(){
        this.numero =1;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du assassin");
    }
}
