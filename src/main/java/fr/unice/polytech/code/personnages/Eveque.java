package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Personnage;

public class Eveque extends Personnage {

    public Eveque(){
        this.numero =5;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void effectuerSpecialite() {
        System.out.println("Spécialité de l'évèque");
    }
}
