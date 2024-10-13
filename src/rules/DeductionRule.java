package rules;
import composite.*;

public abstract class DeductionRule {
    public abstract void execut(CellBase c, Grid g);
}