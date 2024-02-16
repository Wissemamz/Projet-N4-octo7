package model;

public abstract class Fichier extends ObjetJ {
    //private ArrayList<Integer> elements;

    public Fichier(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        //this.elements = new ArrayList<>(); 
    }

    /*public Fichier(String num, int abscisse, int ordonnee, int caseJ,ArrayList<Integer> elements) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = elements;
        this.p=0;
    }*/

    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setCaseJ(int value){caseJ=value;}

    public abstract int getPosFichier();
    //public abstract void setPos(int pos);
    public abstract int F();
    public abstract void F(int i);
    public abstract boolean TEST_EOF();
    public abstract void VOID_F();
    public abstract void SEEK(int n);

    /*
    public int getPosFichier() {return this.p;}

    public void setPos(int pos) {this.p = pos;}

    public int F() {
        if (!EOF()) {
            Integer i = elements.get(p);
            p++;
            return i.intValue();
        }
        else return 0;
    }

    public void F(int i){
        if (!EOF()) {
            this.elements.set(p,i);
        }
        else this.elements.add(i);
        p++;
    } 

    public boolean EOF(){
        return (p==elements.size());
    }
    */ 

    @Override
    public void afficher(){
        System.out.print(" "+getName()+" ");
    }
}