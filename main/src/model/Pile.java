package model;
import java.util.Stack;

public abstract class Pile extends ObjetJ {
    // Les membres existants restent inchangés
    private Stack<Integer> pile;

    public Pile(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
    }

    public void makelifo() {          // Méthode pour créer le fichier de type spécifique
        pile = new Stack<>();
    }

    public void seek() {               // Méthode pour la commande SEEK spécifique à chaque type
       // La commande SEEK ne fait rien dans ce cas
    }

    public int read() {                  // Méthode pour lire la dernière case du fichier
        return pile.pop();
    }

    public void write(int i) {      // Méthode pour écrire en empilant

        pile.push(i);
    }

    public void voidF() {               // Méthode pour effacer la dernière case

        pile.pop();
    }

    public boolean testEOF() {             // Méthode pour tester si le fichier est vide

        return pile.isEmpty();
    }

}
