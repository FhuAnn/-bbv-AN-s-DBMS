package classes.queryprocessor;

import interfaces.ASTNode;

final class InsertStatementParser implements StatementParser {
    @Override
    public boolean supports(String normalizedSql) {
        return normalizedSql.toUpperCase().startsWith("INSERT");
    }

    @Override
    public ASTNode parse(String sql, SyntaxErrorHandler errorHandler) {
        String upper = sql.toUpperCase();
        int intoIndex = upper.indexOf("INTO ");
        int valuesIndex = upper.indexOf(" VALUES ");
        if (intoIndex < 0 || valuesIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }

        String tableName = sql.substring(intoIndex + 5, valuesIndex).trim();
        String valuesPart = sql.substring(valuesIndex + 8).trim();
        valuesPart = valuesPart.replaceFirst("^\\(", "").replaceFirst("\\);?$", "");

        if (tableName.isEmpty() || valuesPart.isEmpty()) {
            throw errorHandler.handleError(null, sql);
        }

        InsertStatementNode node = new InsertStatementNode();
        node.targetTable = new TableReferenceNode(tableName, null);
        for (String value : valuesPart.split(",")) {
            node.values.add(parseLiteral(value.trim()));
        }
        return node;
    }

    private LiteralNode parseLiteral(String token) {
        if (token.startsWith("'") && token.endsWith("'")) {
            return new LiteralNode(token.substring(1, token.length() - 1), "STRING");
        }
        if (token.matches("-?\\d+")) {
            return new LiteralNode(Integer.parseInt(token), "INT");
        }
        if (token.matches("-?\\d+\\.\\d+")) {
            return new LiteralNode(Double.parseDouble(token), "DOUBLE");
        }
        return new LiteralNode(token, "IDENTIFIER");
    }
}