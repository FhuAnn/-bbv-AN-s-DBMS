
package unit.metadata;

import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.metadata.Row;

@DisplayName("Row Unit Tests")
class RowTests { //59

    private Row row;

    @BeforeEach
    void setUp() {
        row = new Row();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Should create row successfully")
        void constructor_ShouldCreateRow() {
            assertNotNull(row);
        }

        @Test
        @DisplayName("Should generate row ID")
        void constructor_ShouldGenerateRowId() {
            assertNotNull(row.getId());
        }

        @Test
        @DisplayName("Should generate unique row IDs")
        void constructor_ShouldGenerateUniqueRowIds() {
            Row secondRow = new Row();
            assertNotEquals(row.getId(), secondRow.getId());
        }

        @Test
        @DisplayName("Should initialize empty values")
        void constructor_ShouldInitializeEmptyValues() {
            assertNotNull(row.getValues());
            assertTrue(row.getValues().isEmpty());
        }

        @Test
        @DisplayName("Should initialize row as active")
        void constructor_ShouldInitializeActiveState() {
            assertTrue(row.isActive());
            assertFalse(row.isDeleted());
        }

        @Test
        @DisplayName("Should initialize default version")
        void constructor_ShouldInitializeDefaultVersion() {
            assertEquals(1L, row.getVersion());
        }
    }

    @Nested
    @DisplayName("Set Value Tests")
    class SetValueTests {

        @Test
        @DisplayName("Should store value successfully")
        void setValue_ShouldStoreValueSuccessfully() {
            row.setValue("name", "An");
            assertEquals("An", row.getValue("name"));
        }

        @Test
        @DisplayName("Should store multiple values")
        void setValue_ShouldStoreMultipleValues() {
            row.setValue("id", 1);
            row.setValue("name", "An");

            assertEquals(1, row.getValue("id"));
            assertEquals("An", row.getValue("name"));
            assertEquals(2, row.getValues().size());
        }

        @Test
        @DisplayName("Should replace an existing value")
        void setValue_ShouldReplaceExistingValue() {
            row.setValue("name", "An");
            row.setValue("name", "Binh");

            assertEquals("Binh", row.getValue("name"));
            assertEquals(1, row.getValues().size());
        }

        @Test
        @DisplayName("Should allow null value")
        void setValue_ShouldAllowNullValue() {
            row.setValue("middle_name", null);

            assertTrue(row.containsColumn("middle_name"));
            assertNull(row.getValue("middle_name"));
        }

