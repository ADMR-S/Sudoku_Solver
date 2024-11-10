package strategy.rules;
import composite.*;
import composite.cell.Cell;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import log.SudokuLogger;
import strategy.Pile;

public class DR1 extends DeductionRule {
    // SINGLETONS NUS : Seule valeur possible restante dans la cellule
    // https://sudoku.com/fr/regles-du-sudoku/singletons-nus/

    /** Règle qui, pour une cellule donnée, si il ne reste qu'une valeur possible, la remplit et appel DR0.
     *
     * @param c CellBase la cellule à tester
     * @param g Grid la grille de jeu
     * @return  boolean vrai si la règle est appliqué à la cellule c, faux sinon (donc si c n'est pas une cellule vide).
     */
    public boolean execut(CellBase c, Grid g){
        SudokuLogger.getLogger().info("DR1 execut");
        if (c instanceof EmptyCell cell) {
            int[] p = cell.getPossibleValues();
            int value = 0;
            // On vérifie qu'il ne reste qu'une valeur et on la récupère.
            for (int i=0; i < p.length ; i++) {
                if (p[i] != 0 && value == 0) {
                    value = p[i];
                } else if (p[i] != 0) {
                    return false;
                }
            }
            if (value != 0) {
                //System.out.println("Nouvelle cellule en " + cell.getXpos() + " " + cell.getYpos() + " avec la valeur " + value);
                Cell c_new = new Cell(cell.getXpos(), cell.getYpos(), value);
                g.set(c_new, cell.getXpos(), cell.getYpos());
                g.setCellsToFill(g.getCellsToFill() - 1);
                DR0 r = new DR0();
                SudokuLogger.getLogger().info("DR0 dans DR1");
                r.execut(c_new, g);
                return true;
            }
        }
        return false;
    }

    /**
     * Applique la règle DR1 à la pile p et à la grille g dans le cadre d'une strategie
     * Et empile les cellules vides de la même ligne/colonne/carré quand elle est appliqué.
     *
     * @param pile Pile la pile de cellules à tester
     * @param grid Grid la grille de jeu
     */
    public void routine(Pile pile, Grid grid) {//Repeat DR1 and try to solve without the help of user

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (execut(cell, grid)) {
                SudokuLogger.getLogger().info("DR1.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}
