package model;



import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.Flow;


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
                leJeu.jouer();

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
                     // Ajouter l'action pour le bouton "Levels"
                     System.exit(0);                   
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
                        startGUI();
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
                    
                    final int finalK = k;
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
                            // Affichez le tooltip au survol de la souris
                            String commentaire = subCellLabels[finalK].getToolTipText();
                            System.out.println(commentaire);
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
    
    

    public void startGUI() {
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
            ImageIcon icon = new ImageIcon("main/src/images/case.png"); // Assurez-vous de charger une image de 16x16 pixels
    
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
            ImageIcon iconPas = new ImageIcon("main/src/images/pasBouttonW.png"); // Assurez-vous de charger une image de 16x16 pixels
    
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
                }
            });

            // Chargement des images pour les backgrounds des cases
            ImageIcon iconStop = new ImageIcon("main/src/images/stopButtonW.png"); // Assurez-vous de charger une image de 16x16 pixels
    
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
                    resetRegisterValues(jeu.robot1);
                    resetRegisterValues(jeu.robot2);
            
                    // Rafraîchir l'affichage des panneaux de registres
                    updateRegisterPanel(registreRobot1, jeu.robot1);
                    updateRegisterPanel(registreRobot2, jeu.robot2);
            
                    // Réinitialiser d'autres éléments si nécessaire
                    // Effacer le contenu des zones mémoires
                    memoryArea1.setText("");
                    memoryArea2.setText("");

                    // Reouvrir les zones de texte
                    OctopunksGUI.memoryArea1.setEditable(true);
                    OctopunksGUI.memoryArea2.setEditable(true);
            
                    // Réinitialiser l'emplacement des robots sur la grille (affichage de setNiveau1())
                    jeu.setNiveau1GUI();
                    jeu.resetPosition();
            
                    // Mettre à jour l'affichage de la grille
                    createGridCells(Prototype, x, y, scaledIcon);
                }
            });
            
            // Chargement des images pour les backgrounds des cases
            ImageIcon iconAuto = new ImageIcon("main/src/images/autoButtonW.png"); // Assurez-vous de charger une image de 16x16 pixels
    
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

                    jeu.jouerGUIAuto();

                    updateGUI();
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

            // Ajout de la zone de texte en bas de la page
            JLabel missionLabel = new JLabel("Zone de la mission : ");
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new BorderLayout());
            bottomPanel.add(missionLabel);

            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(topPanel, BorderLayout.NORTH);
            getContentPane().add(gamePanel, BorderLayout.CENTER);
            getContentPane().add(bottomPanel, BorderLayout.SOUTH); // Ajouter bottomPanel au Sud

            setExtendedState(JFrame.MAXIMIZED_BOTH); // Mettre en plein écran
            //setUndecorated(true); // Enlever la décoration de fenêtre (barre de titre, boutons de fermeture, etc.)

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

        String fileContent = "j'ai pas de moyen pour afficher le cotenu pour le moment hihi";

        // Mettre a jour de contenu des fichiers 
        updateFilePanel(jeu.robot1,filePanel1, fileContent);
        updateFilePanel(jeu.robot2, filePanel2, fileContent);

        // Rafraîchir l'interface utilisateur
        revalidate();
        repaint();
    }
    
    private JPanel createFilePanel(Robot robot) {
        Fichier fichier = robot.getFichier();
       
        // Crée un panneau pour afficher le contenu du fichier
        JPanel filePanel = new JPanel(new BorderLayout());
    
        // Crée une zone de texte pour le contenu du fichier
        JTextArea fileContentArea = new JTextArea(5, 30);
        fileContentArea.setEditable(false); // Assurez-vous que l'utilisateur ne peut pas modifier le contenu
        fileContentArea.setLineWrap(true); // Assurez-vous que le texte se coupe correctement
        fileContentArea.setWrapStyleWord(true); // Assurez-vous que les mots se coupent correctement
        
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
    
    private void updateFilePanel(Robot robot,JPanel filePanel, String fileContent) {
        // Récupère la zone de texte du contenu du fichier
        JTextArea fileContentArea = (JTextArea) ((JScrollPane) filePanel.getComponent(1)).getViewport().getView();
    
        // Met à jour le contenu du fichier dans la zone de texte
        fileContentArea.setText(fileContent);
        
        String nomFichier;
        // Crée un label pour le titre
        if(robot.getFichier() != null){  
            nomFichier = robot.getFichier() .getName();
        }else{
            nomFichier = "";
        }

        JLabel fileLabel = new JLabel("" + nomFichier, SwingConstants.CENTER);
        fileLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        // Ajoute le label et la zone de texte au panneau
        filePanel.add(fileLabel, BorderLayout.NORTH);
    }
    
    private JPanel createRegisterPanel(Robot robot) {
        // Récupérer les valeurs initiales des registres
        int rX = robot.getX().getValeur();
        int rT = robot.getT().getValeur();
        String rF = "NONE";
        if(robot.getFichier() != null){
            rF = robot.getFichier().getName();
        }
    
        // Créer un tableau de données pour les valeurs des registres
        String[][] data = {
            {"X", String.valueOf(rX)},
            {"T", String.valueOf(rT)},
            {"F", rF}  
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
        String rF = "NONE";
        if (robot.getFichier() != null) {
            rF = robot.getFichier().getName();
        }
    
        // Mettre à jour les valeurs affichées dans le tableau des registres
        JTable table = (JTable) registerPanel.getComponent(0);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setValueAt(rX + "", 0, 1);
        tableModel.setValueAt(rT + "", 1, 1);
        tableModel.setValueAt(rF + "", 2, 1);
    }
    
    private void resetRegisterValues(Robot robot) {
        // Réinitialiser les valeurs des registres du robot
        robot.getX().setValeur(0);
        robot.getT().setValeur(0);
        robot.setFichier(null);

    }
    
}
