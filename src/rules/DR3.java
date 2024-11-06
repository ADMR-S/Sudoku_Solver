package rules;
import composite.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class DR3 extends DeductionRule{
    public boolean execut(CellBase c_in, Grid g) {
        if (c_in instanceof EmptyCell cell) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();
            if (cell.numberPossibleValue() == 2) {
                for (int i = 0 ;  i < 9 ; i++) {
                    if (l.get(i) instanceof EmptyCell elt) {
                        if (elt.getXpos() != x) {
                            if (Arrays.equals(elt.getPossibleValues(), table)) {
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (l.get(j) instanceof EmptyCell elt2) {
                                        if (elt2.getXpos() != x && elt2.getXpos() != elt.getXpos()) {
                                            elt2.removePossibleValue(table[0]);
                                            elt2.removePossibleValue(table[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (c.get(i) instanceof EmptyCell elt) {
                        if (elt.getYpos() != y) {
                            if (Arrays.equals(elt.getPossibleValues(), table)) {
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (c.get(j) instanceof EmptyCell elt2) {
                                        if (elt2.getYpos() != y && elt2.getYpos() != elt.getYpos()) {
                                            elt2.removePossibleValue(table[0]);
                                            elt2.removePossibleValue(table[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (s.get(i) instanceof EmptyCell elt) {
                        if (elt.getXpos() != x && elt.getYpos() != y) {
                            if (Arrays.equals(elt.getPossibleValues(), table)) {
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (s.get(j) instanceof EmptyCell elt2) {
                                        if (elt2.getXpos() != x && elt2.getYpos() != y && elt2.getXpos() != elt.getXpos() && elt2.getYpos() != elt.getYpos()) {
                                            elt2.removePossibleValue(table[0]);
                                            elt2.removePossibleValue(table[1]);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
