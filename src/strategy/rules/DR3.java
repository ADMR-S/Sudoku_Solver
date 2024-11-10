package strategy.rules;
import composite.*;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;
import log.SudokuLogger;
import strategy.Pile;

import java.util.Arrays;

public class DR3 extends DeductionRule {
    //Paires nues : deux cases avec deux mêmes valeurs possibles dans une ligne, colonne ou carré
    //https://sudoku.com/fr/regles-du-sudoku/paires-nues/

    /** Règle qui, pour une cellule donnée, si il ne reste que deux valeurs possibles, et que ces valeurs sont les mêmes que dans une autre cellule de la même ligne/colonne/carré,
     * retire ces valeurs des autres cellules de la même ligne/colonne/carré.
     *
     * @param c_in CellBase la cellule à tester
     * @param g Grid la grille de jeu
     * @return  boolean vrai si la règle est appliqué à la cellule c, faux sinon (donc si c n'est pas une cellule vide).
     */
    public boolean execut(CellBase c_in, Grid g) {
        SudokuLogger.getLogger().info("DR3 execut");
        if (c_in instanceof EmptyCell cell && cell.numberPossibleValue() == 2) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();
            boolean change = false;
            int i_v1 = -1;
            int i_v2 = -1;
            for (int j = 0; j < 9; j++) {
                if (i_v1 == -1 && table[j] != 0) {
                    i_v1 = j;
                } else if (table[j] != 0) {
                    i_v2 = j;
                }
            }
            // Pour toutes les valeurs des cellules de la même ligne, colonne ou carré on vérifie si elles sont égales à celles de la cellule.
            // Puis si elles sont égales on retire ces valeurs des autres cellules.
            // Le code est similaire mais légèrement différent donc difficilement factorisable en une fonction.
            for (int i = 0; i < 9; i++) {
                if (l.get(i) instanceof EmptyCell elt) {
                    if (elt.getXpos() != x) {
                        if (Arrays.equals(elt.getPossibleValues(), table)) {
                            for (int j = 0; j < 9; j++) {
                                if (l.get(j) instanceof EmptyCell elt2) {
                                    if (elt2.getXpos() != x && elt2.getXpos() != elt.getXpos()) {
                                        if (elt2.checkPossibleValue(i_v1 + 1) || elt2.checkPossibleValue(i_v1 + 1)) {
                                            change = true;
                                        }
                                        elt2.removePossibleValue(table[i_v1]);
                                        elt2.removePossibleValue(table[i_v2]);
                                    }
                                }
                            }
                        }
                    }
                }
                if (c.get(i) instanceof EmptyCell elt) {
                    if (elt.getYpos() != y) {
                        if (Arrays.equals(elt.getPossibleValues(), table)) {
                            for (int j = 0; j < 9; j++) {
                                if (c.get(j) instanceof EmptyCell elt2) {
                                    if (elt2.getYpos() != y && elt2.getYpos() != elt.getYpos()) {
                                        if (elt2.checkPossibleValue(i_v1 + 1) || elt2.checkPossibleValue(i_v2 + 1)) {
                                            change = true;
                                        }
                                        elt2.removePossibleValue(table[i_v1]);
                                        elt2.removePossibleValue(table[i_v2]);
                                    }
                                }
                            }
                        }
                    }
                }
                if (s.get(i) instanceof EmptyCell elt) {
                    if (elt.getXpos() != x || elt.getYpos() != y) {
                        if (Arrays.equals(elt.getPossibleValues(), table)) {
                            for (int j = 0; j < 9; j++) {
                                if (s.get(j) instanceof EmptyCell elt2) {
                                    if ((elt2.getXpos() != x || elt2.getYpos() != y) && (elt2.getXpos() != elt.getXpos() || elt2.getYpos() != elt.getYpos())) {
                                        if (elt2.checkPossibleValue(i_v1 + 1) || elt2.checkPossibleValue(i_v2 + 1)) {
                                            change = true;
                                        }
                                        elt2.removePossibleValue(table[i_v1]);
                                        elt2.removePossibleValue(table[i_v2]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return change;
        }
        return false;
    }

    /**
     * Applique la règle DR3 en plus de DR1 et DR2 à la pile p et à la grille g dans le cadre d'une strategie
     * Et empile les cellules vides de la même ligne/colonne/carré quand elle est appliqué.
     *
     * @param pile Pile la pile de cellules à tester
     * @param grid Grid la grille de jeu
     */
    public void routine(Pile pile, Grid grid) { //Repeat DR3, DR1 and DR2 and try to solve without the help of user

        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (this.execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR3.");
                pile.pushAllRelated(cell, grid);
            } else if (dr1.execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR1.");
                pile.pushAllRelated(cell, grid);
            } else if (dr2.execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR2.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}