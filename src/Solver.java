import java.util.Scanner;
import Composite.*;

//Idées (voir commentaires dans code):
//Classe Printer
//Créer exception erreur d'input


public class Solver {

    //Transformer en singleton
    private int cellsToFill ;
    private Grid grid ;

    public static void main(String[] args) {

        Solver solver = new Solver();

        Scanner sc = new Scanner(System.in);

        //BUILD
        Builder builder = new Builder(sc);
        solver.grid = builder.buildGrid();

        System.out.println("\nCells to fill " + solver.grid.getCellsToFill());

        //BOUCLE "Chain of responsibility" ? :

        while(solver.grid.getCellsToFill() != 0){
            //Ne pas oublier de vérifier l'entrée de l'utilisateur avant de diminiuer cellsToFill si saisie manuelle

        }
/*
        //PRINT
        // Mettre cette boucle dans une classe Printer ? ou overkill?
        // (Peut-être utile quand on aura les classes lignes, colonnes etc...)
        System.out.println("\nLignes :\n");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(grid.getLines()[i].getTable()[j] instanceof Cell){
                    System.out.print(((Cell) grid.getLines()[i].getTable()[j]).getValue());
                }
            }
            System.out.println("");
        }
        System.out.println("\nCOLONNES :\n");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(grid.getColumns()[i].getTable()[j] instanceof Cell){
                    System.out.print(((Cell) grid.getColumns()[i].getTable()[j]).getValue());
                }
            }
            System.out.println("");
        }
        System.out.println("\nSQUARES :\n");
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(grid.getSquares()[i].getTable()[j] instanceof Cell){
                    System.out.print(((Cell) grid.getSquares()[i].getTable()[j]).getValue());
                }
            }
            System.out.println("");
        }

 */

    }
}