package Composite;

public class Grid{

    private Line[] lines;
    private Column[] columns;
    private Square[] squares;

    public Grid() {
        Line[] lignes = new Line[9];
        Column[] columns = new Column[9];
        Square[] squares = new Square[9];
        this.lines = lignes;
        this.columns = columns;
        this.squares = squares;
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

    public Square[] getSquares() {
        return squares;
    }

    public void setSquares(Square[] squares) {
        this.squares = squares;
    }
}