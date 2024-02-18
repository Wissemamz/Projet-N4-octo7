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

    public String getParametreRang(int i) {
        return parametre[i];
    }

    public void execute(ObjetJ[][][] grille, Robot r, ArrayList<Instruction> instructions, int[] pos) {
        if (commande.equals(Commande.GRAB.toString())) {
            if (parametre.length == 0) {
                return;
            } else if (parametre.length == 1) {
                for (int i = 0; i < 4; i++) {
                    if (grille[r.getAbscisse()][r.getOrdonnee()][i] != null) {
                        if (parametre[0].equals(grille[r.getAbscisse()][r.getOrdonnee()][i].getName())) {
                            r.setFichier((grille[r.getAbscisse()][r.getOrdonnee()][i]));
                            grille[r.getAbscisse()][r.getOrdonnee()][i] = null;
                            return;
                        }
                    }
                }
            } else
                return;
        }

        else if (commande.equals(Commande.DROP.toString())) {
            if (parametre.length == 0) {
                if (r.getFichier() == null)
                    return;
                else {
                    for (int i = 0; i < 4; i++) {
                        if (grille[r.getAbscisse()][r.getOrdonnee()][i] == null) {
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
            } else
                return;
        }

        else if (commande.equals(Commande.LINK.toString())) {
            if (parametre.length == 1) {
                switch (parametre[0]) {
                    case "0":
                        if (r.getAbscisse() >= 4 || r.getAbscisse() < 0) {
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                        } else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                            for (int i = 0; i < 4; i++) {
                                if (grille[r.getAbscisse() + 1][r.getOrdonnee()][i] != null
                                        && grille[r.getAbscisse() + 1][r.getOrdonnee()][i].getName()
                                                .equals("obstacle")) {
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse() + 1][r.getOrdonnee()][i] == null) {
                                    r.setAbscisse((r.getAbscisse()) + 1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "1":
                        if (r.getOrdonnee() > 4 || r.getOrdonnee() <= 0) {
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                        } else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                            for (int i = 0; i < 4; i++) {
                                if (grille[r.getAbscisse()][r.getOrdonnee() - 1][i] != null
                                        && grille[r.getAbscisse()][r.getOrdonnee() - 1][i].getName()
                                                .equals("obstacle")) {
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()][r.getOrdonnee() - 1][i] == null) {
                                    r.setOrdonnee((r.getOrdonnee()) - 1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "2":
                        if (r.getAbscisse() > 4 || r.getAbscisse() <= 0) {
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                        } else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                            for (int i = 0; i < 4; i++) {
                                if (grille[r.getAbscisse() - 1][r.getOrdonnee()][i] != null
                                        && grille[r.getAbscisse() - 1][r.getOrdonnee()][i].getName()
                                                .equals("obstacle")) {
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse() - 1][r.getOrdonnee()][i] == null) {
                                    r.setAbscisse((r.getAbscisse()) - 1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    case "3":
                        if (r.getOrdonnee() >= 4 || r.getOrdonnee() < 0) {
                            r.meurt();
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                        } else {
                            grille[r.getAbscisse()][r.getOrdonnee()][r.getCaseJ()] = null;
                            for (int i = 0; i < 4; i++) {
                                if (grille[r.getAbscisse()][r.getOrdonnee() + 1][i] != null
                                        && grille[r.getAbscisse()][r.getOrdonnee() + 1][i].getName()
                                                .equals("obstacle")) {
                                    r.meurt();
                                    return;
                                }
                                if (grille[r.getAbscisse()][r.getOrdonnee() + 1][i] == null) {
                                    r.setOrdonnee((r.getOrdonnee()) + 1);
                                    r.setCaseJ(i);
                                    grille[r.getAbscisse()][r.getOrdonnee()][i] = r;
                                    return;
                                }
                            }
                            r.meurt();
                        }
                        break;
                    default:
                        return;
                }
            } else
                return;
        }

        else if (commande.equals(Commande.COPY.toString())) {
            if (parametre.length != 2)
                return;
            else {
                if (parametre[0].equals("X") && parametre[1].equals("T")) {
                    r.getT().setValeur(r.getX().getValeur());
                    r.setLastRegistre(r.getT());
                } else if (parametre[0].equals("X") && parametre[1].equals("X")) {
                    r.getX().setValeur(r.getX().getValeur());
                    r.setLastRegistre(r.getX());
                } else if (parametre[0].equals("T") && parametre[1].equals("X")) {
                    r.getX().setValeur(r.getT().getValeur());
                    r.setLastRegistre(r.getX());
                } else if (parametre[0].equals("T") && parametre[1].equals("T")) {
                    r.getT().setValeur(r.getT().getValeur());
                    r.setLastRegistre(r.getT());
                } else if (parametre[0].matches("[+-]?\\d*(\\.\\d+)?") && parametre[1].equals("X")) {
                    int val = Integer.parseInt(parametre[0]);
                    r.getX().setValeur(val);
                    r.setLastRegistre(r.getX());
                } else if (parametre[0].matches("[+-]?\\d*(\\.\\d+)?") && parametre[1].equals("T")) {
                    int val = Integer.parseInt(parametre[0]);
                    r.getT().setValeur(val);
                    r.setLastRegistre(r.getT());
                } else if (parametre[0].equals("X") && parametre[1].equals("F")) {
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    }
                } else if (parametre[0].equals("F") && parametre[1].equals("X")) {
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    }
                } else if (parametre[0].equals("T") && parametre[1].equals("F")) {
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    }
                } else if (parametre[0].equals("F") && parametre[1].equals("T")) {
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    }
                } else if (parametre[0].equals("F") && parametre[1].equals("F")) {
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(r.getFichier().F());
                        r.getFichier().F(r.getF().getValeur());
                    }
                } else if (parametre[0].matches("[+-]?\\d*(\\.\\d+)?") && parametre[1].equals("F")) {
                    int val = Integer.parseInt(parametre[0]);
                    if (r.getFichier() == null)
                        return;
                    else {
                        r.getF().setValeur(val);
                        r.getFichier().F(r.getF().getValeur());
                    }
                } else
                    return;
            }
        }

        else if (commande.equals(Commande.ADDI.toString())) {
            if (parametre.length != 3)
                return;
            else {
                if (parametre[0].equals("X")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getX().setValeur(r.getX().getValeur() + r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getX().setValeur(r.getX().getValeur() + r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getX().setValeur(r.getT().getValeur() + r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2] == "X") {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getX().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getT().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    } else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getT().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() + val);
                        r.setLastRegistre(r.getX());
                    }
                } else if (parametre[0].equals("T")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getT().setValeur(r.getX().getValeur() + r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getT().setValeur(r.getX().getValeur() + r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getT().setValeur(r.getT().getValeur() + r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getX().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getT().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    } else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getT().getValeur() + r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() + val);
                        r.setLastRegistre(r.getT());
                    }
                } else if (parametre[0].equals("F")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getX().getValeur() + r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getX().getValeur() + r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getT().getValeur() + r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() + r.getFichier().F());
                        // r.getF().setValeur(r.getFichier().F());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getX().getValeur() + val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val + r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(val + r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val + r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getX().getValeur() + r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getT().getValeur() + r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() + val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() + val);
                        r.getFichier().F(r.getF().getValeur());
                    }
                }
            }
        } else if (commande.equals(Commande.MULI.toString())) {
            if (parametre.length != 3)
                return;
            else {
                if (parametre[0].equals("X")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getX().setValeur(r.getX().getValeur() * r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getX().setValeur(r.getX().getValeur() * r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getX().setValeur(r.getT().getValeur() * r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getX().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(r.getT().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    }

                    else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getT().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() * val);
                        r.setLastRegistre(r.getX());
                    }
                } else if (parametre[0].equals("T")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getT().setValeur(r.getX().getValeur() * r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getT().setValeur(r.getX().getValeur() * r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getT().setValeur(r.getT().getValeur() * r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getX().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(r.getT().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    } else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getX().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getT().getValeur() * r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() * val);
                        r.setLastRegistre(r.getT());
                    }
                } else if (parametre[0].equals("F")) {
                    if ((parametre[1].equals("X") && parametre[2].equals("T"))
                            || (parametre[1].equals("T") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getX().getValeur() * r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getX().getValeur() * r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getT().getValeur() * r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() * r.getFichier().F());
                        // r.getF().setValeur(r.getFichier().F());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getX().getValeur() * val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val * r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(val * r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val * r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if ((parametre[1].equals("X") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("X"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getX().getValeur() * r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if ((parametre[1].equals("T") && parametre[2].equals("F"))
                            || (parametre[1].equals("F") && parametre[2].equals("T"))) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getT().getValeur() * r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() * val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() * val);
                        r.getFichier().F(r.getF().getValeur());
                    }
                }
            }
        } else if (commande.equals(Commande.SUBI.toString())) {
            if (parametre.length != 3)
                return;
            else {
                if (parametre[0].equals("X")) {
                    if (parametre[1].equals("X") && parametre[2].equals("T")) {
                        r.getX().setValeur(r.getX().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getX().setValeur(r.getX().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getX().setValeur(r.getT().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].equals("X")) {
                        r.getX().setValeur(r.getT().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getX().getValeur() - val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(val - r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getX().setValeur(r.getT().getValeur() - val);
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getX().setValeur(val - r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("X") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("T") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getT().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(val - r.getF().getValeur());
                        r.setLastRegistre(r.getX());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur() - val);
                        r.setLastRegistre(r.getX());
                    }
                } else if (parametre[0].equals("T")) {
                    if (parametre[1].equals("X") && parametre[2].equals("T")) {
                        r.getT().setValeur(r.getX().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getT().setValeur(r.getX().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getT().setValeur(r.getT().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getF().getValeur());
                        r.getF().setValeur(r.getFichier().F());
                        r.getX().setValeur(r.getX().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].equals("X")) {
                        r.getT().setValeur(r.getT().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getX().getValeur() - val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(val - r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getT().setValeur(r.getT().getValeur() - val);
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getT().setValeur(val - r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("X") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getX().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() - r.getX().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("T") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getT().getValeur() - r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() - r.getT().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(val - r.getF().getValeur());
                        r.setLastRegistre(r.getT());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getT().setValeur(r.getF().getValeur() - val);
                        r.setLastRegistre(r.getT());
                    }
                } else if (parametre[0].equals("F")) {
                    if (parametre[1].equals("X") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getX().getValeur() - r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getT().getValeur() - r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getX().getValeur() - r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getT().getValeur() - r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() - r.getFichier().F());
                        // r.getF().setValeur(r.getFichier().F());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getX().getValeur() - val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("X")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val - r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getT().getValeur() - val);
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("T")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(val - r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("X") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getX().getValeur() - r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].equals("X")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() - r.getX().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("T") && parametre[2].equals("F")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getT().getValeur() - r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].equals("T")) {
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() - r.getT().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].matches("[+-]?\\d*(\\.\\d+)?") && parametre[2].equals("F")) {
                        int val = Integer.parseInt(parametre[1]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(val - r.getF().getValeur());
                        r.getFichier().F(r.getF().getValeur());
                    } else if (parametre[1].equals("F") && parametre[2].matches("[+-]?\\d*(\\.\\d+)?")) {
                        int val = Integer.parseInt(parametre[2]);
                        r.getF().setValeur(r.getFichier().F());
                        r.getF().setValeur(r.getF().getValeur() - val);
                        r.getFichier().F(r.getF().getValeur());
                    }
                }
            }
        } else if (commande.equals(Commande.JUMP.toString())) {
            if (parametre.length != 1)
                return;
            else {
                int val = Integer.parseInt(parametre[0]);
                if ((pos[0] + val < 0) || (pos[0] + val > instructions.size()))
                    return;
                else {
                    pos[0] = pos[0] + val - 1;
                }
            }
        } else if (commande.equals(Commande.FJMP.toString())) {
            if (parametre.length != 1)
                return;
            else {
                int val = Integer.parseInt(parametre[0]);
                if (r.getLastRegistre().getValeur() != 0)
                    return;
                else {
                    if ((pos[0] + val < 0) || (pos[0] + val > instructions.size()))
                        return;
                    else {
                        pos[0] = pos[0] + val - 1;
                    }
                }
            }
        } else if (commande.equals(Commande.MAKE.toString())) {
            if (parametre.length != 1)
                return;
            else {
                r.setFichier(new TableauDynamique(parametre[0], r.getAbscisse(), r.getOrdonnee(), r.getCaseJ()));
            }
        } else if (commande.equals(Commande.MAKELIFO.toString())) {
            if (parametre.length != 1)
                return;
            else {
                r.setFichier(new Pile(parametre[0], r.getAbscisse(), r.getOrdonnee(), r.getCaseJ()));
            }
        } else if (commande.equals(Commande.MAKEFIFO.toString())) {
            if (parametre.length != 1)
                return;
            else {
                r.setFichier(new File(parametre[0], r.getAbscisse(), r.getOrdonnee(), r.getCaseJ()));
            }
        } else if (commande.equals(Commande.TEST_EOF.toString())) {
            // mettre le registre T à 1 si le fichier est a sa fin, sinon 0
            if (parametre.length != 0)
                return;
            else {
                if (r.getFichier() == null)
                    return;
                else if (r.getFichier().TEST_EOF())
                    r.getT().setValeur(1);
                else
                    r.getT().setValeur(0);
            }
        } else if (commande.equals(Commande.SEEK.toString())) {
            // mettre le registre T à 1 si le fichier est a sa fin, sinon 0
            if (parametre.length != 0)
                return;
            else {
                if (r.getFichier() == null)
                    return;
                else if (r.getFichier().TEST_EOF())
                    r.getT().setValeur(1);
                else
                    r.getT().setValeur(0);
            }
        } else if (commande.equals(Commande.VOID_F.toString())) {
            // mettre le registre T à 1 si le fichier est a sa fin, sinon 0
            if (parametre.length != 0)
                return;
            else if (r.getFichier() == null)
                return;
            else
                r.getFichier().VOID_F();
        }
    }
}