package composite;

public class EmptyCell extends CellBase{
    private int[] possibleValues;

    //A tester
    public EmptyCell(int xpos, int ypos) {
        super(xpos, ypos);
        this.possibleValues =  new int[] {1,2,3,4,5,6,7,8,9};
    }

    public int[] getPossibleValues() {
        return possibleValues;
    }
    public void setPossibleValues(int[] possibleValues) {
        this.possibleValues = possibleValues;
    }
    public void removePossibleValue( int v) {
        possibleValues[v-1] = 0;
    }

}