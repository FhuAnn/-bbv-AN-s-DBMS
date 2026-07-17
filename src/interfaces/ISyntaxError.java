package interfaces;

import classes.queryprocessor.SyntaxErrorException;

public interface ISyntaxError {
    SyntaxErrorException handleError(Object errorTokens, String rawSql);

    String formatErrorMessage(int line, int col);
}
