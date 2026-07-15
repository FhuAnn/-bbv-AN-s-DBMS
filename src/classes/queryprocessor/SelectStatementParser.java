package classes.queryprocessor;

import interfaces.ASTNode;

final class SelectStatementParser implements StatementParser {
    @Override
    public boolean supports(String normalizedSql) {
        return normalizedSql.toUpperCase().startsWith("SELECT");
    }

    @Override
    public ASTNode parse(String sql, SyntaxErrorHandler errorHandler) {
        String upper = sql.toUpperCase();
        int fromIndex = upper.indexOf(" FROM ");
        if (fromIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }
        String afterFrom = sql.substring(fromIndex + 6).trim();
        if (afterFrom.isEmpty()) {
            throw errorHandler.handleError(null, sql);
        }

        String tableName = afterFrom.split("\\s+")[0].replace(";", "");
        SelectStatementNode node = new SelectStatementNode();
        node.fromClause = new TableReferenceNode(tableName, null);
        node.projectionList.add(new IdentifierNode("*"));
        return node;
    }
}