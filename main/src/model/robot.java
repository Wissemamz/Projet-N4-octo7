package model;

public class Robot extends ObjetJ{
    private boolean vivant;
    private Registre X;
    private Registre T;
    private Registre last;
    private Fichier fichier;

    public Robot(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
        this.vivant = true;
        this.X = new Registre();
        this.T = new Registre();
        this.last = X;
        this.fichier = null;
    }

    public boolean getVivant(){
        return this.vivant;
    }

    public void meurt(){ this.vivant = false; }

    public Registre getX(){ return this.X; }

    public Registre getT(){ return this.T; }

    public Fichier getFichier(){ return this.fichier; }

    public Registre getLastRegistre() { return this.last; }
    
    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setCaseJ(int value){caseJ=value;}

    public void setFichier(ObjetJ f){this.fichier=(Fichier) f;} 
    
    public void setLastRegistre(Registre Y) {this.last = Y;} 


    @Override
    public void afficher(){
        System.out.print("(°+°)");
    }
   
}
