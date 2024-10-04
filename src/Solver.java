import java.util.Scanner;
import Composite.*;

//Idées (voir commentaires dans code):
//Classe Printer
//Créer exception erreur d'input


public class Solver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //BUILD
        Builder builder = new Builder(sc);
        Grid grid = builder.build();

        //Mettre cette boucle dans une classe Printer ? ou overkill?
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

    }
}