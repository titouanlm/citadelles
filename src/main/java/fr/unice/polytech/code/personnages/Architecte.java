package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

public class Architecte extends Personnage {

    public Architecte(){
        this.numero = 7;
        this.nom = "Architecte";
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction,PiocheCartesCitadelles piocheCartesCitadelles) {
        //System.out.println("L'architecte effectue sa spécialité ! \n");
        joueurQuiEffectueAction.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());
        joueurQuiEffectueAction.ajouterCartesCitadellesDansMain(piocheCartesCitadelles.piocher());

    }
}
