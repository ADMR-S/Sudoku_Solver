package rules;
import composite.*;
import java.lang.reflect.Array;

public class DR3 extends DeductionRule{
    @Override
    public void execut(CellBase c_in, Grid g) {
        if (c_in instanceof EmptyCell cell) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();
            if (cell.numberPossibleValue() == 2) {
                boolean correct_line = false;
                boolean correct_column = false;
                boolean correct_square = false;
                for (int i = 0 ;  i < 9 ; i++) {
                    if (i != x && l.get(i) instanceof EmptyCell c_l) {
                        if (Arrays.equals(table, c_l.getPossibleValues())) {

                        }
                        correct_line = false;
                    }
                    if (i != y && c.get(i).checkPossibleValue(elt)) {
                        correct_column = false;
                    }
                    if (s.get(i).getXpos() != x && s.get(i).getYpos() != y && s.get(i).checkPossibleValue(elt)) {
                        correct_square = false;
                    }
                }
                if (correct_column || correct_line || correct_square) {
                    Cell c_new = new Cell(cell.getXpos(), cell.getXpos(), elt);
                    DR0 r = new DR0();
                    r.execut(c_new, g);
                    // Modifier la ligne, colonne et square associé (ça serait plus simple dans DR0)
                } 
            }
        }
    }
}
