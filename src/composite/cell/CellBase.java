package composite.cell;

public abstract class CellBase{ //Base pour les classes Cell et EmptyCell
    protected int xpos;
    protected int ypos;

    public CellBase(int xpos, int ypos) {
        this.xpos = xpos;
        this.ypos = ypos;
    }

    /**
     * Retourne un entier représentant la valeur de la cellule (0 pour une cellule vide).
     *
     * @return  int
     */
    public int getValue(){return 0;}

    /**
     * Retourne un entier représentant la position x de la cellule.
     *
     * @return  int
     */
    public int getXpos() {
        return xpos;
    }
    /**
     * Set la position x de la cellule.
     *
     * @param xpos int
     */
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }
    /**
     * Retourne un entier représentant la position y de la cellule.
     *
     * @return  int
     */
    public int getYpos() {
        return ypos;
    }
    /**
     * Set la position y de la cellule.
     *
     * @param ypos int
     */
    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
    /**
     * Retourne un boolean, vrais si la valeur i est possible pour la cellule, faux sinon.
     *
     * @return  boolean
     */
    public boolean  checkPossibleValue(int i) {
        return false;
    }

    /**
     * Retourne une copy de la cellule.
     *
     * @return  CellBase
     */
    public abstract CellBase getCopy();

    /**
     * Retourne un String affichant la position de la cellule.
     *
     * @return  String
     */
    public String toString() {
        return "CellBase [xpos=" + xpos + ", ypos=" + ypos + "]";
    }
}