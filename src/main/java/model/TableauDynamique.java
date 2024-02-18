package model;

import java.util.ArrayList;

public class TableauDynamique extends Fichier {

    private ArrayList<Integer> elements;
    private int p;

    public TableauDynamique(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = new ArrayList<>();
        this.p = 0;
    }

    public TableauDynamique(String num, int abscisse, int ordonnee, int caseJ, ArrayList<Integer> elements) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = elements;
        this.p = 0;
    }

    @Override
    public int getPosFichier() {
        return this.p;
    }

    public void setPos(int pos) {
        this.p = pos;
    }

    @Override
    public void SEEK(int n) {
        if (p + n < 0) {
            p = 0;
        } else if (p + n >= elements.size()) {
            p = elements.size() - 1;
        } else {
            p = p + n;
        }
    }

    @Override
    public int F() {
        if (!TEST_EOF()) {
            Integer i = elements.get(p);
            p++;
            return i.intValue();
        } else
            return 0;
    }

    @Override
    public void F(int i) {
        if (!TEST_EOF()) {
            this.elements.set(p, i);
        } else
            this.elements.add(i);
        p++;
    }

    @Override
    public boolean TEST_EOF() {
        return (p == elements.size());
    }

    @Override
    public void VOID_F() {
        if (TEST_EOF())
            return;
        else
            this.elements.remove(p);
    }
}