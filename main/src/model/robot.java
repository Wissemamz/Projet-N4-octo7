package model;

public class Robot extends ObjetJ{
    private boolean vivant;
    private int X; 
    private Fichier fichier;

    public Robot(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
        this.vivant = true;
        this.X=0;
        this.fichier = null;
    }

    public boolean getVivant(){
        return this.vivant;
    }

    public int getX(){ return this.X; }

    public Fichier getFichier(){ return this.fichier; }
    
    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setX(int value){this.X=value;}

    public void setFichier(ObjetJ f){this.fichier=(Fichier) f;}  


    @Override
    public void afficher(){
        System.out.print("(°+°)");
    }
   
}
