package rules;
import composite.*;

//Egalement remplacer la cellule des lignes, colonnes et squares par cell en entré pour ne pas à avoir à le faire autre part ?
//(ça serait probablement plus simple pour le builder et les règles).

public class DR0 extends DeductionRule {
    public void execut(CellBase c_in, Grid g) {
        if (c_in instanceof Cell cell) {
            Line l = g.getLine(cell.getYpos());
            Column c = g.getColumn(cell.getXpos());
            Square s = g.getSquare(cell.getXpos(), cell.getYpos());
            for (int i = 0; i < 9 ; i++) {
                if (l.get(i) instanceof EmptyCell empty_cell) {
                    empty_cell.removePossibleValue(cell.getValue());
                }
                if (c.get(i) instanceof EmptyCell empty_cell) {
                    empty_cell.removePossibleValue(cell.getValue());
                }
                if (s.get(i) instanceof EmptyCell empty_cell) {
                    empty_cell.removePossibleValue(cell.getValue());
                }
            }
        }
    }
}
