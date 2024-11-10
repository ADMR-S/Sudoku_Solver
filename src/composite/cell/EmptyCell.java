package composite.cell;
import composite.Subscriber;
import log.SudokuLogger;

import java.util.ArrayList;

public class EmptyCell extends CellBase{//Représente une cellule vide dont on ne connaît pas encore la valeur
    /**
     * Test de description d'un champ
     */
    private int[] possibleValues;
    private ArrayList<Subscriber> subscribers;

    public EmptyCell(int xpos, int ypos) {
        super(xpos, ypos);
        this.possibleValues =  new int[] {1,2,3,4,5,6,7,8,9};
        this.subscribers = new ArrayList<Subscriber>();
    }

    /**
     * Retourne un tableau indexé par valeur des valeurs possibles d'une cellule vide
     *
     * @return  int[]   un tableau des valeurs possibles d'une cellule vide
     */
    public int[] getPossibleValues() {
        return possibleValues;
    }

    /**
     * Retourne un boolean, vrais si la valeur i est possible pour la cellule, faux sinon.
     *
     * @return  boolean
     */
    public boolean  checkPossibleValue(int i) {
        return this.possibleValues[i-1] == i;
    }

    /**
     * Retire la valeur i des valeurs possibles de la cellule
     *
     * @param  v   int  la valeur à retirer
     */
    public void removePossibleValue( int v) {
        possibleValues[v-1] = 0;
        SudokuLogger.getLogger().info("On retire la valeur : " + v + " à la cellule " + this.xpos + " " + this.ypos + " reste : " + numberPossibleValue() + " valeurs possibles");
        if(this.numberPossibleValue() == 0){
            notifySubscribersNoPossibleValue();
        }
    }

    /**
     * Retourne le nombre de valeurs possibles pour la cellule
     *
     * @return  int   le nombre de valeurs possibles pour la cellule
     */
    public int numberPossibleValue() {
        int n = 0;
        for (int elt : this.possibleValues) {
            if (elt != 0) {n++;}
        }
        return n;
    }

    /**
     * Retourne un String affichant la position et les valeurs possibles de la cellule.
     *
     * @return  String   un String représentant la cellule
     */
    public String toString() {
        return "EmptyCell [xpos=" + getXpos() + ", ypos=" + getYpos() + ", possibleValues=" + java.util.Arrays.toString(possibleValues) + "]";
    }

    /**
     * Retourne une copy de la cellule avec les mêmes valeurs possibles et la même position.
     *
     * @return  EmptyCell   une copy de la cellule.
     */
    public EmptyCell getCopy(){
        EmptyCell emptyCellCopy = new EmptyCell(this.xpos, this.ypos);
        System.arraycopy(this.possibleValues, 0, emptyCellCopy.possibleValues, 0, 9);
        for (Subscriber subscriber : this.subscribers) {
            emptyCellCopy.subscribe(subscriber);
        }
        return emptyCellCopy;
    }

    /**
     * Ajoute un subscriber à la liste des subscribers
     *
     * @param  s   Subscriber  le subscriber à ajouter
     */
    public void subscribe(Subscriber s){
        this.subscribers.add(s);
    }

    /**
     * Retire un subscriber de la liste des subscribers
     *
     * @param  s   Subscriber  le subscriber à retirer
     */
    public void unsubscribe(Subscriber s){
        this.subscribers.remove(s);
    }

    /**
     * Notifie les subscribers que la cellule a été remplie
     *
     */
    public void notifySubscribersNoPossibleValue(){
        for (int i = 0;i<subscribers.size();i++){
            subscribers.get(i).update(0);
        }
    }
}