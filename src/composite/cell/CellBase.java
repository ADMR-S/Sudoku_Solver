package composite.cell;

public abstract class CellBase{ //Base pour les classes Cell et EmptyCell
    protected int xpos;
    protected int ypos;

    public CellBase(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getValue(){return 0;}
    public int getXpos() {
        return xpos;
    }
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }
    public int getYpos() {
        return ypos;
    }
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    public boolean  checkPossibleValue(int i) {
        return false;
    }
    public abstract CellBase getCopy();

    public String toString() {
        return "CellBase [xpos=" + xpos + ", ypos=" + ypos + "]";
    }
}