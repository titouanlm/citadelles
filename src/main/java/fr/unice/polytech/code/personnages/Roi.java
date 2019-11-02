package fr.unice.polytech.code.personnages;

import fr.unice.polytech.code.Bot;
import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;
import fr.unice.polytech.code.Personnage;

public class Roi extends Personnage {

    public Roi(){
        this.numero =4;
        this.nom = "Roi";
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public void effectuerSpecialite(Bot joueurQuiEffectueAction, Bot joueurQuiSubitAction) {
        //System.out.println("Le roi effectue sa spécialité ! \n");
        if(joueurQuiEffectueAction.getPersonnageACeTour()instanceof Roi){
            for(CarteCitadelles c : joueurQuiEffectueAction.getVilleDuBot().getBatimentsConstruits()){
                if(c.getCouleur()== CouleurCarteCitadelles.JAUNE){
                    joueurQuiEffectueAction.ajouterPiece(1);
                }
            }
        }

    }
}
