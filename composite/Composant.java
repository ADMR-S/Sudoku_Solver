package composite;

//Faire dériver Cell et Composant d'une classe composite ? (Boite / Objet) Mais quel intérêt ?
public class Composant{
    private CellBase[] table = new CellBase[9];

/*
Obligé de déclarer la taille de table au dessus sinon NullPointerException à l'affectation des Cell dans setTableCell()
  public Composant(){
        CellBase[] table = new CellBase[9];
    }

 */

    public CellBase[] getTable(){
        return this.table;
    }
    public void setTable(CellBase[] table){
        this.table = table;
    }
    public void setTableCell(CellBase cell, int index){this.table[index] = cell;}
    public CellBase get(int n) {
        return table[n];
    }

    public String toString() {
        String res = "";
        for (int i = 0; i<9;i++) {
            res += this.table[i].getValue() + " ";
        }
        return res;
    }
}