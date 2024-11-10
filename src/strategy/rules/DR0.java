package strategy.rules;
import composite.*;
import composite.cell.Cell;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;
import log.SudokuLogger;
import strategy.Pile;

//Egalement remplacer la cellule des lignes, colonnes et squares par cell en entré pour ne pas à avoir à le faire autre part ?
//(ça serait probablement plus simple pour le builder et les règles).
// -> Mieux que le builder se charge seul de ses tâches d'après moi pour compartimenter
// Ok pour l'inclusion du remplacement pour les règles, tant que le Builder n'appelle pas DR0(Adam)

public class DR0 extends DeductionRule {
    /** Règle de base qui, pour une cellule donnée, retire sa valeur
     * des valeurs possibles des cellules de la même ligne/colonne/carré
     * que la cellule donnée en paramètre.
     *
     * @param c_in CellBase la cellule pour laquelle on veut retirer la valeur.
     * @param g Grid la grille de jeu
     * @return  boolean vrai si la règle est appliqué à la cellule c, faux sinon (donc si c n'est pas une cellule pleine).
     */
    public boolean execut(CellBase c_in, Grid g) {

        SudokuLogger.getLogger().info("DR0 execut");
        if (c_in instanceof Cell cell) {
            //Récupère la ligne/colonne/carré associée à la cellule.
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
            return true;
        }
        return false;
    }

    /** Applique la règle à la pile p et à la grille g dans le cadre d'une strategie.
     *
     * @param pile Pile la pile de cellules à tester
     * @param grid Grid la grille de jeu
     */
    public void routine(Pile pile, Grid grid){//Routine : execution de DR0 pour chaque cellule remplie de la grille
        while (!pile.isEmpty()) {
            SudokuLogger.getLogger().info("DR0.");
            CellBase cell = pile.pop();
            execut(cell, grid);
        }
    }
}
