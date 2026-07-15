package classes.queryprocessor;

import interfaces.ASTNode;

interface StatementParser {
    boolean supports(String normalizedSql);

    ASTNode parse(String sql, SyntaxErrorHandler errorHandler);
}