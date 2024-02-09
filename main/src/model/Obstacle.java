package model;

public class Obstacle extends ObjetJ{
    
   
    public Obstacle(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
    }

    @Override
    public void afficher(){
        System.out.print(" [/] ");
    }
}