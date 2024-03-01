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
}