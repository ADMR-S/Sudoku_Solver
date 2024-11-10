package strategy;
import composite.cell.*;

import composite.*;

public interface Visitor {
    boolean visitEmptyCell(EmptyCell cell, Grid grid);
    boolean visitCell(Cell cell, Grid grid);
}