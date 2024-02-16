package model;

public abstract class ObjetJ {
    private String name;
    public int abscisse;
    public int ordonnee;
    public int caseJ;

    public ObjetJ (String name, int abscisse, int ordonnee, int caseJ) {
        if (name == null) {
            throw new NullPointerException();
        }   
        if (name.length()==0 || abscisse<0 || ordonnee<0 || caseJ<0) {
            throw new IllegalArgumentException();
        }
        this.name=name;
        this.ordonnee=ordonnee;
        this.abscisse=abscisse;
        this.caseJ=caseJ;
    }

    public String getName(){return this.name;}
    public int getAbscisse(){return this.abscisse;}
    public int getOrdonnee(){return this.ordonnee;}
    public int getCaseJ(){return this.caseJ;}
    
    public abstract void afficher();

  

   
}
