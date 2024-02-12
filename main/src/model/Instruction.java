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
    
    public void execute (ObjetJ[][][] grille,Robot r){
        if (commande == "GRAB"){
            if (parametre.length==0){
                return;
            }
            else if (parametre.length==0) {
                for (int i=0; i<3; i++){
                    if (parametre[1].equals(grille[r.getAbscisse()][r.getOrdonnee()][i].getName())){
                        r.setFichier((grille[r.getAbscisse()][r.getOrdonnee()][i]));
                        grille[r.getAbscisse()][r.getOrdonnee()][i] = null;
                        return;
                    }
                }
            }
            else return; 
        }

        else if (commande == "DROP"){
            if (parametre.length == 0){
                if (r.getFichier() == null) return;
                else {
                    for (int i=0; i<3; i++){
                        if (grille[r.getAbscisse()][r.getOrdonnee()][i] == null){
                            grille[r.getAbscisse()][r.getOrdonnee()][i] = r.getFichier();
                            r.setFichier(null);
                            return;
                        }
                    }
                    return;
                }
            }
            else return;
        }

        else if (commande == "LINK"){
            if (parametre.length == 1){
                switch (parametre[1]){
                    case "0" :
                        if (r.getAbscisse()>=4 || r.getAbscisse()<0){
                            r.meurt();
                        }
                        else {
                            r.setAbscisse((r.getAbscisse())+1);
                        }
                        break;
                    case "1" :
                        if (r.getOrdonnee()>4 || r.getOrdonnee()<=0){
                            r.meurt();
                        }
                        else {
                            r.setOrdonnee((r.getOrdonnee())-1);
                        }
                        break;
                    case "2" :
                        if (r.getAbscisse()>4 || r.getAbscisse()<=0){
                            r.meurt();
                        }
                        else {
                            r.setAbscisse((r.getAbscisse())-1);
                        }
                        break;
                    case "3" :
                        if (r.getOrdonnee()>=4 || r.getOrdonnee()<0){
                            r.meurt();
                        }
                        else {
                            r.setOrdonnee((r.getOrdonnee())+1);
                        }
                        break;
                    default :
                        return;
                }
            }
            else return;
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