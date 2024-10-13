import composite.*;
import java.util.Scanner;

public class Builder{

    private final Scanner input;

    public Builder (Scanner input){
        this.input = input;
    }

    public Grid buildGrid(){ //Fonction principale qui crée la grille complète à partir du Scanner
        Grid grid = new Grid();

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
                Line newLine = buildLineFromStringArray(ligneCourante, i, grid); //Envisager de trouver comment actualiser cellsToFill sans passer la grille en argument
                grid.setLineValue(newLine, i);
            }
            else {
                System.err.println("""

                                    ERREUR
                                    Le format d'entr\u00e9e doit \u00eatre 9 lignes successives de la forme
                                    1,2,3,4,0,6,7,8,9  o\u00f9 0 repr\u00e9sente une absence de chiffre""");
                System.exit(1);
            }
        }
        buildColumnsKnowingLines(grid);
        buildSquaresKnowingLines(grid);
        return grid;
    }


    public Line buildLineFromStringArray(String[] ligneCourante, int ypos, Grid grid){ //Fonction qui permet de construire une ligne de grille
        //On prend la grille en argument pour actualiser son champ cellsToFill
        Line newLine = new Line();

        for (int j = 0; j < 9; j++) {
            //Check si la valeur est bien un entier pour éviter un plantage ?
            int valeurCourante = Integer.parseInt(ligneCourante[j]);
            CellBase newCell;

            if (valeurCourante == 0){ //Si la case est vide
                newCell = new EmptyCell(ypos, j);
            }
            else{ //Si la case contient une valeur
                newCell = new Cell(ypos, j, valeurCourante);
                grid.setCellsToFill(grid.getCellsToFill() - 1);
            }
            newLine.setTableCell(newCell, j);
        }
        return newLine;
    }

    public void buildColumnsKnowingLines(Grid grid){ //Fonction qui construit toutes les colonnes de la grille à partir des lignes
        Line[] lines = grid.getLines();

        for(int i=0; i<9; i++){
            Column currentColumn = new Column();
            for(int j=0; j<9; j++){
                currentColumn.setTableCell(lines[j].getTable()[i], j);
            }
            grid.setColumnValue(currentColumn, i);
        }
    }

    public void buildSquaresKnowingLines(Grid grid){
        //Fonction qui construit toutes les carrés de la grille à partir des lignes
        // Part d'en haut à gauche et va vers en bas à droite (1 = en haut à gauche, 2 = en haut au milieu etc...)
        Line[] lines = grid.getLines();

        for (int i=0; i<9; i++){
            Square currentSquare = new Square();
            int resteI = i/3;
            
            for(int j=0; j<9; j++){
                int resteJ = j/3;
                currentSquare.setTableCell(lines[3*resteI+resteJ].getTable()[j%3+3*(i%3)], j);
            }
            grid.setSquareValue(currentSquare, i);
        }
    }
}