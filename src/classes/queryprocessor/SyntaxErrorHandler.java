package classes.queryprocessor;

import interfaces.ISyntaxError;

public class SyntaxErrorHandler implements ISyntaxError {
    public SyntaxErrorException handleError(Object errorTokens, String rawSql) {
        return null;
    }

    public String formatErrorMessage(int line, int col) {
        return null;
    }
}
