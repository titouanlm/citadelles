package fr.unice.polytech.code;

import fr.unice.polytech.code.personnages.Architecte;
import fr.unice.polytech.code.pioches.PiocheCartesCitadelles;
import fr.unice.polytech.code.pioches.PiocheCartesPersonnage;

import java.util.ArrayList;

public class BotIntelligent extends Bot {

    public BotIntelligent(String nom, String couleur) {
        super(nom, couleur);
    }

    @Override
    public void strategie(PiocheCartesCitadelles piocheCartesCitadelles) {
        this.choisirPiocherOuPrendrePiece(piocheCartesCitadelles);
        this.strategieConstruit();
    }

    @Override
    public void choixDuPersonnagePourLeTour(PiocheCartesPersonnage piocheCartesPersonnage) {
        this.setPersonnageACeTour(piocheCartesPersonnage.piocherPersonnageNonAleatoirement(this.nbPiece, this.cartesCitadellesEnMain,this.villeDuBot));
    }

    @Override
    public void choisirPiocherOuPrendrePiece(PiocheCartesCitadelles piocheCartesCitadelles) {
        if (this.determinerChoixPiocherOuPiece(piocheCartesCitadelles) == 1) {
            this.ajouterPiece(2);
        } else {
            ArrayList<CarteCitadelles> cartesCitadellesIntermedieaire = new ArrayList<>();

            cartesCitadellesIntermedieaire.add(piocheCartesCitadelles.piocher());// Peut être déterminée par le joueur
            cartesCitadellesIntermedieaire.add(piocheCartesCitadelles.piocher());

            if(cartesCitadellesIntermedieaire.get(0)!=null && cartesCitadellesIntermedieaire.get(1)!=null) {

                CarteCitadelles carteAchoisir;

                String couleur = "";
                ArrayList<String> couleursVilleDuBot = new ArrayList<>();
                couleursVilleDuBot.add("JAUNE");
                couleursVilleDuBot.add("BLEU");
                couleursVilleDuBot.add("VERT");
                couleursVilleDuBot.add("ROUGE");
                couleursVilleDuBot.add("VIOLET");

                int j = 0;
                int b = 0;
                int v = 0;
                int r = 0;
                int vi = 0;
                int max = j;
                int x = 0;
                ArrayList<Integer> nbrCouleursVilleDuBot = new ArrayList<Integer>();
                nbrCouleursVilleDuBot.add(j);
                nbrCouleursVilleDuBot.add(b);
                nbrCouleursVilleDuBot.add(v);
                nbrCouleursVilleDuBot.add(r);
                nbrCouleursVilleDuBot.add(vi);

                for (CarteCitadelles batimentsConstruits : this.getVilleDuBot().getBatimentsConstruits()) {
                    if (batimentsConstruits.getCouleur() == CouleurCarteCitadelles.JAUNE) {
                        j++;
                    }
                    if (batimentsConstruits.getCouleur() == CouleurCarteCitadelles.BLEU) {
                        b++;
                    }
                    if (batimentsConstruits.getCouleur() == CouleurCarteCitadelles.VERT) {
                        v++;
                    }
                    if (batimentsConstruits.getCouleur() == CouleurCarteCitadelles.ROUGE) {
                        r++;
                    }
                    if (batimentsConstruits.getCouleur() == CouleurCarteCitadelles.VIOLET) {
                        vi++;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (nbrCouleursVilleDuBot.get(i) < nbrCouleursVilleDuBot.get(i + 1)) {
                        max = nbrCouleursVilleDuBot.get(i);
                        x = 0;
                    }
                }
                for (int i = 0; i < 4; i++) {
                    if (x == i) {
                        couleur = couleursVilleDuBot.get(i);
                    }
                }

                for (CarteCitadelles carte : cartesCitadellesIntermedieaire) {
                    if (max > 1) {
                        if (carte.getCouleur().equals(couleur)) {
                            carteAchoisir = carte;
                        }
                    } else {
                        if (cartesCitadellesIntermedieaire.get(0).getPoint() < cartesCitadellesIntermedieaire.get(1).getPoint()) {
                            if (this.getVilleDuBot().getNbBatimentsConstruits() == 7) {
                                carteAchoisir = cartesCitadellesIntermedieaire.get(0);
                            } else {
                                carteAchoisir = cartesCitadellesIntermedieaire.get(1);
                            }
                        }
                        carteAchoisir = cartesCitadellesIntermedieaire.get(0);
                    }
                }
            }
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
            boolean aConstruit = false;
            CarteCitadelles carteEnMainDePlusHauteValeur = this.rechercheCartePlusHauteValeurConstruisable();
            if (carteEnMainDePlusHauteValeur != null) {
                this.retirerPiece(carteEnMainDePlusHauteValeur.getPoint());
                this.villeDuBot.construireBatiment(carteEnMainDePlusHauteValeur);
                //System.out.println(this.nom + " a construit le batiment " + quartierÀConstruire.getNom() + " dans sa ville.");
                this.cartesCitadellesEnMain.remove(carteEnMainDePlusHauteValeur);
                aConstruit = true;
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


