package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Marchand extends Personnage {

    Marchand(){
        this.numero =6;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du marchand");
    }
}
