package classes.queryprocessor;

import java.util.List;

import interfaces.ASTNode;

public class ParserService {
    private final SyntaxErrorHandler errorHandler = new SyntaxErrorHandler();
    private final List<StatementParser> statementParsers = List.of(
            new SelectStatementParser(),
            new InsertStatementParser()
    );

    public ASTBuildResult parserSQL(String sql) {
        try {
            String normalized = sql.trim();
            StatementParser parser = statementParsers.stream()
                    .filter(candidate -> candidate.supports(normalized))
                    .findFirst()
                    .orElse(null);
            if (parser != null) {
                ASTNode root = parser.parse(normalized, errorHandler);
                return new ASTBuildResult(true, root, null);
            }
            throw errorHandler.handleError(null, sql);
        } catch (SyntaxErrorException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        } catch (RuntimeException ex) {
            return new ASTBuildResult(false, null, ex.getMessage());
        }
    }
}
