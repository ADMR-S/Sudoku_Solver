package strategy.rules;
import composite.*;
import composite.cell.CellBase;
import strategy.Pile;

public abstract class DeductionRule {

    /**
     * Retourne un boolean, vrai si la règle est effectivement appliquée à la cellule c, faux sinon.
     *
     * @param c CellBase la cellule à tester
     * @param g Grid la grille de jeu
     * @return  boolean vrai si la règle est appliqué à la cellule c, faux sinon
     */
    public abstract boolean execut(CellBase c, Grid g);

    /**
     * Applique la règle à la pile p et à la grille g dans le cadre d'une strategie.
     *
     * @param p Pile la pile de cellules à tester
     * @param g Grid la grille de jeu
     */
    public abstract void routine(Pile p, Grid g);
}