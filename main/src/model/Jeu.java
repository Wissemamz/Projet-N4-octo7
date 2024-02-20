package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    public ObjetJ[][][] grille = new ObjetJ[5][5][4];
    private Random random;
    public Robot robot1;
    public Robot robot2;

    public Jeu() {
        this.robot1 = new Robot("Robot1", 4, 0, 0);
        this.robot2 = new Robot("Robot2", 4, 4, 0);
        grille[4][0][0] = robot1;
        grille[4][4][0] = robot2;
        this.random = new Random();
    }

    public ArrayList<Instruction> parseTextFromInput(int r) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();

        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer le texte pour le robot " + r + ",(tapez 'fin' pour terminer) :");

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

        // scanner.close();

        return instructionsList;
    }

    public void setNiveau1() {
        for (int i = 0; i < 4; i++) {
            grille[1][3][i] = new Obstacle("obstacle", 1, 3, i);
            grille[2][1][i] = new Obstacle("obstacle", 2, 1, i);
            grille[3][4][i] = new Obstacle("obstacle", 3, 4, i);
            grille[4][2][i] = new Obstacle("obstacle", 4, 1, i);
        }
        Fichier fichier = new TableauDynamique("199", 2, 2, 1);

        grille[2][2][1] = fichier;
        grille[2][3][2] = new TableauDynamique("299", 2, 3, 2);
        System.out.println("Mission : Deplacez les fichiers 199 et 299 dans la case [0][2]:");
        System.out.println();
    }

    public void afficherJeu() {
        for (ObjetJ[][] ligne : grille) {
            System.out.print("|");
            for (ObjetJ[] colonne : ligne) {
                for (ObjetJ obj : colonne) {
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

    public void jouerGUI() {
        setNiveau1();
        ArrayList<Instruction> instruR1 = parseTextFromInputGUI(OctopunksGUI.memoryArea1.getText());
        ArrayList<Instruction> instruR2 = parseTextFromInputGUI(OctopunksGUI.memoryArea2.getText());

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
            tab1[0]++;
            tab2[0]++;
            afficherJeu();
        }

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

    public void afficherMessage(String message) {
        System.out.println(message);
    }
}
