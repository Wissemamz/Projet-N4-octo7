package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

public class OctopunksGUI extends JFrame {
    public Jeu jeu; // Instance de la classe Jeu

    public static JTextArea memoryArea1;
    public static JTextArea memoryArea2;
    public JLabel fileContentLabel1;
    public JLabel fileContentLabel2;
    private JButton stepButton;
    private JButton stopButton;
    private JButton autoButton;

    private JPanel gamePanel;
    private JPanel rightPanel;
    private JPanel filePanel1;
    private JPanel filePanel2;
    private JPanel memoryAndFilePanel1;
    private JPanel memoryAndFilePanel2;
    private int x, y, z;

    ImageIcon scaledIcon;
    JPanel gridPanel;
    JPanel Prototype;

    private JPanel registreRobot1;
    private JPanel registreRobot2;

    //int n;

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
         ImageIcon iconGraph = new ImageIcon("main/src/images/Graphique1.png");
    
         // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
         Image image = iconGraph.getImage();
         Image newImageGraph = image.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
         ImageIcon scaledIconGraph = new ImageIcon(newImageGraph);

         // Chargement des images pour les backgrounds des cases
         ImageIcon iconText = new ImageIcon("main/src/images/Textuel.png");

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
                btnCommencerGraphique.setFont(new Font("Arial", Font.BOLD, 20));
                btnCommencerGraphique.setForeground(Color.WHITE);
                btnCommencerGraphique.setBackground(Color.BLACK);

                JButton btnLevels = new JButton("LEVELS");
                btnLevels.setPreferredSize(new Dimension(200, 60));
                btnLevels.setFont(new Font("Arial", Font.BOLD, 20));
                btnLevels.setForeground(Color.WHITE);
                btnLevels.setBackground(Color.BLACK);


                JButton btnExit = new JButton("EXIT");
                btnExit.setPreferredSize(new Dimension(200, 60));
                btnExit.setFont(new Font("Arial", Font.BOLD, 20));
                btnExit.setForeground(Color.WHITE);
                btnExit.setBackground(Color.BLACK);

                
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
                        btnLevel1.setForeground(Color.WHITE);
                        btnLevel1.setBackground(Color.BLACK);
        
                        JButton btnLevel2 = new JButton("Niveau 2");
                        btnLevel2.setPreferredSize(new Dimension(200, 60));
                        btnLevel2.setFont(new Font("Arial", Font.PLAIN, 20));
                        btnLevel2.setForeground(Color.WHITE);
                        btnLevel2.setBackground(Color.BLACK);
        
