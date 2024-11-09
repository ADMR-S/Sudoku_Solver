package composite;

public class Snapshot implements Memento{
    Line[] lines;
    Column[] columns;
    Square[] squares;
    int cellsToFill;

    public Snapshot(Line[] lines, Column[] columns, Square[] squares, int cellsToFill) {
        this.lines = lines;
        this.columns = columns;
        this.squares = squares;
        this.cellsToFill = cellsToFill;
    }

    public Line[] getLines() {
        return lines;
    }

    public Column[] getColumns() {
        return columns;
    }

    public Square[] getSquares() {
        return squares;
    }

    public int getCellsToFill() {
        return cellsToFill;
    }
}