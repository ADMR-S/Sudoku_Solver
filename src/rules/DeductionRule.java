package rules;
import composite.*;

public abstract class DeductionRule {
    public abstract boolean execut(CellBase c, Grid g);
}