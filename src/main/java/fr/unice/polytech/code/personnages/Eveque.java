package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Eveque extends Personnage {

    Eveque(){
        this.numero =5;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité de l'évèque");
    }
}
