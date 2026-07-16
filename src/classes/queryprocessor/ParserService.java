package classes.queryprocessor;

import java.util.List;
import java.util.Map;

import interfaces.ASTNode;

public class ParserService {
    private final SyntaxErrorHandler errorHandler = new SyntaxErrorHandler();

private final Map<String, StatementParser> parserMap = Map.of(
        "SELECT", new SelectStatementParser(),
        "INSERT", new InsertStatementParser()
    );
    public ASTBuildResult parserSQL(String sql) {
        return null;
    }
}
