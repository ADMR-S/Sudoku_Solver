package strategy.rules;
import composite.*;
import composite.cell.Cell;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;
import log.SudokuLogger;
import strategy.Pile;

public class DR2 extends DeductionRule{
    //Singletons cachés : seule valeur possible pour cette valeur dans une ligne, colonne ou carré
    //https://sudoku.com/fr/regles-du-sudoku/singletons-caches/

    /** Règle qui, pour une cellule donnée, si une valeur possible n'est pas possible ailleurs dans la ligne/colonne/carré, la remplit.
     *
     * @param c_in CellBase la cellule à tester
     * @param g Grid la grille de jeu
     * @return  boolean vrai si la règle est appliqué à la cellule c, faux sinon (donc si c n'est pas une cellule vide).
     */
    public boolean execut(CellBase c_in, Grid g) {
        SudokuLogger.getLogger().info("DR2 execut");
        if (c_in instanceof EmptyCell cell) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();

            //Pour toutes les valeurs de la cellule on vérifie si elle apparait autre part dans la ligne, colonne ou carré.
            for (int value : table) {
                if (value != 0) {
                    boolean correct_line = true;
                    boolean correct_column = true;
                    boolean correct_square = true;
                    for (int i = 0 ;  i < 9 ; i++) {
                        if (l.get(i) instanceof EmptyCell emp_c){
                            if (i != x && emp_c.checkPossibleValue(value)) {
                                correct_line = false;
                            }
                        }
                        if (c.get(i) instanceof EmptyCell emp_c) {
                            if (i != y && emp_c.checkPossibleValue(value)) {
                                correct_column = false;
                            }
                        }
                        if (s.get(i) instanceof EmptyCell emp_c) {
                            if ((emp_c.getXpos() != x || emp_c.getYpos() != y) && emp_c.checkPossibleValue(value)) {
                                correct_square = false;
                            }
                        }
                    }
                    // Si c'est la seul valeur possible dans la ligne/colonne/carré, on la set.
                    if (correct_line || correct_column || correct_square) {
                        //System.out.println("Nouvelle cellule en " + x + " " + y + " avec la valeur " + value);
                        Cell c_new = new Cell(x, y, value);
                        g.set(c_new, x, y);
                        g.setCellsToFill(g.getCellsToFill() - 1);
                        DR0 r = new DR0();
                        SudokuLogger.getLogger().info("DR0 dans DR2 execut");
                        r.execut(c_new, g);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Applique la règle DR2 en plus de DR1 à la pile p et à la grille g dans le cadre d'une strategie
     * Et empile les cellules vides de la même ligne/colonne/carré quand elle est appliqué.
     *
     * @param pile Pile la pile de cellules à tester
     * @param grid Grid la grille de jeu
     */
    public void routine(Pile pile, Grid grid) {//Repeat DR1 and DR2 and try to solve without the help of user

        DR1 dr1 = new DR1();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (dr1.execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR1.");
                pile.pushAllRelated(cell, grid);
            } else if (execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR2.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}
