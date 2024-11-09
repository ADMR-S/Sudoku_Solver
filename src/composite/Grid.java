package composite;

public class Grid{

    private static Grid instance;
    private Line[] lines;
    private Column[] columns;
    private Square[] squares;
    private int cellsToFill;

    private Grid() {
        this.lines = new Line[9];
        this.columns = new Column[9];
        this.squares = new Square[9];
        this.cellsToFill = 81;
    }

    public CellBase get(int x, int y) {
        return this.lines[y].get(x);
    }

    public void set(CellBase cell, int x, int y) {
        this.lines[y].setTableCell(cell, x);
        this.columns[x].setTableCell(cell, y);
        this.squares[x/3 + (y/3)*3].setTableCell(cell, x%3 + (y%3)*3);
    }

    public Line[] getLines() {
        return lines;
    }
    public Line getLine(int i) {
        return lines[i];
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

    public Column getColumn(int i) {
        return columns[i];
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

    public Square getSquare(int x, int y) {
        int i = x/3;
        int j = y/3;
        return squares[i + j*3];
    }

    public static Grid getInstance(){
        if(Grid.instance == null){
            Grid.instance = new Grid();
        }
        return Grid.instance;
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

    @Override
    public String toString() {
        String output = "";
        //PRINT
        // Mettre cette boucle dans une classe Printer ? ou overkill?
        // (Peut-être utile quand on aura les classes lignes, colonnes etc...)
        output = output.concat("\nLignes :\n");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.getLines()[i].getTable()[j] instanceof Cell cell) {
                    output = output.concat(Integer.toString(cell.getValue()));
                } else {
                    output = output.concat(" ");
                }
            }
            output = output.concat("\n");
        }/*
        output = output.concat("\nColonnes :\n");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(this.getColumns()[i].getTable()[j] instanceof Cell cell){
                    output = output.concat(Integer.toString(cell.getValue()));
                } else {
                    output = output.concat(" ");
                }
            }
            output = output.concat("\n");
        }
        output = output.concat("\nSquares :\n");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(this.getSquares()[i].getTable()[j] instanceof Cell cell){
                    output = output.concat(Integer.toString(cell.getValue()));
                } else {
                    output = output.concat(" ");
                }
            }
            output = output.concat("\n");
        }*/
        return output;
    }
}