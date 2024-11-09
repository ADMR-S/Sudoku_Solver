package rules;
import composite.*;
import pile.Pile;
import java.lang.reflect.Array;
import java.util.Arrays;

public class DR3 extends DeductionRule{
    //Paires nues : deux cases avec deux mêmes valeurs possibles dans une ligne, colonne ou carré
    //https://sudoku.com/fr/regles-du-sudoku/paires-nues/
    public boolean execut(CellBase c_in, Grid g) {
        if (c_in instanceof EmptyCell cell) {
            int x = cell.getXpos();
            int y = cell.getYpos();
            Line l = g.getLine(y);
            Column c = g.getColumn(x);
            Square s = g.getSquare(x, y);
            int[] table = cell.getPossibleValues();
            boolean change = false;
            if (cell.numberPossibleValue() == 2) {
                int i_v1 = -1;
                int i_v2 = -1;
                for (int j = 0 ; j < 9 ; j++) {
                    if (i_v1 == -1 && table[j] != 0) {
                        i_v1 = j;
                    } else if (table[j] != 0 ) {
                        i_v2 = j;
                    }
                }
                if (i_v1 == -1 || i_v2 == -1) {
                    System.out.println("PROBLEME");
                }
                for (int i = 0 ;  i < 9 ; i++) {
                    if (l.get(i) instanceof EmptyCell elt) {
                        if (elt.getXpos() != x) {
                            if (Arrays.equals(elt.getPossibleValues(), table)) {
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (l.get(j) instanceof EmptyCell elt2) {
                                        if (elt2.getXpos() != x && elt2.getXpos() != elt.getXpos()) {
                                            if (elt2.checkPossibleValue(i_v1+1) || elt2.checkPossibleValue(i_v1+1)) {change =true;}
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
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (c.get(j) instanceof EmptyCell elt2) {
                                        if (elt2.getYpos() != y && elt2.getYpos() != elt.getYpos()) {
                                            if (elt2.checkPossibleValue(i_v1+1) || elt2.checkPossibleValue(i_v2+1)) {change =true;}
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
                                for (int j = 0 ; j < 9 ; j++) {
                                    if (s.get(j) instanceof EmptyCell elt2) {
                                        if ((elt2.getXpos() != x || elt2.getYpos() != y) && (elt2.getXpos() != elt.getXpos() || elt2.getYpos() != elt.getYpos())) {
                                            if (elt2.checkPossibleValue(i_v1+1) || elt2.checkPossibleValue(i_v2+1)) {change =true;}
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
        }
        return false;
    }

    public void routine(Pile pile, Grid grid){ //Repeat DR3, DR1 and DR2 and try to solve without the help of user

        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (execut(cell, grid)) {
                System.out.println("DR3.");
                pile.pushAllRelated(cell, grid);
            } else if (dr1.execut(cell, grid)) {
                System.out.println("DR1.");
                pile.pushAllRelated(cell, grid);
            } else if (dr2.execut(cell, grid)) {
                System.out.println("DR2.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}

