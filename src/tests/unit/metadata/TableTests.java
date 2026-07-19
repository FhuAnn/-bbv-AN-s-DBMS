package unit.metadata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.metadata.ColumnMetadata;
import classes.metadata.Constraint;
import classes.metadata.Index;
import classes.metadata.Row;
import classes.metadata.Table;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.UUID;

@DisplayName("Table Unit Tests")
class TableTests {

    private Table table;
    private UUID schemaId;
    private ColumnMetadata idColumn;
    private ColumnMetadata nameColumn;
    private Row firstRow;
    private Constraint primaryKey;
    private Index emailIndex;

    @BeforeEach
    void setUp() {
        schemaId = UUID.randomUUID();
        table = new Table("users", schemaId);

        idColumn = mock(ColumnMetadata.class);
        nameColumn = mock(ColumnMetadata.class);
        firstRow = mock(Row.class);
        primaryKey = mock(Constraint.class);
        emailIndex = mock(Index.class);

        when(idColumn.getName()).thenReturn("id");
        when(nameColumn.getName()).thenReturn("name");
        when(firstRow.getId()).thenReturn(UUID.randomUUID());
        when(primaryKey.getName()).thenReturn("pk_users");
        when(emailIndex.getName()).thenReturn("idx_users_email");
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void constructor_ShouldCreateTable() {
            assertNotNull(table);
        }

        @Test
        void constructor_ShouldGenerateTableId() {
            assertNotNull(table.getId());
        }

        @Test
        void constructor_ShouldGenerateUniqueTableIds() {
            Table secondTable = new Table("roles", schemaId);

            assertNotEquals(table.getId(), secondTable.getId());
        }

        @Test
        void constructor_ShouldInitializeSchemaId() {
            assertEquals(schemaId, table.getSchemaId());
        }

        @Test
        void constructor_ShouldInitializeEmptyColumns() {
            assertNotNull(table.getColumns());
            assertTrue(table.getColumns().isEmpty());
            assertEquals(0, table.getColumnCount());
        }

        @Test
        void constructor_ShouldInitializeEmptyRows() {
            assertNotNull(table.getRows());
            assertTrue(table.getRows().isEmpty());
            assertEquals(0, table.getRowCount());
        }

        @Test
        void constructor_ShouldInitializeEmptyConstraints() {
            assertNotNull(table.getConstraints());
            assertTrue(table.getConstraints().isEmpty());
            assertEquals(0, table.getConstraintCount());
        }

        @Test
        void constructor_ShouldInitializeEmptyIndexes() {
            assertNotNull(table.getIndexes());
            assertTrue(table.getIndexes().isEmpty());
            assertEquals(0, table.getIndexCount());
        }
    }

    @Nested
    @DisplayName("Name Tests")
    class NameTests {

        @Test
        void getName_ShouldReturnTableName() {
            assertEquals("users", table.getName());
        }

        @Test
        void rename_ShouldChangeTableName() {
            table.rename("customers");

            assertEquals("customers", table.getName());
        }

