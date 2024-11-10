package strategy;
import composite.*;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;


public class Pile {

    private CellBase[] stack;
    private int size;
    private int capacity;

    public Pile(int capacity) {
        this.capacity = capacity;
        this.stack = new CellBase[capacity];
        this.size = 0;
    }
    /**
     * @return true si la pile est vide, false sinon
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * @return true si la pile est pleine, false sinon
     */
    public boolean isFull() {
        return this.size == this.capacity;
    }
    /**
     * Ajoute une cellule à la pile si elle n'est pas déjà dedans.
     *
     * @param cell la cellule à ajouter
     */
    public void push(CellBase cell) {
        //Check if Cell already in stack
        for (int i = 0; i < this.size; i++) {
            if (this.stack[i].equals(cell)) {
                return;
            }
        }

        if (this.isFull()) {
            System.err.println("Stack is full");
            return;
        }
        this.stack[this.size++] = cell;
    }
    /**
     * Retire la cellule au sommet de la pile.
     *
     * @return la cellule retirée
     */
    public CellBase pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.stack[--this.size];
    }
    /**
     * Retourne la cellule au sommet de la pile.
     *
     * @return la cellule au sommet de la pile
     */
    public CellBase head() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.stack[this.size - 1];
    }
    /**
     * Retourne la taille de la pile.
     *
     * @return la taille de la pile
     */
    public int getSize() {
        return this.size;
    }
    /**
     * Retourne la capacité de la pile.
     *
     * @return la capacité de la pile
     */
    public int getCapacity() {
        return this.capacity;
    }
    /**
     * Affiche la pile.
     */
    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.stack[i]);
        }
    }

    /**
     * Empile toutes les cellules vides de la grille.
     *
     * @param grid la grille de jeu
     */
    public void stackAllEmpty(Grid grid) {//Stack all EmptyCells in a Pile
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid.get(i, j).getValue() == 0) {
                    this.push(grid.get(i, j));
                }
            }
        }
        return;
    }
    /**
     * Empile toutes les cellules non vides de la grille.
     *
     * @param grid la grille de jeu
     */
    public void stackAllNonEmpty(Grid grid) {//Stack all non-empty cells in a Pile
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid.get(i, j).getValue() != 0) {
                    push(grid.get(i, j));
                }
            }
        }
    }

    /**
     * Empile toutes les cellules vides appartenant à la même ligne, colonne ou carré que la cellule donnée.
     *
     * @param grid la grille de jeu
     * @param cell la cellule donnée
     */
    public void pushAllRelated(CellBase cell, Grid grid) { // push on the stack all EmptyCell in the same Line/Column/Square
        Line l = grid.getLine(cell.getYpos());
        Column c = grid.getColumn(cell.getXpos());
        Square s = grid.getSquare(cell.getXpos(), cell.getYpos());
        for (int i = 0; i < 9; i++) {
            if (l.get(i) instanceof EmptyCell empty_cell) {
                push(empty_cell);
            }
            if (c.get(i) instanceof EmptyCell empty_cell) {
                push(empty_cell);
            }
            if (s.get(i) instanceof EmptyCell empty_cell) {
                push(empty_cell);
            }
        }
    }

    
}