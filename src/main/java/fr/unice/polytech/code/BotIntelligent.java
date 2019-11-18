package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.personnages.Personnage;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

public class BotIntelligent extends Bot {

    public BotIntelligent(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.strategieConstruit();
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage, Personnage personnageDefausseVisible) {
        //this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(this.nbPiece, this.cartesCitadellesEnMain,this.villeDuBot));
        if(piocheCartesPersonnage.contient("Roi")){
            this.setPersonnageACeTour(piocheCartesPersonnage.prendre("Roi"));
            System.out.println(this.getNom() + " a pris le personnage Roi.");
        }else{
            Personnage pChoisi = piocheCartesPersonnage.piocherPersonnageAleatoirement();
            this.setPersonnageACeTour(pChoisi);
            System.out.println(this.getNom() + " a pris le personnage " + pChoisi.getNom() + ".");
        }
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            System.out.println("Prend 2 pièces.");
            this.ajouterPiece(2);
        } else {
            CarteCitadelles carteChoisie;
            CarteCitadelles cartePiochee1 = piocheCartesCitadelles.piocher();
            CarteCitadelles cartePiochee2 = piocheCartesCitadelles.piocher();

            if(cartePiochee2!=null){
                System.out.println("Pioche " + cartePiochee1.getNom() + " et " + cartePiochee2.getNom());
                // On teste si le joueur n'a pas déjà construit les quartiers ou les possède déjà dans sa main
                if(!this.getVilleDuBot().contient(cartePiochee1) && !this.getVilleDuBot().contient(cartePiochee2) &&
                        !this.contientDansSaMain(cartePiochee1) && !this.contientDansSaMain(cartePiochee2)){

                    if(cartePiochee1.getCouleur().toString().equals("VIOLET") && cartePiochee2.getCouleur().toString().equals("VIOLET")){
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }else if(cartePiochee1.getCouleur().toString().equals("VIOLET")){ //Prend la première carte si elle est violette
                        carteChoisie=cartePiochee1;
                    }else if(cartePiochee2.getCouleur().toString().equals("VIOLET")){//Prend la deuxième carte si elle est violette
                        carteChoisie=cartePiochee2;
                    }else{
                        carteChoisie= cartePiochee1.compareNbPoints(cartePiochee2); //Prend la carte ayant le plus de point
                    }
                }else if(!this.getVilleDuBot().contient(cartePiochee1) && !this.contientDansSaMain(cartePiochee1)){ //Prend la première carte s'il possède déjà la 2ème
                    carteChoisie=cartePiochee1;
                }else if(!this.getVilleDuBot().contient(cartePiochee2) && !this.contientDansSaMain(cartePiochee2)){ //Prend la 2ème carte s'il possède déjà la première
                    carteChoisie=cartePiochee2;
                }else{ //Prend la première carte si il possède déjà les deux
                    carteChoisie=cartePiochee1;
                }

                this.ajouterCartesCitadellesDansMain(carteChoisie);

                //On remet dans la pioche la carte que l'on a pas choisi
                if(carteChoisie==cartePiochee1){
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee2);
                }else{
                    piocheCartesCitadelles.ajouterCarteCitadelles(cartePiochee1);
                }
            }else{
                carteChoisie=cartePiochee1;
                this.ajouterCartesCitadellesDansMain(carteChoisie);
            }
            System.out.println("Choisit de prendre la carte " + carteChoisie.getNom() + " dans sa main.");
        }
    }

    public int determinerChoixPiocherOuPiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (piocheCartesCitadelles.nbCartesRestantes() > 0 && getNbPiece() >= 5) {
            return 0;
        } else {
            return 1;
        }
    }

    public void strategieConstruit() { //Construire le plus gros batiment d'un coup
        int i = 1;
        if (this.personnageACeTour instanceof Architecte) {
            i = 3;
        }
        while (i > 0) {
            CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
            if (carteEnMainDePlusHauteValeur != null) {
                this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
                this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
                this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
            }
            i--;
        }
    }

    public CarteCitadelles rechercheCartePlusHauteValeurConstruisable() {
        int valeurCartePlusHauteEnMain = 0;
        CarteCitadelles quartierAConstruire = null;
        for (CarteCitadelles carteEnMain : cartesCitadellesEnMain) {
            if (nbPiece >= carteEnMain.getPoint() && !villeDuBot.contient(carteEnMain)
                    && valeurCartePlusHauteEnMain < carteEnMain.getPoint()) {
                valeurCartePlusHauteEnMain = carteEnMain.getPoint();
                quartierAConstruire = carteEnMain;
            }
        }
        return quartierAConstruire;
    }
}
