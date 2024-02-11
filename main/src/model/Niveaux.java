package model;

import javax.swing.*;
import java.awt.*;

public class Niveaux {

    public static void displayTiles(Grille grille, JPanel rightPanel) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel subPanel = new JPanel(new BorderLayout());

                // Si c'est la case 0,0, afficher le robot
               
                    ImageIcon icon = new ImageIcon(grille.tile[i].image.getScaledInstance(140, 140, Image.SCALE_DEFAULT));
                    JLabel background = new JLabel(icon);
                    subPanel.add(background, BorderLayout.CENTER);

                rightPanel.add(subPanel);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JPanel subPanel = new JPanel(new BorderLayout());

                // Si c'est la case 0,0, afficher le robot
                if (i == 0 && j == 0) {
                    ImageIcon robotIcon = new ImageIcon("main/player/octo_down.png"); // Spécifiez le chemin de l'image du robot
                    grille.robot = new RobotGraph(robotIcon);
                    // Mettre à l'échelle l'image du robot pour qu'elle soit de la même taille que les tuiles
                    grille.robot.setIcon(new ImageIcon(robotIcon.getImage().getScaledInstance(140, 140, Image.SCALE_DEFAULT)));
                    subPanel.add(grille.robot);
                }

                rightPanel.add(subPanel);
            }
        }
    }
}
