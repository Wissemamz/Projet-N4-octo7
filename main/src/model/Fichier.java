package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Fichier extends ObjetJ {

    public Fichier(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
    }

    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setCaseJ(int value){caseJ=value;}

    public abstract int getPosFichier();
    public abstract int F();
    public abstract void F(int i);
    public abstract boolean TEST_EOF();
    public abstract void VOID_F();
    public abstract void SEEK(int n);
    public abstract boolean meme_elements(Fichier fichier);

    @Override
    public ImageIcon getIcon() {
        // Chargement des images pour les backgrounds des cases
        ImageIcon icon = new ImageIcon("main/src/images/file.png");

        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);
        return scaledIcon;
    }

    @Override
    public void afficher(){
        System.out.print(" "+getName()+" ");
    }
}