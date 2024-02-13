package model;

import java.util.ArrayList;

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
    
    public void execute (ObjetJ[][][] grille,Robot r,ArrayList<Instruction> instructions,int[] pos){
        if (commande.equals(Commande.GRAB.toString())){
            if (parametre.length==0){
                return;
            }
            else if (parametre.length==1) {
                for (int i=0; i<4; i++){
                    if (grille[r.getAbscisse()][r.getOrdonnee()][i] != null)
                    {
                        if (parametre[0].equals(grille[r.getAbscisse()][r.getOrdonnee()][i].getName())){
                            r.setFichier((grille[r.getAbscisse()][r.getOrdonnee()][i]));
                            grille[r.getAbscisse()][r.getOrdonnee()][i] = null;
                            return;
                        }
                    }
                }
            }
            else return; 
        }

        else if (commande.equals(Commande.DROP.toString())){
            if (parametre.length == 0){
                if (r.getFichier() == null) return;
                else {
                    for (int i=0; i<4; i++){
                        if (grille[r.getAbscisse()][r.getOrdonnee()][i] == null){
                            r.getFichier().setAbscisse(r.getAbscisse());
                            r.getFichier().setOrdonnee(r.getOrdonnee());
                            r.getFichier().setCaseJ(i);
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

        else if (commande.equals(Commande.LINK.toString())){
            if (parametre.length == 1){
                switch (parametre[0]){
                    case "0" :
                        if (r.getAbscisse()>=4 || r.getAbscisse()<0){
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                        }
                        else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                            for (int i=0; i<4; i++){
                                if (grille[r.getAbscisse()+1][r.getOrdonnee()][i]!=null && grille[r.getAbscisse()+1][r.getOrdonnee()][i].getName().equals("obstacle")){
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()+1][r.getOrdonnee()][i] == null){
                                    r.setAbscisse((r.getAbscisse())+1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "1" :
                        if (r.getOrdonnee()>4 || r.getOrdonnee()<=0){
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                        }
                        else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                            for (int i=0; i<4; i++){
                                if (grille[r.getAbscisse()][r.getOrdonnee()-1][i]!=null && grille[r.getAbscisse()][r.getOrdonnee()-1][i].getName().equals("obstacle")){
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()][r.getOrdonnee()-1][i] == null){
                                    r.setOrdonnee((r.getOrdonnee())-1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "2" :
                        if (r.getAbscisse()>4 || r.getAbscisse()<=0){
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                        }
                        else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                            for (int i=0; i<4; i++){
                                if (grille[r.getAbscisse()-1][r.getOrdonnee()][i]!=null && grille[r.getAbscisse()-1][r.getOrdonnee()][i].getName().equals("obstacle")){
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()-1][r.getOrdonnee()][i] == null){
                                    r.setAbscisse((r.getAbscisse())-1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "3" :
                        if (r.getOrdonnee()>=4 || r.getOrdonnee()<0){
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                        }
                        else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()]=null;
                            for (int i=0; i<4; i++){
                                if (grille[r.getAbscisse()][r.getOrdonnee()+1][i]!=null && grille[r.getAbscisse()][r.getOrdonnee()+1][i].getName().equals("obstacle")){
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()][r.getOrdonnee()+1][i] == null){
                                    r.setOrdonnee((r.getOrdonnee())+1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    default :
                        return;
                }
            }
            else return;
        }

        else if (commande.equals(Commande.COPY.toString())){
            if (parametre.length != 2) return;
            else {
                if (parametre[0].equals("X") && parametre[1].equals("T")){
                    r.getT().setValeur(r.getX().getValeur()) ;    
                    r.setLastRegistre(r.getT());
                }
                else  if (parametre[0].equals("T") && parametre[1].equals("X")){
                    r.getX().setValeur(r.getT().getValeur()) ; 
                    r.setLastRegistre(r.getX());               
                }
                else if (parametre[0].matches("[+-]?\\d*(\\.\\d+)?") && parametre[1].equals("X")){
                    int val = Integer.parseInt(parametre[0]);
                    r.getX().setValeur(val); 
                    r.setLastRegistre(r.getX());                
                }
                else if (parametre[0].matches("[+-]?\\d*(\\.\\d+)?") && parametre[1].equals("T")){
                    int val = Integer.parseInt(parametre[0]);
                    r.getT().setValeur(val);  
                    r.setLastRegistre(r.getT());                
                }
                else return;
            }   
        }

        else if (commande.equals(Commande.ADDI.toString())){
            if (parametre.length != 3) return;
            else {
                if (parametre[0]=="X"){
                    if ((parametre[1]=="X" && parametre[2]=="T") || (parametre[1]=="T" && parametre[2]=="X")){
                        r.getX().setValeur(r.getX().getValeur()+r.getT().getValeur());
                        r.setLastRegistre(r.getX());  
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur()+val);
                        r.setLastRegistre(r.getX());  
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getX().getValeur()+val);
                        r.setLastRegistre(r.getX());  
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur()+val);
                        r.setLastRegistre(r.getX());  
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getT().getValeur()+val);
                        r.setLastRegistre(r.getX());  
                    }
                }
                else if (parametre[0]=="T"){
                    if ((parametre[1]=="X" && parametre[2]=="T") || (parametre[1]=="T" && parametre[2]=="X")){
                        r.getT().setValeur(r.getX().getValeur()+r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur()+val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getX().getValeur()+val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur()+val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getT().getValeur()+val);
                        r.setLastRegistre(r.getT());
                    }
                }
            }        
        }
        else if (commande.equals(Commande.MULI.toString())){
            if (parametre.length != 3) return;
            else {
                if (parametre[0]=="X"){
                    if ((parametre[1]=="X" && parametre[2]=="T") || (parametre[1]=="T" && parametre[2]=="X")){
                        r.getX().setValeur(r.getX().getValeur()*r.getT().getValeur());
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur()*val);
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getX().getValeur()*val);
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur()*val);
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getT().getValeur()*val);
                        r.setLastRegistre(r.getX()); 
                    }
                }
                else if (parametre[0]=="T"){
                    if ((parametre[1]=="X" && parametre[2]=="T") || (parametre[1]=="T" && parametre[2]=="X")){
                        r.getT().setValeur(r.getX().getValeur()*r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur()*val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getX().getValeur()*val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur()*val);
                        r.setLastRegistre(r.getT());
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getT().getValeur()*val);
                        r.setLastRegistre(r.getT());
                    }
                }
            }   
        }
        else if (commande.equals(Commande.SUBI.toString())){
            if (parametre.length != 3) return;
            else {
                if (parametre[0]=="X"){
                    if (parametre[1]=="X" && parametre[2]=="T"){
                        r.getX().setValeur(r.getX().getValeur()-r.getT().getValeur());
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1]=="T" && parametre[2]=="X"){
                        r.getX().setValeur(r.getT().getValeur()-r.getX().getValeur());
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur()-val);
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(val-r.getX().getValeur());
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur()-val);
                        r.setLastRegistre(r.getX()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(val-r.getT().getValeur());
                        r.setLastRegistre(r.getX()); 
                    }
                }
                else if (parametre[0]=="T"){
                    if (parametre[1]=="X" && parametre[2]=="T"){
                        r.getT().setValeur(r.getX().getValeur()-r.getT().getValeur());
                        r.setLastRegistre(r.getT()); 
                    }
                    else if (parametre[1]=="T" && parametre[2]=="X"){
                        r.getT().setValeur(r.getT().getValeur()-r.getX().getValeur());
                        r.setLastRegistre(r.getT()); 
                    }
                    else if (parametre[1]=="X" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur()-val);
                        r.setLastRegistre(r.getT()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="X"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(val-r.getX().getValeur());
                        r.setLastRegistre(r.getT()); 
                    }
                    else if (parametre[1]=="T" && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")){
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur()-val);
                        r.setLastRegistre(r.getT()); 
                    }
                    else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2]=="T"){
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(val-r.getT().getValeur());
                        r.setLastRegistre(r.getT()); 
                    }
                }
            }   
        }
        else if (commande.equals(Commande.JUMP.toString())){
            if (parametre.length != 1) return ;
            else {
                int val = Integer.parseInt(parametre[0]);
                if ((pos[0]+val<0) || (pos[0]+val>instructions.size())) return;
                else {
                    pos[0] = pos[0] + val -1;
                }
            }
        }
        else if (commande.equals(Commande.FJMP.toString())){
            if (parametre.length != 1) return ;
            else {
                int val = Integer.parseInt(parametre[0]);
                if (r.getLastRegistre().getValeur()!=0) return;
                else 
                {
                    if ((pos[0]+val<0) || (pos[0]+val>instructions.size())) return;
                    else {
                        pos[0] = pos[0] + val -1;
                    }
                }
            }
        }
    }
}