package composite;

//PATTERN MEMENTO
public interface Memento {
    public Line[] getLines();

    public Column[] getColumns();

    public Square[] getSquares();

    public int getCellsToFill();
}