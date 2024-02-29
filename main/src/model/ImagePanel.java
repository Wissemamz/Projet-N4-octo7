package model;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {
    private Image backgroundImage;

    public ImagePanel(String imagePath) {
        // Charger l'image depuis le chemin spécifié
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dessiner l'image en arrière-plan
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