                        JButton btnLevel3 = new JButton("Niveau 3");
                        btnLevel3.setPreferredSize(new Dimension(200, 60));
                        btnLevel3.setFont(new Font("Arial", Font.PLAIN, 20));
                        btnLevel3.setForeground(Color.WHITE);
                        btnLevel3.setBackground(Color.BLACK);
        
                        
                        btnLevel1.addActionListener(new ActionListener() {
                        @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau1GUI();
                                jeu.niveau=1;
                                startGUI(1);
                            }
                        });
        
                        btnLevel2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau2GUI();
                                jeu.niveau=2;
                                startGUI(2); 
                        }
                        });
                        btnLevel3.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                levelMenu.dispose();
                                jeu.setNiveau3GUI();
                                jeu.niveau=3;
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
                        jeu.niveau=1;
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
            
            // Créer le JPanel avec l'image de fond
            ImagePanel backgroundPanel = new ImagePanel("main/src/images/FondZone.png");
            backgroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

            // Ajouter le gridPanel à l'intérieur du JPanel avec l'image de fond
            backgroundPanel.add(gridPanel);


            // Chargement des images pour les backgrounds des cases
            ImageIcon icon = new ImageIcon("main/src/images/case.png");
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            scaledIcon = new ImageIcon(newImage);

            createGridCells(gridPanel, x, y, scaledIcon);

            Prototype = gridPanel;

            JScrollPane codeScrollPane = new JScrollPane(backgroundPanel);
    
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
            // Chargement des images pour les backgrounds des cases
            ImageIcon iconPas = new ImageIcon("main/src/images/pasBouttonW.png");

            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image imagePas = iconPas.getImage();
            Image newImagePas = imagePas.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon scaledPasIcon = new ImageIcon(newImagePas);

            stepButton = new JButton("");
            stepButton.setBackground(Color.BLACK);
            stepButton.setIcon(scaledPasIcon);
            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Bloquer les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(false);
                    OctopunksGUI.memoryArea2.setEditable(false);

                    jeu.jouerGUI();
                    updateGUI();

                    if (jeu.L==1) {
                        message_defaiteGUI();      
                    }
                    else if (jeu.W==1){
                        message_victoireGUI();
                    }
                }
            });

            // Chargement des images pour les backgrounds des cases
            ImageIcon iconStop = new ImageIcon("main/src/images/stopButtonW.png");
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image imageStop = iconStop.getImage();
            Image newImageStop = imageStop.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon scaledStopIcon = new ImageIcon(newImageStop);

            stopButton = new JButton("");
            stopButton.setBackground(Color.BLACK);
            stopButton.setIcon(scaledStopIcon);
            stopButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Réinitialiser les valeurs des registres pour chaque robot
                    resetRegisterValues(jeu.robot1,registreRobot1);
                    resetRegisterValues(jeu.robot2,registreRobot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);

                    // Reset de la zone des fichiers
                    resetFilePanel(jeu.robot1, filePanel1);
                    resetFilePanel(jeu.robot2, filePanel2);
            
                    // Réinitialiser d'autres éléments si nécessaire
                    // Effacer le contenu des zones mémoires
                    //memoryArea1.setText("");
                    //memoryArea2.setText("");

                    // Reouvrir les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(true);
                    OctopunksGUI.memoryArea2.setEditable(true);
            
                    // Réinitialiser l'emplacement des robots sur la grille (affichage de setNiveau*())
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
                    jeu.W=0;
                    jeu.L=0;
            
                    // Mettre à jour l'affichage de la grille
                    createGridCells(Prototype, x, y, scaledIcon);
                    stepButton.setEnabled(true);
                    jeu.t1[0]=0;
                    jeu.t2[0]=0;
                    jeu.robot1.setMode(1);
                    jeu.robot2.setMode(1);
                }
            });
            
            // Chargement des images pour les backgrounds des cases
            ImageIcon iconAuto = new ImageIcon("main/src/images/autoButtonW.png");
    
            // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
            Image imageAuto = iconAuto.getImage();
            Image newImageAuto = imageAuto.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            ImageIcon scaledAutoIcon = new ImageIcon(newImageAuto);
            
            autoButton = new JButton("");
            autoButton.setBackground(Color.BLACK);
            autoButton.setIcon(scaledAutoIcon);
            autoButton.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e) {
                    // Bloquer les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(false);
                    OctopunksGUI.memoryArea2.setEditable(false);

                    // Réinitialiser les valeurs des registres pour chaque robot
                    resetRegisterValues(jeu.robot1,registreRobot1);
                    resetRegisterValues(jeu.robot2,registreRobot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);
                    // Réinitialiser l'emplacement des robots sur la grille 
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
                    updateGUI();

                    stepButton.setEnabled(false);
                    
                    if(jeu.W==1){
                        message_victoireGUI();}
                    else {
                        message_defaiteGUI();}
                }
            });
    
            gamePanel = new JPanel();
            gamePanel.setBackground(Color.WHITE);
            gamePanel.setPreferredSize(new Dimension(600, 400));
    
            // Layout setup
            JPanel controlPanel = new JPanel();
            controlPanel.setLayout(new GridLayout(1, 4));
            controlPanel.add(stepButton);
            controlPanel.add(autoButton);
            controlPanel.add(stopButton);
            
            JPanel leftPanel = new JPanel();
            leftPanel.setLayout(new BorderLayout());
            leftPanel.add(codeScrollPane, BorderLayout.CENTER);
            leftPanel.add(controlPanel, BorderLayout.SOUTH);
                        
            // Créer les panneaux affichant les valeurs des registres pour chaque robot
            registreRobot1 = createRegisterPanel(jeu.robot1);
            registreRobot2 = createRegisterPanel(jeu.robot2);

            // Créer le zones des fichiers 
            filePanel1 = createFilePanel(jeu.robot1);
            filePanel2 = createFilePanel(jeu.robot2);
            
            JPanel flowLayoutPanelMemoryArea1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            flowLayoutPanelMemoryArea1.add(memoryScrollPane1);
            flowLayoutPanelMemoryArea1.add(registreRobot1);

            JPanel flowLayoutPanelMemoryArea2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
            flowLayoutPanelMemoryArea2.add(memoryScrollPane2);
            flowLayoutPanelMemoryArea2.add(registreRobot2);

            // Créer un conteneur pour chaque ensemble de mémoire et de contenu de fichier
            memoryAndFilePanel1 = new JPanel(new BorderLayout());
            memoryAndFilePanel1.add(flowLayoutPanelMemoryArea1, BorderLayout.NORTH);
            memoryAndFilePanel1.add(filePanel1);

            memoryAndFilePanel2 = new JPanel(new BorderLayout());
            memoryAndFilePanel2.add(flowLayoutPanelMemoryArea2, BorderLayout.NORTH);
            memoryAndFilePanel2.add(filePanel2);

            rightPanel = new JPanel();
            rightPanel.setLayout(new GridLayout(2, 1));
            rightPanel.add(memoryAndFilePanel1);
            rightPanel.add(memoryAndFilePanel2);

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BorderLayout());
            topPanel.add(leftPanel, BorderLayout.CENTER);
            topPanel.add(rightPanel, BorderLayout.EAST);

            JLabel missionLabel = new JLabel();
            switch (n) {
                case (1) :
                    missionLabel.setText("Mission : Deplacez les fichiers 199 et 299 dans la case [0][2]");
                    break;
                case (2) :
                    missionLabel.setText("Mission : Deplacez le contenu du fichier 667 vers un fichier et deposez le dans la case [0][0]");
                    break;
                case (3) :
                missionLabel.setText("<html>Mission : Recuperer le fichier 444, additionner les 2 premieres valeurs, multiplier par la troisieme, soustraire la 4eme, additionner avec la premiere, <br>mettre tout les resultats dans une pile et la deposer a la case [4][4], detruire le fichier 444 et le robot qui l'a recuperé</html>");
                    break;
            }
            missionLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            missionLabel.setForeground(Color.BLACK);

            JPanel bottomPanel = new JPanel();
            bottomPanel.setPreferredSize(new Dimension(600, 100));
            bottomPanel.setLayout(new BorderLayout());
            

            JButton openPdfButton = new JButton("Manuel");
            openPdfButton.setPreferredSize(new Dimension(100, 50));
            // Définition de la taille du texte
            Font buttonFont = new Font("Times New Roman", Font.BOLD, 20);
            openPdfButton.setFont(buttonFont);
            openPdfButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ouvrirPDF();
                }
            });

            bottomPanel.add(missionLabel, BorderLayout.PAGE_START);
            bottomPanel.add(openPdfButton, BorderLayout.EAST);
            
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(topPanel, BorderLayout.NORTH);
            getContentPane().add(gamePanel, BorderLayout.CENTER);
            getContentPane().add(bottomPanel, BorderLayout.AFTER_LAST_LINE); // Ajouter bottomPanel au Sud

            setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
            
            pack();
            setVisible(true);
        });
    }

    protected void updateGUI() {
        // Mettre à jour l'affichage de la grille
        createGridCells(gridPanel, x, y, scaledIcon);
    
        // Mettre à jour les panneaux de registres et le contenu des fichiers pour chaque robot
        updateRegisterPanel(registreRobot1, jeu.robot1);
        updateRegisterPanel(registreRobot2, jeu.robot2);
    
        // Mettre à jour le contenu des fichiers
        updateFilePanel(jeu.robot1, filePanel1, jeu.robot1.getFichier());
        updateFilePanel(jeu.robot2, filePanel2, jeu.robot2.getFichier());
    
        // Rafraîchir l'interface utilisateur*/
        revalidate();
        repaint();
    }
    
    
    
    private JPanel createFilePanel(Robot robot) {
        Fichier fichier = robot.getFichier();
       
        // Crée un panneau pour afficher le contenu du fichier
        JPanel filePanel = new JPanel(new BorderLayout());
    
        // Crée une zone de texte pour le contenu du fichier
        JTextArea fileContentArea = new JTextArea(5, 30);
        fileContentArea.setEditable(false); 
        fileContentArea.setLineWrap(true);
        fileContentArea.setWrapStyleWord(true);
        
        JLabel fileLabel;
        String nomFichier;

        // Crée un label pour le titre
        if(fichier != null){  
            nomFichier = fichier.getName();
        }else{
            nomFichier = "";
        }

        fileLabel = new JLabel(""+ nomFichier, SwingConstants.CENTER);
        fileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Ajoute le label et la zone de texte au panneau
        filePanel.add(fileLabel, BorderLayout.NORTH);
        filePanel.add(new JScrollPane(fileContentArea), BorderLayout.CENTER);
        
        return filePanel;
    }
    
    protected void updateFilePanel(Robot robot, JPanel filePanel, Fichier fichier) {
        // Récupérer la zone de texte du contenu du fichier
        JTextArea fileContentArea = (JTextArea) ((JScrollPane) filePanel.getComponent(1)).getViewport().getView();
        String nomFichier;
        // Obtenez le JLabel existant du panneau
        JLabel fileLabel = (JLabel) filePanel.getComponent(0);
    
        // Mettre à jour le texte du JLabel avec le nouveau nom de fichier
        if (robot.getFichier() != null) {
            nomFichier = robot.getFichier().getName();
        } else {
            nomFichier = "";
        }
        fileLabel.setText(nomFichier);
        // Récupérer les éléments sous forme de tableau de chaînes de caractères
        String[] elems = fichier == null ? new String[0] : fichier.elemsToString();
    
        // Créer une chaîne de caractères pour le contenu du fichier avec soulignement
        StringBuilder fileContent = new StringBuilder();
        for (int i = 0; i < elems.length; i++) {
            // Vérifier si l'index correspond à l'index retourné par getPos()
            if (fichier != null && i == fichier.getPosFichier()) {
                // Ajouter le texte souligné
                fileContent.append("[").append(elems[i]).append("]");
            } else {
                // Ajouter le texte sans soulignement
                fileContent.append(elems[i]);
            }
            // Ajouter une virgule si ce n'est pas le dernier élément
            if (i < elems.length - 1) {
                fileContent.append(", ");
            }
        }
    
        // Mettre à jour le contenu du fichier dans la zone de texte
        fileContentArea.setText(fileContent.toString());
    }
    
    
    private JPanel createRegisterPanel(Robot robot) {
        // Récupérer les valeurs initiales des registres
        int rX = robot.getX().getValeur();
        int rT = robot.getT().getValeur();
        String rF = robot.getFichier()==null ? "None" : robot.getFichier().getName();
        String rM = jeu.getM().getValeur()==0 ? "None" : String.valueOf(jeu.getM().getValeur());

        // Créer un tableau de données pour les valeurs des registres   
        String[][] data = {
                {"X", String.valueOf(rX)},
                {"T", String.valueOf(rT)}, 
                {"F", rF}, 
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
        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.add(table, BorderLayout.CENTER);

        // Créer et ajouter un bouton
        final JButton button = new JButton("");
        button.setPreferredSize(new Dimension(50, 25));

        ImageIcon iconSwitch = new ImageIcon("main/src/images/globale.jpg");
        // Redimensionner l'image pour qu'elle s'adapte à la taille des cases
        Image imageSwitch = iconSwitch.getImage();
        Image newImageSwitch = imageSwitch.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledSwitchIcon = new ImageIcon(newImageSwitch);
        button.setBackground(Color.white);
        button.setIcon(scaledSwitchIcon);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon newIcon;
                if (robot.getMode()==1) {
                    robot.setMode(0);
                    newIcon = new ImageIcon("main/src/images/local.jpg");                
                }
                else {
                    robot.setMode(1);
                    newIcon = new ImageIcon("main/src/images/globale.jpg"); 
                }
                Image image = newIcon.getImage();
                Image newImage = image.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(newImage);
                button.setIcon(scaledIcon);
            }
        });

        // Ajout du panneau du bouton en bas
        registerPanel.add(button, BorderLayout.SOUTH);
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
    
    
    private void resetRegisterValues(Robot robot, JPanel registerPanel) {
        // Réinitialiser les valeurs des registres du robot
        robot.getX().setValeur(0);
        robot.getT().setValeur(0);
        robot.setFichier(null);
        jeu.getM().setValeur(0);

        ImageIcon newIcon = new ImageIcon("main/src/images/globale.jpg");
        Image image = newIcon.getImage();
        Image newImage = image.getScaledInstance(50, 25, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(newImage);
        JButton button = (JButton) registerPanel.getComponent(1);
        button.setIcon(scaledIcon);
    }
    
    private void resetFilePanel(Robot robot, JPanel filePanel) {
        robot.setFichier(null);
        updateFilePanel(robot,filePanel,robot.getFichier());
    }

     public void ouvrirPDF() {
        try {
            File file = new File("main/src/ManOctopunks.pdf");
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    System.out.println("Desktop not supported");
                }
            } else {
                System.out.println("Fichier PDF introuvable");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static Image resizeImage(String imagePath, int width, int height) {
        try {
            // Chargement de l'image
            File file = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(file);
    
            // Redimensionnement de l'image
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    
            return resizedImage;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void message_victoireGUI() {
        // Création de la fenêtre modale
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300); // Augmentation de la taille de la fenêtre
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE); // Fond blanc pour la fenêtre

        // Message de victoire
        JLabel label = new JLabel("Félicitations !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30)); // Changement de la police d'écriture
        label.setForeground(Color.BLACK); // Couleur du texte en noir
        frame.add(label, BorderLayout.NORTH);

        // Boutons "Exit" et "Niveau suivant"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10)); // Utilisation d'un GridLayout avec un espace vertical de 10 pixels entre les boutons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 30, 0)); // Espacement en haut et en bas des boutons

        JButton exitButton = new JButton("Exit");
        JButton nextLevelButton = new JButton("Niveau suivant");

        exitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Changement de la police d'écriture pour les boutons
        exitButton.setBackground(Color.BLACK); // Fond noir pour le bouton
        exitButton.setForeground(Color.WHITE); // Texte blanc pour le bouton
        exitButton.setFocusPainted(false); // Suppression du contour de focus
        exitButton.setMargin(new Insets(10, 20, 10, 20)); // Espacement entre le texte et les bords du bouton

        nextLevelButton.setFont(new Font("Arial", Font.BOLD, 20)); // Changement de la police d'écriture pour les boutons
        nextLevelButton.setBackground(Color.BLACK); // Fond noir pour le bouton
        nextLevelButton.setForeground(Color.WHITE); // Texte blanc pour le bouton
        nextLevelButton.setFocusPainted(false); // Suppression du contour de focus
        nextLevelButton.setMargin(new Insets(10, 20, 10, 20)); // Espacement entre le texte et les bords du bouton

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quitter le jeu
            }
        });

        nextLevelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fermer la fenêtre du menu principal
                frame.dispose();
                //getContentPane().removeAll();
                passerAuNiveauSuivant(jeu.niveau);
                // Réinitialiser les valeurs des registres pour chaque robot
                resetRegisterValues(jeu.robot1,registreRobot1);
                resetRegisterValues(jeu.robot2,registreRobot2);
        
                // Rafraîchir l'affichage des panneaux de registres
                updateRegisterPanel(registreRobot1, jeu.robot1);
                updateRegisterPanel(registreRobot2, jeu.robot2);

                // Reset de la zone des fichiers
                resetFilePanel(jeu.robot1, filePanel1);
                resetFilePanel(jeu.robot2, filePanel2);

                // Reouvrir les zones de texte
                OctopunksGUI.memoryArea1.setEditable(true);
                OctopunksGUI.memoryArea2.setEditable(true);
        
                // Réinitialiser l'emplacement des robots sur la grille (affichage de setNiveau1())
                switch (jeu.niveau) {
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
                jeu.W=0;
                jeu.L=0;
        
                // Mettre à jour l'affichage de la grille
                createGridCells(Prototype, x, y, scaledIcon);
                stepButton.setEnabled(true);
                jeu.t1[0]=0;
                jeu.t2[0]=0;
                jeu.robot1.setMode(1);
                jeu.robot2.setMode(1);
                updateGUI();

            }
        });

        buttonPanel.add(exitButton);
        buttonPanel.add(nextLevelButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }

    public void message_defaiteGUI() {
        // Création de la fenêtre modale
        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300); // Augmentation de la taille de la fenêtre
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.WHITE); // Fond blanc pour la fenêtre

        // Message de défaite
        JLabel label = new JLabel("Mince ! Vous avez perdu !");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30)); // Changement de la police d'écriture
        label.setForeground(Color.BLACK); // Couleur du texte en noir
        frame.add(label, BorderLayout.NORTH);

        // Boutons "Exit" et "Réessayer le niveau"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 10)); // Utilisation d'un GridLayout avec un espace vertical de 10 pixels entre les boutons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Espacement en haut des boutons

        JButton exitButton = new JButton("Exit");
        JButton retryLevelButton = new JButton("Réessayer le niveau");

        exitButton.setFont(new Font("Arial", Font.BOLD, 20)); // Changement de la police d'écriture pour les boutons
        exitButton.setBackground(Color.BLACK); // Fond noir pour le bouton
        exitButton.setForeground(Color.WHITE); // Texte blanc pour le bouton
        exitButton.setFocusPainted(false); // Suppression du contour de focus
        exitButton.setMargin(new Insets(10, 20, 10, 20)); // Espacement entre le texte et les bords du bouton

        retryLevelButton.setFont(new Font("Arial", Font.BOLD, 20)); // Changement de la police d'écriture pour les boutons
        retryLevelButton.setBackground(Color.BLACK); // Fond noir pour le bouton
        retryLevelButton.setForeground(Color.WHITE); // Texte blanc pour le bouton
        retryLevelButton.setFocusPainted(false); // Suppression du contour de focus
        retryLevelButton.setMargin(new Insets(10, 20, 10, 20)); // Espacement entre le texte et les bords du bouton

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Quitter le jeu
            }
        });

        retryLevelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    // Réinitialiser les valeurs des registres pour chaque robot
                    resetRegisterValues(jeu.robot1,registreRobot1);
                    resetRegisterValues(jeu.robot2,registreRobot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);

                    // Reset de la zone des fichiers
                    resetFilePanel(jeu.robot1, filePanel1);
                    resetFilePanel(jeu.robot2, filePanel2);

                    // Reouvrir les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(true);
                    OctopunksGUI.memoryArea2.setEditable(true);

            
                    // Réinitialiser l'emplacement des robots sur la grille (affichage de setNiveau1())
                    switch (jeu.niveau) {
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
                    updateGUI();


                    jeu.resetPosition();
                    jeu.W=0;
                    jeu.L=0;
            
                    // Mettre à jour l'affichage de la grille
                    createGridCells(Prototype, x, y, scaledIcon);
                    stepButton.setEnabled(true);
                    jeu.t1[0]=0;
                    jeu.t2[0]=0;
                    jeu.robot1.setMode(1);
                    jeu.robot2.setMode(1);

                    frame.dispose();
                }
        });

        buttonPanel.add(exitButton);
        buttonPanel.add(retryLevelButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Centrer la fenêtre sur l'écran
        frame.setLocationRelativeTo(null);

        // Rendre la fenêtre visible
        frame.setVisible(true);
    }


    private void passerAuNiveauSuivant(int niveauActuel) {
        if (niveauActuel < 3) {
            jeu.niveau = niveauActuel + 1;
            startGUI(jeu.niveau);
            updateGUI();

        } else {
            // Si le niveau actuel est déjà le niveau 3, afficher un message ou effectuer toute autre action nécessaire
            ImageIcon victoireIcon = new ImageIcon(resizeImage("main/src/images/octo_down.png", 50, 50));
            JOptionPane.showMessageDialog(null, "Vous avez terminé tous les niveaux disponibles !", "Fin du jeu", JOptionPane.INFORMATION_MESSAGE,victoireIcon);
        }
    }
}
