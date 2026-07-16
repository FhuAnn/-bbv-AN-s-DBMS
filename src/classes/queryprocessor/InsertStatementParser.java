package classes.queryprocessor;

import interfaces.ASTNode;

final class InsertStatementParser implements StatementParser {
    @Override
    public boolean supports(String normalizedSql) {
        return false;
    }

    @Override
    public ASTNode parse(String sql, SyntaxErrorHandler errorHandler) {
        return null;
    }

    private LiteralNode parseLiteral(String token) {
        return null;
    }
}