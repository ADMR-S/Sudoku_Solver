package Composite;

//Faire dériver Cell et Composant d'une classe composite ? (Boite / Objet) Mais quel intérêt ?
public class Composant{
    private Cell[] table;

    public Cell[] get_table(){
        return this.table;
    }
    public void set_table(Cell[] table){
        this.table = table;
    }
}