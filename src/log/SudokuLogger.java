package log;

import java.util.logging.Logger;

public class SudokuLogger {
    private static final Logger logger = Logger.getLogger(SudokuLogger.class.getName());

    public static Logger getLogger(){
        return logger;
    }
}
