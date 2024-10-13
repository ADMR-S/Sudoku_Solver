import composite.*;
import java.util.Scanner;

//Idées (voir commentaires dans code):
//Classe Printer
//Créer exception erreur d'input

public class Solver {

    //Transformer en singleton
    private int cellsToFill; // Plutôt une pile qu'un entier
    private Grid grid ;

    public static void main(String[] args) {

        Solver solver = new Solver();

        Scanner sc = new Scanner(System.in);

        //BUILD
        Builder builder = new Builder(sc);
        solver.grid = builder.buildGrid();

        System.out.println("\nCells to fill " + solver.grid.getCellsToFill());
        System.out.println(solver.grid);
        //BOUCLE "Chain of responsibility" ? :
        
        while(solver.grid.getCellsToFill() != 0){
            //Ne pas oublier de vérifier l'entrée de l'utilisateur avant de diminiuer cellsToFill si saisie manuelle
            break;
        }
    }
}