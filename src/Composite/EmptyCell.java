package Composite;

public class EmptyCell extends CellBase{
    private int[] possibleValues;

    //A tester
    public EmptyCell(int xpos, int ypos, int[] possibleValues) {
        super(xpos, ypos);
        this.possibleValues = possibleValues;
    }

    public int[] getPossibleValues() {
        return possibleValues;
    }
    public void setPossibleValues(int[] possibleValues) {
        this.possibleValues = possibleValues;
    }
}