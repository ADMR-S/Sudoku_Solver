package composite;

import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;

//PATTERN MEMENTO
public interface Memento {

    /**
     * @return les lignes
     */
    public Line[] getLines();

    /**
     * @return les colonnes
     */
    public Column[] getColumns();

    /**
     * @return les carrés
     */
    public Square[] getSquares();

    /**
     * @return le nombre de cases à remplir
     */
    public int getCellsToFill();
}