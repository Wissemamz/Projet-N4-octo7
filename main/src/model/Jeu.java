package model;

import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;


public class Jeu {
    static ObjetJ [][][] grille = new ObjetJ[5][5][3];
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
    

    
     public ArrayList<Instruction> parseTextFromInput() {
        ArrayList<Instruction> instructionsList = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez entrer le texte (tapez 'fin' pour terminer) :");

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

        scanner.close(); // Fermer le scanner

        return instructionsList;
    }


    public void setNiveau1() {
        for (int i=0 ;i<3; i++){
            grille[1][3][i] = new Obstacle("obstacle 01",1,3,i);
            grille[2][1][i] = new Obstacle("obstacle 02",2,1,i);
            grille[3][4][i] = new Obstacle("obstacle 03",3,4,i);
            grille[4][2][i] = new Obstacle("obstacle 04",4,1,i);        
        }
        grille[2][2][1] = new Fichier("199",2,2,1);
    }
    
    public void afficherJeu () {
            for (ObjetJ[][] ligne : grille) {
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
    }
    
    public void jouer(ArrayList<Instruction> instruR1,ArrayList<Instruction> instruR2 ){
        int taille = instruR1.size()<instruR2.size() ? instruR1.size() : instruR2.size();
        ArrayList<Instruction> instru = instruR1.size()<instruR2.size() ? instruR2 : instruR1;
        Robot robot= instruR1.size()<instruR2.size() ? robot2 : robot1;

        for (int i=0; i<taille; i++){
            double choix=random.nextDouble();
            if(choix<0.5){
                instruR1.get(i).execute(grille,robot1);
                instruR2.get(i).execute(grille,robot2);
            }
            else{
                instruR2.get(i).execute(grille,robot2);
                instruR1.get(i).execute(grille,robot1);
            }
        }
        for(int i=taille;i<instru.size();i++){
            instru.get(i).execute(grille,robot);
        }

    }
    
   
        //public void jouerNiveau1(){}
}
    
