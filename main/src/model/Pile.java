package model;
import java.util.Stack;

public class Pile extends Fichier {
    
    public Stack<Integer> elements;
    private int p;

    public Pile(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        elements = new Stack<>();
        this.p=0;
    }

    public Pile(String num, int abscisse, int ordonnee, int caseJ,Stack<Integer> elements) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = elements;
        this.p=0;
    }

    public Stack<Integer> getElements(){return elements;}

    @Override
    public int getPosFichier() {return this.p;}

    @Override
    public void SEEK(int n) {
        return;// La commande SEEK ne fait rien dans ce cas
    }

    @Override
    public int F() {
        if (!TEST_EOF()) {
            return elements.pop();
        }
        else return 0;
    }

    @Override
    public void F(int i){
        elements.push(i);
    } 

    @Override
    public void VOID_F() {               // Méthode pour effacer la dernière case
        if (TEST_EOF()) return;
        else elements.pop();
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

    @Override
    public String[] elemsToString(){
        String[] elems = new String[elements.size()];
        Stack<Integer> stack = new Stack<Integer>();
        for (Integer element : elements) {
            stack.push(element);
        }
        for (int i = 0; i <elements.size();i++) {
            elems[i] = stack.pop().toString();
        }
        return elems;
    }
}