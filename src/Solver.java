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
        int[][] tab = builder.build();

        //Mettre cette boucle dans une classe Printer ? ou overkill?
        // (Peut-être utile quand on aura les classes lignes, colonnes etc...)
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(tab[i][j]);
            }
            System.out.println("");
        }

    }
}