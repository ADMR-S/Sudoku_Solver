package composite;

import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;

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

    /**
     * Renvoie les lignes de la grille que le memento a sauvegardé.
     *
     * @return les lignes
     */
    public Line[] getLines() {
        return lines;
    }

    /**
     * Renvoie les colonnes de la grille que le memento a sauvegardé.
     *
     * @return les colonnes
     */
    public Column[] getColumns() {
        return columns;
    }

    /**
     * Renvoie les carrés de la grille que le memento a sauvegardé.
     *
     * @return les carrés
     */
    public Square[] getSquares() {
        return squares;
    }

    /**
     * Renvoie le nombre de cases à remplir de la grille que le memento a sauvegardé.
     *
     * @return le nombre de cases à remplir
     */
    public int getCellsToFill() {
        return cellsToFill;
    }
}