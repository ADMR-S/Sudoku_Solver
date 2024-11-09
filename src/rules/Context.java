import composite.*;
import rules.*;

public class Context{
    private DeductionRule strategy;
    private Grid grid;
    private Pile pile;

    public Context(DeductionRule strategy, Grid grid){
        this.strategy = strategy;
        this.grid = grid;
        this.pile = new Pile(grid.getCellsToFill());
        pile.stackAllEmpty(grid);
    }

    public DeductionRule getStrategy() {
        return strategy;
    }

    public void setStrategy(DeductionRule strategy) {
        this.strategy = strategy;
    }

    void solve(){
        if (strategy instanceof DR1){
            solveWithDR1();
        }
        else if (strategy instanceof DR2){
            solveWithDR2();
        }
        else {
            solveWithDR3();
        }
    }

    void solveWithDR1() {

        pile.stackAllEmpty(grid);
        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (strategy.execut(cell, grid)) {
                pile.pushAllRelated(cell, grid);
            }
        }
    }

    void solveWithDR2() {

        pile.stackAllEmpty(grid);

        DR1 dr1 = new DR1();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (dr1.execut(cell, grid)) {
                System.out.println("DR1.");
                pile.pushAllRelated(cell, grid);
            } else if (strategy.execut(cell, this.grid)) {
                System.out.println("DR2.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }

    void solveWithDR3() {

        pile.stackAllEmpty(grid);
        DR1 dr1 = new DR1();
        DR2 dr2 = new DR2();

        while (!pile.isEmpty()) {
            CellBase cell = pile.pop();

            if (strategy.execut(cell, grid)) {
                System.out.println("DR3.");
                pile.pushAllRelated(cell, grid);
            } else if (dr1.execut(cell, grid)) {
                System.out.println("DR1.");
                pile.pushAllRelated(cell, grid);
            } else if (dr2.execut(cell, grid)) {
                System.out.println("DR2.");
                pile.pushAllRelated(cell, grid);
            }
        }
    }
}