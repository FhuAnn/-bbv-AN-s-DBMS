package classes.queryprocessor;

import interfaces.ISyntaxError;

public class SyntaxErrorHandler implements ISyntaxError {
    public SyntaxErrorException handleError(Object errorTokens, String rawSql) {
        return new SyntaxErrorException(formatErrorMessage(1, 1) + ": " + rawSql);
    }

    public String formatErrorMessage(int line, int col) {
        return "Syntax error at line " + line + ", col " + col;
    }
}
