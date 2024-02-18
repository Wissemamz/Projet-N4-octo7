package model;

import java.awt.Image;

import javax.swing.ImageIcon;

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
    @Override
    public ImageIcon getIcon() {
        // Chargement des images pour les backgrounds des cases
        ImageIcon icon = new ImageIcon("main/src/images/robot.png");

        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);

        // Chargement des images pour les backgrounds des cases
        ImageIcon iconDead = new ImageIcon("main/src/images/Dead.png");

        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image imageDead = iconDead.getImage();
        Image newImageDead = imageDead.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledIconDead = new ImageIcon(newImageDead);
        if(this.vivant == true){
            return scaledIcon;
        }else {
            return scaledIconDead;   
        }
    }
}
