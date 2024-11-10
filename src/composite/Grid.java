package composite;

public class Grid implements Subscriber{

    private static Grid instance;
    private Line[] lines;
    private Column[] columns;
    private Square[] squares;
    private int cellsToFill;
    private boolean isWrong;

    private Grid() {}//Constructeur déplacé dans Builder.java

    public CellBase get(int x, int y) {
        return this.lines[y].get(x);
    }

    public void set(CellBase cell, int x, int y) {
        this.lines[y].setTableCell(cell, x);
        this.columns[x].setTableCell(cell, y);
        this.squares[x/3 + (y/3)*3].setTableCell(cell, x%3 + (y%3)*3);
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

    public Column getColumn(int i) {
        return columns[i];
    }

    public void setColumns(Column[] columns) {
        this.columns = columns;
    }
    public void setColumnValue(Column column, int index) { // pour set la valeur d'une ligne
        this.columns[index] = column;
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
        output = output.concat("\nGrid :\n");
        output = output.concat("           x     \n");
        output = output.concat("      012 345 678\n");
        output = output.concat("      vvv vvv vvv\n");
        output = output.concat("      ___________\n");
        for (int i = 0; i < 9; i++) {
            if (i==4){
                output = output.concat("y 4> |");
            }
            else {
                output = output.concat("  " + Integer.toString(i) + "> |");
            }
            for (int j = 0; j < 9; j++) {
                if (this.lines[i].getTable()[j] instanceof Cell cell) {
                    output = output.concat(Integer.toString(cell.getValue()));
                } else {
                    output = output.concat(" ");
                }
                if((j+1)%3 ==0){
                    output = output.concat("|");
                }
            }
            output = output.concat("\n");
            if((i+1)%3 ==0 && i!=8){
                output = output.concat("     |---|---|---|\n");
            }
            if (i==8){
                output = output.concat("      ----------- \n");
            }
        }
        return output;
    }
    public String toCSV() {
        String output = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                output = output.concat(Integer.toString(this.lines[i].getTable()[j].getValue()));
                output = output.concat(",");
            }
            output = output.concat("\n");
        }
        output = output.substring(0, output.length()-2);//On retire la dernière virgule
        return output;
    }

    public boolean checkValidity() {
        for (int i = 0; i < 9; i++) {
            if (!lines[i].checkValidity()) return false;
            if (!columns[i].checkValidity()) return false;
            if (!squares[i].checkValidity()) return false;
        }
        return true;
    }

    public Snapshot makeSnapshot(){
        Line[] linesCopy = new Line[9];
        Column[] columnsCopy = new Column[9];
        Square[] squaresCopy = new Square[9];

        for(int k = 0; k<9; k++){
            linesCopy[k] = new Line();
            columnsCopy[k] = new Column();
            squaresCopy[k] = new Square();
        }
        //Répétition code set(...) ci-dessous, encapsuler ? ITERATOR ?
        for (int x = 0; x<9; x++){
            for(int y = 0; y<9; y++){
                CellBase cellOrEmptyCellCopy = this.lines[y].getTable()[x].getCopy();
                linesCopy[y].setTableCell(cellOrEmptyCellCopy, x);
                columnsCopy[x].setTableCell(cellOrEmptyCellCopy, y);
                squaresCopy[x/3 + (y/3)*3].setTableCell(cellOrEmptyCellCopy, x%3 + (y%3)*3);
            }
        }
        Snapshot snap = new Snapshot(linesCopy, columnsCopy, squaresCopy, this.cellsToFill);
        return snap;
    }

    public void restore(Memento memento){
        this.lines = memento.getLines();
        this.columns = memento.getColumns();
        this.squares = memento.getSquares();
        this.cellsToFill = memento.getCellsToFill();
        this.isWrong = false;

        memento = this.makeSnapshot(); //On remplace l'ancienne copie par une nouvelle

        return;
    }

    public void update(int nbPossibleValues){
        if(nbPossibleValues==0){
            this.isWrong = true;
        }
    }

    public boolean isWrong(){
        return this.isWrong;
    }

    public void setIsWrong(boolean wrong) {
        isWrong = wrong;
    }
}