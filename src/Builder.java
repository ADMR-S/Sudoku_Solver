import java.util.Scanner;
import Composite.*;

public class Builder{

    private Scanner input;

    public Builder (Scanner input){
        this.input = input;
    }

    public Grid build(){
        CellBase[][] tab = new CellBase[9][9];
        Grid grid = new Grid();

        //BUILDER Brute force :
        for (int i = 0; i < 9; i++) {
            if (input.hasNextLine()) {
                String[] ligneCourante = input.nextLine().split(",");
                Line newLine = new Line();
                if(ligneCourante.length != 9){
                    //Créer une exception à appeler en cas d'erreur d'input ?
                    System.err.println("\nERREUR\n" +
                            "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                            "1,2,3,4,0,6,7,8,9  (par exemple) où 0 représente une absence de chiffre");
                    System.exit(1);
                }
                for (int j = 0; j < 9; j++) {
                    int valeurCourante = Integer.parseInt(ligneCourante[j]);
                    CellBase newCell;

                    if (valeurCourante == 0){ //Si la case est vide
                        newCell = createEmptyCell(i, j);
                    }
                    else{ //Si la case contient une valeur
                        newCell = new Cell(i, j, valeurCourante);
                    }
                    newLine.setTableCell(newCell, j);
                    tab[i][j] = newCell;
                }
                grid.setLineValue(newLine, i);
            }
            else {
                System.err.println("\nERREUR\n" +
                        "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                        "1,2,3,4,0,6,7,8,9  où 0 représente une absence de chiffre");
                System.exit(1);
            }
        }
        return grid;
    }

    public EmptyCell createEmptyCell(int x, int y){
        int[] possibleValues = new int[]{1,2,3,4,5,6,7,8,9};
        EmptyCell cell = new EmptyCell(x, y, possibleValues);
        return cell;
    }
}