package composite;

import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;

//PATTERN MEMENTO
public interface Memento {
    public Line[] getLines();

    public Column[] getColumns();

    public Square[] getSquares();

    public int getCellsToFill();
}