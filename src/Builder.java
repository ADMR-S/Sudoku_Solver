import java.util.Scanner;
import Composite.*;

public class Builder{

    private Scanner input;

    public Builder (Scanner input){
        this.input = input;
    }

    public Grid build(){ //Fonction principale qui crée la grille à partir du Scanner
        Grid grid = new Grid();

        //BUILDER Brute force :
        for (int i = 0; i < 9; i++) {
            if (input.hasNextLine()) {
                String[] ligneCourante = input.nextLine().split(",");
                if(ligneCourante.length != 9){
                    //Créer une exception à appeler en cas d'erreur d'input ?
                    System.err.println("\nERREUR\n" +
                            "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                            "1,2,3,4,0,6,7,8,9  (par exemple) où 0 représente une absence de chiffre");
                    System.exit(1);
                }
                Line newLine = buildLineFromStringArray(ligneCourante, i);
                grid.setLineValue(newLine, i);
            }
            else {
                System.err.println("\nERREUR\n" +
                        "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                        "1,2,3,4,0,6,7,8,9  où 0 représente une absence de chiffre");
                System.exit(1);
            }
        }
        buildColumnsKnowingLines(grid);
        return grid;
    }

    public EmptyCell createEmptyCell(int x, int y){ //Possibilité de déplacer dans le constructeur de EmptyCell
        int[] possibleValues = new int[]{1,2,3,4,5,6,7,8,9};
        EmptyCell cell = new EmptyCell(x, y, possibleValues);
        return cell;
    }

    public Line buildLineFromStringArray(String[] ligneCourante, int ypos){ //Fonction qui permet de construire une ligne de grille
        Line newLine = new Line();

        for (int j = 0; j < 9; j++) {
            //Check si la valeur est bien un entier pour éviter un plantage ?
            int valeurCourante = Integer.parseInt(ligneCourante[j]);
            CellBase newCell;

            if (valeurCourante == 0){ //Si la case est vide
                newCell = createEmptyCell(ypos, j);
            }
            else{ //Si la case contient une valeur
                newCell = new Cell(ypos, j, valeurCourante);
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
}