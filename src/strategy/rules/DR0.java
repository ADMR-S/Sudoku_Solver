package strategy.rules;
import composite.*;
import composite.cell.Cell;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;
import strategy.Pile;
import strategy.Visitor;

//Egalement remplacer la cellule des lignes, colonnes et squares par cell en entré pour ne pas à avoir à le faire autre part ?
//(ça serait probablement plus simple pour le builder et les règles).
// -> Mieux que le builder se charge seul de ses tâches d'après moi pour compartimenter
// Ok pour l'inclusion du remplacement pour les règles, tant que le Builder n'appelle pas DR0(Adam)

public class DR0 extends DeductionRule implements Visitor{
    /*Règle de base qui, pour une cellule donnée, retire sa valeur
    des valeurs possibles des cellules de la même ligne/colonne/carré*/
    public boolean execut(CellBase c_in, Grid g) {
        System.out.println("DR0 execut");
        return c_in.accept(this, g);

    }
    public void routine(Pile pile, Grid grid){//Routine : execution de DR0 pour chaque cellule remplie de la grille
        while (!pile.isEmpty()) {
            System.out.println("DR0.");
            CellBase cell = pile.pop();
            execut(cell, grid);
        }
    }
    public boolean visitEmptyCell(EmptyCell cell, Grid g) {
        return false;
    }
    public boolean visitCell(Cell cell, Grid g) {
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
}
