
package model;

public class Main {
    static objetJ [][] grille = new objetJ[5][5];
    public static void main (String args[]) {
        objetJ robot1 = new robot("Joueur1", 4, 0);
        objetJ robot2 = new robot("Joueur2", 4, 4);
        grille[4][0] = robot1;
        grille[4][4] = robot2;
        setNiveau1();
        afficheNiveau();
        //jouerNiveau1();
    }

    public static void setNiveau1() {
        grille[1][3] = new porte("Porte 01",1,3);
        grille[2][1] = new porte("Porte 02",2,1);
        grille[3][4] = new porte("Porte 03",3,4);
        grille[4][1] = new porte("Porte 04",4,1);
    }

    public static void afficheNiveau () {
        for (objetJ[] ligne : grille) {
            for (objetJ obj : ligne) {
                if (obj == null) {
                    System.out.print("|     ");
                } else {
                    System.out.print("|");
                    obj.afficher();
                    //System.out.print(" ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
    }

    //public void jouerNiveau1(){}
}
