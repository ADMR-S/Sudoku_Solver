import composite.*;
import rules.DR0;
import rules.DR1;
import rules.DR2;
import rules.DR3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Idées (voir commentaires dans code):
//Classe Printer
//Créer exception erreur d'input

public class Solver {

    //Transformer en singleton
    private int cellsToFill; // Plutôt une pile qu'un entier
    private Grid grid;

    public static void main(String[] args) throws FileNotFoundException{

        for(int k = 0; k<args.length; k++) {
            final Scanner sc = new Scanner(new File(args[k]));

            //BUILD
            Builder builder = new Builder(sc);

            Solver solver = new Solver();

            solver.grid = builder.buildGrid();

            System.out.println("\nCells to fill " + solver.grid.getCellsToFill());
            System.out.println(solver.grid);
            //BOUCLE "Chain of responsibility" ? :

            //Stack all non-empty cells in a Pile
            Pile pile_init = new Pile(81 - solver.grid.getCellsToFill());
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (solver.grid.get(i, j).getValue() != 0) {
                        pile_init.push(solver.grid.get(i, j));
                    }
                }
            }


            DR0 dr0 = new DR0();
            while (!pile_init.isEmpty()) {
                CellBase cell = pile_init.pop();
                dr0.execut(cell, solver.grid);
            }

            solver.solveWithDR1();

            if (solver.grid.getCellsToFill() == 0) {
                System.out.println(solver.grid);
                System.out.println("Sudoku résolu en difficulté facile.");
                return;
            }

            solver.solveWithDR2();

            if (solver.grid.getCellsToFill() == 0) {
                System.out.println(solver.grid);
                System.out.println("Sudoku résolu en difficulté moyenne.");
                return;
            }

            solver.solveWithDR3();

            if (solver.grid.getCellsToFill() == 0) {
                System.out.println(solver.grid);
                System.out.println("Sudoku résolu en difficulté difficile.");
                return;
            }

            System.out.println("TRES DIFFICILE.");

            //Implémenter la demande à l'utilisateur.
            Scanner saisie = new Scanner(System.in);

            while (solver.grid.getCellsToFill() > 0) {

                System.out.println(solver.grid);

                System.out.println("Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                int x = saisie.nextInt();
                int y = saisie.nextInt();
                while (solver.grid.get(x, y).getValue() != 0) {
                    System.out.println("Cette cellule n'est pas vide. Entrez les coordonnées de la cellule vide que vous voulez remplir (x y) : ");
                    x = saisie.nextInt();
                    y = saisie.nextInt();
                }

                if (solver.grid.get(x, y) instanceof EmptyCell cell && cell.numberPossibleValue() == 0) {
                    System.out.println("Cette cellule n'a pas de valeur possible, une mauvaise valeur à été rentré veuillez relancer le solver.");
                    return;
                }

                System.out.print("Les valeurs possible pour cette cellule sont : ");
                for (int i = 0; i < 9; i++) {
                    if (solver.grid.get(x, y).checkPossibleValue(i + 1)) {
                        System.out.print(i + 1 + " ");
                    }
                }
                System.out.println();
                System.out.println("Entrez la valeur que vous voulez mettre dans cette cellule : ");
                int value = saisie.nextInt();
                while (value < 1 || value > 9 || !solver.grid.get(x, y).checkPossibleValue(value)) {
                    System.out.println("La valeur doit être comprise entre 1 et 9 et être une valeur possible pour la cellule. Entrez la valeur que vous voulez mettre dans cette cellule : ");
                    value = saisie.nextInt();
                }

                Cell c_new = new Cell(x, y, value);
                DR0 r = new DR0();
                r.execut(c_new, solver.grid);
                solver.grid.set(c_new, x, y);
                solver.grid.setCellsToFill(solver.grid.getCellsToFill() - 1);

                solver.solveWithDR3();
            }


            System.out.println(solver.grid.getCellsToFill());
            System.out.println("Sudoku résolu avec une difficulté très difficiles.");
            System.out.println(solver.grid);
        }
    }

    Pile stackAllEmpty() {//Stack all EmptyCells in a Pile
        Pile pile = new Pile(this.grid.getCellsToFill());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid.get(i, j).getValue() == 0) {
                    pile.push(this.grid.get(i, j));
                }
            }
        }
        return pile;
    }

    void pushAllRelated(CellBase cell, Pile pile) { // push on the stack all EmptyCell in the same Line/Column/Square
        Line l = this.grid.getLine(cell.getYpos());
        Column c = this.grid.getColumn(cell.getXpos());
        Square s = this.grid.getSquare(cell.getXpos(), cell.getYpos());
        for (int i = 0; i < 9; i++) {
            if (l.get(i) instanceof EmptyCell empty_cell) {
                pile.push(empty_cell);
            }
            if (c.get(i) instanceof EmptyCell empty_cell) {
                pile.push(empty_cell);
            }
            if (s.get(i) instanceof EmptyCell empty_cell) {
                pile.push(empty_cell);
            }
        }
    }

    void solveWithDR1() {

        Pile pile = stackAllEmpty();

        DR1 dr1 = new DR1();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (dr1.execut(cell, this.grid)) {
                pushAllRelated(cell, pile);
            }
        }
    }

    void solveWithDR2() {

        Pile pile = stackAllEmpty();

        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (dr1.execut(cell, this.grid)) {
                System.out.println("DR1.");
                pushAllRelated(cell, pile);
            } else if (dr2.execut(cell, this.grid)) {
                System.out.println("DR2.");
                pushAllRelated(cell, pile);
            }
        }
    }

    void solveWithDR3() {
        Pile pile = stackAllEmpty();

        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();
        DR3 dr3 = new DR3();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (dr3.execut(cell, this.grid)) {
                System.out.println("DR3.");
                pushAllRelated(cell, pile);
            } else if (dr1.execut(cell, this.grid)) {
                System.out.println("DR1.");
                pushAllRelated(cell, pile);
            } else if (dr2.execut(cell, this.grid)) {
                System.out.println("DR2.");
                pushAllRelated(cell, pile);
            }
        }
    }

    boolean checkGrid() {
        for (int i = 0; i < 9; i++) {
            Line l = this.grid.getLine(i);
            Column c = this.grid.getColumn(i);
            Square s = this.grid.getSquare(i/3, i%3);
            if (!checkComposants(l)) return false;
            if (!checkComposants(c)) return false;
            if (!checkComposants(s)) return false;
        }
        return true;
    }

    boolean checkComposants(Composant c1) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (i != j && c1.get(i).getValue() != 0 && c1.get(j).getValue() != 0 && c1.get(i).getValue() == c1.get(j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}