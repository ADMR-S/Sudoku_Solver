package composite;

public class Cell extends CellBase{

    private int value;

    public Cell(int xpos, int ypos, int value){
        super(xpos, ypos);
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }
}