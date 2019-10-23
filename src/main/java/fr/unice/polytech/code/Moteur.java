package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.*;

import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;
/**
 Créer une liste de carte et pas un tableau
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Moteur {

    //private PiocheCartesCitadelles piocheCartesCitadelles = new PiocheCartesCitadelles();
    private PiocheCartesPersonnage piocheCartesPersonnage = new PiocheCartesPersonnage();
    private ArrayList<CarteCitadelles> cc;//pioche

    Moteur() {
    }

    void lancerUnePartie(ArrayList<Bot> listeJoueurs) {
        this.implementerCartesCitadelles();
        this.implementerCartesPersonnage();
        this.initialiserPartie(listeJoueurs);
        progressionDeLaPartieAkaDeroulementDesTours(listeJoueurs);

    }


    private void implementerCartesCitadelles() {
        cc = new ArrayList<>();
        cc.add(new CarteCitadelles(1, CouleurCarteCitadelles.BLEU, "Temple", 1));
        cc.add(new CarteCitadelles(2, CouleurCarteCitadelles.BLEU, "Temple", 1));
        cc.add(new CarteCitadelles(3, CouleurCarteCitadelles.BLEU, "Temple", 1));

        cc.add(new CarteCitadelles(4, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        cc.add(new CarteCitadelles(5, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        cc.add(new CarteCitadelles(6, CouleurCarteCitadelles.BLEU, "Eglise", 2));
        cc.add(new CarteCitadelles(7, CouleurCarteCitadelles.BLEU, "Eglise", 2));

        cc.add(new CarteCitadelles(8, CouleurCarteCitadelles.BLEU, "Menastère", 3));
        cc.add(new CarteCitadelles(9, CouleurCarteCitadelles.BLEU, "Menastère", 3));
        cc.add(new CarteCitadelles(10, CouleurCarteCitadelles.BLEU, "Menastère", 3));

        cc.add(new CarteCitadelles(11, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));
        cc.add(new CarteCitadelles(12, CouleurCarteCitadelles.BLEU, "Cathédrale", 5));

        cc.add(new CarteCitadelles(13, CouleurCarteCitadelles.JAUNE, "Manoire", 3));
        cc.add(new CarteCitadelles(14, CouleurCarteCitadelles.JAUNE, "Manoire", 3));
        cc.add(new CarteCitadelles(15, CouleurCarteCitadelles.JAUNE, "Manoire", 3));
        cc.add(new CarteCitadelles(16, CouleurCarteCitadelles.JAUNE, "Manoire", 3));
        cc.add(new CarteCitadelles(17, CouleurCarteCitadelles.JAUNE, "Manoire", 3));

        cc.add(new CarteCitadelles(18, CouleurCarteCitadelles.JAUNE, "Château", 4));
        cc.add(new CarteCitadelles(19, CouleurCarteCitadelles.JAUNE, "Château", 4));
        cc.add(new CarteCitadelles(20, CouleurCarteCitadelles.JAUNE, "Château", 4));
        cc.add(new CarteCitadelles(21, CouleurCarteCitadelles.JAUNE, "Château", 4));

        cc.add(new CarteCitadelles(22, CouleurCarteCitadelles.JAUNE, "Palais", 5));
        cc.add(new CarteCitadelles(23, CouleurCarteCitadelles.JAUNE, "Palais", 5));

        cc.add(new CarteCitadelles(24, CouleurCarteCitadelles.VERT, "Taverne", 1));
        cc.add(new CarteCitadelles(25, CouleurCarteCitadelles.VERT, "Taverne", 1));
        cc.add(new CarteCitadelles(26, CouleurCarteCitadelles.VERT, "Taverne", 1));
        cc.add(new CarteCitadelles(27, CouleurCarteCitadelles.VERT, "Taverne", 1));
        cc.add(new CarteCitadelles(28, CouleurCarteCitadelles.VERT, "Taverne", 1));

        cc.add(new CarteCitadelles(29, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        cc.add(new CarteCitadelles(30, CouleurCarteCitadelles.VERT, "Échoppe", 2));
        cc.add(new CarteCitadelles(31, CouleurCarteCitadelles.VERT, "Échoppe", 2));

        cc.add(new CarteCitadelles(32, CouleurCarteCitadelles.VERT, "Marché", 2));
        cc.add(new CarteCitadelles(33, CouleurCarteCitadelles.VERT, "Marché", 2));
        cc.add(new CarteCitadelles(34, CouleurCarteCitadelles.VERT, "Marché", 2));
        cc.add(new CarteCitadelles(35, CouleurCarteCitadelles.VERT, "Marché", 2));

        cc.add(new CarteCitadelles(36, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        cc.add(new CarteCitadelles(37, CouleurCarteCitadelles.VERT, "Comptoir", 3));
        cc.add(new CarteCitadelles(38, CouleurCarteCitadelles.VERT, "Comptoir", 3));

        cc.add(new CarteCitadelles(39, CouleurCarteCitadelles.VERT, "Port", 4));
        cc.add(new CarteCitadelles(40, CouleurCarteCitadelles.VERT, "Port", 4));
        cc.add(new CarteCitadelles(41, CouleurCarteCitadelles.VERT, "Port", 4));

        cc.add(new CarteCitadelles(42, CouleurCarteCitadelles.VERT, "Hôtel de ville ", 5));
        cc.add(new CarteCitadelles(43, CouleurCarteCitadelles.VERT, "Hôtel de ville ", 5));

        cc.add(new CarteCitadelles(44, CouleurCarteCitadelles.ROUGE, "Tour de guet ", 1));
        cc.add(new CarteCitadelles(45, CouleurCarteCitadelles.ROUGE, "Tour de guet ", 1));
        cc.add(new CarteCitadelles(46, CouleurCarteCitadelles.ROUGE, "Tour de guet ", 1));

        cc.add(new CarteCitadelles(47, CouleurCarteCitadelles.ROUGE, "Prison", 2));
        cc.add(new CarteCitadelles(48, CouleurCarteCitadelles.ROUGE, "Prison", 2));
        cc.add(new CarteCitadelles(49, CouleurCarteCitadelles.ROUGE, "Prison", 2));

        cc.add(new CarteCitadelles(50, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        cc.add(new CarteCitadelles(51, CouleurCarteCitadelles.ROUGE, "Caserne", 3));
        cc.add(new CarteCitadelles(52, CouleurCarteCitadelles.ROUGE, "Caserne", 3));

        cc.add(new CarteCitadelles(53, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));
        cc.add(new CarteCitadelles(54, CouleurCarteCitadelles.ROUGE, "Forteresse", 5));

        cc.add(new CarteCitadelles(55, CouleurCarteCitadelles.VIOLET, "Cour des miracles", 2));

        cc.add(new CarteCitadelles(56, CouleurCarteCitadelles.VIOLET, "Donjon", 3));
        cc.add(new CarteCitadelles(57, CouleurCarteCitadelles.VIOLET, "Donjon", 3));

        cc.add(new CarteCitadelles(58, CouleurCarteCitadelles.VIOLET, "Laboratoire", 5));

        cc.add(new CarteCitadelles(59, CouleurCarteCitadelles.VIOLET, "Manufacture", 5));

        cc.add(new CarteCitadelles(60, CouleurCarteCitadelles.VIOLET, "Observatoire", 5));

        cc.add(new CarteCitadelles(61, CouleurCarteCitadelles.VIOLET, "Cimitière", 5));

        cc.add(new CarteCitadelles(62, CouleurCarteCitadelles.VIOLET, "Bibliothèque", 6));

        cc.add(new CarteCitadelles(63, CouleurCarteCitadelles.VIOLET, "École de magie", 6));

        cc.add(new CarteCitadelles(64, CouleurCarteCitadelles.VIOLET, "Universitè", 8));

        cc.add(new CarteCitadelles(65, CouleurCarteCitadelles.VIOLET, "Dracopert", 8));


        //... pour les 65 cartes

    }

    private void implementerCartesPersonnage() {
        Personnage[] cp = new Personnage[8];
        cp[0] = new Assassin();
        cp[1] = new Voleur();
        cp[2] = new Magicien();
        cp[3] = new Roi();
        cp[4] = new Eveque();
        cp[5] = new Marchand();
        cp[6] = new Architecte();
        cp[7] = new Condottiere();

        for (int i = 0; i < 8; i++) {
            piocheCartesPersonnage.ajouterCartePersonnage(cp[i]);
        }
    }

    private void initialiserPartie(ArrayList<Bot> listeJoueurs) {
        Collections.shuffle(cc);
        piocheCartesPersonnage.melanger();
        int carteposition=0;

        for (Bot joueur : listeJoueurs) {
            joueur.ajouterPiece(2);
            for (int j = 0; j < 4; j++) {
                joueur.ajouterCartesCitadellesDansMain(cc.get(carteposition));
                cc.remove(0);
            }
        }

        //this.attributionCarteCouronneDebutPartie(listeJoueurs);

    }

    void progressionDeLaPartieAkaDeroulementDesTours(ArrayList<Bot> listeJoueurs) {
        int arreterlejeu=0;
        int choix ;
        int nombrecartedanslapioche=cc.size();
        int indiceJoueurPossedantCouronne;
        indiceJoueurPossedantCouronne = (int)(Math.random() * (listeJoueurs.size()-1));

        while (1 == 1) { //Titouan rage pas devant mon while (1==1)
            // ne pas prendre en compte la ligne blanche d'après pour le premier tour
            //chaque joueur se voit attribuer une carte personnage
            // on fait une condition : si personne n'a la carte roi alors on distribue la carte couronne aléatoirement
            indiceJoueurPossedantCouronne = (int)(Math.random() * (listeJoueurs.size()-1)); /** le roi sera à la n-ième position */


            for (int i=indiceJoueurPossedantCouronne; i < listeJoueurs.size() ;i++) { /** le joueur qui a la carte couronne commence */

                if (nombrecartedanslapioche>0){choix = (int)(Math.random());} /** on lui propose de choisir entre 1 carte quartier ou 2 pieces */
                else {choix=1;}                  /** mais on vérifie qu'il y a encore des cartes dans la pioche */
                if (choix==1){
                    listeJoueurs.get(i).ajouterPiece(2);
                }
                    else{
                        listeJoueurs.get(i).ajouterCartesCitadellesDansMain(cc.get(choix));
                        cc.remove(0);
                        nombrecartedanslapioche-=1;
                }

                listeJoueurs.get(i).godstrat();    //penser à mettre le if d'après dans god strat pour arreter la partie au bon moment
                if (listeJoueurs.get(i).getVilleDuBot().getNbBatimentsConstruits() == 8) { /** on arrete le jeu si qq'un possède 8 quartiers*/
                    arreterlejeu=1;
                }
            }
            for (int n2=0;n2<indiceJoueurPossedantCouronne;n2++) {
                if (nombrecartedanslapioche>0){choix = (int)(Math.random());}
                else {choix=1;}
                if (choix==0){
                    listeJoueurs.get(n2).ajouterPiece(2);
                }
                else{
                    listeJoueurs.get(n2).ajouterCartesCitadellesDansMain(cc.get(0));
                    cc.remove(0);
                    nombrecartedanslapioche-=1;
                }
                listeJoueurs.get(n2).godstrat();
                if (listeJoueurs.get(n2).getVilleDuBot().getNbBatimentsConstruits() >= 8) {
                    arreterlejeu=1;


            }
            if (arreterlejeu==1){ //on sort du while, quelqu'un à 8 quartiers
                break;
            }


            }
        }}




    /*private void attributionCarteCouronneDebutPartie(ArrayList<Bot> listeJoueurs) {

    }*/



}
