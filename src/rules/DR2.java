package rules;
import composite.*;

public class DR2 extends DeductionRule{
    public boolean execut(CellBase c_in, Grid g) {
        if (c_in instanceof EmptyCell cell) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();

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
                    if (correct_line || correct_column || correct_square) {
                        System.out.println("Nouvelle cellule en " + x + " " + y + " avec la valeur " + value);
                        Cell c_new = new Cell(x, y, value);
                        DR0 r = new DR0();
                        r.execut(c_new, g);
                        g.set(c_new, x, y);
                        g.setCellsToFill(g.getCellsToFill() - 1);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
