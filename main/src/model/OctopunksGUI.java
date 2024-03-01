package model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class OctopunksGUI extends JFrame {
    public Jeu jeu; // Instance de la classe Jeu

    public static JTextArea memoryArea1;
    public static JTextArea memoryArea2;
    public JLabel fileContentLabel;
    private JButton stepButton;
    private JButton stopButton;
    //private JButton autoButton;

    private JPanel gamePanel;
    private int x, y, z;

    ImageIcon scaledIcon;
    JPanel gridPanel;
    JPanel Prototype;

    private JPanel registreRobot1;
    private JPanel registreRobot2;

    int n;

    public OctopunksGUI() {
        jeu = new Jeu();
        jeu.setNiveau1GUI();

        x = 5;
        y = 5;
        z = 4;

        //---------------------------------------affichage de menu-----------------------------------------
        setTitle("Menu");
        setSize(400, 320); // Augmentation de la hauteur pour accommoder le titre
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);
        

        // Panel principal pour contenir tous les composants
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.white);


        // Titre "OCTOPUNKS" en haut de la page
        JLabel titleLabel = new JLabel("OCTOPUNKS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel pour les boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout()); // Ajout de marges entre les boutons

         // Chargement des images pour les backgrounds des cases
         ImageIcon iconGraph = new ImageIcon("main/src/images/Graphique1.png"); // Assurez-vous de charger une image de 16x16 pixels
    
         // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
         Image image = iconGraph.getImage();
         Image newImageGraph = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
         ImageIcon scaledIconGraph = new ImageIcon(newImageGraph);

         // Chargement des images pour les backgrounds des cases
         ImageIcon iconText = new ImageIcon("main/src/images/Textuel.png"); // Assurez-vous de charger une image de 16x16 pixels
    
         // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
         Image imageText = iconText.getImage();
         Image newImageText = imageText.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
         ImageIcon scaledIconText = new ImageIcon(newImageText);


        // Créer les boutons avec les images
        JButton btnGraphique = new JButton(scaledIconGraph);
        JButton btnTextuelle = new JButton(scaledIconText);

        // Définir la taille des boutons
        btnGraphique.setPreferredSize(new Dimension(250, 100));
        btnTextuelle.setPreferredSize(new Dimension(250, 100));

        btnTextuelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Jeu leJeu = new Jeu();
                leJeu.afficherMenuNiveaux();

            }
        });

        btnGraphique.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // Créer un nouveau menu pour la version graphique
                JFrame graphiqueMenu = new JFrame("OCTOPUNKS GUI");
                graphiqueMenu.setSize(400, 320);
                graphiqueMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graphiqueMenu.setLocationRelativeTo(null);
                graphiqueMenu.setResizable(false);

                JPanel graphiqueMainPanel = new JPanel();
                graphiqueMainPanel.setLayout(new BorderLayout());

                JLabel graphiqueTitleLabel = new JLabel("Menu", SwingConstants.CENTER);
                graphiqueTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
                graphiqueMainPanel.add(graphiqueTitleLabel, BorderLayout.NORTH);
                graphiqueMainPanel.setBackground(Color.white);

                JPanel graphiqueButtonPanel = new JPanel();
                graphiqueButtonPanel.setLayout(new FlowLayout());
                graphiqueButtonPanel.setBackground(Color.GRAY);

                JButton btnCommencerGraphique = new JButton("START");
                btnCommencerGraphique.setPreferredSize(new Dimension(200, 60));
                btnCommencerGraphique.setFont(new Font("Arial", Font.PLAIN, 20));
                btnCommencerGraphique.setBackground(Color.GREEN);

                JButton btnLevels = new JButton("Levels");
                btnLevels.setPreferredSize(new Dimension(200, 60));
                btnLevels.setFont(new Font("Arial", Font.PLAIN, 20));
                btnLevels.setBackground(Color.cyan);


                JButton btnExit = new JButton("Exit");
                btnExit.setPreferredSize(new Dimension(200, 60));
                btnExit.setFont(new Font("Arial", Font.PLAIN, 20));
                btnExit.setBackground(Color.red);

                
                // Ajouter les actions pour les nouveaux boutons "Levels" et "Exit"
                btnLevels.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        graphiqueMenu.dispose();
                        // Créer un nouveau menu pour la version graphique
                        JFrame levelMenu = new JFrame("OCTOPUNKS Level");
                        levelMenu.setSize(400, 320);
                        levelMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        levelMenu.setLocationRelativeTo(null);
                        levelMenu.setResizable(false);
        
                        JPanel levelMainPanel = new JPanel();
                        levelMainPanel.setLayout(new BorderLayout());
        
                        JLabel levelTitleLabel = new JLabel("Level", SwingConstants.CENTER);
                        levelTitleLabel.setFont(new Font("Times New Roman", Font.BOLD, 50));
                        levelMainPanel.add(levelTitleLabel, BorderLayout.NORTH);
                        levelMainPanel.setBackground(Color.white);
        
                        JPanel levelButtonPanel = new JPanel();
                        levelButtonPanel.setLayout(new FlowLayout());
                        levelButtonPanel.setBackground(Color.GRAY);
        
                        JButton btnLevel1 = new JButton("Niveau 1");
                        btnLevel1.setPreferredSize(new Dimension(200, 60));
                        btnLevel1.setFont(new Font("Arial", Font.PLAIN, 20));
                        btnLevel1.setBackground(Color.GREEN);
        
                        JButton btnLevel2 = new JButton("Niveau 2");
                        btnLevel2.setPreferredSize(new Dimension(200, 60));
                        btnLevel2.setFont(new Font("Arial", Font.PLAIN, 20));
                        btnLevel2.setBackground(Color.cyan);
        
                        JButton btnLevel3 = new JButton("Niveau 3");
                        btnLevel3.setPreferredSize(new Dimension(200, 60));
                        btnLevel3.setFont(new Font("Arial", Font.PLAIN, 20));
                        btnLevel3.setBackground(Color.red);
        
                        
                        btnLevel1.addActionListener(new ActionListener() {
                        @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau1GUI();
                                n=1;
                                startGUI(1);
                            }
                        });
        
                        btnLevel2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau2GUI();
                                n=2;
                                startGUI(2); 
                        }
                        });
                        btnLevel3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau3GUI();
                                n=3;
                                startGUI(3);
                            }
                        });
        
                        levelButtonPanel.add(btnLevel1);
                        levelButtonPanel.add(btnLevel2);
                        levelButtonPanel.add(btnLevel3);
        
                        levelMainPanel.add(levelButtonPanel, BorderLayout.CENTER);
        
                        levelMenu.add(levelMainPanel);
                        levelMenu.setVisible(true);
                    }
                });

                btnExit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    // Ajouter l'action pour le bouton "Exit"
                    System.exit(0); // Quitter l'application
                }
                });
                btnCommencerGraphique.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose(); // Fermer la fenêtre du menu principal
                        graphiqueMenu.dispose(); // Fermer la fenêtre du menu graphique
                        startGUI(1);
                    }
                });

                graphiqueButtonPanel.add(btnCommencerGraphique);
                graphiqueButtonPanel.add(btnLevels);
                graphiqueButtonPanel.add(btnExit);

                graphiqueMainPanel.add(graphiqueButtonPanel, BorderLayout.CENTER);

                graphiqueMenu.add(graphiqueMainPanel);
                graphiqueMenu.setVisible(true);
            }
        });

        buttonPanel.add(btnTextuelle);
        buttonPanel.add(btnGraphique);
        buttonPanel.setBackground(Color.BLACK);
        // Ajout du panel des boutons au panel principal
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        // Ajout du panel principal à la fenêtre
        add(mainPanel);
        setVisible(true);

        // --------------------------------------------- FIN MENU ---------------------------------------
    }


    private void createGridCells(JPanel gridPanel, int x, int y, ImageIcon scaledIcon) {
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
                    
                    //final int finalK = k;
                    // Créer un JLabel pour chaque sous-case
                    subCellLabels[k] = new JLabel();

                    // Vérifier le type de l'élément et agir en conséquence
                    if (jeu.grille[i][j][k] instanceof Fichier) {
                        // Ajoutez un tooltip pour afficher des informations sur le fichier
                        subCellLabels[k].setToolTipText("Numero du fichier : " + jeu.grille[i][j][k].getName());
                    } else if (jeu.grille[i][j][k] instanceof Robot) {
                        // Ajoutez un tooltip pour afficher des informations sur le robot
                        subCellLabels[k].setToolTipText("Robot : " + jeu.grille[i][j][k].getName());
                    } else if (jeu.grille[i][j][k] instanceof Obstacle) {
                        // Ajoutez un tooltip pour afficher des informations sur le danger
                        subCellLabels[k].setToolTipText("Danger");
                    } else {
                        // Le cas par défaut, par exemple, pour les cases vides
                        subCellLabels[k].setToolTipText("Case vide");
                    }

                    // Ajoutez un MouseListener pour afficher le tooltip au survol de la souris
                    subCellLabels[k].addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent e) {
                        }
                    });
                  
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
    
    

    public void startGUI(int n) {
        SwingUtilities.invokeLater(() -> {
            // Supprimer tous les composants du contenu de la fenêtre
            getContentPane().removeAll();
    
            setTitle("Octopunks");
            setBackground(Color.GRAY);
    
            // Code area
            // Création du panneau droit avec une grille de 5x5
            gridPanel = new JPanel();
            gridPanel.setLayout(new GridLayout(10, 10));
            
            JPanel flowLayoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            flowLayoutPanel.add(gridPanel);

            // Chargement des images pour les backgrounds des cases
            ImageIcon icon = new ImageIcon("main/src/images/case.png"); // Assurez-vous de charger une image de 16x16 pixels
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(newImage);

            createGridCells(gridPanel, x, y, scaledIcon);

            Prototype = gridPanel;

            JScrollPane codeScrollPane = new JScrollPane(flowLayoutPanel);
    
            // Memory areas
            memoryArea1 = new JTextArea(10, 30);
            JScrollPane memoryScrollPane1 = new JScrollPane(memoryArea1);
            JLabel titleLabel1 = new JLabel("ROBOT 1", SwingConstants.CENTER);
            memoryScrollPane1.setColumnHeaderView(titleLabel1); // Utiliser le titre en tant que header
            memoryArea1.setBackground(Color.LIGHT_GRAY);
    
            memoryArea2 = new JTextArea(10,30);
            JScrollPane memoryScrollPane2 = new JScrollPane(memoryArea2);
            JLabel titleLabel2 = new JLabel("ROBOT 2", SwingConstants.CENTER);
            memoryScrollPane2.setColumnHeaderView(titleLabel2); // Utiliser le titre en tant que header
            memoryArea2.setBackground(Color.LIGHT_GRAY);
            
            // Buttons
            stepButton = new JButton("Pas");
            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    // Bloquer les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(false);
                    OctopunksGUI.memoryArea2.setEditable(false);

                    jeu.jouerGUI();

                    updateGUI();
                }
            });


            stopButton = new JButton("Stop");
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Réinitialiser les valeurs des registres pour chaque robot
                    resetRegisterValues(jeu.robot1);
                    resetRegisterValues(jeu.robot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);
            
                    // Réinitialiser d'autres éléments si nécessaire
                    // Effacer le contenu des zones mémoires
                    //memoryArea1.setText("");
                    //memoryArea2.setText("");

                    // Reouvrir les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(true);
                    OctopunksGUI.memoryArea2.setEditable(true);
            
                    // Réinitialiser l'emplacement des robots sur la grille (affichage de setNiveau1())
                    switch (n) {
                        case 1 : 
                            jeu.setNiveau1GUI();
                            break;
                        case 2 : 
                            jeu.setNiveau2GUI();
                            break;
                        case 3 : 
                            jeu.setNiveau3GUI();
                            break;
                    }
                    jeu.resetPosition();
            
                    // Mettre à jour l'affichage de la grille
                    createGridCells(Prototype, x, y, scaledIcon);
                }
            });
            

            JButton autoButton = new JButton("Auto");
            autoButton.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                    // Bloquer les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(false);
                    OctopunksGUI.memoryArea2.setEditable(false);

                    // Réinitialiser les valeurs des registres pour chaque robot
                    resetRegisterValues(jeu.robot1);
                    resetRegisterValues(jeu.robot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);
                    // Réinitialiser l'emplacement des robots sur la grille    )
                    switch (n) {
                        case 1 : 
                            jeu.setNiveau1GUI();
                            break;
                        case 2 : 
                            jeu.setNiveau2GUI();
                            break;
                        case 3 : 
                            jeu.setNiveau3GUI();
                            break;
                    }
                    jeu.resetPosition();

                    jeu.jouerGUIAuto();
                    /*if (jeu.verifierVictoire()) {
                        String[] options = {"Exit", "Next Level"};
                        int choice = JOptionPane.showOptionDialog(null, "Victoire ! Choisissez une action :", "Victoire",
                                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    
                        if (choice == 0) {
                            System.exit(0); // Exit
                        } else if (choice == 1) {
                            // Charger le niveau suivant ou effectuer une autre action
                            // Par exemple :
                            // chargerNiveauSuivant();
                        }
                    }*/
                    updateGUI();
                }
            });
    
                // Game panel
                gamePanel = new JPanel();
                gamePanel.setBackground(Color.WHITE);
                gamePanel.setPreferredSize(new Dimension(600, 400));

                // Layout setup
                JPanel controlPanel = new JPanel();
                controlPanel.setLayout(new GridLayout(1, 3));
                controlPanel.add(stepButton);
                controlPanel.add(autoButton);
                controlPanel.add(stopButton);

                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new BorderLayout());
                leftPanel.add(gamePanel, BorderLayout.CENTER); // Ajout du gamePanel au centre
                leftPanel.add(codeScrollPane, BorderLayout.CENTER);
                leftPanel.add(controlPanel, BorderLayout.SOUTH); // Ajout du controlPanel en dessous du gamePanel

                // Créer les panneaux affichant les valeurs des registres pour chaque robot
                registreRobot1 = createRegisterPanel(jeu.robot1);
                registreRobot2 = createRegisterPanel(jeu.robot2);

                JPanel flowLayoutPanelMemoryArea1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                flowLayoutPanelMemoryArea1.add(memoryScrollPane1);
                flowLayoutPanelMemoryArea1.add(registreRobot1);
                JPanel flowLayoutPanelMemoryArea2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                flowLayoutPanelMemoryArea2.add(memoryScrollPane2);
                flowLayoutPanelMemoryArea2.add(registreRobot2);
                //JPanel flowLayoutPanelMemoryArea3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                //JPanel flowLayoutPanelMemoryArea4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
                JPanel flowLayoutPanelMemoryArea3 = new JPanel(new GridLayout(2, 1));
                JPanel flowLayoutPanelMemoryArea4 = new JPanel(new GridLayout(2, 1));
                
                JPanel rightPanel = new JPanel();
                rightPanel.setLayout(new GridLayout(4, 1));
                rightPanel.add(flowLayoutPanelMemoryArea1);
                rightPanel.add(flowLayoutPanelMemoryArea3);
                rightPanel.add(flowLayoutPanelMemoryArea2);
                rightPanel.add(flowLayoutPanelMemoryArea4);

                // Ajout de la zone de texte en bas de la page
                JLabel missionLabel = new JLabel("Zone de la mission : ");
                JPanel bottomPanel = new JPanel();
                bottomPanel.setPreferredSize(new Dimension(600, 100));
                bottomPanel.setLayout(new BorderLayout());
                bottomPanel.add(missionLabel, BorderLayout.NORTH);

                getContentPane().setLayout(new BorderLayout());
                getContentPane().add(leftPanel, BorderLayout.CENTER); // leftPanel au centre
                getContentPane().add(rightPanel, BorderLayout.EAST); // rightPanel à droite
                getContentPane().add(bottomPanel, BorderLayout.SOUTH); // bottomPanel en bas


            setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
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
            // Mettre à jour l'affichage de la grille
            createGridCells(gridPanel, x, y, scaledIcon);
    
            // Mettre à jour les panneaux de registres pour chaque robot
            updateRegisterPanel(registreRobot1, jeu.robot1);
            updateRegisterPanel(registreRobot2, jeu.robot2);
    
            // Rafraîchir l'interface utilisateur
            revalidate();
            repaint();
    }

    private JPanel createRegisterPanel(Robot robot) {
        // Récupérer les valeurs initiales des registres
        int rX = robot.getX().getValeur();
        int rT = robot.getT().getValeur();
        //int rF = Integer.parseInt(robot.getFichier().getName());
        String rF = robot.getFichier()==null ? "None" : robot.getFichier().getName();
        String rM = jeu.getM().getValeur()==0 ? "None" : String.valueOf(jeu.getM().getValeur());

        // Créer un tableau de données pour les valeurs des registres   
        String[][] data = {
                {"X", String.valueOf(rX)},
                {"T", String.valueOf(rT)}, // Mettez T en deuxième position
                {"F", rF},  // Mettez F en troisième position
                {"M", rM}
        };
            
        // Créer un tableau de noms de colonnes
        String[] columnNames = {"Registre", "Valeur"};
    
        // Créer un modèle de tableau avec les données et les noms de colonnes
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    
        // Créer un tableau avec le modèle de données
        JTable table = new JTable(tableModel);
    
        // Mettre en forme le tableau
        table.setRowHeight(30); // Définir la hauteur des lignes
        table.setFont(new Font("Arial", Font.PLAIN, 16)); // Définir la police du texte dans le tableau
    
        // Créer un panneau pour contenir le tableau
        JPanel registerPanel = new JPanel(new FlowLayout());
        registerPanel.add(table);
        return registerPanel;
    }
    

    private void updateRegisterPanel(JPanel registerPanel, Robot robot) {
        // Récupérer les valeurs actuelles des registres du robot
        int rX = robot.getX().getValeur();
        int rT = robot.getT().getValeur();
        String rF = (robot.getFichier() == null) ? "None" : String.valueOf(robot.getFichier().getName());
        String rM = jeu.getM().getValeur()==0 ? "None" : String.valueOf(jeu.getM().getValeur());

        // Mettre à jour les valeurs affichées dans le tableau des registres
        JTable table = (JTable) registerPanel.getComponent(0);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setValueAt(rX + "", 0, 1); // Mise à jour de la valeur du registre X
        tableModel.setValueAt(rT + "", 1, 1); // Mise à jour de la valeur du registre T
        tableModel.setValueAt(rF + "", 2, 1); // Mise à jour de la valeur du registre F
        tableModel.setValueAt(rM + "", 3, 1); // Mise à jour de la valeur du registre M
    }

    private void resetRegisterValues(Robot robot) {
        // Réinitialiser les valeurs des registres du robot
        robot.getX().setValeur(0);
        robot.getT().setValeur(0);
        robot.setFichier(null);
        jeu.getM().setValeur(0);
    }
    /* 
     private void createMemoryPanel(Robot robot) {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Création du label "ROBOT" avec le nom du robot comme en-tête
        JLabel headerLabel = new JLabel(robot.getName(), JLabel.CENTER);
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        // Création du JTextArea dans le côté droit
        JTextArea memoryArea = new JTextArea(10, 10);
        JScrollPane scrollPane = new JScrollPane(memoryArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Création de trois petits tableaux dans le côté gauche
        JPanel tablePanel = new JPanel(new GridLayout(3, 1));
        createRegisterPanel(robot);
        
        mainPanel.add(tablePanel, BorderLayout.WEST);
    }*/
}
