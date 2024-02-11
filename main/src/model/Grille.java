package model;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Grille {

    Tile[] tile = new Tile[5];
    RobotGraph robot; // Déclaration de l'instance du robot

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Octopunks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        // frame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(1, 2));

        JPanel leftPanel = new JPanel(new BorderLayout());

        JTextArea textArea1 = new JTextArea(10, 10);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        leftPanel.add(scrollPane1, BorderLayout.CENTER);

        JTextArea textArea2 = new JTextArea(10, 10);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        leftPanel.add(scrollPane2, BorderLayout.SOUTH);

        JButton pasButton = new JButton("Pas");
        JButton stopButton = new JButton("Stop");

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(pasButton);
        buttonsPanel.add(stopButton);
        leftPanel.add(buttonsPanel, BorderLayout.NORTH);

        JPanel rightPanel = new JPanel(new GridLayout(5, 5));

        Grille grille = new Grille();
        grille.getTileImage();

        Niveaux.displayTiles(grille,rightPanel);

        panel.add(leftPanel);
        panel.add(rightPanel);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = getPlayerImage("grass1");

        tile[1] = new Tile();
        tile[1].image = getPlayerImage("water1");

        tile[2] = new Tile();
        tile[2].image = getPlayerImage("earth");

        tile[3] = new Tile();
        tile[3].image = getPlayerImage("wall");

        tile[4] = new Tile();
        tile[4].image = getPlayerImage("magma");
    }

    public static BufferedImage getPlayerImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Grille.class.getResourceAsStream(path + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}

class Tile {
    BufferedImage image;
}
