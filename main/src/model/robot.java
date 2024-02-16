package model;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Robot extends ObjetJ{
    private boolean vivant;
    private Registre X;
    private Registre T;
    private Registre F;
    private Registre last;
    private Fichier fichier;

    public Robot(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
        this.vivant = true;
        this.X = new Registre();
        this.T = new Registre();
        this.F = new Registre();
        this.last = X;
        this.fichier = null;
    }

    public boolean getVivant(){
        return this.vivant;
    }

    public void meurt(){ this.vivant = false; }

    public Registre getX(){ return this.X; }

    public Registre getT(){ return this.T; }

    public Registre getF(){ return this.F; }

    public Fichier getFichier(){ return this.fichier; }

    public Registre getLastRegistre() { return this.last;}
    
    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setCaseJ(int value){caseJ=value;}

    public void setFichier(ObjetJ f){
        this.fichier=(Fichier) f;
        //F.setValeur(fichier.F());
    }


    
    public void setLastRegistre(Registre Y) {this.last = Y;} 


    @Override
    public void afficher(){
        System.out.print("(°+°)");
    }

    public ImageIcon getIcon() {
        // Chargement des images pour les backgrounds des cases
        ImageIcon icon = new ImageIcon("main/src/images/ocot_down.png"); // Assurez-vous de charger une image de 16x16 pixels

        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);
        return scaledIcon;
    }  
}
