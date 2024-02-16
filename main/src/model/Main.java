package model;

import javax.swing.SwingUtilities;

public class Main {
    public static void main (String args[]) {
        //Jeu leJeu = new Jeu();
        //leJeu.jouer();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OctopunksGUI octo =  new OctopunksGUI();
            }
        });
    }
}   