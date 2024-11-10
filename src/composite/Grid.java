package composite;

import composite.cell.Cell;
import composite.cell.CellBase;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;

public class Grid implements Subscriber{

    private static Grid instance;
    private Line[] lines;
    private Column[] columns;
    private Square[] squares;
    private int cellsToFill;
    private boolean isWrong;

    private Grid() {}//Constructeur déplacé dans Builder.java

    /**
     * Get la valeur d'une cellule dans la grille.
     *
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     * @return la cellule à la position x, y
     */
    public CellBase get(int x, int y) {
        return this.lines[y].get(x);
    }

    /**
     * Set la valeur d'une cellule dans la grille.
     *
     * @param cell la cellule à set
     * @param x la position x de la cellule
     * @param y la position y de la cellule
     */
    public void set(CellBase cell, int x, int y) {
        this.lines[y].setTableCell(cell, x);
        this.columns[x].setTableCell(cell, y);
        this.squares[x/3 + (y/3)*3].setTableCell(cell, x%3 + (y%3)*3);
    }

    /**
     * Get la ligne à l'index i.
     *
     * @param i l'index de la ligne (0 à 8).
     * @return la ligne à l'index i
     */
    public Line getLine(int i) {
        return lines[i];
    }

    /**
     * Set les lignes de la grille.
     *
     * @param lines les lignes de la grille
     */
    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    /**
     * Set la valeur d'une ligne dans la grille.
     *
     * @param line la ligne à set
     * @param index l'index de la ligne (entre 0 et 8).
     */
    public void setLineValue(Line line, int index) { // pour set la valeur d'une ligne
        this.lines[index] = line;
    }

    /**
     * Get la colonne à l'index i.
     *
     * @param i l'index de la colonne (0 à 8).
     * @return la colonne à l'index i
     */
    public Column getColumn(int i) {
        return columns[i];
    }

    /**
     * Set les colonnes de la grille.
     *
     * @param columns les colonnes de la grille
     */
    public void setColumns(Column[] columns) {
        this.columns = columns;
    }

    /**
     * Set la valeur d'une colonne dans la grille.
     *
     * @param column la colonne à set
     * @param index l'index de la colonne (entre 0 et 8).
     */
    public void setColumnValue(Column column, int index) { // pour set la valeur d'une ligne
        this.columns[index] = column;
    }

    /**
     * Get le carré à la position x, y.
     *
     * @param x la position x du carré (entre 0 et 8).
     * @param y la position y du carré (entre 0 et 8).
     * @return le carré dans lequel la cellule x, y se trouve.
     */
    public Square getSquare(int x, int y) {
        int i = x/3;
        int j = y/3;
        return squares[i + j*3];
    }

    /**
     * La grille étant un singleton, on ne peut pas instancier plusieurs grilles.
     * Cette méthode permet de récupérer l'instance de la grille.
     *
     * @return l'instance de la grille
     */
    public static Grid getInstance(){
        if(Grid.instance == null){
            Grid.instance = new Grid();
        }
        return Grid.instance;
    }

    /**
     * Set les carrés de la grille.
     *
     * @param squares les carrés de la grille
     */
    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    /**
     * Set la valeur d'un carré dans la grille.
     *
     * @param square le carré à set
     * @param index l'index du carré (entre 0 et 8).
     */
    public void setSquareValue(Square square, int index) { // pour set la valeur d'une ligne
        this.squares[index] = square;
    }

    /**
     * Get le nombre de cellules à remplir dans la grille.
     *
     * @return  int  le nombre de cellules à remplir dans la grille
     */
    public int getCellsToFill() {
        return cellsToFill;
    }

    /**
     * Set le nombre de cellules à remplir dans la grille.
     *
     * @param cellsToFill le nombre de cellules à remplir dans la grille
     */
    public void setCellsToFill(int cellsToFill) {
        this.cellsToFill = cellsToFill;
    }

    /**
     * Retourne un String affichant la grille.
     *
     * @return  String  un String représentant la grille
     */
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

    /**
     * Retourne un String affichant la grille au format CSV.
     * Chaque ligne est séparée par un retour à la ligne et chaque valeur est séparée par une virgule.
     * Et il y a un espace entre chaque valeur et les valeurs sont comprises entre 0 et 9.
     *
     * @return  String  un String représentant la grille au format CSV
     */
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

    /**
     * Retourne un boolean, vrai si la grille est valide, faux sinon.
     *
     * @return  boolean  vrai si la grille est valide, faux sinon
     */
    public boolean checkValidity() {
        for (int i = 0; i < 9; i++) {
            if (!lines[i].checkValidity()) return false;
            if (!columns[i].checkValidity()) return false;
            if (!squares[i].checkValidity()) return false;
        }
        return true;
    }

    /**
     * Fonction du pattern memento qui crée une copie de l'état de la grille à l'instant t
     *
     * @return  Snapshot La copie de l'état de la grille
     */
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

    /**
     * Fonction du pattern memento qui rappelle un état précédent de la grille et en recrée une copie
     *
     * @param  memento  Une sauvegarde précédente de l'état de la grille
     * @return  Memento  Une nouvelle sauvegarde pour remplacer la précédente
     */
    public Memento restore(Memento memento){
        this.lines = memento.getLines();
        this.columns = memento.getColumns();
        this.squares = memento.getSquares();
        this.cellsToFill = memento.getCellsToFill();
        this.isWrong = false;

        return this.makeSnapshot();//On remplace l'ancienne copie par une nouvelle
    }

    /**
     * Fonction d'actualisation du pattern Observer pour prendre en compte les notifications reçues
     *
     * @param  nbPossibleValues  Le nombre de valeurs possibles restantes à la cellule qui envoie la notification
     */
    public void update(int nbPossibleValues){
        if(nbPossibleValues==0){
            this.isWrong = true;
        }
    }

    /**
     * Getter pour le champ isWrong qui tracke si une erreur a été commise
     * @return boolean Le champ isWrong
     */
    public boolean isWrong(){
        return this.isWrong;
    }

    /**
     * Setter pour le champ isWrong qui tracke si une erreur a été commise
     * @return boolean Le champ isWrong
     */
    public void setIsWrong(boolean wrong) {
        isWrong = wrong;
    }
}