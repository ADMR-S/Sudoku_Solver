package composite.cell;
import composite.*;
import strategy.Visitor;

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
    public boolean  checkPossibleValue(int i) {
        return this.possibleValues[i-1] == i;
    }
    public void removePossibleValue( int v) {
        possibleValues[v-1] = 0;
        System.out.println("On retire la valeur : " + v + " à la cellule " + this.xpos + " " + this.ypos + " reste : " + numberPossibleValue() + " valeurs possibles");
        if(this.numberPossibleValue() == 0){
            notifySubscribersNoPossibleValue();
        }
    }
    public int numberPossibleValue() {
        int n = 0;
        for (int elt : this.possibleValues) {
            if (elt != 0) {n++;}
        }
        return n;
    }

    public String toString() {
        return "EmptyCell [xpos=" + getXpos() + ", ypos=" + getYpos() + ", possibleValues=" + java.util.Arrays.toString(possibleValues) + "]";
    }

    public boolean accept(Visitor v, Grid grid){
        return v.visitEmptyCell(this, grid);
    }

    public EmptyCell getCopy(){
        EmptyCell emptyCellCopy = new EmptyCell(this.xpos, this.ypos);
        System.arraycopy(this.possibleValues, 0, emptyCellCopy.possibleValues, 0, 9);
        for (Subscriber subscriber : this.subscribers) {
            emptyCellCopy.subscribe(subscriber);
        }
        return emptyCellCopy;
    }
    public void subscribe(Subscriber s){
        this.subscribers.add(s);
    }
    public void unsubscribe(Subscriber s){
        this.subscribers.remove(s);
    }
    public void notifySubscribersNoPossibleValue(){
        for (int i = 0;i<subscribers.size();i++){
            subscribers.get(i).update(0);
        }
    }
}