package fr.unice.polytech.code;

public class CarteCitadelles {

    private int numero;
    private CouleurCarteCitadelles couleur;
    private String nom;
    private int point;

    public CarteCitadelles(int numero, CouleurCarteCitadelles couleur, String nom, int point) {
        this.numero = numero;
        this.couleur = couleur;
        this.nom = nom;
        this.point = point;
    }

    public int getNumero() {
        return numero;
    }

    public CouleurCarteCitadelles getCouleur() {
        return couleur;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint() {
        return point;
    }

    public CarteCitadelles compareNbPoints(CarteCitadelles c2){
        if(this.getPoint() >= c2.getPoint()){
            return this;
        }else{
            return c2;
        }
    }
}
