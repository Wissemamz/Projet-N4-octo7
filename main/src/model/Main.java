package model;

public class Main {
    public static void main(String args[]) {
        // SwingUtilities.invokeLater(new Runnable() {

        // OctopunksGUI octo = new OctopunksGUI();
        // }
        // });
        Jeu leJeu = new Jeu();
        leJeu.afficherMenuNiveaux();

        while (true) {
            if (leJeu.verifierVictoire()) {
                System.out.println("Félicitations ! Vous avez gagné !");
                break; // Sortir de la boucle après la victoire
            } else {
                System.out.println("Désolé, vous n'avez pas réussi.");

                if (leJeu.verifierDefaiteniveau1()) {
                    System.out.println("Vous avez perdu.");
                    break; // Sortir de la boucle après la défaite
                }
            }
        }

        System.out.println("Merci d'avoir joué. Au revoir !");
    }
}
