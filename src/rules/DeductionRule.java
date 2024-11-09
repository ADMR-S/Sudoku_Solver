package rules;
import composite.*;
import pile.Pile;

public abstract class DeductionRule {
    public abstract boolean execut(CellBase c, Grid g);
    public abstract void routine(Pile p, Grid g);
}