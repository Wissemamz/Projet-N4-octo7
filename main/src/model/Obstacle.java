package model;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Obstacle extends ObjetJ{
    
   
    public Obstacle(String name, int abscisse, int ordonnee, int caseJ){
        super(name,abscisse,ordonnee,caseJ);
    }

    @Override
    public void afficher(){
        System.out.print(" [/] ");
    }
    @Override
    public ImageIcon getIcon() {
        // Chargement des images pour les backgrounds des cases
        ImageIcon icon = new ImageIcon("main/src/images/danger.png");

        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);
        return scaledIcon;
    }
}