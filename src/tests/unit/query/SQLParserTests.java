package unit.query;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            
        }

        @Test
        void parseUpdate_ShouldExtractTableName() {
            
        }
    }

    @Nested
    class DeleteTests {
        @Test
        void parse_ShouldParseDeleteStatement() {
           
        }

        @Test
        void parseDelete_ShouldExtractTableName() {
           
        }
    }

    @Nested
    class ValidationTests {
        @Test
        void parse_ShouldRejectNullSql() {
          
        }

        @Test
        void parse_ShouldRejectBlankSql() {
    
        }

        @Test
        void parse_ShouldRejectUnsupportedStatement() {
            
        }

        @Test
        void getColumns_ShouldReturnUnmodifiableList() {
           
        }

        @Test
        void parsedQuery_ShouldPreserveRawSql() {
           
        }
    }
}