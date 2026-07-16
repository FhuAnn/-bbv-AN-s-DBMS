package classes.queryprocessor;

import interfaces.ASTNode;

final class SelectStatementParser implements StatementParser {
    @Override
    public boolean supports(String normalizedSql) {
        return false;
    }

    @Override
    public ASTNode parse(String sql, SyntaxErrorHandler errorHandler) {
        return null;
    }
}