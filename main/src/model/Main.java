package model;

public class Main {
    public static void main (String args[]) {
        Jeu leJeu = new Jeu();
        leJeu.setNiveau1();
        leJeu.afficherJeu();
        leJeu.parseTextFromInput();
    }
}
    