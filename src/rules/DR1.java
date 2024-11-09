package rules;
import composite.*;
import pile.Pile;

public class DR1 extends DeductionRule {
    //SINGLETONS NUS : Seule valeur possible restante dans la cellule
    //https://sudoku.com/fr/regles-du-sudoku/singletons-nus/
    public boolean execut(CellBase c, Grid g){
        if (c instanceof EmptyCell cell) {
            int[] p = cell.getPossibleValues();
            int value = 0;
            for (int i=0; i < p.length ; i++) {
                if (p[i] != 0 && value == 0) {
                    value = p[i];
                } else if (p[i] != 0) {
                    return false;
                }
            }
            if (value != 0) {
                System.out.println("Nouvelle cellule en " + cell.getXpos() + " " + cell.getYpos() + " avec la valeur " + value);
                Cell c_new = new Cell(cell.getXpos(), cell.getYpos(), value);
                DR0 r = new DR0();
                r.execut(c_new, g);
                g.set(c_new, cell.getXpos(), cell.getYpos());
                g.setCellsToFill(g.getCellsToFill() - 1);
                return true;
            }
        }
        return false;
    }

    public void routine(Pile pile, Grid grid) {

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (execut(cell, grid)) {
                System.out.println("DR1.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}
