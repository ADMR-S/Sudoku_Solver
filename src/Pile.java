import composite.CellBase;

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

    
}