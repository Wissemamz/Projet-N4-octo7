package model;

import java.util.ArrayList;

public class TableauDynamique extends Fichier {

    public ArrayList<Integer> elements;
    private int p;

    public TableauDynamique(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = new ArrayList<>(); 
        this.p=0;
    }

    public TableauDynamique(String num, int abscisse, int ordonnee, int caseJ,ArrayList<Integer> elements) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = elements;
        this.p=0;
    }

    public ArrayList<Integer> getElements() {return this.elements;}

    @Override
    public int getPosFichier() {return this.p;}

    public void setPos(int pos) {this.p = pos;}

    @Override
    public void SEEK(int n) {
        if (p+n<0) {
            p=0;
        }
        else if (p+n>=elements.size()){
            p=elements.size()-1;
        }
        else{
            p=p+n;
        }
    }

    @Override
    public int F() {
        if (!TEST_EOF()) {
            Integer i = elements.get(p);
            p++;
            return i.intValue();
        }
        else return 0;
    }

    @Override
    public void F(int i){
        if (!TEST_EOF()) {
            this.elements.set(p,i);
        }
        else this.elements.add(i);
        p++;
    } 

    @Override
    public boolean TEST_EOF(){
        return (p==elements.size());
    }

    @Override
    public void VOID_F() {
        if (TEST_EOF()) return;
        else this.elements.remove(p);
    }

    @Override 
    public boolean meme_elements(Fichier fichier) {
        if (fichier instanceof TableauDynamique) {
            TableauDynamique t = (TableauDynamique) fichier;
            return t.getElements().containsAll(elements) && this.elements.containsAll(t.getElements());
        }
        else if (fichier instanceof Pile) {
            Pile p = (Pile) fichier;
            return p.getElements().containsAll(elements) && this.elements.containsAll(p.getElements());
        }
        else if (fichier instanceof File) {
            File f = (File) fichier;
            return f.getElements().containsAll(elements) && this.elements.containsAll(f.getElements());
        }
        else return false;
    }   

    @Override
    public String[] elemsToString(){
        String[] elems = new String[elements.size()];
        for (int i = 0; i <elements.size();i++) {
            elems[i] = elements.get(i).toString();
        }
        return elems;
    }
}