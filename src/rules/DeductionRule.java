package rules;
import composite.*;
import composite.cell.CellBase;
import pile.Pile;

public abstract class DeductionRule {
    public abstract boolean execut(CellBase c, Grid g);
    public abstract void routine(Pile p, Grid g);
}