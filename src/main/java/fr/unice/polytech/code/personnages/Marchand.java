package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.Personnage;

public class Marchand extends Personnage {

    public Marchand(){
        this.numero =6;
        this.nom = "Marchand";
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getNom() {
        return nom;
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction) {
        //System.out.println("Le marchand effectue sa spécialité ! \n");
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Marchand){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.VERT){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }
    }
}
