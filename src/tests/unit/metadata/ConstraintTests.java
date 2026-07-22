package unit.metadata;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.metadata.CheckConstraint;
import classes.metadata.ConstraintDefinition;
import classes.metadata.ForeignKeyConstraint;
import classes.metadata.NotNullConstraint;
import classes.metadata.PrimaryKeyConstraint;
import classes.metadata.Row;
import classes.metadata.UniqueConstraint;
import enums.ConstraintType;
import factories.ConstraintFactory;
import factories.DefaultConstraintFactory;
import interfaces.IConstraint;

@DisplayName("Constraint Unit Tests")
class ConstraintTests {

    private PrimaryKeyConstraint primaryKey;
    private UniqueConstraint uniqueConstraint;
    private NotNullConstraint notNullConstraint;
    private ForeignKeyConstraint foreignKey;
    private CheckConstraint checkConstraint;
    private Row row;
    private ConstraintFactory constraintFactory;
    @BeforeEach
    void setUp() {
        constraintFactory = new DefaultConstraintFactory();
        constraintFactory.create(new ConstraintDefinition(
                "pk_users",
                ConstraintType.PRIMARY_KEY,
                List.of("id")
        ));
        primaryKey = new ForeignKeyConstraint(
                "pk_users",
                Constraint.ConstraintType.PRIMARY_KEY,
                List.of("id")
        );

        uniqueConstraint = new Constraint(
                "uq_users_email",
                Constraint.ConstraintType.UNIQUE,
                List.of("email")
        );

        notNullConstraint = new Constraint(
                "nn_users_name",
                Constraint.ConstraintType.NOT_NULL,
                List.of("name")
        );

        foreignKey = new ForeignKeyConstraint(
                "fk_orders_user",
                Constraint.ConstraintType.FOREIGN_KEY,
                List.of("user_id")
        );

        checkConstraint = new Constraint(
                "ck_users_age",
                Constraint.ConstraintType.CHECK,
                List.of("age")
        );

        row = new Row();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        void constructor_ShouldCreateConstraint() {
            assertNotNull(primaryKey);
        }

        @Test
        void constructor_ShouldGenerateConstraintId() {
            assertNotNull(primaryKey.getId());
        }

        @Test
        void constructor_ShouldGenerateUniqueConstraintIds() {
            assertNotEquals(
                    primaryKey.getId(),
                    uniqueConstraint.getId()
            );
        }

        @Test
        void constructor_ShouldInitializeMetadata() {
            assertEquals("pk_users", primaryKey.getName());
            assertEquals(
                    Constraint.ConstraintType.PRIMARY_KEY,
                    primaryKey.getType()
            );
            assertEquals(
                    List.of("id"),
                    primaryKey.getColumnNames()
            );
        }

        @Test
        void constructor_ShouldEnableConstraintByDefault() {
            assertTrue(primaryKey.isEnabled());
        }

        @Test
        void constructor_ShouldRejectInvalidName() {
            assertAll(
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> new Constraint(
                                    null,
                                    Constraint.ConstraintType.UNIQUE,
                                    List.of("email")
                            )
                    ),
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> new Constraint(
                                    " ",
                                    Constraint.ConstraintType.UNIQUE,
                                    List.of("email")
                            )
                    )
            );
        }

        @Test
        void constructor_ShouldRejectNullType() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Constraint(
                            "constraint",
                            null,
                            List.of("id")
                    )
            );
        }

        @Test
        void constructor_ShouldRejectEmptyColumns() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Constraint(
                            "constraint",
                            Constraint.ConstraintType.UNIQUE,
                            List.of()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Metadata Tests")
    class MetadataTests {

        @Test
        void rename_ShouldChangeConstraintName() {
            primaryKey.rename("pk_customers");

            assertEquals("pk_customers", primaryKey.getName());
        }

        @Test
        void rename_ShouldRejectInvalidName() {
            assertAll(
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> primaryKey.rename(null)
                    ),
                    () -> assertThrows(
                            IllegalArgumentException.class,
                            () -> primaryKey.rename(" ")
                    )
            );
        }

        @Test
        void getColumnNames_ShouldReturnUnmodifiableList() {
            assertThrows(
                    UnsupportedOperationException.class,
                    () -> primaryKey.getColumnNames().add("email")
            );
        }

        @Test
        void setReferencedTableId_ShouldStoreId() {
            UUID referencedTableId = UUID.randomUUID();

            foreignKey.setReferencedTableId(referencedTableId);

            assertEquals(
                    referencedTableId,
                    foreignKey.getReferencedTableId()
            );
        }

        @Test
        void setReferencedTableId_ShouldRejectNull() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> foreignKey.setReferencedTableId(null)
            );
        }

        @Test
        void setReferencedColumnNames_ShouldStoreColumns() {
            foreignKey.setReferencedColumnNames(List.of("id"));

            assertEquals(
                    List.of("id"),
                    foreignKey.getReferencedColumnNames()
            );
        }

        @Test
        void getReferencedColumnNames_ShouldBeUnmodifiable() {
            foreignKey.setReferencedColumnNames(List.of("id"));

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> foreignKey
                            .getReferencedColumnNames()
                            .add("other")
            );
        }

        @Test
        void setCheckExpression_ShouldStoreExpression() {
            checkConstraint.setCheckExpression("age >= 18");

            assertEquals(
                    "age >= 18",
                    checkConstraint.getCheckExpression()
            );
        }

        @Test
        void setCheckExpression_ShouldRejectBlankExpression() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> checkConstraint.setCheckExpression(" ")
            );
        }

        @Test
        void setCheckPredicate_ShouldRejectNull() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> checkConstraint.setCheckPredicate(null)
            );
        }
    }

    @Nested
    @DisplayName("State Tests")
    class StateTests {

        @Test
        void disable_ShouldDisableConstraint() {
            primaryKey.disable();

            assertFalse(primaryKey.isEnabled());
        }

        @Test
        void enable_ShouldEnableConstraint() {
            primaryKey.disable();
            primaryKey.enable();

            assertTrue(primaryKey.isEnabled());
        }

        @Test
        void disable_ShouldBeIdempotent() {
            primaryKey.disable();
            primaryKey.disable();

            assertFalse(primaryKey.isEnabled());
        }

        @Test
        void enable_ShouldBeIdempotent() {
            primaryKey.enable();
            primaryKey.enable();

            assertTrue(primaryKey.isEnabled());
        }

        @Test
        void disabledConstraint_ShouldAlwaysPassValidation() {
            row.setValue("id", null);
            primaryKey.disable();

            assertTrue(
                    primaryKey.validatePrimaryKey(
                            row,
                            Set.of()
                    )
            );
        }
    }

    @Nested
    @DisplayName("Primary Key Tests")
    class PrimaryKeyTests {

        @Test
        void validatePrimaryKey_ShouldAcceptUniqueNonNullValue() {
            row.setValue("id", 1);

            assertTrue(
                    primaryKey.validatePrimaryKey(
                            row,
                            Set.of(List.of(2))
                    )
            );
        }

        @Test
        void validatePrimaryKey_ShouldRejectNullValue() {
            row.setValue("id", null);

            assertFalse(
                    primaryKey.validatePrimaryKey(
                            row,
                            Set.of()
                    )
            );
        }

        @Test
        void validatePrimaryKey_ShouldRejectDuplicateValue() {
            row.setValue("id", 1);

            assertFalse(
                    primaryKey.validatePrimaryKey(
                            row,
                            Set.of(List.of(1))
                    )
            );
        }

        @Test
        void validatePrimaryKey_ShouldSupportCompositeKey() {
            Constraint composite = new Constraint(
                    "pk_order_lines",
                    Constraint.ConstraintType.PRIMARY_KEY,
                    List.of("order_id", "line_number")
            );

            row.setValue("order_id", 10);
            row.setValue("line_number", 2);

            assertTrue(
                    composite.validatePrimaryKey(
                            row,
                            Set.of(List.of(10, 1))
                    )
            );
        }

        @Test
        void validatePrimaryKey_ShouldRejectNullExistingKeys() {
            row.setValue("id", 1);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> primaryKey.validatePrimaryKey(row, null)
            );
        }
    }

    @Nested
    @DisplayName("Unique Tests")
    class UniqueTests {

        @Test
        void validateUnique_ShouldAcceptUniqueValue() {
            row.setValue("email", "an@example.com");

            assertTrue(
                    uniqueConstraint.validateUnique(
                            row,
                            Set.of(List.of("other@example.com"))
                    )
            );
        }

        @Test
        void validateUnique_ShouldRejectDuplicateValue() {
            row.setValue("email", "an@example.com");

            assertFalse(
                    uniqueConstraint.validateUnique(
                            row,
                            Set.of(List.of("an@example.com"))
                    )
            );
        }

        @Test
        void validateUnique_ShouldAllowNullValue() {
            row.setValue("email", null);

            assertTrue(
                    uniqueConstraint.validateUnique(
                            row,
                            Set.of()
                    )
            );
        }

        @Test
        void validateUnique_ShouldSupportCompositeValue() {
            Constraint composite = new Constraint(
                    "uq_user_name",
                    Constraint.ConstraintType.UNIQUE,
                    List.of("first_name", "last_name")
            );

            row.setValue("first_name", "An");
            row.setValue("last_name", "Pham");

            assertTrue(
                    composite.validateUnique(
                            row,
                            Set.of(List.of("An", "Nguyen"))
                    )
            );
        }
    }

    @Nested
    @DisplayName("Not Null Tests")
    class NotNullTests {

        @Test
        void validateNotNull_ShouldAcceptNonNullValue() {
            row.setValue("name", "An");

            assertTrue(notNullConstraint.validateNotNull(row));
        }

        @Test
        void validateNotNull_ShouldRejectNullValue() {
            row.setValue("name", null);

            assertFalse(notNullConstraint.validateNotNull(row));
        }

        @Test
        void validateNotNull_ShouldCheckAllColumns() {
            Constraint composite = new Constraint(
                    "nn_full_name",
                    Constraint.ConstraintType.NOT_NULL,
                    List.of("first_name", "last_name")
            );

            row.setValue("first_name", "An");
            row.setValue("last_name", null);

            assertFalse(composite.validateNotNull(row));
        }

        @Test
        void validate_ShouldDispatchToNotNullValidation() {
            row.setValue("name", "An");

            assertTrue(notNullConstraint.validate(row));
        }
    }

    @Nested
    @DisplayName("Foreign Key Tests")
    class ForeignKeyTests {

        @BeforeEach
        void configureForeignKey() {
            foreignKey.setReferencedTableId(UUID.randomUUID());
            foreignKey.setReferencedColumnNames(List.of("id"));
        }

        @Test
        void validateForeignKey_ShouldAcceptExistingReference() {
            row.setValue("user_id", 10);

            assertTrue(
                    foreignKey.validateForeignKey(
                            row,
                            Set.of(List.of(10))
                    )
            );
        }

        @Test
        void validateForeignKey_ShouldRejectMissingReference() {
            row.setValue("user_id", 99);

            assertFalse(
                    foreignKey.validateForeignKey(
                            row,
                            Set.of(List.of(10))
                    )
            );
        }

        @Test
        void validateForeignKey_ShouldAllowNullValue() {
            row.setValue("user_id", null);

            assertTrue(
                    foreignKey.validateForeignKey(
                            row,
                            Set.of()
                    )
            );
        }

        @Test
        void validateForeignKey_ShouldSupportCompositeReference() {
            Constraint composite = new Constraint(
                    "fk_order_product",
                    Constraint.ConstraintType.FOREIGN_KEY,
                    List.of("product_id", "variant_id")
            );

            composite.setReferencedTableId(UUID.randomUUID());
            composite.setReferencedColumnNames(
                    List.of("id", "variant_id")
            );

            row.setValue("product_id", 10);
            row.setValue("variant_id", 2);

            assertTrue(
                    composite.validateForeignKey(
                            row,
                            Set.of(List.of(10, 2))
                    )
            );
        }
    }

    @Nested
    @DisplayName("Check Tests")
    class CheckTests {

        @BeforeEach
        void configureCheck() {
            checkConstraint.setCheckExpression("age >= 18");
            checkConstraint.setCheckPredicate(
                    currentRow ->
                            (Integer) currentRow.getValue("age") >= 18
            );
        }

        @Test
        void validateCheck_ShouldAcceptMatchingRow() {
            row.setValue("age", 22);

            assertTrue(checkConstraint.validateCheck(row));
        }

        @Test
        void validateCheck_ShouldRejectNonMatchingRow() {
            row.setValue("age", 16);

            assertFalse(checkConstraint.validateCheck(row));
        }

        @Test
        void validateCheck_ShouldRejectMissingPredicate() {
            Constraint missingPredicate = new Constraint(
                    "ck_age",
                    Constraint.ConstraintType.CHECK,
                    List.of("age")
            );

            missingPredicate.setCheckExpression("age >= 18");

            assertThrows(
                    IllegalStateException.class,
                    () -> missingPredicate.validateCheck(row)
            );
        }

        @Test
        void validate_ShouldDispatchToCheckValidation() {
            row.setValue("age", 22);

            assertTrue(checkConstraint.validate(row));
        }
    }

    @Nested
    @DisplayName("Definition Tests")
    class DefinitionTests {

        @Test
        void isValidDefinition_ShouldAcceptPrimaryKey() {
            assertTrue(primaryKey.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldAcceptUniqueConstraint() {
            assertTrue(uniqueConstraint.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldAcceptNotNullConstraint() {
            assertTrue(notNullConstraint.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldAcceptConfiguredForeignKey() {
            foreignKey.setReferencedTableId(UUID.randomUUID());
            foreignKey.setReferencedColumnNames(List.of("id"));
            assertTrue(foreignKey.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldRejectIncompleteForeignKey() {
            assertFalse(foreignKey.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldAcceptConfiguredCheck() {
            checkConstraint.setCheckExpression("age >= 18");
            checkConstraint.setCheckPredicate(currentRow -> true);

            assertTrue(checkConstraint.isValidDefinition());
        }

        @Test
        void isValidDefinition_ShouldRejectIncompleteCheck() {
            assertFalse(checkConstraint.isValidDefinition());
        }
    }
}