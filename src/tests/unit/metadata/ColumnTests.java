package unit.metadata;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.metadata.ColumnMetadata;
import enums.DataType;

@DisplayName("Column Unit Tests")
class ColumnTests { //55

    private ColumnMetadata column;

    @BeforeEach
    void setUp() {
        column = new ColumnMetadata("name", DataType.VARCHAR);
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void constructor_ShouldCreateColumn() {
            assertNotNull(column);
        }

        @Test
        void constructor_ShouldGenerateColumnId() {
            assertNotNull(column.getId());
        }

        @Test
        void constructor_ShouldGenerateUniqueColumnIds() {
            ColumnMetadata second = new ColumnMetadata("email", DataType.VARCHAR);

            assertNotEquals(column.getId(), second.getId());
        }

        @Test
        void constructor_ShouldInitializeNameAndType() {
            assertEquals("name", column.getName());
            assertEquals(DataType.VARCHAR, column.getDataType());
        }

        @Test
        void constructor_ShouldInitializeNullableAsTrue() {
            assertTrue(column.isNullable());
        }

        @Test
        void constructor_ShouldInitializeDefaultPosition() {
            assertEquals(0, column.getPosition());
        }

        @Test
        void constructor_ShouldInitializeIdentityAsFalse() {
            assertFalse(column.isIdentity());
            assertEquals(1L, column.getNextIdentityValue());
        }

        @Test
        void constructor_ShouldRejectInvalidName() {
            assertAll(
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> new ColumnMetadata(
                                    null,
                                    DataType.VARCHAR)),
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> new ColumnMetadata(
                                    "   ",
                                    DataType.VARCHAR)));
        }

        @Test
        void constructor_ShouldRejectNullDataType() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new ColumnMetadata("name", null));
        }
    }

    @Nested
    @DisplayName("Metadata Tests")
    class MetadataTests {

        @Test
        void rename_ShouldChangeColumnName() {
            column.rename("full_name");

            assertEquals("full_name", column.getName());
        }

        @Test
        void rename_ShouldRejectInvalidName() {
            assertAll(
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> column.rename(null)),
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> column.rename(" ")));
        }

        @Test
        void setDataType_ShouldChangeDataType() {
            column.setDataType(DataType.TEXT);

            assertEquals(DataType.TEXT, column.getDataType());
        }

        @Test
        void setDataType_ShouldRejectNull() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setDataType(null));
        }

        @Test
        void setNullable_ShouldChangeNullableState() {
            column.setNullable(false);

            assertFalse(column.isNullable());
        }

        @Test
        void setPosition_ShouldChangePosition() {
            column.setPosition(3);

            assertEquals(3, column.getPosition());
        }

        @Test
        void setPosition_ShouldRejectNegativePosition() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setPosition(-1));
        }
    }

    @Nested
    @DisplayName("Length Tests")
    class LengthTests {

        @Test
        void setLength_ShouldStorePositiveLength() {
            column.setLength(100);

            assertEquals(100, column.getLength());
        }

        @Test
        void setLength_ShouldAllowNull() {
            column.setLength(100);
            column.setLength(null);

            assertNull(column.getLength());
        }

        @Test
        void setLength_ShouldRejectZero() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setLength(0));
        }

        @Test
        void setLength_ShouldRejectNegativeLength() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setLength(-1));
        }

        @Test
        void validateValue_ShouldRejectStringLongerThanLength() {
            column.setLength(3);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.validateValue("long"));
        }
    }

    @Nested
    @DisplayName("Precision and Scale Tests")
    class PrecisionScaleTests {

        @Test
        void setPrecision_ShouldStorePositivePrecision() {
            column.setPrecision(10);

            assertEquals(10, column.getPrecision());
        }

        @Test
        void setPrecision_ShouldRejectInvalidPrecision() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setPrecision(0));
        }

        @Test
        void setScale_ShouldStoreNonNegativeScale() {
            column.setPrecision(10);
            column.setScale(2);

            assertEquals(2, column.getScale());
        }

        @Test
        void setScale_ShouldRejectNegativeScale() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setScale(-1));
        }

        @Test
        void setScale_ShouldRejectScaleGreaterThanPrecision() {
            column.setPrecision(5);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setScale(6));
        }

        @Test
        void setPrecision_ShouldRejectPrecisionBelowExistingScale() {
            column.setPrecision(10);
            column.setScale(6);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setPrecision(5));
        }
    }

    @Nested
    @DisplayName("Default Value Tests")
    class DefaultValueTests {

        @Test
        void setDefaultValue_ShouldStoreCompatibleValue() {
            column.setDefaultValue("unknown");

            assertEquals("unknown", column.getDefaultValue());
        }

        @Test
        void setDefaultValue_ShouldAllowNull() {
            column.setDefaultValue(null);

            assertNull(column.getDefaultValue());
        }

        @Test
        void setDefaultValue_ShouldRejectIncompatibleValue() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setDefaultValue(123));
        }
    }

    @Nested
    @DisplayName("Value Validation Tests")
    class ValueValidationTests {

        @Test
        void validateValue_ShouldAcceptCompatibleValue() {
            assertTrue(column.validateValue("An"));
        }

        @Test
        void validateValue_ShouldAllowNullWhenNullable() {
            assertTrue(column.validateValue(null));
        }

        @Test
        void validateValue_ShouldRejectNullWhenNotNullable() {
            column.setNullable(false);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.validateValue(null));
        }

        @Test
        void validateValue_ShouldRejectIncompatibleValue() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.validateValue(100));
        }

        @Test
        void resolveValue_ShouldReturnSuppliedValue() {
            assertEquals("An", column.resolveValue("An"));
        }

        @Test
        void resolveValue_ShouldUseDefaultValue() {
            column.setDefaultValue("unknown");

            assertEquals("unknown", column.resolveValue(null));
        }

        @Test
        void resolveValue_ShouldReturnNullWhenNullable() {
            assertNull(column.resolveValue(null));
        }

        @Test
        void resolveValue_ShouldRejectMissingRequiredValue() {
            column.setNullable(false);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.resolveValue(null));
        }
    }

    @Nested
    @DisplayName("Identity Tests")
    class IdentityTests {

        @Test
        void setIdentity_ShouldEnableIdentity() {
            column.setIdentity(true);

            assertTrue(column.isIdentity());
        }

        @Test
        void generateNextIdentityValue_ShouldReturnCurrentValue() {
            column.setIdentity(true);

            assertEquals(1L, column.generateNextIdentityValue());
        }

        @Test
        void generateNextIdentityValue_ShouldIncrementCounter() {
            column.setIdentity(true);

            column.generateNextIdentityValue();

            assertEquals(2L, column.getNextIdentityValue());
        }

        @Test
        void generateNextIdentityValue_ShouldGenerateSequentialValues() {
            column.setIdentity(true);

            assertEquals(1L, column.generateNextIdentityValue());
            assertEquals(2L, column.generateNextIdentityValue());
            assertEquals(3L, column.generateNextIdentityValue());
        }

        @Test
        void generateNextIdentityValue_ShouldRejectNonIdentityColumn() {
            assertThrows(
                    IllegalStateException.class,
                    column::generateNextIdentityValue);
        }

        @Test
        void setNextIdentityValue_ShouldChangeCounter() {
            column.setNextIdentityValue(100L);

            assertEquals(100L, column.getNextIdentityValue());
        }

        @Test
        void setNextIdentityValue_ShouldRejectInvalidValue() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> column.setNextIdentityValue(0L));
        }

        @Test
        void resolveValue_ShouldGenerateIdentityWhenValueMissing() {
            column.setIdentity(true);

            assertEquals(1L, column.resolveValue(null));
            assertEquals(2L, column.getNextIdentityValue());
        }

        @Test
        void resolveValue_ShouldPreferSuppliedValueOverIdentity() {
            column.setIdentity(true);

            assertEquals("manual", column.resolveValue("manual"));
            assertEquals(1L, column.getNextIdentityValue());
        }
    }

    @Nested
    @DisplayName("Definition Tests")
    class DefinitionTests {

        @Test
        void isValidDefinition_ShouldReturnTrueForValidColumn() {
            assertTrue(column.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldRemainValidWithLength() {
            column.setLength(100);

            assertTrue(column.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldRemainValidWithPrecisionAndScale() {
            column.setPrecision(10);
            column.setScale(2);

            assertTrue(column.isValidDefinition());
        }
    }

    @Nested
    @DisplayName("Equality Tests")
    class EqualityTests {

        @Test
        void equals_ShouldReturnTrueForSameInstance() {
            assertEquals(column, column);
        }

        @Test
        void equals_ShouldReturnFalseForDifferentColumns() {
            ColumnMetadata second = new ColumnMetadata("name", DataType.VARCHAR);

            assertNotEquals(column, second);
        }

        @Test
        void equals_ShouldReturnFalseForNull() {
            assertNotEquals(column, null);
        }

        @Test
        void hashCode_ShouldRemainStable() {
            int originalHashCode = column.hashCode();

            column.rename("full_name");
            column.setNullable(false);

            assertEquals(originalHashCode, column.hashCode());
        }
    }
}