package composite;
import java.util.ArrayList;

public class EmptyCell extends CellBase{//Représente une cellule vide dont on ne connaît pas encore la valeur
    private int[] possibleValues;
    private ArrayList<Subscriber> subscribers;

    public EmptyCell(int xpos, int ypos) {
        super(xpos, ypos);
        this.possibleValues =  new int[] {1,2,3,4,5,6,7,8,9};
        this.subscribers = new ArrayList<Subscriber>();
    }

    public int[] getPossibleValues() {
        return possibleValues;
    }
    public boolean  checkPossibleValue(int i) {
        return this.possibleValues[i-1] == i;
    }
    public void removePossibleValue( int v) {
        possibleValues[v-1] = 0;
        if(numberPossibleValue() == 0){
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
    public EmptyCell getCopy(){
        EmptyCell emptyCellCopy = new EmptyCell(this.xpos, this.ypos);
        System.arraycopy(this.possibleValues, 0, emptyCellCopy.possibleValues, 0, 9);
        ArrayList<Subscriber> subscribersCopy = new ArrayList<>();
        for(int i=0; i<subscribers.size(); i++){
            subscribersCopy.add(subscribers.get(i));
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
        for(int i = 0;i<subscribers.size();i++){
            subscribers.get(i).update(0);
        }
    }
}