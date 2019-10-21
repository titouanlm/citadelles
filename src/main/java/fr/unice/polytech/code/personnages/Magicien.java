package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Magicien extends Personnage {

    Magicien(){
        this.numero = 3;
    }
    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité du magicien");
    }
}
