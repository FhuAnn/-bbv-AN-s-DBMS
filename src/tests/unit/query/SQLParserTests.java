package unit.query;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.ASTBuildResult;
import classes.queryprocessor.Parser;

@DisplayName("SQL Parser Tests")

class ParserTests {

    private Parser parser;

    @BeforeEach
    void setUp() {
        parser = new Parser();
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateParser() {
            assertNotNull(parser);
        }
    }

    @Nested
    class SelectTests {
        @Test
        void parse_ShouldParseSelectStatement() {
            assertThrows(UnsupportedOperationException.class, () -> parser.parse("SELECT * FROM my_table"));
        }

        @Test
        void parseSelect_ShouldExtractTableName() {

        }

        @Test
        void parseSelect_ShouldExtractColumns() {

        }

        @Test
        void parseSelect_ShouldSupportWildcard() {

        }

        @Test
        void parseSelect_ShouldNormalizeWhitespace() {

        }

        @Test
        void parseSelect_ShouldRejectMissingFrom() {

        }

        @Test
        void parseSelect_ShouldRejectMissingColumns() {
        }

        @Test
        void parseSelect_ShouldRejectMissingTable() {

        }
    }

    @Nested
    class InsertTests {
        @Test
        void parse_ShouldParseInsertStatement() {
            ASTBuildResult result = parser.parse(
                    "INSERT INTO users (id, username) VALUES (1, 'an')");

            assertSuccessfulParse(result);
            assertAstContains(result, "INSERT");
        }

        @Test
        void parseInsert_ShouldExtractTableName() {

        }

        @Test
        void parseInsert_ShouldExtractColumns() {

        }
    }

    @Nested
    class UpdateTests {
        @Test
        void parse_ShouldParseUpdateStatement() {
            ASTBuildResult result = parser.parse(
                    "UPDATE users SET username = 'admin' WHERE id = 1");

            assertSuccessfulParse(result);
            assertAstContains(result, "UPDATE");
        }

        @Test
        void parseUpdate_ShouldExtractTableName() {

        }
    }

    @Nested
    class DeleteTests {
        @Test
        void parse_ShouldParseDeleteStatement() {
            ASTBuildResult result = parser.parse("DELETE FROM users WHERE id = 1");

            assertSuccessfulParse(result);
            assertAstContains(result, "DELETE");
        }

        @Test
        void parseDelete_ShouldExtractTableName() {

        }
    }

    @Nested
    class ValidationTests {
        @Test
        void parse_ShouldRejectNullSql() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> parser.parse(null));
        }

        @Test
        void parse_ShouldRejectBlankSql() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> parser.parse("   "));
        }

        @Test
        void parse_ShouldRejectUnsupportedStatement() {
            ASTBuildResult result = parser.parse("VACUUM users");

            assertFailedParse(result);
        }

        @Test
        void getColumns_ShouldReturnUnmodifiableList() {

        }

        @Test
        void parsedQuery_ShouldPreserveRawSql() {

        }
    }

    private void assertSuccessfulParse(ASTBuildResult result) {
        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.success),
                () -> assertNotNull(result.root),
                () -> assertTrue(
                        result.errorMessage == null
                                || result.errorMessage.isBlank()));
    }

    private void assertFailedParse(ASTBuildResult result) {
        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(!result.success),
                () -> assertNotNull(result.errorMessage),
                () -> assertTrue(!result.errorMessage.isBlank()));
    }

    private void assertAstContains(
            ASTBuildResult result,
            String expectedToken) {
        assertNotNull(result.root);

        String astText = result.root.toString().toUpperCase();

        assertTrue(
                astText.contains(expectedToken.toUpperCase()),
                () -> "AST should contain token: " + expectedToken
                        + ", but was: " + astText);
    }
}
