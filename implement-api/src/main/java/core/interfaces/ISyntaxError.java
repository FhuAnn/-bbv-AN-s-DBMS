package core.interfaces;

import core.classes.queryprocessor.SyntaxErrorException;

public interface ISyntaxError {
    SyntaxErrorException handleError(Object errorTokens, String rawSql);

    String formatErrorMessage(int line, int col);
}
