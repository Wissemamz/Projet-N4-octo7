package model;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class OctopunksGUI extends JFrame {
    public Jeu jeu; // Instance de la classe Jeu

    public static JTextArea memoryArea1;
    public static JTextArea memoryArea2;
    private JButton stepButton;
    private JButton stopButton;
    private JPanel gamePanel;
    private int x, y, z;

    private Robot robot1;
    private Robot robot2;

    private Obstacle obstacle;


    public OctopunksGUI() {
        jeu = new Jeu();
        jeu.setNiveau1();

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
                // Créer un nouveau menu pour la version graphique
                JFrame graphiqueMenu = new JFrame("Menu Graphique");
                graphiqueMenu.setSize(300, 250);
                graphiqueMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphiqueMenu.setLocationRelativeTo(null);

                JPanel graphiqueMainPanel = new JPanel();
                graphiqueMainPanel.setLayout(new BorderLayout());

                JLabel graphiqueTitleLabel = new JLabel("Menu Graphique", SwingConstants.CENTER);
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
    private void createGridCells(JPanel gridPanel, int x, int y, ImageIcon scaledIcon, Robot robot1,  Robot robot2) {
        // Effacer le contenu précédent du panel
        gridPanel.removeAll();
        
        // Utiliser un GridLayout pour organiser les cases de la grille principale
        gridPanel.setLayout(new GridLayout(x, y));
    
        // Parcourir les dimensions de la grille principale
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // Créer un JPanel pour chaque case de la grille principale
                JPanel mainCellPanel = new JPanel(new GridLayout(2, 2)); // grille de 2x2 pour chaque case principale
                
                // Ajouter une bordure à chaque cellule principale
                mainCellPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                
                // Créer un JLabel pour chaque sous-case de la grille principale
                JLabel[] subCellLabels = new JLabel[4];
                

                // Parcourir les sous-cases de la grille principale
                for (int k = 0; k < z; k++) {
                    
                    
                    // Créer un JLabel pour chaque sous-case
                    subCellLabels[k] = new JLabel();

                    
                  
                    if(jeu.grille[i][j][k]==null){
                        subCellLabels[k].setIcon(scaledIcon);
                    }else{
                       subCellLabels[k].setIcon(jeu.grille[i][j][k].getIcon());

                    }


                    // Ajouter le JLabel représentant la sous-case à la case principale
                    mainCellPanel.add(subCellLabels[k]);
                }
                
                // Ajouter la case principale à gridPanel
                gridPanel.add(mainCellPanel);
            }
        }
        
        // Rafraîchir l'affichage du panel
        gridPanel.revalidate();
        gridPanel.repaint();
    }
    
    

    public void startGUI() {
        SwingUtilities.invokeLater(() -> {
            // Supprimer tous les composants du contenu de la fenêtre
            getContentPane().removeAll();
    
            setTitle("Octopunks");
    
            // Code area
            // Création du panneau droit avec une grille de 5x5
            JPanel gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(10, 10));
    
            // Chargement des images pour les backgrounds des cases
            ImageIcon icon = new ImageIcon("main/src/images/case.png"); // Assurez-vous de charger une image de 16x16 pixels
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(newImage);


            robot1 = new Robot("Robot1", 4, 0, 0);
            robot2 = new Robot("Robot2", 4, 4, 1);
            obstacle = new Obstacle("obstacle", 0, 0, 0);

            createGridCells(gridPanel, x, y, scaledIcon, robot1, robot2);
    
            JScrollPane codeScrollPane = new JScrollPane(gridPanel);
    
            // Memory areas
            memoryArea1 = new JTextArea(10, 72);
            JScrollPane memoryScrollPane1 = new JScrollPane(memoryArea1);
            JLabel titleLabel1 = new JLabel("ROBOT 1", SwingConstants.CENTER);
            memoryScrollPane1.setColumnHeaderView(titleLabel1); // Utiliser le titre en tant que header
    
            memoryArea2 = new JTextArea(10,72);
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

            //setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
            //setUndecorated(true); // Enlever la décoration de fenêtre (barre de titre, boutons de fermeture, etc.)

            pack();
            setVisible(true);
        });
    }
    

    /*private void executeInstructions() {
        // Lire le contenu des zones de mémoire
        String instructionsRobot1 = memoryArea1.getText();
        String instructionsRobot2 = memoryArea2.getText();
    
        // Analyser les instructions et exécuter sur les robots
        ArrayList<Instruction> instructions1 = parseInstructions(instructionsRobot1);
        ArrayList<Instruction> instructions2 = parseInstructions(instructionsRobot2);
    
        // Exécuter les instructions sur le premier robot
        for (Instruction instruction : instructions1) {
            instruction.execute(grille, robot,instructions1,); // Supposons que grille et robot1 sont définis quelque part dans votre classe
        }
    
        // Exécuter les instructions sur le deuxième robot
        //for (Instruction instruction : instructions2) {
        //    instruction.execute(grille, robot2); // Supposons que grille et robot2 sont définis quelque part dans votre classe
        //}
    
        // Mettre à jour la grille graphique
        updateGUI();
    }*/

    protected void updateGUI() {
        // Mettre à jour la grille graphique en fonction de l'état actuel des robots et de la grille
        // Vous devez implémenter cette méthode pour refléter les changements dans votre interface graphique
        // Par exemple, mettre à jour les icônes des robots dans la grille, etc.
    }
    
}
