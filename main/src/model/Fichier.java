package model;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Fichier extends ObjetJ {
    private ArrayList<Integer> elements;
    private int p;

    public Fichier(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
        this.elements = new ArrayList<>(); 
        this.p = 0;
    }

    public void setAbscisse(int value){abscisse=value;}
    
    public void setOrdonnee(int value){ordonnee=value;}

    public void setCaseJ(int value){caseJ=value;}

    public int F() {
        if (!EOF()) {
            Integer i = elements.get(p);
            p++;
            return i.intValue();
        }
        else return -1;
    }

    public void F(int i){
        this.elements.add(i);
    }

    public boolean EOF(){
        return (p==elements.size());
    } 

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