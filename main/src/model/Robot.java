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

    private boolean Ready ;
    public boolean isMReady(){return Ready;}
    public void setReady(){Ready=true;}
    public void setNotReady(){Ready=false;}
    public int modeM; 
    public int getMode(){return modeM;}
    public void setMode(int mode){modeM = mode;}

    public Robot(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
        this.vivant = true;
        this.X = new Registre();
        this.T = new Registre();
        this.F = new Registre();
        this.last = X;
        this.fichier = null;
        Ready = true;
        modeM = 1; // Global
    }

    public boolean getVivant(){
        return this.vivant;
    }

    public void meurt(){ this.vivant = false; }

    public void ressuciter(){ this.vivant = true; }

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

    public void updateXValue(int newValue) {
        X.setValeur(newValue);
    }

    public void updateFValue(int newValue) {
        F.setValeur(newValue);
    }

    public void updateTValue(int newValue) {
        T.setValeur(newValue);
    }


    
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
