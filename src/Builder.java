import composite.*;
import composite.cell.Cell;
import composite.cell.CellBase;
import composite.cell.EmptyCell;
import composite.composant.Column;
import composite.composant.Line;
import composite.composant.Square;

import java.util.Scanner;

public class Builder{

    private final Scanner input;

    /**
     * Le constructeur du Builder. Prend un scanner en entrée (qui doit être formaté selon le format sudoku attendu)
     *
     * @param  input  Un Scanner qui contient la grille de sudoku au format spécifié
     */
    public Builder (Scanner input){
        this.input = input;
    }

    /**
     * Fonction de construction de la grille de sudoku utilisée par le solver et le context de rules/
     * Initialise la grille à partir du Scanner à partir duquel le Builder est créé
     *
     *
     * @return  Grid   La grille de sudoku initialisée
     * @see         buildFromStringArray()
     */
    public Grid buildGrid(){ //Fonction principale qui crée la grille complète à partir du Scanner
        Grid grid = Grid.getInstance();
        grid.setLines(new Line[9]);
        grid.setColumns(new Column[9]);
        grid.setSquares(new Square[9]);
        grid.setCellsToFill(81);
        grid.setIsWrong(false);
        for (int i = 0; i < 9; i++) {
            grid.setLineValue(new Line(), i);
            grid.setColumnValue(new Column(), i);
            grid.setSquareValue(new Square(), i);
        }

        //Build the lines of the grid first (Encapsuler dans une sous-fonction ?) :
        for (int i = 0; i < 9; i++) {

            if (input.hasNextLine()) {

                String[] ligneCourante = input.nextLine().split(",");
                if(ligneCourante.length != 9){
                    //Créer une exception à appeler en cas d'erreur d'input ?
                    System.err.println("""
                                        
                                        ERREUR
                                        Le format d'entr\u00e9e doit \u00eatre 9 lignes successives de la forme
                                        1,2,3,4,0,6,7,8,9  (par exemple) o\u00f9 0 repr\u00e9sente une absence de chiffre""");
                    System.exit(1);
                }

                buildFromStringArray(ligneCourante, i, grid); //Envisager de trouver comment actualiser cellsToFill sans passer la grille en argument

            }
            else {
                System.err.println("""

                                    ERREUR
                                    Le format d'entr\u00e9e doit \u00eatre 9 lignes successives de la forme
                                    1,2,3,4,0,6,7,8,9  o\u00f9 0 repr\u00e9sente une absence de chiffre""");
                System.exit(1);
            }
        }
        return grid;
    }


    /**
     * Fonction de construction auxiliaire qui crée les cellules d'une ligne de la grille
     * et les assigne à ses différents composants via la fonction set de la classe Grid
     *
     * @param  ligneCourante  La ligne sous forme de String que l'on souhaite créer avec des CellBase (Cell ou EmptyCell)
     * @param  ypos  Le numéro de la ligne courante
     * @param  grid La grille de sudoku
     * @see         buildGrid()
     */
    public void buildFromStringArray(String[] ligneCourante, int ypos, Grid grid){ //Fonction qui permet de construire une ligne de grille
        //On prend la grille en argument pour actualiser son champ cellsToFill
        Line newLine = new Line();

        for (int i = 0; i < 9; i++) {
            int valeurCourante;

            try {
                valeurCourante = Integer.parseInt(ligneCourante[i]);
                CellBase newCell;

                if (valeurCourante == 0){ //Si la case est vide
                    EmptyCell emptyCell = new EmptyCell(i, ypos);//En deux temps pour pouvoir souscrire. Visiteur ?
                    emptyCell.subscribe(grid);
                    newCell = emptyCell;
                }
                else{ //Si la case contient une valeur
                    newCell = new Cell(i, ypos, valeurCourante);
                    grid.setCellsToFill(grid.getCellsToFill() - 1);
                }
                grid.set(newCell, i, ypos);

            } catch (NumberFormatException e) {
                System.err.println("""

                                    ERREUR
                                    Le format d'entr\u00e9e doit \u00eatre 9 lignes successives de la forme
                                    1,2,3,4,0,6,7,8,9  o\u00f9 0 repr\u00e9sente une absence de chiffre""");
                System.exit(1);
            }
        }
    }

}