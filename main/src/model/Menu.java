package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Menu");
        setSize(300, 250); // Augmentation de la hauteur pour accommoder le titre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal pour contenir tous les composants
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Titre "OCTOPUNKS" en haut de la page
        JLabel titleLabel = new JLabel("OCTOPUNKS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 0, 10)); // Ajout de marges entre les boutons

        // Boutons avec taille fixe et police plus grande
        JButton btnCommencer = new JButton("Commencer");
        btnCommencer.setPreferredSize(new Dimension(150, 40));
        btnCommencer.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnLevels = new JButton("Niveaux");
        btnLevels.setPreferredSize(new Dimension(150, 40));
        btnLevels.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnSettings = new JButton("Paramètres");
        btnSettings.setPreferredSize(new Dimension(150, 40));
        btnSettings.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnExit = new JButton("Quitter");
        btnExit.setPreferredSize(new Dimension(150, 40));
        btnExit.setFont(new Font("Arial", Font.PLAIN, 14));

        // Ajout des ActionListeners pour chaque bouton
        btnCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grille.createAndShowGUI();
                dispose();
            }
        });

        btnLevels.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Page des niveaux");
            }
        });

        btnSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Paramètres du jeu");
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Ajout des boutons au panel
        buttonPanel.add(btnCommencer);
        buttonPanel.add(btnLevels);
        buttonPanel.add(btnSettings);
        buttonPanel.add(btnExit);

        // Ajout du panel des boutons au panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Ajout du panel principal à la fenêtre
        add(mainPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}
