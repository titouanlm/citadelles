package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;

public class Magicien extends Personnage {

    public Magicien(){
        this.numero = 3;
        this.nom = "Magicien";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction) {
        //System.out.println("Le magicien effectue sa spécialité ! \n");
    }
}
