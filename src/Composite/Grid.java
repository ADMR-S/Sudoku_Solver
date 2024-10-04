package Composite;

public class Grid{

    private Line[] lines;
    private Column[] columns;
    private Square[] squares;
    private int cellsToFill;

    public Grid() {
        Line[] lignes = new Line[9];
        Column[] columns = new Column[9];
        Square[] squares = new Square[9];
        this.lines = lignes;
        this.columns = columns;
        this.squares = squares;
        this.cellsToFill = 81;
    }

    public Line[] getLines() {
        return lines;
    }

    public void setLines(Line[] lines) {
        this.lines = lines;
    }
    public void setLineValue(Line line, int index) { // pour set la valeur d'une ligne
        this.lines[index] = line;
    }

    public Column[] getColumns() {
        return columns;
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }
    public void setColumnValue(Column column, int index) { // pour set la valeur d'une ligne
        this.columns[index] = column;
    }

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }
    public void setSquareValue(Square square, int index) { // pour set la valeur d'une ligne
        this.squares[index] = square;
    }

    public int getCellsToFill() {
        return cellsToFill;
    }

    public void setCellsToFill(int cellsToFill) {
        this.cellsToFill = cellsToFill;
    }
}