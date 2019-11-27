package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;
import java.util.Random;

public class CourDesMiracles extends CarteCitadellesAvecPouvoir {

    public CourDesMiracles(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(CarteCitadellesAvecPouvoir carte, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        int indiceDernierQuartierConstruit = joueur.getVilleDuBot().getNbBatimentsConstruits();

        if (joueur.getVilleDuBot().getBatimentsConstruits().get(indiceDernierQuartierConstruit-1) instanceof CourDesMiracles){

        } else {

            int nbrBatRouge=joueur.getVilleDuBot().compterNbQuartiersRouge();
            int nbrBatVert=joueur.getVilleDuBot().compterNbQuartiersVert();
            int nbrBatBleu=joueur.getVilleDuBot().compterNbQuartiersBleu();
            int nbrBatJaune=joueur.getVilleDuBot().compterNbQuartiersJaune();
            int nbrBatViolet=joueur.getVilleDuBot().compterNbQuartiersViolet();

            ArrayList<Integer> couleursVilleDuBot = new ArrayList<>();

            couleursVilleDuBot.add(nbrBatJaune);
            couleursVilleDuBot.add(nbrBatBleu);
            couleursVilleDuBot.add(nbrBatVert);
            couleursVilleDuBot.add(nbrBatRouge);
            couleursVilleDuBot.add(nbrBatViolet);

            int cpt=0;
            int x=0;


            for (int i=0;i<4;i++){
                if (couleursVilleDuBot.get(i)==0){
                    cpt++;
                    x=i;
                }
            }
            if(cpt==1){
                if(x==0){
                    this.setCouleur(CouleurCarteCitadelles.JAUNE);
                }
                else if (x==1){
                    this.setCouleur(CouleurCarteCitadelles.BLEU);
                }
                else if (x==2){
                    this.setCouleur(CouleurCarteCitadelles.VERT);
                }
                else if (x==3){
                    this.setCouleur(CouleurCarteCitadelles.ROUGE);
                }
                else{
                    this.setCouleur(CouleurCarteCitadelles.VIOLET);
                }

            }
        }
    }

}
