package model;

import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;


public class Jeu {
    static ObjetJ [][][] grille = new ObjetJ[5][5][4];
    private Random random;
    private Robot robot1;
    private Robot robot2;

    public Jeu() {
        this.robot1= new Robot("Robot1", 4, 0,0);
        this.robot2= new Robot("Robot2", 4, 4,0);
        grille[4][0][0] = robot1;
        grille[4][4][0] = robot2;
        this.random=new Random();
    }
    

    
     public ArrayList<Instruction> parseTextFromInput(int r) {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        
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
        for (int i=0 ;i<4; i++){
            grille[1][3][i] = new Obstacle("obstacle",1,3,i);
            grille[2][1][i] = new Obstacle("obstacle",2,1,i);
            grille[3][4][i] = new Obstacle("obstacle",3,4,i);
            grille[4][2][i] = new Obstacle("obstacle",4,1,i);        
        }
        grille[2][2][1] = new Fichier("199",2,2,1);
        grille[2][3][2] = new Fichier("299",2,3,2);
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
    
    public void jouer(){
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
    }
}
    
