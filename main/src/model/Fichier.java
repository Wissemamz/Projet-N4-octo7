package model;

import java.util.ArrayList;

public class Fichier extends ObjetJ {
    private ArrayList<Integer> elements;
    private int p;

    public Fichier(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = new ArrayList<>(); 
        this.p = 0;
    }

    public int F() {
        if (!EOF()) {
            Integer i = elements.get(p);
            p++;
            return i.intValue();
        }
        else return -1;
    }

    public void F(int i){
        this.elements.add(i);
    }

    public boolean EOF(){
        return (p==elements.size());
    } 

    @Override
    public void afficher(){
        System.out.print("F_num");
    }
}