package classes.queryprocessor;

public class ParserService {
    private final SyntaxErrorHandler errorHandler = new SyntaxErrorHandler();

    public ASTBuildResult parserSQL(String sql) {
        try {
            String normalized = sql.trim();
            if (normalized.toUpperCase().startsWith("SELECT")) {
                return new ASTBuildResult(true, parseSelect(normalized), null);
            }
            if (normalized.toUpperCase().startsWith("INSERT")) {
                return new ASTBuildResult(true, parseInsert(normalized), null);
            }
            throw errorHandler.handleError(null, sql);
        } catch (SyntaxErrorException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        } catch (RuntimeException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        }
    }

    private SelectStatementNode parseSelect(String sql) {
        String upper = sql.toUpperCase();
        int fromIndex = upper.indexOf(" FROM ");
        if (fromIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }
        String afterFrom = sql.substring(fromIndex + 6).trim();
        String tableName = afterFrom.split("\\s+")[0].replace(";", "");
        SelectStatementNode node = new SelectStatementNode();
        node.fromClause = new TableReferenceNode(tableName, null);
        node.projectionList.add(new IdentifierNode("*"));
        return node;
    }

    private InsertStatementNode parseInsert(String sql) {
        String upper = sql.toUpperCase();
        int intoIndex = upper.indexOf("INTO ");
        int valuesIndex = upper.indexOf(" VALUES ");
        if (intoIndex < 0 || valuesIndex < 0) {
            throw errorHandler.handleError(null, sql);
        }
        String tableName = sql.substring(intoIndex + 5, valuesIndex).trim();
        String valuesPart = sql.substring(valuesIndex + 8).trim();
        valuesPart = valuesPart.replaceFirst("^\\(", "").replaceFirst("\\);?$", "");

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
