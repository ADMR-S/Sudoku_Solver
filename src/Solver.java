import composite.*;
import rules.DR0;
import rules.DR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Idées (voir commentaires dans code):
//Classe Printer
//Créer exception erreur d'input

public class Solver {

    //Transformer en singleton
    private int cellsToFill; // Plutôt une pile qu'un entier
    private Grid grid ;

    public static void main(String[] args) throws FileNotFoundException {

        Solver solver = new Solver();

        Scanner sc = new Scanner(new File("/home/florent/Documents/Master/Software_eng/Sudoku_Solver-main/ressources/grille_facile.txt"));

        //BUILD
        Builder builder = new Builder(sc);

        solver.grid = builder.buildGrid();

        System.out.println("\nCells to fill " + solver.grid.getCellsToFill());
        System.out.println(solver.grid);
        //BOUCLE "Chain of responsibility" ? :

        //Stack all non-empty cells in a Pile
        Pile pile_init = new Pile(81 - solver.grid.getCellsToFill());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(solver.grid.get(i, j).getValue() != 0){
                    pile_init.push(solver.grid.get(i, j));
                }
            }
        }

        DR0 dr0 = new DR0();
        while(!pile_init.isEmpty()){
            CellBase cell = pile_init.pop();
            dr0.execut(cell, solver.grid);
        }

        //Stack all EmptyCells in a Pile
        Pile pile = new Pile(solver.grid.getCellsToFill());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(solver.grid.get(i, j).getValue() == 0){
                    pile.push(solver.grid.get(i, j));
                }
            }
        }

        DR1 dr1 = new DR1();
        System.out.println("Début while\n");

        while(!pile.isEmpty()){
            //Ne pas oublier de vérifier l'entrée de l'utilisateur avant de diminiuer cellsToFill si saisie manuelle
            CellBase cell = pile.pop();

            if (dr1.execut(cell, solver.grid)) {

                Line l = solver.grid.getLine(cell.getYpos());
                Column c = solver.grid.getColumn(cell.getXpos());
                Square s = solver.grid.getSquare(cell.getXpos(), cell.getYpos());
                for (int i = 0; i < 9 ; i++) {
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
            };

        }
        System.out.println(solver.grid);



    }
}