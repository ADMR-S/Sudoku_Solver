package rules;
import composite.*;
import rules.*;
import pile.Pile;

public class Context{
    private DeductionRule strategy;
    private Grid grid;

    public Context(DeductionRule strategy, Grid grid){
        this.strategy = strategy;
        this.grid = grid;
    }

    public DeductionRule getStrategy() {
        return strategy;
    }

    public void setStrategy(DeductionRule strategy) {
        this.strategy = strategy;
    }

    public void solve(){
        Pile p;
        if (strategy instanceof DR0){
            p = new Pile(81-grid.getCellsToFill());
            p.stackAllNonEmpty(grid);
        }
        else{
            p = new Pile(grid.getCellsToFill());
            p.stackAllEmpty(grid);
        }
        strategy.routine(p, grid);
    }
}