package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class File extends Fichier {

    private Queue<Integer> elements;
    private int p;

    public File(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        elements = new LinkedList<>();
        this.p=0;
    }

    public File(String num, int abscisse, int ordonnee, int caseJ,LinkedList<Integer> elements) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = elements;
        this.p=0;
    }

    public Queue<Integer> getElements() {return elements;}

    @Override
    public int getPosFichier() {return this.p;}

    @Override
    public void SEEK(int n) {
        return;// La commande SEEK ne fait rien dans ce cas
    }

    @Override
    public int F() {
        if (!TEST_EOF()) {
            return elements.poll();
        }
        else return 0;
    }

    @Override
    public void F(int i){
        elements.offer(i);
    } 

    @Override
    public void VOID_F() {
        if (TEST_EOF()) return;
        else this.elements.poll();
    }

    @Override
    public boolean TEST_EOF() {
        return elements.isEmpty();
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
}