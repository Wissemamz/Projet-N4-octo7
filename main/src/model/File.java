package model;

import java.util.LinkedList;
import java.util.Queue;

public class File extends Fichier {

    private Queue<Integer> file;

    public File(String num, int abscisse, int ordonnee, int caseJ) {
        super(num, abscisse, ordonnee, caseJ);
    }

    
    public void makefifo() {
        file = new LinkedList<>();
    }

    public void seek() {
        // La commande SEEK ne fait rien dans ce cas
    }

    public int read() {
        return file.poll();
    }

    public void write(int i) {
        file.offer(i);
    }

    public void voidF() {
        // La commande VOID F efface la dernière case (opération "pop")
        // Dans le cas d'une file, cela peut être géré en ne faisant rien.
    }

    public boolean testEOF() {
        return file.isEmpty();
    }
}
