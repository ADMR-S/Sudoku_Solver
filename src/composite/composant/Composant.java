package composite.composant;

import composite.cell.CellBase;

//Faire dériver Cell et Composant d'une classe composite ? (Boite / Objet) Mais quel intérêt ?
public class Composant{
    private CellBase[] table = new CellBase[9];

/*
Obligé de déclarer la taille de table au dessus sinon NullPointerException à l'affectation des Cell dans setTableCell()
  public Composant(){
        CellBase[] table = new CellBase[9];
    }

 */

    /**
     * Retourne un tableau de CellBase représentant les cellules du composant.
     *
     * @return  CellBase[]
     */
    public CellBase[] getTable(){
        return this.table;
    }

    /**
     * Set le tableau de CellBase représentant les cellules du composant.
     *
     * @param table CellBase[]
     */
    public void setTable(CellBase[] table){
        this.table = table;
    }

    /**
     * Set la cellule cell à l'index index du tableau de CellBase.
     *
     * @param cell CellBase  la cellule à set
     * @param index int  l'index de la cellule à set
     */
    public void setTableCell(CellBase cell, int index){this.table[index] = cell;}

    /**
     * Retourne la cellule à l'index n du tableau de CellBase.
     *
     * @param n int  l'index de la cellule à retourner
     * @return  CellBase
     */
    public CellBase get(int n) {
        return table[n];
    }

    /**
     * Retourne un String affichant les valeurs des cellules du composant.
     *
     * @return  String
     */
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

    /**
     * Retourne un boolean, vrai si le composant est valide, faux sinon.
     * Un composant est valide si toutes ses cellules ont des valeurs différentes.
     *
     * @return  boolean
     */
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