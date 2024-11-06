import composite.*;
import rules.DR0;
import rules.DR1;
import rules.DR2;

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

    public static void main(String[] args) throws FileNotFoundException {


        Scanner sc = new Scanner(new File("/home/florent/Documents/Master/Software_eng/Sudoku_Solver-main/ressources/grille_facile.txt"));

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

        if(solver.grid.getCellsToFill()==0) {
            System.out.println(solver.grid);
            System.out.println("Sudoku résolu en difficulté facile.");
            return;
        }

        solver.solveWithDR2();

        if(solver.grid.getCellsToFill()==0) {
            System.out.println(solver.grid);
            System.out.println("Sudoku résolu en difficulté moyenne.");
            return;
        }


        System.out.println(solver.grid.getCellsToFill());
    }


    void solveWithDR1() {

        //Stack all EmptyCells in a Pile
        Pile pile = new Pile(this.grid.getCellsToFill());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid.get(i, j).getValue() == 0) {
                    pile.push(this.grid.get(i, j));
                }
            }
        }

        DR1 dr1 = new DR1();

        while (!pile.isEmpty()) {
            //Ne pas oublier de vérifier l'entrée de l'utilisateur avant de diminiuer cellsToFill si saisie manuelle
            CellBase cell = pile.pop();

            if (dr1.execut(cell, this.grid)) {
                pushAllRelated(cell, pile);
            }
        }
    }

    void solveWithDR2() {

        //Stack all EmptyCells in a Pile
        Pile pile = new Pile(this.grid.getCellsToFill());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.grid.get(i, j).getValue() == 0) {
                    pile.push(this.grid.get(i, j));
                }
            }
        }

        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();

        while (!pile.isEmpty()) {
            //Ne pas oublier de vérifier l'entrée de l'utilisateur avant de diminiuer cellsToFill si saisie manuelle
            CellBase cell = pile.pop();

            if (dr1.execut(cell, this.grid) || dr2.execut(cell, this.grid)) {
                pushAllRelated(cell, pile);
            }
        }
    }

    void pushAllRelated(CellBase cell, Pile pile) {
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
}