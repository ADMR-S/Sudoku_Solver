import java.util.Scanner;
import Composite.*;

public class Builder{

    private Scanner input;

    public Builder (Scanner input){
        this.input = input;
    }

    public int[][] build(){
        int[][] tab = new int[9][9];

        //BUILDER Brute force :
        for (int i = 0; i < 9; i++) {
            if (input.hasNextLine()) {
                String[] ligne = input.nextLine().split(",");
                if(ligne.length != 9){
                    //Créer une exception à appeler en cas d'erreur d'input ?
                    System.err.println("\nERREUR\n" +
                            "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                            "1,2,3,4,0,6,7,8,9  où 0 représente une absence de chiffre");
                    System.exit(1);
                }
                for (int j = 0; j < 9; j++) {
                    tab[i][j] = Integer.parseInt(ligne[j]);
                }
            }
            else {
                System.err.println("\nERREUR\n" +
                        "Le format d'entrée doit être 9 lignes successives de la forme\n" +
                        "1,2,3,4,0,6,7,8,9  où 0 représente une absence de chiffre");
                System.exit(1);
            }
        }
        return tab;
    }

    public EmptyCell createEmptyCell(int x, int y){
        int[] possibleValues = new int[]{1,2,3,4,5,6,7,8,9};
        EmptyCell cell = new EmptyCell(x, y, possibleValues);
        return cell;
    }
}