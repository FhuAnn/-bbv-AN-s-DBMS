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
        String normalized = sql.trim();
        // Lấy từ đầu tiên của câu SQL (ví dụ: "SELECT * FROM..." -> "SELECT")
        String firstWord = normalized.split("\\s+")[0].toUpperCase(); 

        // Tìm trực tiếp parser trong Map, không cần duyệt List nữa
        StatementParser parser = parserMap.get(firstWord);

        if (parser != null) {
            ASTNode root = parser.parse(normalized, errorHandler);
            return new ASTBuildResult(true, root, null);
        }
        throw errorHandler.handleError(null, sql);
    }
}
