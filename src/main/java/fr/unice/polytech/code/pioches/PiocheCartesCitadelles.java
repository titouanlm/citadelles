package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.bots.Bot;
import fr.unice.polytech.code.cartes.*;
import fr.unice.polytech.code.cartes.CouleurCarteCitadelles;

import java.util.ArrayList;
import java.util.Collections;

public class PiocheCartesCitadelles {
    private ArrayList<CarteCitadelles> piocheCC;

    public PiocheCartesCitadelles() {
        this.piocheCC = new ArrayList<>();
    }

    public ArrayList<CarteCitadelles> getPiocheCC() {
        return piocheCC;
    }

    public void ajouterCarteCitadelles(CarteCitadelles cc) {
        this.piocheCC.add(cc);
    }

    public void melanger(){
        Collections.shuffle(this.piocheCC);
    }

    public CarteCitadelles piocher(){
        if(piocheCC.size()>0){
            CarteCitadelles cartePiochee = piocheCC.get(0);
            piocheCC.remove(0);
            return cartePiochee;
        }
        return null;
    }

    public int nbCartesRestantes(){
        return this.piocheCC.size();
    }

    public void implementerCartesCitadelles() {
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadellesSansPouvoir(1, CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadellesSansPouvoir(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[2] = new CarteCitadellesSansPouvoir(3,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[3] = new CarteCitadellesSansPouvoir(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[4] = new CarteCitadellesSansPouvoir(5,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[5] = new CarteCitadellesSansPouvoir(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[6] = new CarteCitadellesSansPouvoir(7,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[7] = new CarteCitadellesSansPouvoir(8,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[8] = new CarteCitadellesSansPouvoir(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[9] = new CarteCitadellesSansPouvoir(10,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[10] = new CarteCitadellesSansPouvoir(11,CouleurCarteCitadelles.BLEU, "Cathédrale", 5 );
        cc[11] = new CarteCitadellesSansPouvoir(12,CouleurCarteCitadelles.BLEU, "Cathédrale", 5 );

        cc[12] = new CarteCitadellesSansPouvoir(13,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[13] = new CarteCitadellesSansPouvoir(14,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[14] = new CarteCitadellesSansPouvoir(15,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[15] = new CarteCitadellesSansPouvoir(16,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[16] = new CarteCitadellesSansPouvoir(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[17] = new CarteCitadellesSansPouvoir(18,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[18] = new CarteCitadellesSansPouvoir(19,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[19] = new CarteCitadellesSansPouvoir(20,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[20] = new CarteCitadellesSansPouvoir(21,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[21] = new CarteCitadellesSansPouvoir(22,CouleurCarteCitadelles.JAUNE, "Palais", 5 );
        cc[22] = new CarteCitadellesSansPouvoir(23,CouleurCarteCitadelles.JAUNE, "Palais", 5 );

        cc[23] = new CarteCitadellesSansPouvoir(24,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[24] = new CarteCitadellesSansPouvoir(25,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[25] = new CarteCitadellesSansPouvoir(26,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[26] = new CarteCitadellesSansPouvoir(27,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[27] = new CarteCitadellesSansPouvoir(28,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[28] = new CarteCitadellesSansPouvoir(29,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[29] = new CarteCitadellesSansPouvoir(30,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[30] = new CarteCitadellesSansPouvoir(31,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[31] = new CarteCitadellesSansPouvoir(32,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[32] = new CarteCitadellesSansPouvoir(33,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[33] = new CarteCitadellesSansPouvoir(34,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[34] = new CarteCitadellesSansPouvoir(35,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[35] = new CarteCitadellesSansPouvoir(36,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[36] = new CarteCitadellesSansPouvoir(37,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[37] = new CarteCitadellesSansPouvoir(38,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[38] = new CarteCitadellesSansPouvoir(39,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[39] = new CarteCitadellesSansPouvoir(40,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[40] = new CarteCitadellesSansPouvoir(41,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[41] = new CarteCitadellesSansPouvoir(42,CouleurCarteCitadelles.VERT, "Hôtel de ville", 5 );
        cc[42] = new CarteCitadellesSansPouvoir(43,CouleurCarteCitadelles.VERT, "Hôtel de ville", 5 );

        cc[43] = new CarteCitadellesSansPouvoir(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[44] = new CarteCitadellesSansPouvoir(45,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[45] = new CarteCitadellesSansPouvoir(46,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[46] = new CarteCitadellesSansPouvoir(47,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[47] = new CarteCitadellesSansPouvoir(48,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[48] = new CarteCitadellesSansPouvoir(49,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[49] = new CarteCitadellesSansPouvoir(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[50] = new CarteCitadellesSansPouvoir(51,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[51] = new CarteCitadellesSansPouvoir(52,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[52] = new CarteCitadellesSansPouvoir(53,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 );
        cc[53] = new CarteCitadellesSansPouvoir(54,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 );

        cc[54] = new CourDesMiracles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 );
        cc[55] = new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
        cc[56] = new Donjon(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
        cc[57] = new Laboratoire(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 );
        cc[58] = new Manufacture(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 );
        cc[59] = new Observatoire(60,CouleurCarteCitadelles.VIOLET, "Observatoire", 5 );
        cc[60] = new Cimitiere(61,CouleurCarteCitadelles.VIOLET, "Cimitière", 5 );
        cc[61] = new Bibliotheque(62,CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6 );
        cc[62] = new EcoleDeMagie(63,CouleurCarteCitadelles.VIOLET, "École de magie", 6 );
        cc[63] = new Universite(64,CouleurCarteCitadelles.VIOLET, "Université", 8 );
        cc[64] = new Dracopert(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 8 );

        for(int i=0; i<65;i++){
            this.ajouterCarteCitadelles(cc[i]);
        }
        this.melanger();
    }
}
