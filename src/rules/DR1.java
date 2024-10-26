package rules;
import composite.*;

public class DR1 extends DeductionRule {
    public void execut(CellBase c, Grid g){
        if (c instanceof EmptyCell cell) {
            int[] p = cell.getPossibleValues();
            int value = 0;
            for (int i=0; i < p.length ; i++) {
                if (p[i] != 0 && value == 0) {
                    value = p[i];
                } else if (p[i] != 0) {
                    return;
                }
            }
            if (value != 0) {
                Cell c_new = new Cell(cell.getXpos(), cell.getXpos(), value);
                DR0 r = new DR0();
                r.execut(c_new, g);
                // Modifier la ligne, colonne et square associé (ça serait plus simple dans DR0)
            }
        }
    }
}
