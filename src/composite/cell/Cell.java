package composite.cell;
import composite.*;
import strategy.Visitor;

public class Cell extends CellBase{ //Représente une cellule dont la valeur est fixée

    private final int value;

    public Cell(int xpos, int ypos, int value){
        super(xpos, ypos);
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public String toString() {
        return "Cell [xpos=" + this.getXpos() + ", ypos=" + this.getYpos() + "] value : " + this.value;
    }

    public boolean accept(Visitor v, Grid grid) {
        return v.visitCell(this, grid);
    }

    public Cell getCopy(){
        return new Cell(this.xpos, this.ypos, this.value);
    }
}