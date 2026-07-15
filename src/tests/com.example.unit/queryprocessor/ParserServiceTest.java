import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.ASTBuildResult;
import classes.queryprocessor.InsertStatementNode;
import classes.queryprocessor.ParserService;
import classes.queryprocessor.SelectStatementNode;

class ParserServiceTest {

    private final ParserService parserService = new ParserService();

    @Test
    @DisplayName("parserSQL - Nên parse được SELECT bất kể khoảng trắng và chữ hoa/thường")
    void parserSQL_ShouldParseSelectStatement() {
        ASTBuildResult result = parserService.parserSQL("   select * FROM Orders   ");

        assertTrue(result.success);
        assertInstanceOf(SelectStatementNode.class, result.root);

        SelectStatementNode select = (SelectStatementNode) result.root;
        assertEquals("Orders", select.getFromClause().getTableName());
        assertEquals(1, select.getProjectionList().size());
    }

    @Test
    @DisplayName("parserSQL - Nên parse được INSERT và tách đúng literal values")
    void parserSQL_ShouldParseInsertStatement() {
        ASTBuildResult result = parserService.parserSQL("insert into Orders values (1, 'north', 9.5)");

        assertTrue(result.success);
        assertInstanceOf(InsertStatementNode.class, result.root);

        InsertStatementNode insert = (InsertStatementNode) result.root;
        assertEquals("Orders", insert.getTargetTable().getTableName());
        assertEquals(3, insert.getValues().size());
        assertEquals(1, insert.getValues().get(0).getValue());
        assertEquals("north", insert.getValues().get(1).getValue());
        assertEquals(9.5d, ((Number) insert.getValues().get(2).getValue()).doubleValue(), 0.0001d);
    }

    @Test
    @DisplayName("parserSQL - Nên trả về lỗi khi câu lệnh không được hỗ trợ")
    void parserSQL_ShouldReturnFailureForUnsupportedStatement() {
        ASTBuildResult result = parserService.parserSQL("UPDATE Orders SET amount = 10");

        assertFalse(result.success);
        assertEquals(null, result.root);
        assertTrue(result.errorMessage.contains("Syntax error at line 1, col 1"));
        assertTrue(result.errorMessage.contains("UPDATE Orders SET amount = 10"));
    }

    @Test
    @DisplayName("parserSQL - Nên trả về lỗi khi SELECT thiếu FROM")
    void parserSQL_ShouldReturnFailureForMalformedSelect() {
        ASTBuildResult result = parserService.parserSQL("SELECT * Orders");

        assertFalse(result.success);
        assertEquals(null, result.root);
        assertTrue(result.errorMessage.contains("Syntax error at line 1, col 1"));
        assertTrue(result.errorMessage.contains("SELECT * Orders"));
    }
}
