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
    public void effectuerSpecialite(int numero, CouleurCarteCitadelles couleur, String nom, int point, Bot joueur, PiocheCartesCitadelles piocheCartesCitadelles) {
        if (joueur.getVilleDuBot().getBatimentsConstruits().get(-1).equals(CourDesMiracles.super.getNom())){

        }
        else {

            ArrayList<String> couleursVilleDuBot = new ArrayList<>();
            couleursVilleDuBot.add("JAUNE");
            couleursVilleDuBot.add("BLEU");
            couleursVilleDuBot.add("VERT");
            couleursVilleDuBot.add("ROUGE");
            couleursVilleDuBot.add("VIOLET");

            int j=0;
            int b=0;
            int v=0;
            int r=0;
            int vi=0;
            int cpt=0;
            int x=0;
            ArrayList<Integer> nbrCouleursVilleDuBot = new ArrayList<Integer>();
            nbrCouleursVilleDuBot.add(j);
            nbrCouleursVilleDuBot.add(b);
            nbrCouleursVilleDuBot.add(v);
            nbrCouleursVilleDuBot.add(r);
            nbrCouleursVilleDuBot.add(vi);

            for(CarteCitadelles batimentsConstruits :joueur.getVilleDuBot().getBatimentsConstruits()){
                if(batimentsConstruits.getCouleur()==CouleurCarteCitadelles.JAUNE){
                    j++;
                }
                if(batimentsConstruits.getCouleur()==CouleurCarteCitadelles.BLEU){
                    b++;
                }
                if(batimentsConstruits.getCouleur()==CouleurCarteCitadelles.VERT){
                    v++;
                }
                if(batimentsConstruits.getCouleur()==CouleurCarteCitadelles.ROUGE){
                    r++;
                }
                if(batimentsConstruits.getCouleur()==CouleurCarteCitadelles.VIOLET){
                    vi++;
                }
            }
            for (int i=0;i<4;i++){
                if (nbrCouleursVilleDuBot.get(i)==0){
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
            else {
                Random ra = new Random();
                int y=ra.nextInt(4);
                if(y==0){
                    this.setCouleur(CouleurCarteCitadelles.JAUNE);
                }
                else if (y==1){
                    this.setCouleur(CouleurCarteCitadelles.BLEU);
                }
                else if (y==2){
                    this.setCouleur(CouleurCarteCitadelles.VERT);
                }
                else if (y==3){
                    this.setCouleur(CouleurCarteCitadelles.ROUGE);
                }
                else{
                    this.setCouleur(CouleurCarteCitadelles.VIOLET);
                }
            }


        }
    }

}
