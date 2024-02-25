package model;

import java.util.Random;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Jeu {
    public ObjetJ [][][] grille = new ObjetJ[5][5][4];
    private Random random;
    public Robot robot1;
    public Robot robot2;
    public Registre M;

    // Déclarer des variables membres pour suivre la position actuelle dans les listes d'instructions de chaque robot
    private int positionR1 = 0;
    private int positionR2 = 0;


    public Jeu() {
        this.robot1= new Robot("Robot1", 4, 0,0);
        this.robot2= new Robot("Robot2", 4, 4,0);
        //grille[4][0][0] = robot1;
        //grille[4][4][0] = robot2;
        this.random=new Random();
        this.M = new Registre();
    }

    
    public ArrayList<Instruction> parseTextFromInput(int r) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer le texte pour le robot "+r+",(tapez 'fin' pour terminer) :");

        // Lire le texte jusqu'à ce que l'utilisateur entre "fin"
        while (true) {
            String inputLine = scanner.nextLine();

            if (inputLine.equals("fin")) {
                break; // Sortir de la boucle si l'utilisateur entre "fin"
            }

            String[] parts = inputLine.split("\\s+");
            String command = parts[0];

            String[] parameters = new String[parts.length - 1];
            System.arraycopy(parts, 1, parameters, 0, parameters.length);

            Instruction instruction = new Instruction(command, parameters);
            instructionsList.add(instruction);
        }

        //scanner.close();

        return instructionsList;
    }

    public void setNiveau1() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                for (int k = 0; k < grille[i][j].length; k++) {
                    grille[i][j][k] = null; // Affecter null à chaque élément
                }
            }
        }
        grille[4][0][0] = robot1;
        grille[4][4][0] = robot2;
        
        for (int i=0 ;i<4; i++){
            grille[1][3][i] = new Obstacle("obstacle",1,3,i);
            grille[2][1][i] = new Obstacle("obstacle",2,1,i);
            grille[3][4][i] = new Obstacle("obstacle",3,4,i);
            grille[4][2][i] = new Obstacle("obstacle",4,1,i);        
        }
        Fichier fichier = new TableauDynamique("199",2,2,1);
        grille[2][2][1] = fichier;
        grille[2][3][2] = new TableauDynamique("299",2,3,2);
        System.out.println("Mission : Deplacez les fichiers 199 et 299 dans la case [0][2]:");
        System.out.println();
    }

    public void afficherJeu () {
            for (ObjetJ[][] ligne : grille) {
                System.out.print("|");
                for (ObjetJ[] colonne : ligne) {
                    for (ObjetJ obj : colonne){
                        if (obj == null) {
                            System.out.print("     ");
                        } else {
                            obj.afficher();
                        }
                    }
                    System.out.print("|");
                }
                System.out.println();    
            }
            System.out.println(); 
            System.out.println(); 
    }

    public void setNiveau1GUI() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                for (int k = 0; k < grille[i][j].length; k++) {
                    grille[i][j][k] = null; // Affecter null à chaque élément
                }
            }
        }
        grille[4][0][0] = robot1;
        robot1.setAbscisse(4);
        robot1.setOrdonnee(0);
        robot1.setCaseJ(0);
        robot1.ressuciter();
        robot1.setFichier(null);

        grille[4][4][0] = robot2;
        robot2.setAbscisse(4);
        robot2.setOrdonnee(4);
        robot2.setCaseJ(0);
        robot2.ressuciter();
        robot2.setFichier(null);

        for (int i=0 ;i<4; i++){
            grille[1][3][i] = new Obstacle("obstacle",1,3,i);
            grille[2][1][i] = new Obstacle("obstacle",2,1,i);
            grille[3][4][i] = new Obstacle("obstacle",3,4,i);
            grille[4][2][i] = new Obstacle("obstacle",4,1,i);        
        }
        Fichier fichier1 = new TableauDynamique("199",2,2,1);
        grille[2][2][1] = fichier1;
        fichier1.setAbscisse(2);
        fichier1.setOrdonnee(2);
        fichier1.setCaseJ(1);
        Fichier fichier2 = new TableauDynamique("299",2,3,2);
        grille[2][3][2] = fichier2;
        fichier2.setAbscisse(2);
        fichier2.setOrdonnee(3);
        fichier2.setCaseJ(2);
    }

    public void afficherJeuGUI() {
            System.out.println("vous etes dans la partie graphique de OCTOPUNKS");
    }
    
    public void afficherMenuNiveaux() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Choisissez un niveau :");
            System.out.println("Tapez 1 pour jouer le niveau 1");
            System.out.println("Tapez 2 pour jouer le niveau 2");
            System.out.println("Tapez 3 pour jouer le niveau 3");
            System.out.println("Tapez 0 pour quitter le jeu");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    jouer();
                    break;
                case 2:

                    break;
                case 3:
                    // Implémentez la logique pour le niveau 3
                    break;
                case 0:
                    System.out.println("Merci d'avoir joué. Au revoir !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir un niveau valide.");
            }
        } finally {

            scanner.close();
        }
    }

    public boolean verifierVictoire() {
        // Vérifier si les fichiers sont à la position attendue
        Fichier fichier199 = (Fichier) grille[0][2][1];
        Fichier fichier299 = (Fichier) grille[0][2][2];

        if (fichier199 != null && fichier299 != null) {
            // Vérifier si les fichiers sont à la position [0][2]
            if (estAEmplacementAttendu(fichier199, 0, 2, 1) && estAEmplacementAttendu(fichier299, 0, 2, 2)) {
                System.out.println("Victoire ! Vous avez déplacé les fichiers à la position attendue.");
                return true;
            }
        }

        return false;
    }

    private boolean estAEmplacementAttendu(Fichier fichier, int abscisseAttendue, int ordonneeAttendue,
            int caseJAttendue) {
        return fichier.getAbscisse() == abscisseAttendue &&
                fichier.getOrdonnee() == ordonneeAttendue &&
                fichier.getCaseJ() == caseJAttendue;
    }

    public boolean verifierDefaiteniveau1() {
        return !(robot1.getVivant() && robot2.getVivant());
    }

    public void jouer() {
        setNiveau1();
        afficherJeu();
        ArrayList<Instruction> instruR1 = parseTextFromInput(1);
        ArrayList<Instruction> instruR2 = parseTextFromInput(2);

        int i = 0, j = 0;
        int[] tab1 = { i };
        int[] tab2 = { j };
        while (tab1[0] < instruR1.size() && tab2[0] < instruR2.size()) {
            double choix = random.nextDouble();
            if (choix < 0.5) {
                if (robot1.getVivant())
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1);
                if (robot2.getVivant())
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2);
            } else {
                if (robot2.getVivant())
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2);
                if (robot1.getVivant())
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1);
            }
            if (verifierDefaiteniveau1()) {
                afficherMessage("Vous avez perdu !");

                tab1[0]++;
                tab2[0]++;
            }

            // Vérification de la victoire à la fin du niveau 1
            if (verifierVictoire()) {
                afficherMessage("Vous avez gagné !");
            }

            // Affichage du jeu après avoir traité toutes les instructions
            afficherJeu();

            int k = 0;
            int[] tab = { k };
            ArrayList<Instruction> instru;
            Robot robot;

            if (tab1[0] == instruR1.size() && tab2[0] == instruR2.size())
                return;
            if (tab1[0] == instruR1.size() && tab2[0] < instruR2.size()) {
                tab[0] = tab2[0];
                instru = instruR2;
                robot = robot2;
            } else {
                tab[0] = tab1[0];
                instru = instruR1;
                robot = robot1;
            }

            while (tab[0] < instru.size() && robot.getVivant()) {
                instru.get(tab[0]).execute(grille, robot, instru, tab);
                afficherJeu();
                tab[0]++;
            }
        }
    }

   /* public void jouer(){
        setNiveau1();
        afficherJeu();
        ArrayList<Instruction> instruR1 = parseTextFromInput(1);
        ArrayList<Instruction> instruR2 = parseTextFromInput(2);

        int i=0, j=0;
        int[] tab1 = {i};
        int[] tab2 = {j};
        while (tab1[0]<instruR1.size() && tab2[0]<instruR2.size()){
            double choix=random.nextDouble();
            if(choix<0.5){
                if (robot1.getVivant()) instruR1.get(tab1[0]).execute(grille,robot1,instruR1,tab1);
                if (robot2.getVivant()) instruR2.get(tab2[0]).execute(grille,robot2,instruR2,tab2);
            }
            else{
                if (robot2.getVivant()) instruR2.get(tab2[0]).execute(grille,robot2,instruR2,tab2);
                if (robot1.getVivant()) instruR1.get(tab1[0]).execute(grille,robot1,instruR1,tab1);
            }
            tab1[0]++;
            tab2[0]++;
            afficherJeu();
        }

        int k=0;
        int[] tab = {k};
        ArrayList<Instruction> instru;
        Robot robot;

        if (tab1[0]==instruR1.size() && tab2[0]==instruR2.size()) return;
        if (tab1[0]==instruR1.size() && tab2[0]<instruR2.size()) {
            tab[0]=tab2[0];
            instru = instruR2;
            robot = robot2;
        } 
        else {
            tab[0]=tab1[0];
            instru = instruR1;
            robot = robot1;
        }

        while (tab[0]<instru.size() && robot.getVivant()) {
            instru.get(tab[0]).execute(grille,robot,instru,tab);
            afficherJeu();
            tab[0]++;
        }
    }*/


    public ArrayList<Instruction> parseTextFromInput(String robotText) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        // Séparer le texte par lignes
        String[] lines = robotText.split("\n");
    
        // Parcourir chaque ligne de texte
        for (String line : lines) {
            // Séparer la ligne par les espaces
            String[] parts = line.split("\\s+");
    
            String command = parts[0];
    
            // Si la ligne contient des paramètres
            if (parts.length > 1) {
                String[] parameters = new String[parts.length - 1];
                System.arraycopy(parts, 1, parameters, 0, parameters.length);
                Instruction instruction = new Instruction(command, parameters);
                instructionsList.add(instruction);
            } else {
                // Si la ligne ne contient que la commande sans paramètres
                Instruction instruction = new Instruction(command);
                instructionsList.add(instruction);
            }
        }
    
        return instructionsList;
    }


    public void jouerGUIAuto() {
        setNiveau1();
        ArrayList<Instruction> instruR1 = parseTextFromInputGUI(OctopunksGUI.memoryArea1.getText());
        ArrayList<Instruction> instruR2 = parseTextFromInputGUI(OctopunksGUI.memoryArea2.getText());

        int i=0, j=0;
        int[] tab1 = {i};
        int[] tab2 = {j};
        while (tab1[0]<instruR1.size() && tab2[0]<instruR2.size()){
            double choix=random.nextDouble();
            if(choix<0.5){
                if (robot1.getVivant()) instruR1.get(tab1[0]).execute(grille,robot1,instruR1,tab1);
                if (robot2.getVivant()) instruR2.get(tab2[0]).execute(grille,robot2,instruR2,tab2);
            }
            else{
                if (robot2.getVivant()) instruR2.get(tab2[0]).execute(grille,robot2,instruR2,tab2);
                if (robot1.getVivant()) instruR1.get(tab1[0]).execute(grille,robot1,instruR1,tab1);
            }
            tab1[0]++;
            tab2[0]++;
            afficherJeu();
        }
    
        int k=0;
        int[] tab = {k};
        ArrayList<Instruction> instru;
        Robot robot;
    
        if (tab1[0]==instruR1.size() && tab2[0]==instruR2.size()) return;
        if (tab1[0]==instruR1.size() && tab2[0]<instruR2.size()) {
            tab[0]=tab2[0];
            instru = instruR2;
            robot = robot2;
        } 
        else {
            tab[0]=tab1[0];
            instru = instruR1;
            robot = robot1;
        }
    
        while (tab[0]<instru.size() && robot.getVivant()) {
            instru.get(tab[0]).execute(grille,robot,instru,tab);
            afficherJeu();
            tab[0]++;
        }
    }

    public void jouerGUI() {
        // Si les deux zones de texte sont vides ou si l'une des deux est vide, retourner
        /*if (OctopunksGUI.memoryArea1.getText().isEmpty() || OctopunksGUI.memoryArea2.getText().isEmpty()) {
            return;
        }*/
    
        // Récupérer les instructions à partir des zones de texte
        ArrayList<Instruction> instruR1 = parseTextFromInputGUI(OctopunksGUI.memoryArea1.getText());
        ArrayList<Instruction> instruR2 = parseTextFromInputGUI(OctopunksGUI.memoryArea2.getText());
    
        // Récupérer la prochaine instruction à exécuter pour chaque robot
        Instruction nextInstruR1 = getNextInstruction(instruR1, positionR1);
        Instruction nextInstruR2 = getNextInstruction(instruR2, positionR2);
    
        // Exécuter les instructions pour chaque robot s'ils sont vivants
        if (robot1.getVivant() && nextInstruR1 != null) {
            nextInstruR1.execute(grille, robot1, instruR1, null);
            positionR1++; // Avancer la position pour le robot 1
        }
        if (robot2.getVivant() && nextInstruR2 != null) {
            nextInstruR2.execute(grille, robot2, instruR2, null);
            positionR2++; // Avancer la position pour le robot 2
        }
     
        
        // Mettre à jour l'affichage du jeu
        afficherJeuGUI();
    }
    
    private Instruction getNextInstruction(ArrayList<Instruction> instructions, int position) {
        // Vérifier si nous avons atteint la fin des instructions
        if (instructions == null || position >= instructions.size()) {
            return null;
        }
    
        // Récupérer l'instruction à la position actuelle
        return instructions.get(position);
    }
    
    public void resetPosition() { 
        positionR1=0;
        positionR2=0;
    }
    
    public ArrayList<Instruction> parseTextFromInputGUI(String robotText) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        // Séparer le texte par lignes
        String[] lines = robotText.split("\n");
            
        // Parcourir chaque ligne de texte
        for (String line : lines) {
            // Séparer la ligne par les espaces
            String[] parts = line.split("\\s+");
            
            String command = parts[0];
            
            // Si la ligne contient des paramètres
            if (parts.length > 1) {
                String[] parameters = new String[parts.length - 1];
                System.arraycopy(parts, 1, parameters, 0, parameters.length);
                Instruction instruction = new Instruction(command, parameters);
                instructionsList.add(instruction);
            } else {
                // Si la ligne ne contient que la commande sans paramètres
                Instruction instruction = new Instruction(command);
                instructionsList.add(instruction);
            }
        }
            
        return instructionsList;
    }
    
}
    
