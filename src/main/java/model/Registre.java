package model;

public class Registre {
    private int valeur;

    public Registre() {
        this.valeur = 0; // Initialise la valeur du registre à zéro par défaut
    }

    public Registre(int valeurInitiale) {
        this.valeur = valeurInitiale;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public void incrementer() {
        this.valeur++;
    }

    public void decrementer() {
        this.valeur--;
    }

    public void ajouter(int valeurAjoutee) {
        this.valeur += valeurAjoutee;
    }

    public void soustraire(int valeurSoustraite) {
        this.valeur -= valeurSoustraite;
    }

    public void effacer() {
        this.valeur = 0;
    }

    @Override
    public String toString() {
        return "Registre : " + valeur;
    }
}
