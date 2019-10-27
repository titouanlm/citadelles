package fr.unice.polytech.code.pioches;

import fr.unice.polytech.code.CarteCitadelles;
import fr.unice.polytech.code.CouleurCarteCitadelles;

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
        CarteCitadelles cartePiochee = piocheCC.get(0);
        piocheCC.remove(0);
        return cartePiochee;
    }

    public int nbCartesRestantes(){
        return this.piocheCC.size();
    }

    public void implementerCartesCitadelles() {
        CarteCitadelles[] cc = new CarteCitadelles[65];
        cc[0] = new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[1] = new CarteCitadelles(2,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[2] = new CarteCitadelles(3,CouleurCarteCitadelles.BLEU, "Temple", 1 );
        cc[3] = new CarteCitadelles(4,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[4] = new CarteCitadelles(5,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[5] = new CarteCitadelles(6,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[6] = new CarteCitadelles(7,CouleurCarteCitadelles.BLEU, "Eglise", 2 );
        cc[7] = new CarteCitadelles(8,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[8] = new CarteCitadelles(9,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[9] = new CarteCitadelles(10,CouleurCarteCitadelles.BLEU, "Monastère", 3 );
        cc[10] = new CarteCitadelles(11,CouleurCarteCitadelles.BLEU, "Cathédrale", 5 );
        cc[11] = new CarteCitadelles(12,CouleurCarteCitadelles.BLEU, "Cathédrale", 5 );

        cc[12] = new CarteCitadelles(13,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[13] = new CarteCitadelles(14,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[14] = new CarteCitadelles(15,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[15] = new CarteCitadelles(16,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[16] = new CarteCitadelles(17,CouleurCarteCitadelles.JAUNE, "Manoir", 3 );
        cc[17] = new CarteCitadelles(18,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[18] = new CarteCitadelles(19,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[19] = new CarteCitadelles(20,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[20] = new CarteCitadelles(21,CouleurCarteCitadelles.JAUNE, "Château", 4 );
        cc[21] = new CarteCitadelles(22,CouleurCarteCitadelles.JAUNE, "Palais", 5 );
        cc[22] = new CarteCitadelles(23,CouleurCarteCitadelles.JAUNE, "Palais", 5 );

        cc[23] = new CarteCitadelles(24,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[24] = new CarteCitadelles(25,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[25] = new CarteCitadelles(26,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[26] = new CarteCitadelles(27,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[27] = new CarteCitadelles(28,CouleurCarteCitadelles.VERT, "Taverne", 1 );
        cc[28] = new CarteCitadelles(29,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[29] = new CarteCitadelles(30,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[30] = new CarteCitadelles(31,CouleurCarteCitadelles.VERT, "Échoppe", 2 );
        cc[31] = new CarteCitadelles(32,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[32] = new CarteCitadelles(33,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[33] = new CarteCitadelles(34,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[34] = new CarteCitadelles(35,CouleurCarteCitadelles.VERT, "Marché", 2 );
        cc[35] = new CarteCitadelles(36,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[36] = new CarteCitadelles(37,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[37] = new CarteCitadelles(38,CouleurCarteCitadelles.VERT, "Comptoir", 3 );
        cc[38] = new CarteCitadelles(39,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[39] = new CarteCitadelles(40,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[40] = new CarteCitadelles(41,CouleurCarteCitadelles.VERT, "Port", 4 );
        cc[41] = new CarteCitadelles(42,CouleurCarteCitadelles.VERT, "Hôtel de ville", 5 );
        cc[42] = new CarteCitadelles(43,CouleurCarteCitadelles.VERT, "Hôtel de ville", 5 );

        cc[43] = new CarteCitadelles(44,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[44] = new CarteCitadelles(45,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[45] = new CarteCitadelles(46,CouleurCarteCitadelles.ROUGE, "Tour de guet", 1 );
        cc[46] = new CarteCitadelles(47,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[47] = new CarteCitadelles(48,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[48] = new CarteCitadelles(49,CouleurCarteCitadelles.ROUGE, "Prison", 2 );
        cc[49] = new CarteCitadelles(50,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[50] = new CarteCitadelles(51,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[51] = new CarteCitadelles(52,CouleurCarteCitadelles.ROUGE, "Caserne", 3 );
        cc[52] = new CarteCitadelles(53,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 );
        cc[53] = new CarteCitadelles(54,CouleurCarteCitadelles.ROUGE, "Forteresse", 5 );

        cc[54] = new CarteCitadelles(55,CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2 );
        cc[55] = new CarteCitadelles(56,CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
        cc[56] = new CarteCitadelles(57,CouleurCarteCitadelles.VIOLET, "Donjon", 3 );
        cc[57] = new CarteCitadelles(58,CouleurCarteCitadelles.VIOLET, "Laboratoire", 5 );
        cc[58] = new CarteCitadelles(59,CouleurCarteCitadelles.VIOLET, "Manufacture", 5 );
        cc[59] = new CarteCitadelles(60,CouleurCarteCitadelles.VIOLET, "Observatoire", 5 );
        cc[60] = new CarteCitadelles(61,CouleurCarteCitadelles.VIOLET, "Cimitière", 5 );
        cc[61] = new CarteCitadelles(62,CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6 );
        cc[62] = new CarteCitadelles(63,CouleurCarteCitadelles.VIOLET, "École de magie", 6 );
        cc[63] = new CarteCitadelles(64,CouleurCarteCitadelles.VIOLET, "Universitè", 8 );
        cc[64] = new CarteCitadelles(65,CouleurCarteCitadelles.VIOLET, "Dracopert", 8 );

        for(int i=0; i<65;i++){
            this.ajouterCarteCitadelles(cc[i]);
        }
    }
}
