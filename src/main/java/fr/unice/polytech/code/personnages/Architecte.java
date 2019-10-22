package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Architecte extends Personnage {

    public Architecte(){
        this.numero = 7;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité de l'architecte");
    }
}
