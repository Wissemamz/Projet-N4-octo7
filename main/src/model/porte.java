package model;

public class porte extends objetJ{
    
   
    public porte(String name, int abscisse, int ordonnee){
        super(name,abscisse,ordonnee);
    }

    @Override
    public void afficher(){
        System.out.print(" [ ] ");
    }
}