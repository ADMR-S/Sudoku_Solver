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
    public boolean  checkPossibleValue(int i) {
        return this.possibleValues[i-1] == i;
    }
    public void setPossibleValues(int[] possibleValues) {
        this.possibleValues = possibleValues;
    }
    public void removePossibleValue( int v) {
        possibleValues[v-1] = 0;
    }
    public int numberPossibleValue() {
        int n = 0;
        for (int elt : this.possibleValues) {
            if (elt != 0) {n++;}
        }
        return n;
    }
}