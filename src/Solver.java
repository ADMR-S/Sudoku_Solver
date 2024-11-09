import composite.*;
import rules.DR0;
import rules.DR1;
import rules.DR2;
import rules.DR3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Idées (voir commentaires dans code):
//Gérer crash si mauvaise entrée utilisateur (input mismatch). Ex : String au lieu de int
//Essayer de faire gestion propre des erreurs (créer des exceptions, faire des try catch, print sur la sortie d'erreur...)

public class Solver {

    //Transformer en singleton
    private int cellsToFill; // Plutôt une pile qu'un entier

    public static void main(String[] args) throws FileNotFoundException {

        try {
            for (int k = 0; k < args.length; k++) {//Chaque argument est un fichier .txt qui contient une grille
                System.out.println(args[k]);
                final Scanner sc = new Scanner(new File(args[k]));

                //BUILD
                Builder builder = new Builder(sc);

                Solver solver = new Solver();

                Grid grid = builder.buildGrid();

                System.out.println("\nCells to fill " + grid.getCellsToFill());
                System.out.println(grid);
                //BOUCLE "Chain of responsibility" ? :

                Context ctx = new Context(new DR0(), grid);
                ctx.solve();

                ctx.setStrategy(new DR1());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté facile.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    continue;
                }

                ctx.setStrategy(new DR2());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté moyenne.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    continue;
                }

                ctx.setStrategy(new DR3());
                ctx.solve();

                if (grid.getCellsToFill() == 0) {
                    System.out.println(grid);
                    System.out.println("Sudoku résolu en difficulté difficile.");
                    System.out.println("Grille valide : " + grid.checkValidity());
                    continue;
                }

                System.out.println("TRES DIFFICILE.");

                Memento memento = grid.makeSnapshot();

                //Implémenter la demande à l'utilisateur.
                Scanner saisie = new Scanner(System.in);

                while (grid.getCellsToFill() > 0) {

                    System.out.println(grid);

                    System.out.println("Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                    int x = saisie.nextInt();
                    int y = saisie.nextInt();
                    while (grid.get(x, y).getValue() != 0) {
                        System.out.println("Cette cellule n'est pas vide. Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                        x = saisie.nextInt();
                        y = saisie.nextInt();
                    }

                    if (grid.get(x, y) instanceof EmptyCell cell && cell.numberPossibleValue() == 0) {
                        System.out.println("Cette cellule n'a pas de valeur possible, une mauvaise valeur à été rentré veuillez relancer le solver.");
                        System.out.println("Appuyez sur une touche pour recharger l'état de la grille avant la première saisie manuelle ou quittez le programme avec Ctrl+C.");
                        grid.restore(memento);
                        memento = grid.makeSnapshot(); //On refait une copie p
                        saisie.next();// our le prochain reload
                    }
                    else {
                        System.out.print("Les valeurs possible pour cette cellule sont : ");
                        for (int i = 0; i < 9; i++) {
                            if (grid.get(x, y).checkPossibleValue(i + 1)) {
                                System.out.print(i + 1 + " ");
                            }
                        }
                        System.out.println();
                        System.out.println("Entrez la valeur que vous voulez mettre dans cette cellule : ");
                        int value = saisie.nextInt();
                        while (value < 1 || value > 9 || !grid.get(x, y).checkPossibleValue(value)) {
                            System.out.println("La valeur doit être comprise entre 1 et 9 et être une valeur possible pour la cellule. Entrez la valeur que vous voulez mettre dans cette cellule : ");
                            value = saisie.nextInt();
                        }

                        Cell c_new = new Cell(x, y, value);
                        DR0 r = new DR0();
                        r.execut(c_new, grid);
                        grid.set(c_new, x, y);
                        grid.setCellsToFill(grid.getCellsToFill() - 1);

                        ctx.solve();
                    }
                }


                System.out.println(grid.getCellsToFill());
                System.out.println("Sudoku résolu avec une difficulté très difficiles.");
                System.out.println(grid);
                System.out.println("Grille valide : " + grid.checkValidity());
            }
            return;
        }
        catch(FileNotFoundException e){
            System.err.println("ERREUR : Fichier d'entrée non trouvé.");
            System.err.println("Exemple d'utilisation : ");
            System.err.println("./Solver grille1.txt grille2.txt");
            System.err.println("Veuillez relancer le programme avec des fichiers valides");
            e.printStackTrace();
        }
    }


}