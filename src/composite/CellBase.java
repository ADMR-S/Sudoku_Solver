package composite;

public abstract class CellBase{
    private int xpos;
    private int ypos;

    public CellBase(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

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
}