package model;

public class Instruction {
    private String commande;
    private String[] parametre;

    public Instruction(String commande) {
        if (commande == null) {
            throw new NullPointerException();
        }
        this.commande = commande;
        this.parametre = null;
    }

    public Instruction(String commande, String[] parametre) {
        if (commande == null || parametre == null) {
            throw new NullPointerException();
        }
        this.commande = commande;
        this.parametre = parametre;
    }

    public String getCommande() {
        return commande;
    }

    public String[] getParametre() {
        return parametre;
    }

    public String getParametreRang(int i){
        return parametre[i];
    }
    
    public void execute (ObjetJ[][][] grille, Robot r){
        if (commande == "GRAB"){
            if (parametre.length==0){
                return;
            }
            for (int i=0; i<3; i++){
                if (parametre[1].equals(grille[r.getAbscisse()][r.getOrdonnee()][i].getName())){
                    r.setFichier((grille[r.getAbscisse()][r.getOrdonnee()][i]));
                    grille[r.getAbscisse()][r.getOrdonnee()][i] = null;
                }
            }
        }
        else if (commande == "DROP"){

        }
        else if (commande == "LINK"){

        }
        else if (commande == "ADDI"){

        }
        else if (commande == "SUBI"){

        }
        else if (commande == "MULI"){

        }
        else if (commande == "COPY"){

        }
        else if (commande == "JUMP"){

        }
        else if (commande == "FJMP"){

        }
    }
}