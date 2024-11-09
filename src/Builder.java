import composite.*;
import java.util.Scanner;

public class Builder{

    private final Scanner input;

    public Builder (Scanner input){
        this.input = input;
    }

    public Grid buildGrid(){ //Fonction principale qui crée la grille complète à partir du Scanner
        Grid grid = Grid.getInstance();
        grid.setLines(new Line[9]);
        grid.setColumns(new Column[9]);
        grid.setSquares(new Square[9]);
        grid.setCellsToFill(81);
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


    public void buildFromStringArray(String[] ligneCourante, int ypos, Grid grid){ //Fonction qui permet de construire une ligne de grille
        //On prend la grille en argument pour actualiser son champ cellsToFill
        Line newLine = new Line();

        for (int i = 0; i < 9; i++) {
            //Check si la valeur est bien un entier pour éviter un plantage ?
            int valeurCourante = Integer.parseInt(ligneCourante[i]);
            CellBase newCell;

            if (valeurCourante == 0){ //Si la case est vide
                newCell = new EmptyCell(i, ypos);
            }
            else{ //Si la case contient une valeur
                newCell = new Cell(i, ypos, valeurCourante);
                grid.setCellsToFill(grid.getCellsToFill() - 1);
            }
            grid.set(newCell, i, ypos);
        }
    }

}