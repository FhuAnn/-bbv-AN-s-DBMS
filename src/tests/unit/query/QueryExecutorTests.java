package unit.query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.queryprocessor.Executor;

@DisplayName("Query Executor Tests")
class QueryExecutorTests {

    private Executor executor;

    @BeforeEach
    void setUp() {
        executor = new Executor();

        executor.registerTable(
                "users",
                List.of(
                        Map.of("id", 1, "name", "An"),
                        Map.of("id", 2, "name", "Binh")));
    }

    @Nested
    class ConstructorTests {
        @Test
        void constructor_ShouldCreateExecutor() {
            assertNotNull(executor);
        }
    }

    @Nested
    class TableRegistrationTests {
        @Test
        void registerTable_ShouldStoreTable() {
            assertTrue(executor.containsTable("users"));
        }

        @Test
        void registerTable_ShouldCopyInputRows() {

        }

        @Test
        void registerTable_ShouldRejectBlankName() {

        }

        @Test
        void registerTable_ShouldRejectNullRows() {

        }
    }

    @Nested
    class SelectTests {
        @Test
        void executeSelect_ShouldReturnAllRows() {

        }

        @Test
        void executeSelect_ShouldFilterMatchingRows() {

        }

        @Test
        void executeSelect_ShouldReturnEmptyListWhenNoMatch() {

        }

        @Test
        void executeSelect_ShouldReturnUnmodifiableList() {

        }

        @Test
        void executeSelect_ShouldRejectMissingTable() {

        }
    }

    @Nested
    class InsertTests {
        @Test
        void executeInsert_ShouldAddRow() {

        }

        @Test
        void executeInsert_ShouldCopyRow() {

        }

        @Test
        void executeInsert_ShouldRejectNullRow() {

        }
    }

    @Nested
    class UpdateTests {
        @Test
        void executeUpdate_ShouldUpdateMatchingRows() {
        }

        @Test
        void executeUpdate_ShouldReturnZeroWhenNoMatch() {

        }

        @Test
        void executeUpdate_ShouldUpdateAllRowsWhenMatchValueNull() {

        }

        @Test
        void executeUpdate_ShouldRejectNullColumnName() {

        }
    }

    @Nested
    class DeleteTests {
        @Test
        void executeDelete_ShouldDeleteMatchingRows() {

        }

        @Test
        void executeDelete_ShouldReturnZeroWhenNoMatch() {

        }

        @Test
        void executeDelete_ShouldDeleteAllRowsWhenColumnNull() {

        }
    }

    @Nested
    class MetadataTests {
        @Test
        void getRowCount_ShouldReturnCorrectCount() {

        }

        @Test
        void containsTable_ShouldReturnFalseForMissingTable() {

        }

        @Test
        void getTables_ShouldReturnUnmodifiableMap() {

        }

        @Test
        void getTables_ShouldProtectNestedRows() {

        }
    }
}
