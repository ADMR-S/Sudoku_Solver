package strategy;
import composite.*;
import strategy.rules.DR0;
import strategy.rules.DeductionRule;

public class Context{
    /**
     * La stratégie actuelle utilisée par le context
     */
    private DeductionRule strategy;
    /**
     * La grille de jeu
     */
    private Grid grid;

    /**
     * Constructeur de la classe Context
     * @param strategy la stratégie à utiliser
     * @param grid la grille de jeu
     */
    public Context(DeductionRule strategy, Grid grid){
        this.strategy = strategy;
        this.grid = grid;
    }

    /**
     * Retourne la deduction rule utiliser par le context
     * @return DeductionRule la deduction rule utiliser par le context
     */
    public DeductionRule getStrategy() {
        return strategy;
    }

    /**
     * Set la deduction rule utiliser par le context
     * @param strategy la deduction rule à set
     */
    public void setStrategy(DeductionRule strategy) {
        this.strategy = strategy;
    }


    /**
     * Fonction principale de résolution qui appelle la stratégie actuelle sur la pile remplie de manière appropriée
     */
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