        @Test
        void rename_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.rename(null)
            );
        }

        @Test
        void rename_ShouldRejectBlankName() {
            assertAll(
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> table.rename("")
                    ),
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> table.rename("   ")
                    )
            );
        }
    }

    @Nested
    @DisplayName("Column Management Tests")
    class ColumnManagementTests {

        @Test
        void addColumn_ShouldRegisterColumn() {
            table.addColumn(idColumn);

            assertSame(idColumn, table.getColumn("id"));
            assertTrue(table.containsColumn("id"));
        }

        @Test
        void addColumn_ShouldIncreaseColumnCount() {
            table.addColumn(idColumn);
            table.addColumn(nameColumn);

            assertEquals(2, table.getColumnCount());
        }

        @Test
        void addColumn_ShouldRejectNullColumn() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addColumn(null)
            );
        }

        @Test
        void addColumn_ShouldRejectDuplicateColumnName() {
            ColumnMetadata duplicateColumn = mock(ColumnMetadata.class);
            when(duplicateColumn.getName()).thenReturn("id");

            table.addColumn(idColumn);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addColumn(duplicateColumn)
            );
        }

        @Test
        void getColumn_ShouldReturnExistingColumn() {
            table.addColumn(nameColumn);

            ColumnMetadata result = table.getColumn("name");

            assertSame(nameColumn, result);
        }

        @Test
        void getColumn_ShouldReturnNullForMissingColumn() {
            assertNull(table.getColumn("missing"));
        }

        @Test
        void containsColumn_ShouldReturnTrueForExistingColumn() {
            table.addColumn(idColumn);

            assertTrue(table.containsColumn("id"));
        }

        @Test
        void containsColumn_ShouldReturnFalseForMissingColumn() {
            assertFalse(table.containsColumn("missing"));
        }

        @Test
        void removeColumn_ShouldRemoveExistingColumn() {
            table.addColumn(idColumn);

            ColumnMetadata removed = table.removeColumn("id");

            assertSame(idColumn, removed);
            assertFalse(table.containsColumn("id"));
            assertEquals(0, table.getColumnCount());
        }

        @Test
        void removeColumn_ShouldReturnNullForMissingColumn() {
            assertNull(table.removeColumn("missing"));
        }

        @Test
        void getColumns_ShouldReturnUnmodifiableCollection() {
            table.addColumn(idColumn);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> table.getColumns().clear()
            );
        }
    }

    @Nested
    @DisplayName("Row Management Tests")
    class RowManagementTests {

        @Test
        void insertRow_ShouldInsertRow() {
            Row inserted = table.insertRow(firstRow);

            assertSame(firstRow, inserted);
            assertSame(firstRow, table.getRow(firstRow.getId()));
        }

        @Test
        void insertRow_ShouldIncreaseRowCount() {
            Row secondRow = mock(Row.class);
            when(secondRow.getId()).thenReturn(UUID.randomUUID());

            table.insertRow(firstRow);
            table.insertRow(secondRow);

            assertEquals(2, table.getRowCount());
        }

        @Test
        void insertRow_ShouldRejectNullRow() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.insertRow(null)
            );
        }

        @Test
        void getRow_ShouldReturnExistingRow() {
            table.insertRow(firstRow);

            Row result = table.getRow(firstRow.getId());

            assertSame(firstRow, result);
        }

        @Test
        void getRow_ShouldReturnNullForMissingRow() {
            assertNull(table.getRow(UUID.randomUUID()));
        }

        @Test
        void updateRow_ShouldReplaceExistingRow() {
            UUID rowId = firstRow.getId();
            Row replacement = mock(Row.class);
            when(replacement.getId()).thenReturn(rowId);

            table.insertRow(firstRow);

            Row updated = table.updateRow(rowId, replacement);

            assertSame(replacement, updated);
            assertSame(replacement, table.getRow(rowId));
            assertEquals(1, table.getRowCount());
        }

        @Test
        void updateRow_ShouldReturnNullForMissingRow() {
            UUID missingRowId = UUID.randomUUID();
            Row replacement = mock(Row.class);
            when(replacement.getId()).thenReturn(missingRowId);

            assertNull(table.updateRow(missingRowId, replacement));
        }

        @Test
        void deleteRow_ShouldRemoveExistingRow() {
            table.insertRow(firstRow);

            Row deleted = table.deleteRow(firstRow.getId());

            assertSame(firstRow, deleted);
            assertNull(table.getRow(firstRow.getId()));
        }

        @Test
        void deleteRow_ShouldDecreaseRowCount() {
            Row secondRow = mock(Row.class);
            when(secondRow.getId()).thenReturn(UUID.randomUUID());

            table.insertRow(firstRow);
            table.insertRow(secondRow);

            table.deleteRow(firstRow.getId());

            assertEquals(1, table.getRowCount());
        }

        @Test
        void deleteRow_ShouldReturnNullForMissingRow() {
            assertNull(table.deleteRow(UUID.randomUUID()));
        }

        @Test
        void truncate_ShouldRemoveAllRows() {
            Row secondRow = mock(Row.class);
            when(secondRow.getId()).thenReturn(UUID.randomUUID());

            table.insertRow(firstRow);
            table.insertRow(secondRow);

            table.truncate();

            assertTrue(table.getRows().isEmpty());
            assertEquals(0, table.getRowCount());
        }

        @Test
        void getRows_ShouldReturnUnmodifiableCollection() {
            table.insertRow(firstRow);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> table.getRows().clear()
            );
        }
    }

    @Nested
    @DisplayName("Constraint Management Tests")
    class ConstraintManagementTests {

        @Test
        void addConstraint_ShouldRegisterConstraint() {
            table.addConstraint(primaryKey);

            assertSame(primaryKey, table.getConstraint("pk_users"));
            assertTrue(table.containsConstraint("pk_users"));
        }

        @Test
        void addConstraint_ShouldRejectNullConstraint() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addConstraint(null)
            );
        }

        @Test
        void addConstraint_ShouldRejectDuplicateConstraintName() {
            Constraint duplicateConstraint = mock(Constraint.class);
            when(duplicateConstraint.getName()).thenReturn("pk_users");

            table.addConstraint(primaryKey);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addConstraint(duplicateConstraint)
            );
        }

        @Test
        void getConstraint_ShouldReturnExistingConstraint() {
            table.addConstraint(primaryKey);

            Constraint result = table.getConstraint("pk_users");

            assertSame(primaryKey, result);
        }

        @Test
        void removeConstraint_ShouldRemoveExistingConstraint() {
            table.addConstraint(primaryKey);

            Constraint removed = table.removeConstraint("pk_users");

            assertSame(primaryKey, removed);
            assertFalse(table.containsConstraint("pk_users"));
            assertEquals(0, table.getConstraintCount());
        }

        @Test
        void getConstraints_ShouldReturnUnmodifiableCollection() {
            table.addConstraint(primaryKey);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> table.getConstraints().clear()
            );
        }
    }

    @Nested
    @DisplayName("Index Management Tests")
    class IndexManagementTests {

        @Test
        void addIndex_ShouldRegisterIndex() {
            table.addIndex(emailIndex);

            assertSame(emailIndex, table.getIndex("idx_users_email"));
            assertTrue(table.containsIndex("idx_users_email"));
        }

        @Test
        void addIndex_ShouldRejectNullIndex() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addIndex(null)
            );
        }

        @Test
        void addIndex_ShouldRejectDuplicateIndexName() {
            Index duplicateIndex = mock(Index.class);
            when(duplicateIndex.getName()).thenReturn("idx_users_email");

            table.addIndex(emailIndex);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> table.addIndex(duplicateIndex)
            );
        }

        @Test
        void getIndex_ShouldReturnExistingIndex() {
            table.addIndex(emailIndex);

            Index result = table.getIndex("idx_users_email");

            assertSame(emailIndex, result);
        }

        @Test
        void removeIndex_ShouldRemoveExistingIndex() {
            table.addIndex(emailIndex);

            Index removed = table.removeIndex("idx_users_email");

            assertSame(emailIndex, removed);
            assertFalse(table.containsIndex("idx_users_email"));
            assertEquals(0, table.getIndexCount());
        }

        @Test
        void getIndexes_ShouldReturnUnmodifiableCollection() {
            table.addIndex(emailIndex);

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> table.getIndexes().clear()
            );
        }
    }

    @Nested
    @DisplayName("State Tests")
    class StateTests {

        @Test
        void isEmpty_ShouldReturnTrueForNewTable() {
            assertTrue(table.isEmpty());
        }

        @Test
        void isEmpty_ShouldReturnFalseWhenRowExists() {
            table.insertRow(firstRow);

            assertFalse(table.isEmpty());
        }

        @Test
        void getRowCount_ShouldReturnCorrectCount() {
            Row secondRow = mock(Row.class);
            when(secondRow.getId()).thenReturn(UUID.randomUUID());

            table.insertRow(firstRow);
            table.insertRow(secondRow);

            assertEquals(2, table.getRowCount());
        }

        @Test
        void getColumnCount_ShouldReturnCorrectCount() {
            table.addColumn(idColumn);
            table.addColumn(nameColumn);

            assertEquals(2, table.getColumnCount());
        }

        @Test
        void getConstraintCount_ShouldReturnCorrectCount() {
            Constraint uniqueConstraint = mock(Constraint.class);
            when(uniqueConstraint.getName()).thenReturn("uq_users_email");

            table.addConstraint(primaryKey);
            table.addConstraint(uniqueConstraint);

            assertEquals(2, table.getConstraintCount());
        }

        @Test
        void getIndexCount_ShouldReturnCorrectCount() {
            Index nameIndex = mock(Index.class);
            when(nameIndex.getName()).thenReturn("idx_users_name");

            table.addIndex(emailIndex);
            table.addIndex(nameIndex);

            assertEquals(2, table.getIndexCount());
        }
    }
}