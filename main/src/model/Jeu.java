package model;

public class Jeu {
    static ObjetJ [][][] grille = new ObjetJ[5][5][3];
    public Jeu() {
        ObjetJ robot1 = new Robot("Robot1", 4, 0,0);
        ObjetJ robot2 = new Robot("Robot2", 4, 4,0);
        grille[4][0][0] = robot1;
        grille[4][4][0] = robot2;
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
    
    
        //public void jouerNiveau1(){}
}
    

