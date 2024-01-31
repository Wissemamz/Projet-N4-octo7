package model;

public class robot extends objetJ{
    private boolean vivant;
   
    public robot(String name, int abscisse, int ordonnee){
        super(name,abscisse,ordonnee);
        this.vivant = true;
    }

    public boolean getVivant(){
        return this.vivant;
    }

    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}
    
    @Override
    public void afficher(){
        System.out.print("(°+°)");
    }
   
}
