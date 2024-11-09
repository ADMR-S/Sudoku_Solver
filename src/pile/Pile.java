package pile;
import composite.*;


public class Pile {
    private CellBase[] stack;
    private int size;
    private int capacity;
    public Pile(int capacity) {
        this.capacity = capacity;
        this.stack = new CellBase[capacity];
        this.size = 0;
    }
    public boolean isEmpty() {
        return this.size == 0;
    }
    public boolean isFull() {
        return this.size == this.capacity;
    }
    public void push(CellBase cell) {
        //Check if Cell already in stack
        for (int i = 0; i < this.size; i++) {
            if (this.stack[i].equals(cell)) {
                return;
            }
        }

        if (this.isFull()) {
            System.out.println("Stack is full");
            return;
        }
        this.stack[this.size++] = cell;
    }
    public CellBase pop() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.stack[--this.size];
    }
    public CellBase head() {
        if (this.isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return this.stack[this.size - 1];
    }
    public int getSize() {
        return this.size;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public void display() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(this.stack[i]);
        }
    }


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
    public void stackAllNonEmpty(Grid grid) {//Stack all non-empty cells in a Pile
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid.get(i, j).getValue() != 0) {
                    push(grid.get(i, j));
                }
            }
        }
    }
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
        return;
    }

    
}