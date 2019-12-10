package fr.unice.polytech.code.cartes;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;

import java.util.ArrayList;

public class CourDesMiracles extends CarteCitadellesAvecPouvoir {

    public CourDesMiracles(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    @Override
    public void effectuerSpecialite(CarteCitadellesAvecPouvoir carte, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        int indiceDernierQuartierConstruit = joueur.getVilleDuBot().getNbBatimentsConstruits();
        if (!(joueur.getVilleDuBot().getBatimentsConstruits().get(indiceDernierQuartierConstruit-1) instanceof CourDesMiracles)){
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

            int nbCouleursAbsentes=0;
            int indiceCouleurAbsente=0;

            for (int i=0;i<4;i++){
                if (couleursVilleDuBot.get(i)==0){
                    nbCouleursAbsentes++;
                    indiceCouleurAbsente=i;
                }
            }

            if(nbCouleursAbsentes==1){
                if(indiceCouleurAbsente==0){
                    carte.setCouleur(CouleurCarteCitadelles.JAUNE);
                }else if (indiceCouleurAbsente==1){
                    carte.setCouleur(CouleurCarteCitadelles.BLEU);
                }else if (indiceCouleurAbsente==2){
                    carte.setCouleur(CouleurCarteCitadelles.VERT);
                }else if (indiceCouleurAbsente==3){
                    carte.setCouleur(CouleurCarteCitadelles.ROUGE);
                }
            }
        }
    }

}
