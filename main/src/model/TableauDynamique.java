package model;

import java.util.ArrayList;

public class TableauDynamique extends Fichier {

    private ArrayList<Integer> tableauDynamique;

    public TableauDynamique(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
    }

    public void make() {
        tableauDynamique = new ArrayList<>();
    }

    public void seek() {
        // La commande SEEK ne fait rien dans ce cas
    }

    public int read() {
        int lastIndex = tableauDynamique.size() - 1;
        return tableauDynamique.remove(lastIndex);
    }

    public void write(int i) {
        tableauDynamique.add(i);
    }

    public void voidF() {
        int lastIndex = tableauDynamique.size() - 1;
        tableauDynamique.remove(lastIndex);
    }

    public boolean testEOF() {
        return tableauDynamique.isEmpty();
    }
}
