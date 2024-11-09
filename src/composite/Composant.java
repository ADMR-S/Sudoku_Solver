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
            if (i%10==0 && i!=0){ //Pour la grid
                res+="\n";
            }
            res += this.table[i].getValue() + " ";
        }
        return res;
    }

    public boolean checkValidity() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j && this.get(i).getValue() != 0 && this.get(j).getValue() != 0 && this.get(i).getValue() == this.get(j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}