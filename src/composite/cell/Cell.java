package composite.cell;

public class Cell extends CellBase{ //Représente une cellule dont la valeur est fixée

    private final int value;

    public Cell(int xpos, int ypos, int value){
        super(xpos, ypos);
        this.value = value;
    }

    /**
     * Retourne la valeur de la cellule.
     *
     * @return  int   un entier représentant la valeur de la cellule
     */
    public int getValue(){
        return this.value;
    }

    /**
     * Retourne un String affichant la position et la valeur de la cellule.
     *
     * @return  String   un String représentant la cellule
     */
    public String toString() {
        return "Cell [xpos=" + this.getXpos() + ", ypos=" + this.getYpos() + "] value : " + this.value;
    }

    /**
     * Retourne une copy de la cellule avec les mêmes valeurs.
     *
     * @return  Cell   une copy de la cellule.
     */
    public Cell getCopy(){
        return new Cell(this.xpos, this.ypos, this.value);
    }
}