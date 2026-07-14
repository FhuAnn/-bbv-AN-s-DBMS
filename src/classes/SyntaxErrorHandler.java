package classes;

public class SyntaxErrorHandler {
    SyntaxErrorException handleError(Object errorTokens, String rawSql) {
        return new SyntaxErrorException(formatErrorMessage(1, 1) + ": " + rawSql);
    }

    String formatErrorMessage(int line, int col) {
        return "Syntax error at line " + line + ", col " + col;
    }
}
