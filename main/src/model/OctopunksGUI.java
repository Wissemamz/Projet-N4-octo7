package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OctopunksGUI extends JFrame {
    private Jeu jeu; // Instance de la classe Jeu

    public static JTextArea memoryArea1;
    public static JTextArea memoryArea2;
    private JButton stepButton;
    private JButton stopButton;
    private JPanel gamePanel;
    private int x, y, z;

    private Robot robot;

    public OctopunksGUI() {
        jeu = new Jeu();

        x = 5;
        y = 5;
        z = 4;

        //---------------------------------------affichage de menu-----------------------------------------
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
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10)); // Ajout de marges entre les boutons

        // Boutons avec taille fixe et police plus grande
        JButton btnTextuelle = new JButton("Version Textuelle");
        btnTextuelle.setPreferredSize(new Dimension(150, 40));
        btnTextuelle.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnGraphique = new JButton("Version Graphique");
        btnGraphique.setPreferredSize(new Dimension(150, 40));
        btnGraphique.setFont(new Font("Arial", Font.PLAIN, 14));

        btnTextuelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Jeu leJeu = new Jeu();
                leJeu.jouer();

            }
        });

        btnGraphique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Créer un nouveau menu pour la version graphique
                JFrame graphiqueMenu = new JFrame("Menu");
                graphiqueMenu.setSize(300, 250);
                graphiqueMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphiqueMenu.setLocationRelativeTo(null);

                JPanel graphiqueMainPanel = new JPanel();
                graphiqueMainPanel.setLayout(new BorderLayout());

                JLabel graphiqueTitleLabel = new JLabel("Menu", SwingConstants.CENTER);
                graphiqueTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
                graphiqueMainPanel.add(graphiqueTitleLabel, BorderLayout.NORTH);

                JPanel graphiqueButtonPanel = new JPanel();
                graphiqueButtonPanel.setLayout(new FlowLayout());

                JButton btnCommencerGraphique = new JButton("Commencer");
                btnCommencerGraphique.setPreferredSize(new Dimension(150, 40));
                btnCommencerGraphique.setFont(new Font("Arial", Font.PLAIN, 14));

                btnCommencerGraphique.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // Fermer la fenêtre du menu principal
                        graphiqueMenu.dispose(); // Fermer la fenêtre du menu graphique
                        startGUI();
                    }
                });

                graphiqueButtonPanel.add(btnCommencerGraphique);

                graphiqueMainPanel.add(graphiqueButtonPanel, BorderLayout.CENTER);

                graphiqueMenu.add(graphiqueMainPanel);
                graphiqueMenu.setVisible(true);
            }
        });

        buttonPanel.add(btnTextuelle);
        buttonPanel.add(btnGraphique);

        // Ajout du panel des boutons au panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        // Ajout du panel principal à la fenêtre
        add(mainPanel);
        setVisible(true);

        // --------------------------------------------- FIN MENU ---------------------------------------
    }

    private void createGridCells(JPanel gridPanel, int x, int y, int z, ImageIcon scaledIcon, Robot robot) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < z; k++) {
                    JLabel label;
                    if (robot != null && i == robot.getAbscisse() && j == robot.getOrdonnee() && k == robot.getCaseJ()) {
                        label = new JLabel(robot.getIcon());
                    } else {
                        label = new JLabel(scaledIcon);
                    }
                    label.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Ajouter une bordure
                    gridPanel.add(label);
                }
            }
        }
    }

    public void startGUI() {
        SwingUtilities.invokeLater(() -> {
            // Supprimer tous les composants du contenu de la fenêtre
            getContentPane().removeAll();
    
            setTitle("Octopunks");
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize);
    
            // Code area
            // Création du panneau droit avec une grille de 5x5
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(10, 10));
    
            // Calculer la taille de chaque case pour qu'elle soit carrée
            int cellSize = Math.min(getWidth() / 15, getHeight() / 15); // Divisez la largeur ou la hauteur par le nombre de cases dans une ligne ou une colonne
            gridPanel.setPreferredSize(new Dimension(cellSize * 10, cellSize * 10)); // Définir la taille du panneau de grille pour qu'il puisse contenir la grille entière
    
            // Chargement des images pour les backgrounds des cases
            ImageIcon icon = new ImageIcon("main/src/images/case.png"); // Assurez-vous de charger une image de 16x16 pixels
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(cellSize, cellSize, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(newImage);
    
            robot = new Robot("Robot", 0, 0, 0);
    
            createGridCells(gridPanel, x, y, z, scaledIcon, robot);
    
            JScrollPane codeScrollPane = new JScrollPane(gridPanel);
    
            // Memory areas
            memoryArea1 = new JTextArea(10, 20);
            JScrollPane memoryScrollPane1 = new JScrollPane(memoryArea1);
            JLabel titleLabel1 = new JLabel("ROBOT 1", SwingConstants.CENTER);
            memoryScrollPane1.setColumnHeaderView(titleLabel1); // Utiliser le titre en tant que header
    
            memoryArea2 = new JTextArea(10, 20);
            JScrollPane memoryScrollPane2 = new JScrollPane(memoryArea2);
            JLabel titleLabel2 = new JLabel("ROBOT 2", SwingConstants.CENTER);
            memoryScrollPane2.setColumnHeaderView(titleLabel2); // Utiliser le titre en tant que header
    
            // Buttons
            stepButton = new JButton("Pas");
            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appeler la méthode jouer() du jeu lorsque le bouton "Pas" est cliqué
                    jeu.jouer();
                    // Mettre à jour l'interface graphique après chaque pas
                    updateGUI();
                }
            });
            stopButton = new JButton("Stop");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Réinitialiser le jeu et réactiver la zone de code
                    //resetGame();
                }
            });
    
            // Game panel
            gamePanel = new JPanel();
            gamePanel.setBackground(Color.WHITE);
            gamePanel.setPreferredSize(new Dimension(600, 400));
    
            // Layout setup
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(1, 4));
            controlPanel.add(stepButton);
            controlPanel.add(stopButton);
    
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            leftPanel.add(codeScrollPane, BorderLayout.CENTER);
            leftPanel.add(controlPanel, BorderLayout.SOUTH);
    
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new GridLayout(2, 1));
            rightPanel.add(memoryScrollPane1);
            rightPanel.add(memoryScrollPane2);
    
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            topPanel.add(leftPanel, BorderLayout.CENTER);
            topPanel.add(rightPanel, BorderLayout.EAST);
    
            // Ajout de la zone de texte en bas de la page
            JLabel missionLabel = new JLabel("Zone de la mission : ");
            JPanel bottomPanel = new JPanel();
            bottomPanel.add(missionLabel);
    
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(topPanel, BorderLayout.NORTH);
            getContentPane().add(gamePanel, BorderLayout.CENTER);
            getContentPane().add(bottomPanel, BorderLayout.SOUTH);
    
            //pack();
            setVisible(true);
        });
    }
    
    

    protected void updateGUI() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateGUI'");
    }
}