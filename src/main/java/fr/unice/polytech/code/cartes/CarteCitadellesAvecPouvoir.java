package fr.unice.polytech.code.cartes;

public abstract class CarteCitadellesAvecPouvoir extends CarteCitadelles {
    public CarteCitadellesAvecPouvoir(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        super(numero, couleur, nom, point);
    }

    public abstract void effectuerSpecialite();
}
