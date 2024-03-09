package model;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class Jeu {
    public ObjetJ [][][] grille = new ObjetJ[5][5][4];
    private Random random;
    public Robot robot1;
    public Robot robot2;
    public Fichier f1;
    public Fichier f2;
    public int niveau;
    public JLabel messageLabel;

    public int W=0; //win
    public int L=0; //lose

    private Registre M;
    public Registre getM(){return this.M;}

    // Déclarer des variables membres pour suivre la position actuelle dans les listes d'instructions de chaque robot
    private int positionR1 = 0;
    private int positionR2 = 0;
    public int[] t1 = {positionR1};
    public int[] t2 = {positionR2};

    public Jeu() {
        this.robot1= new Robot("Robot1", 4, 0,0);
        this.robot2= new Robot("Robot2", 4, 4,0);
        //grille[4][0][0] = robot1;
        //grille[4][4][0] = robot2;
        this.random=new Random();
        this.M = new Registre();
        messageLabel = new JLabel();
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
                    niveau=1;
                    setNiveau1();
                    jouer();
                    break;
                case 2:
                    niveau=2;
                    setNiveau2();
                    jouer();
                    break;
                case 3:
                    niveau=3;
                    setNiveau3();
                    jouer();
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

    public void afficherJeuGUI() {
        System.out.println("vous etes dans la partie graphique de OCTOPUNKS");
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
        f1 = new TableauDynamique("199",2,2,1);
        grille[2][2][1] = f1;
        f2 = new TableauDynamique("299",2,3,2);
        grille[2][3][2] = f2;
        System.out.println("Mission : Deplacez les fichiers 199 et 299 dans la case [0][2]:");
        System.out.println();
    }
 
    public void setNiveau2(){
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
            grille[0][3][i] = new Obstacle("obstacle",0,3,i);
            grille[1][3][i] = new Obstacle("obstacle",1,3,i);
            grille[2][3][i] = new Obstacle("obstacle",2,3,i);
            grille[3][3][i] = new Obstacle("obstacle",3,3,i);        
            grille[4][3][i] = new Obstacle("obstacle",4,3,i);        
        }
        ArrayList<Integer> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        f1 = new TableauDynamique("667",0,4,3,liste);
        grille[0][4][3] = f1;
        
        System.out.println("Mission : Deplacez le contenu du fichier 667 vers un fichier et deposez le dans la case [0][0]:");
        System.out.println();
    }

    public void setNiveau3(){
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
            grille[4][1][i] = new Obstacle("obstacle",0,3,i);
            grille[3][0][i] = new Obstacle("obstacle",1,3,i);
            grille[4][3][i] = new Obstacle("obstacle",2,3,i);
            grille[3][4][i] = new Obstacle("obstacle",3,3,i);        
        }
        ArrayList<Integer> liste = new ArrayList<>();
        liste.add(24);
        liste.add(6);
        liste.add(2);
        liste.add(24);
        f1 = new TableauDynamique("111",4,0,1,liste);
        grille[4][0][1] = f1;
        
        System.out.println("Mission : Recuperer le fichier 111, additionner les 2 premiere valuers, multiplier par la troisieme, soustraire la 4eme, additionner la premiere, mettre chaque resultat dans une pile et la deposer a la case [4][4]");
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
        f1 = new TableauDynamique("199",2,2,1);
        grille[2][2][1] = f1;
        f2 = new TableauDynamique("299",2,3,2);
        grille[2][3][2] = f2;
        f1.setAbscisse(2);
        f1.setOrdonnee(2);
        f1.setCaseJ(1);
        f2.setAbscisse(2);
        f2.setOrdonnee(3);
        f2.setCaseJ(2);
    }
    
    public void setNiveau2GUI(){
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
            grille[0][3][i] = new Obstacle("obstacle",0,3,i);
            grille[1][3][i] = new Obstacle("obstacle",1,3,i);
            grille[2][3][i] = new Obstacle("obstacle",2,3,i);
            grille[3][3][i] = new Obstacle("obstacle",3,3,i);        
            grille[4][3][i] = new Obstacle("obstacle",4,3,i);        
        }
        ArrayList<Integer> liste = new ArrayList<>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        f1 = new TableauDynamique("667",0,4,3,liste);
        grille[0][4][1] = f1;
        f1.setAbscisse(0);
        f1.setOrdonnee(4);
        f1.setCaseJ(1);
    }

    public void setNiveau3GUI(){
        
    }

    public boolean verifierVictoire() {
        switch (niveau) {
            case(1) : 
                    if (f1 != null && f2 != null) {
                        // Vérifier si les fichiers sont à la position [0][2]
                        return estAEmplacementAttendu(f1, 0, 2) && estAEmplacementAttendu(f2, 0, 2);
                    }
                    return false; 
            case(2) : 
                    for (int k = 0; k < 4; k++){
                        if (grille[0][0][k] instanceof Fichier) {
                            Fichier f = (Fichier)grille[0][0][k];
                            return f.meme_elements(f1);
                        } 
                    }
                    return false; 
            case(3) : 
                return false;
            default : return false;
        } 
        
    }

    private boolean estAEmplacementAttendu(Fichier fichier, int abscisseAttendue, int ordonneeAttendue) {
        return fichier.getAbscisse() == abscisseAttendue && fichier.getOrdonnee() == ordonneeAttendue ;
    }

    public boolean verifierDefaite() {
        switch (niveau) {
            case(1) : 
                return !(robot1.getVivant() && robot2.getVivant());
            case(2) : 
                return !(robot1.getVivant() && robot2.getVivant());
            case(3) : 
                return !(robot1.getVivant() && robot2.getVivant());
            default : return false;
        }
    }

    public void message_victoire(){
        switch (niveau) {  
            case 1 :
                System.out.println("Victoire ! Vous avez déplacé les fichiers à la position attendue.");
                break;
            case 2 :
                System.out.println("Victoire ! Vous avez copier les elements dans un fichier a la position attendue.");
                break;
            case 3 :

                break;
        }
    }

    public ArrayList<Instruction> parseTextFromInput(int r) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer le texte pour le robot "+r+",(tapez 'FIN' pour terminer) :");

        // Lire le texte jusqu'à ce que l'utilisateur entre "FIN"
        while (true) {
            String inputLine = scanner.nextLine();

            if (inputLine.equals("FIN")) {
                break; // Sortir de la boucle si l'utilisateur entre "FIN"
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

    public void jouer() {
        afficherJeu();
        ArrayList<Instruction> instruR1 = parseTextFromInput(1);
        ArrayList<Instruction> instruR2 = parseTextFromInput(2);
    
        int i = 0, j = 0;
        int[] tab1 = { i };
        int[] tab2 = { j };
        
        while (tab1[0] < instruR1.size() && tab2[0] < instruR2.size()) {
            double choix = random.nextDouble();
            if (choix < 0.5) {
                if (robot1.getVivant()) {
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1 ,M,robot2);
                }
                if (robot2.getVivant()) {
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2,M,robot1);
                }
            } else {
                if (robot2.getVivant()) {
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2,M,robot1);
                }
                if (robot1.getVivant()) {
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1,M,robot2);
                }
            }
            afficherJeu();
            if (verifierDefaite()) {
                System.out.println("Vous avez perdu !");
                return;
            } else {
                if (robot1.isMReady() ) tab1[0]++;
                if (robot2.isMReady()) tab2[0]++;
            }
        }
    
        int k = 0;
        int[] tab = { k };
        ArrayList<Instruction> instru;
        Robot robot, autreRobot;
    
        if (tab1[0] == instruR1.size() && tab2[0] == instruR2.size()) {
            if (verifierVictoire()) {
                message_victoire();
                return;
            }
            System.out.println("Vous avez perdu !");
            return;
        }
        if (tab1[0] == instruR1.size() && tab2[0] < instruR2.size()) {
            tab[0] = tab2[0];
            instru = instruR2;
            robot = robot2;
            autreRobot = robot1; 
        } else {
            tab[0] = tab1[0];
            instru = instruR1;
            robot = robot1;
            autreRobot = robot2;
        }
    
        while (tab[0] < instru.size() && robot.getVivant()) {
            instru.get(tab[0]).execute(grille, robot, instru, tab,M,autreRobot);
            afficherJeu();
            if (verifierDefaite()) {
                System.out.println("Vous avez perdu !");
                return;
            }
            tab[0]++;
        }
    
        // Vérification de la victoire à la fin du niveau 1
        if (verifierVictoire()) {
            message_victoire();
            return;
        }
        System.out.println("Vous avez perdu !");
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

    public void jouerGUIAuto() {
        ArrayList<Instruction> instruR1 = parseTextFromInputGUI(OctopunksGUI.memoryArea1.getText());
        ArrayList<Instruction> instruR2 = parseTextFromInputGUI(OctopunksGUI.memoryArea2.getText());

        int i = 0, j = 0;
        int[] tab1 = { i };
        int[] tab2 = { j };

        while (tab1[0] < instruR1.size() && tab2[0] < instruR2.size()) {
            double choix = random.nextDouble();
            if (choix < 0.5) {
                if (robot1.getVivant()) {
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1 ,M ,robot2);
                }
                if (robot2.getVivant()) {
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2,M,robot1);
                }
            } else {
                if (robot2.getVivant()) {
                    instruR2.get(tab2[0]).execute(grille, robot2, instruR2, tab2,M,robot1);
                }
                if (robot1.getVivant()) {
                    instruR1.get(tab1[0]).execute(grille, robot1, instruR1, tab1,M,robot2);
                }
            }
            afficherJeu();
            if (verifierDefaite()) {
                L=1;
                return;
            } else {
                if (robot1.isMReady() ) tab1[0]++;
                if (robot2.isMReady()) tab2[0]++;
            }
        }
    
        int k = 0;
        int[] tab = { k };
        ArrayList<Instruction> instru;
        Robot robot,autreRobot;
    
        if (tab1[0] == instruR1.size() && tab2[0] == instruR2.size()) {
            if (verifierVictoire()) {
                W=1;
                return;
            }
            L=0;
            return;
        }
        if (tab1[0] == instruR1.size() && tab2[0] < instruR2.size()) {
            tab[0] = tab2[0];
            instru = instruR2;
            robot = robot2;
            autreRobot = robot1;
        } else {
            tab[0] = tab1[0];
            instru = instruR1;
            robot = robot1;
            autreRobot = robot2; 
        }
    
        while (tab[0] < instru.size() && robot.getVivant()) {
            instru.get(tab[0]).execute(grille, robot, instru, tab,M,autreRobot);
            afficherJeu();
            if (verifierDefaite()) {
               L=1;
               return;
            }
            tab[0]++;
        }
    
        // Vérification de la victoire à la fin du niveau 1
        if (verifierVictoire()) {
            W=1;
            return;
        }
        L=1;
    }

    public void jouerGUI() {
        // Récupérer les instructions à partir des zones de texte
        ArrayList<Instruction> instruR1 = parseTextFromInputGUI(OctopunksGUI.memoryArea1.getText());
        ArrayList<Instruction> instruR2 = parseTextFromInputGUI(OctopunksGUI.memoryArea2.getText());

        // Récupérer la prochaine instruction à exécuter pour chaque robot
        Instruction nextInstruR1 = getNextInstruction(instruR1, t1[0]);
        Instruction nextInstruR2 = getNextInstruction(instruR2, t2[0]);
        
        double choix = random.nextDouble();
       
        if (choix < 0.5) {
            if (robot1.getVivant() && nextInstruR1 != null) {
                nextInstruR1.execute(grille, robot1, instruR1, t1,M,robot2);
                if(robot1.isMReady()) t1[0]++; // Avancer la position pour le robot 1
            }
            if (robot2.getVivant() && nextInstruR2 != null) {
                nextInstruR2.execute(grille, robot2, instruR2, t2,M,robot1);
                if(robot2.isMReady()) t2[0]++; // Avancer la position pour le robot 2
            }
        } else {
            if (robot2.getVivant() && nextInstruR2 != null) {
                nextInstruR2.execute(grille, robot2, instruR2, t2,M,robot1);
                if(robot2.isMReady()) t2[0]++; // Avancer la position pour le robot 2
            }
            if (robot1.getVivant() && nextInstruR1 != null) {
                nextInstruR1.execute(grille, robot1, instruR1, t1,M,robot2);
                if(robot1.isMReady()) t1[0]++; // Avancer la position pour le robot 1
            }
        }
    
        // Mettre à jour l'affichage du jeu
        afficherJeuGUI();
    
        // Vérifier la victoire après le mouvement
        if (verifierDefaite()) {
            L = 1;
        }

        if (nextInstruR1==null && nextInstruR2==null) {
            if (verifierVictoire()) W=1;
            else L=1;
        }
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

    /*// Méthode pour vérifier si le joueur a gagné et afficher le pop-up correspondant
    public void verifierEtAfficherResultat() {
        boolean victoire = verifierVictoire();
        if (victoire) {
            JOptionPane.showMessageDialog(null, "Félicitations, vous avez gagné !");
        } else {
            JOptionPane.showMessageDialog(null, "Désolé, vous avez perdu.");
        }
    }*/
}
    
