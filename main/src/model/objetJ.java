package model;
public abstract class objetJ {
    private String name;
    public int abscisse;
    public int ordonnee;

    public objetJ (String name, int abscisse, int ordonnee){
        if (name == null) {
            throw new NullPointerException();
        }   
        if (name.length()==0 || abscisse<0 || ordonnee<0){
            throw new IllegalArgumentException();
        }
        this.name=name;
        this.ordonnee=ordonnee;
        this.abscisse=abscisse;
    }

    public String getName(){return this.name;}
    public int getAbscisse(){return this.abscisse;}
    public int getOrdonnee(){return this.ordonnee;}
    
    abstract void afficher();
}