        @Test
        @DisplayName("Should reject null column name")
        void setValue_ShouldRejectNullColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.setValue(null, "value"));
        }

        @Test
        @DisplayName("Should reject empty column name")
        void setValue_ShouldRejectEmptyColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.setValue("", "value"));
        }

        @Test
        @DisplayName("Should reject blank column name")
        void setValue_ShouldRejectBlankColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.setValue("   ", "value"));
        }
    }

    @Nested
    @DisplayName("Get Value Tests")
    class GetValueTests {

        @Test
        @DisplayName("Should return an existing value")
        void getValue_ShouldReturnExistingValue() {
            row.setValue("name", "An");

            assertEquals("An", row.getValue("name"));
        }

        @Test
        @DisplayName("Should return null for a missing column")
        void getValue_ShouldReturnNullForMissingColumn() {
            assertNull(row.getValue("missing"));
        }

        @Test
        @DisplayName("Should reject null column name")
        void getValue_ShouldRejectNullColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.getValue(null));
        }

        @Test
        @DisplayName("Should reject blank column name")
        void getValue_ShouldRejectBlankColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.getValue("   "));
        }
    }

    @Nested
    @DisplayName("Contains Column Tests")
    class ContainsColumnTests {

        @Test
        @DisplayName("Should return true for an existing column")
        void containsColumn_ShouldReturnTrueForExistingColumn() {
            row.setValue("name", "An");

            assertTrue(row.containsColumn("name"));
        }

        @Test
        @DisplayName("Should return false for a missing column")
        void containsColumn_ShouldReturnFalseForMissingColumn() {
            assertFalse(row.containsColumn("missing"));
        }

        @Test
        @DisplayName("Should return true when existing value is null")
        void containsColumn_ShouldReturnTrueWhenValueIsNull() {
            row.setValue("middle_name", null);
            assertTrue(row.containsColumn("middle_name"));
        }
    }

    @Nested
    @DisplayName("Remove Value Tests")
    class RemoveValueTests {

        @Test
        @DisplayName("Should remove an existing value")
        void removeValue_ShouldRemoveExistingValue() {
            row.setValue("name", "An");

            row.removeValue("name");

            assertFalse(row.containsColumn("name"));
        }

        @Test
        @DisplayName("Should return removed value")
        void removeValue_ShouldReturnRemovedValue() {
            row.setValue("name", "An");

            Object removed = row.removeValue("name");

            assertEquals("An", removed);
        }

        @Test
        @DisplayName("Should do nothing when column does not exist")
        void removeValue_ShouldDoNothingForMissingColumn() {
            assertNull(row.removeValue("missing"));
            assertTrue(row.getValues().isEmpty());
        }

        @Test
        @DisplayName("Should reject null column name")
        void removeValue_ShouldRejectNullColumnName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.removeValue(null));
        }
    }

    @Nested
    @DisplayName("Update Values Tests")
    class UpdateValuesTests {

        @Test
        @DisplayName("Should update multiple values")
        void updateValues_ShouldUpdateMultipleValues() {
            row.updateValues(Map.of(
                    "name", "An",
                    "age", 22));

            assertEquals("An", row.getValue("name"));
            assertEquals(22, row.getValue("age"));
        }

        @Test
        @DisplayName("Should replace existing values")
        void updateValues_ShouldReplaceExistingValues() {
            row.setValue("name", "An");

            row.updateValues(Map.of("name", "Binh"));

            assertEquals("Binh", row.getValue("name"));
        }

        @Test
        @DisplayName("Should preserve values not included in update")
        void updateValues_ShouldPreserveUnchangedValues() {
            row.setValue("name", "An");
            row.setValue("age", 22);

            row.updateValues(Map.of("name", "Binh"));

            assertEquals("Binh", row.getValue("name"));
            assertEquals(22, row.getValue("age"));
        }

        @Test
        @DisplayName("Should reject null values map")
        void updateValues_ShouldRejectNullMap() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.updateValues(null));
        }

        @Test
        @DisplayName("Should accept an empty values map")
        void updateValues_ShouldAcceptEmptyMap() {
            row.setValue("name", "An");
            long versionBefore = row.getVersion();

            assertDoesNotThrow(
                    () -> row.updateValues(Map.of()));

            assertEquals("An", row.getValue("name"));
            assertEquals(versionBefore, row.getVersion());
        }

        @Test
        @DisplayName("Should not partially update when validation fails")
        void updateValues_ShouldNotPartiallyUpdateWhenValidationFails() {
            row.setValue("name", "An");

            Map<String, Object> invalidValues = new LinkedHashMap<>();
            invalidValues.put("age", 22);
            invalidValues.put("   ", "invalid");

            assertThrows(
                    IllegalArgumentException.class,
                    () -> row.updateValues(invalidValues));

            assertEquals("An", row.getValue("name"));
            assertFalse(row.containsColumn("age"));
        }
    }

    @Nested
    @DisplayName("Deletion Tests")
    class DeletionTests {

        @Test
        @DisplayName("Should mark row as deleted")
        void markDeleted_ShouldMarkRowAsDeleted() {
            row.markDeleted();
            assertTrue(row.isDeleted());
            assertFalse(row.isActive());
        }

        @Test
        @DisplayName("Should remain deleted when called twice")
        void markDeleted_ShouldBeIdempotent() {
            row.markDeleted();
            row.markDeleted();

            assertTrue(row.isDeleted());
        }

        @Test
        @DisplayName("Should restore a deleted row")
        void restore_ShouldRestoreDeletedRow() {
            row.markDeleted();

            row.restore();

            assertFalse(row.isDeleted());
            assertTrue(row.isActive());
        }

        @Test
        @DisplayName("Should remain active when restore is called twice")
        void restore_ShouldBeIdempotent() {
            row.restore();
            row.restore();

            assertTrue(row.isActive());
            assertFalse(row.isDeleted());
        }

        @Test
        @DisplayName("Should preserve values after logical deletion")
        void markDeleted_ShouldPreserveRowValues() {
            row.setValue("name", "An");

            row.markDeleted();

            assertEquals("An", row.getValue("name"));
        }
    }

    @Nested
    @DisplayName("Get Values Tests")
    class GetValuesTests {

        @Test
        @DisplayName("Should return all row values")
        void getValues_ShouldReturnAllValues() {
            row.setValue("name", "An");
            row.setValue("age", 22);

            assertEquals(
                    Map.of("name", "An", "age", 22),
                    row.getValues());
        }

        @Test
        @DisplayName("Should return an empty map for a new row")
        void getValues_ShouldReturnEmptyMapForNewRow() {
            assertTrue(row.getValues().isEmpty());
        }

        @Test
        @DisplayName("Should return an unmodifiable map")
        void getValues_ShouldReturnUnmodifiableMap() {
            assertThrows(
                    UnsupportedOperationException.class,
                    () -> row.getValues().put("name", "An"));
        }
    }

    @Nested
    @DisplayName("Copy Tests")
    class CopyTests {

        @Test
        @DisplayName("Should create a new row")
        void copy_ShouldCreateNewRow() {
            Row copy = row.copy();

            assertNotNull(copy);
            assertNotSame(row, copy);
        }

        @Test
        @DisplayName("Should copy all values")
        void copy_ShouldCopyAllValues() {
            row.setValue("name", "An");
            row.setValue("age", 22);

            Row copy = row.copy();

            assertEquals(row.getValues(), copy.getValues());
        }

        @Test
        @DisplayName("Should create an independent values map")
        void copy_ShouldCreateIndependentValues() {
            row.setValue("name", "An");
            Row copy = row.copy();

            copy.setValue("name", "Binh");
            copy.setValue("age", 22);

            assertEquals("An", row.getValue("name"));
            assertFalse(row.containsColumn("age"));
        }

        @Test
        @DisplayName("Should generate a different row ID")
        void copy_ShouldGenerateDifferentRowId() {
            Row copy = row.copy();

            assertNotEquals(row.getId(), copy.getId());
        }

        @Test
        @DisplayName("Should copy deletion state")
        void copy_ShouldCopyDeletionState() {
            row.markDeleted();

            Row copy = row.copy();

            assertTrue(copy.isDeleted());
        }

        @Test
        @DisplayName("Should copy row version")
        void copy_ShouldCopyVersion() {
            row.incrementVersion();
            row.incrementVersion();

            Row copy = row.copy();

            assertEquals(row.getVersion(), copy.getVersion());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        @DisplayName("Should return true for the same row instance")
        void equals_ShouldReturnTrueForSameInstance() {
            assertEquals(row, row);
        }

        @Test
        @DisplayName("Should return true for rows with equal values")
        void equals_ShouldReturnTrueForEqualRows() {
            Row secondRow = new Row();

            row.setValue("name", "An");
            secondRow.setValue("name", "An");

            assertEquals(row, secondRow);
        }

        @Test
        @DisplayName("Should return false for rows with different values")
        void equals_ShouldReturnFalseForDifferentRows() {
            Row secondRow = new Row();

            row.setValue("name", "An");
            secondRow.setValue("name", "Binh");

            assertNotEquals(row, secondRow);
        }

        @Test
        @DisplayName("Should return false when compared with null")
        void equals_ShouldReturnFalseForNull() {
            assertNotEquals(row, null);
        }

        @Test
        @DisplayName("Should return false when compared with another type")
        void equals_ShouldReturnFalseForDifferentType() {
            assertNotEquals(row, "row");
        }

        @Test
        @DisplayName("Equal rows should have equal hash codes")
        void hashCode_ShouldBeEqualForEqualRows() {
            Row secondRow = new Row();

            row.setValue("name", "An");
            secondRow.setValue("name", "An");

            assertEquals(row.hashCode(), secondRow.hashCode());
        }
    }

    @Nested
    @DisplayName("Row Size Tests")
    class RowSizeTests {

        @Test
        @DisplayName("Should return zero for an empty row")
        void calculateSize_ShouldReturnZeroForEmptyRow() {
            assertEquals(0, row.calculateSize());
        }

        @Test
        @DisplayName("Should return a positive size for a populated row")
        void calculateSize_ShouldReturnPositiveSize() {
            row.setValue("name", "An");

            assertTrue(row.calculateSize() > 0);
        }

        @Test
        @DisplayName("Should increase size after adding a value")
        void calculateSize_ShouldIncreaseWhenValueIsAdded() {
            int emptySize = row.calculateSize();

            row.setValue("name", "An");

            assertTrue(row.calculateSize() > emptySize);
        }

        @Test
        @DisplayName("Should decrease size after removing a value")
        void calculateSize_ShouldDecreaseWhenValueIsRemoved() {
            row.setValue("name", "An");
            row.setValue("age", 22);
            int populatedSize = row.calculateSize();

            row.removeValue("age");

            assertTrue(row.calculateSize() < populatedSize);
        }

        @Test
        @DisplayName("Should handle null values")
        void calculateSize_ShouldHandleNullValues() {
            row.setValue("middle_name", null);

            assertDoesNotThrow(row::calculateSize);
            assertTrue(row.calculateSize() >= 0);
        }
    }

    @Nested
    @DisplayName("Version Tests")
    class VersionTests {

        @Test
        @DisplayName("Should return the default version")
        void getVersion_ShouldReturnDefaultVersion() {
            assertEquals(1L, row.getVersion());
        }

        @Test
        @DisplayName("Should increment the row version")
        void incrementVersion_ShouldIncreaseVersion() {
            long initialVersion = row.getVersion();

            row.incrementVersion();

            assertEquals(initialVersion + 1, row.getVersion());
        }

        @Test
        @DisplayName("Should increment version after updating values")
        void updateValues_ShouldIncrementVersion() {
            long initialVersion = row.getVersion();

            row.updateValues(Map.of("name", "An"));

            assertEquals(initialVersion + 1, row.getVersion());
        }
    }
}