package unit.metadata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;

import builder.ConstraintDefinitionBuilder;
import classes.abstraction.Constraint;
import classes.metadata.CheckConstraint;
import classes.metadata.ConstraintDefinition;
import classes.metadata.ForeignKeyConstraint;
import classes.metadata.NotNullConstraint;
import classes.metadata.PrimaryKeyConstraint;
import classes.metadata.UniqueConstraint;
import enums.ConstraintType;
import enums.MetadataType;
import factories.ConstraintFactory;
import factories.DefaultConstraintFactory;

class ConstraintTests {

    private ConstraintFactory constraintFactory;
    private ConstraintDefinitionBuilder builder;

    private UUID tableId;
    private UUID firstColumnId;
    private UUID secondColumnId;
    private UUID referencedTableId;
    private UUID referencedColumnId;

    private ConstraintDefinition primaryKeyDefinition;
    private ConstraintDefinition uniqueDefinition;
    private ConstraintDefinition notNullDefinition;
    private ConstraintDefinition foreignKeyDefinition;
    private ConstraintDefinition checkDefinition;

    private PrimaryKeyConstraint primaryKey;
    private UniqueConstraint uniqueConstraint;
    private NotNullConstraint notNullConstraint;
    private ForeignKeyConstraint foreignKey;
    private CheckConstraint checkConstraint;

    @BeforeEach
    void setUp() {
        constraintFactory = new DefaultConstraintFactory();
        builder = ConstraintDefinitionBuilder.builder();

        tableId = UUID.randomUUID();
        firstColumnId = UUID.randomUUID();
        secondColumnId = UUID.randomUUID();
        referencedTableId = UUID.randomUUID();
        referencedColumnId = UUID.randomUUID();

        primaryKeyDefinition = ConstraintDefinitionBuilder.builder()
                .name("pk_users")
                .type(ConstraintType.PRIMARY_KEY)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .build();

        uniqueDefinition = ConstraintDefinitionBuilder.builder()
                .name("uq_users_email")
                .type(ConstraintType.UNIQUE)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .build();

        notNullDefinition = ConstraintDefinitionBuilder.builder()
                .name("nn_users_name")
                .type(ConstraintType.NOT_NULL)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .build();

        foreignKeyDefinition = ConstraintDefinitionBuilder.builder()
                .name("fk_orders_user")
                .type(ConstraintType.FOREIGN_KEY)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .referencedTableId(referencedTableId)
                .referencedColumnIds(List.of(referencedColumnId))
                .build();

        checkDefinition = ConstraintDefinitionBuilder.builder()
                .name("ck_users_age")
                .type(ConstraintType.CHECK)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .expression("age >= 18")
                .build();

        primaryKey = (PrimaryKeyConstraint) constraintFactory.create(primaryKeyDefinition);
        uniqueConstraint = (UniqueConstraint) constraintFactory.create(uniqueDefinition);
        notNullConstraint = (NotNullConstraint) constraintFactory.create(notNullDefinition);
        foreignKey = (ForeignKeyConstraint) constraintFactory.create(foreignKeyDefinition);
        checkConstraint = (CheckConstraint) constraintFactory.create(checkDefinition);
    }

    @Nested
    @DisplayName("Builder Creation Tests")
    class BuilderCreationTests {

        @Test
        @DisplayName("builder_ShouldCreateBuilder")
        void builder_ShouldCreateBuilder() {
            assertNotNull(ConstraintDefinitionBuilder.builder());
        }

        @Test
        @DisplayName("constructor_ShouldCreateBuilder")
        void constructor_ShouldCreateBuilder() {
            assertNotNull(new ConstraintDefinitionBuilder());
        }

        @Test
        @DisplayName("builder_ShouldReturnNewBuilderEachTime")
        void builder_ShouldReturnNewBuilderEachTime() {
            ConstraintDefinitionBuilder first = ConstraintDefinitionBuilder.builder();
            ConstraintDefinitionBuilder second = ConstraintDefinitionBuilder.builder();
            assertNotSame(first, second);
        }

    }

    @Nested
    @DisplayName("Required Field Builder Tests")
    class RequiredFieldBuilderTests {

        @Test
        @DisplayName("name_ShouldStoreConstraintName")
        void name_ShouldStoreConstraintName() {
            ConstraintDefinition definition = validPrimaryKeyBuilder()
                    .name("pk_customers")
                    .build();

            assertEquals("pk_customers", definition.getName());
        }

        @Test
        @DisplayName("type_ShouldStoreConstraintType")
        void type_ShouldStoreConstraintType() {
            ConstraintDefinition definition = validPrimaryKeyBuilder().build();

            assertEquals(ConstraintType.PRIMARY_KEY, definition.getType());
        }

        @Test
        @DisplayName("tableId_ShouldStoreTableId")
        void tableId_ShouldStoreTableId() {
            ConstraintDefinition definition = validPrimaryKeyBuilder().build();

            assertEquals(tableId, definition.getTableId());
        }
    }

    @Test
    @DisplayName("columnIds_ShouldStoreColumnIds")
    void columnIds_ShouldStoreColumnIds() {
        ConstraintDefinition definition = validPrimaryKeyBuilder().build();

        assertEquals(List.of(firstColumnId), definition.getColumnIds());
    }

    @Test
    @DisplayName("build_ShouldCreateConstraintDefinition")
    void build_ShouldCreateConstraintDefinition() {
        assertNotNull(validPrimaryKeyBuilder().build());
    }

    @Test
    @DisplayName("build_ShouldPreserveRequiredFields")
    void build_ShouldPreserveRequiredFields() {
        assertNotNull(validPrimaryKeyBuilder().build());
    }

    @Test
    @DisplayName("build_ShouldRejectNullName")
    void build_ShouldRejectNullName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name(null)
                        .type(ConstraintType.PRIMARY_KEY)
                        .tableId(tableId)
                        .columnIds(List.of(firstColumnId))
                        .build());
    }

    @Test
    @DisplayName("build_ShouldRejectBlankName")
    void build_ShouldRejectBlankName() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name(" ")
                        .type(ConstraintType.PRIMARY_KEY)
                        .tableId(tableId)
                        .columnIds(List.of(firstColumnId))
                        .build());
    }

    @Test
    @DisplayName("build_ShouldRejectNullType")
    void build_ShouldRejectNullType() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name("constraint")
                        .type(null)
                        .tableId(tableId)
                        .columnIds(List.of(firstColumnId))
                        .build());
    }

    @Test
    @DisplayName("build_ShouldRejectNullTableId")
    void build_ShouldRejectNullTableId() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name("constraint")
                        .type(ConstraintType.PRIMARY_KEY)
                        .tableId(null)
                        .columnIds(List.of(firstColumnId))
                        .build());
    }

    @Test
    @DisplayName("build_ShouldRejectNullColumnIds")
    void build_ShouldRejectNullColumnIds() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name("constraint")
                        .type(ConstraintType.PRIMARY_KEY)
                        .tableId(tableId)
                        .columnIds(null)
                        .build());
    }

    @Test
    @DisplayName("build_ShouldRejectEmptyColumnIds")
    void build_ShouldRejectEmptyColumnIds() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ConstraintDefinitionBuilder.builder()
                        .name("constraint")
                        .type(ConstraintType.PRIMARY_KEY)
                        .tableId(tableId)
                        .columnIds(List.of())
                        .build());
    }

    @Test
    @DisplayName("build_ShouldProtectColumnIdsFromExternalMutation")
    void build_ShouldProtectColumnIdsFromExternalMutation() {
        List<UUID> columnIds = new ArrayList<>();
        columnIds.add(firstColumnId);

        ConstraintDefinition definition = ConstraintDefinitionBuilder.builder()
                .name("pk_users")
                .type(ConstraintType.PRIMARY_KEY)
                .tableId(tableId)
                .columnIds(columnIds)
                .build();

        columnIds.add(secondColumnId);

        assertEquals(List.of(firstColumnId), definition.getColumnIds());
        assertThrows(
                UnsupportedOperationException.class,
                () -> definition.getColumnIds().add(secondColumnId));
    }

    @Nested
    @DisplayName("Foreign Key Builder Tests")
    class ForeignKeyBuilderTests {

        @Test
        @DisplayName("referencedTableId_ShouldStoreReferencedTableId")
        void referencedTableId_ShouldStoreReferencedTableId() {

        }

        @Test
        @DisplayName("referencedColumnIds_ShouldStoreReferencedColumnIds")
        void referencedColumnIds_ShouldStoreReferencedColumnIds() {

        }

        @Test
        @DisplayName("buildForeignKey_ShouldCreateValidDefinition")
        void buildForeignKey_ShouldCreateValidDefinition() {

        }

        @Test
        @DisplayName("buildForeignKey_ShouldRejectMissingReferencedTableId")
        void buildForeignKey_ShouldRejectMissingReferencedTableId() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("fk_orders_user")
                            .type(ConstraintType.FOREIGN_KEY)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId))
                            .referencedColumnIds(List.of(referencedColumnId))
                            .build());
        }

        @Test
        @DisplayName("buildForeignKey_ShouldRejectMissingReferencedColumnIds")
        void buildForeignKey_ShouldRejectMissingReferencedColumnIds() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("fk_orders_user")
                            .type(ConstraintType.FOREIGN_KEY)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId))
                            .referencedTableId(referencedTableId)
                            .build());
        }

        @Test
        @DisplayName("buildForeignKey_ShouldRejectEmptyReferencedColumnIds")
        void buildForeignKey_ShouldRejectEmptyReferencedColumnIds() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("fk_orders_user")
                            .type(ConstraintType.FOREIGN_KEY)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId))
                            .referencedTableId(referencedTableId)
                            .referencedColumnIds(List.of())
                            .build());
        }

        @Test
        @DisplayName("buildForeignKey_ShouldRejectMismatchedColumnCount")
        void buildForeignKey_ShouldRejectMismatchedColumnCount() {

        }

        @Test
        @DisplayName("buildForeignKey_ShouldProtectReferencedColumnIds")
        void buildForeignKey_ShouldProtectReferencedColumnIds() {
            // TODO: implement test
        }

    }

    @Nested
    @DisplayName("Check Builder Tests")
    class CheckBuilderTests {

        @Test
        @DisplayName("expression_ShouldStoreCheckExpression")
        void expression_ShouldStoreCheckExpression() {
            ConstraintDefinition definition = validCheckBuilder().build();

            assertEquals("age >= 18", definition.getExpression());
        }

        @Test
        @DisplayName("buildCheck_ShouldCreateValidDefinition")
        void buildCheck_ShouldCreateValidDefinition() {
            ConstraintDefinition definition = validCheckBuilder().build();

            assertAll(
                    () -> assertEquals(ConstraintType.CHECK, definition.getType()),
                    () -> assertEquals("age >= 18", definition.getExpression()));
        }

        @Test
        @DisplayName("buildCheck_ShouldRejectNullExpression")
        void buildCheck_ShouldRejectNullExpression() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("ck_users_age")
                            .type(ConstraintType.CHECK)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId))
                            .expression(null)
                            .build());
        }

        @Test
        @DisplayName("buildCheck_ShouldRejectBlankExpression")
        void buildCheck_ShouldRejectBlankExpression() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("ck_users_age")
                            .type(ConstraintType.CHECK)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId))
                            .expression(" ")
                            .build());
        }

        @Test
        @DisplayName("buildNonCheck_ShouldIgnoreMissingExpression")
        void buildNonCheck_ShouldIgnoreMissingExpression() {
            assertDoesNotThrow(() -> validPrimaryKeyBuilder().build());

        }

    }

    @Nested
    @DisplayName("Factory Tests")
    class FactoryTests {

        @Test
        @DisplayName("factory_ShouldCreatePrimaryKeyConstraint")
        void factory_ShouldCreatePrimaryKeyConstraint() {
            assertInstanceOf(
                    PrimaryKeyConstraint.class,
                    constraintFactory.create(primaryKeyDefinition));
        }

        @Test
        @DisplayName("factory_ShouldCreateUniqueConstraint")
        void factory_ShouldCreateUniqueConstraint() {
            assertInstanceOf(
                    UniqueConstraint.class,
                    constraintFactory.create(uniqueDefinition));
        }

        @Test
        @DisplayName("factory_ShouldCreateNotNullConstraint")
        void factory_ShouldCreateNotNullConstraint() {
            assertInstanceOf(
                    NotNullConstraint.class,
                    constraintFactory.create(notNullDefinition));
        }

        @Test
        @DisplayName("factory_ShouldCreateForeignKeyConstraint")
        void factory_ShouldCreateForeignKeyConstraint() {
            assertInstanceOf(
                    ForeignKeyConstraint.class,
                    constraintFactory.create(foreignKeyDefinition));
        }

        @Test
        @DisplayName("factory_ShouldCreateCheckConstraint")
        void factory_ShouldCreateCheckConstraint() {
            assertInstanceOf(
                    CheckConstraint.class,
                    constraintFactory.create(checkDefinition));
        }

        @Test
        @DisplayName("factory_ShouldReturnConstraintWithDefinitionName")
        void factory_ShouldReturnConstraintWithDefinitionName() {
            Constraint result = constraintFactory.create(primaryKeyDefinition);

            assertEquals(primaryKeyDefinition.getName(), result.getName());
        }

        @Test
        @DisplayName("factory_ShouldReturnConstraintWithTableId")
        void factory_ShouldReturnConstraintWithTableId() {
            Constraint result = constraintFactory.create(primaryKeyDefinition);

            assertEquals(tableId, result.getTableId());
        }

        @Test
        @DisplayName("factory_ShouldReturnConstraintWithColumnIds")
        void factory_ShouldReturnConstraintWithColumnIds() {
            Constraint result = constraintFactory.create(primaryKeyDefinition);

            assertEquals(List.of(firstColumnId), result.getColumnIds());
        }

        @Test
        @DisplayName("factory_ShouldRejectNullDefinition")
        void factory_ShouldRejectNullDefinition() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> constraintFactory.create(null));
        }

        @Test
        @DisplayName("factory_ShouldRejectUnsupportedConstraintType")
        void factory_ShouldRejectUnsupportedConstraintType() {
            ConstraintDefinition invalidDefinition = new ConstraintDefinition(
                    "unsupported",
                    null,
                    tableId,
                    List.of(firstColumnId),
                    null,
                    null,
                    null);

            assertThrows(
                    RuntimeException.class,
                    () -> constraintFactory.create(invalidDefinition));
        }

    }

    @Nested
    @DisplayName("Primary Key Definition Tests")
    class PrimaryKeyDefinitionTests {

        @Test
        @DisplayName("primaryKey_ShouldReturnPrimaryKeyType")
        void primaryKey_ShouldReturnPrimaryKeyType() {
            assertEquals(ConstraintType.PRIMARY_KEY, primaryKey.getType());
        }

        @Test
        @DisplayName("primaryKey_ShouldValidateCompleteDefinition")
        void primaryKey_ShouldValidateCompleteDefinition() {
            assertTrue(primaryKey.validateDefinition());
        }

        @Test
        @DisplayName("primaryKey_ShouldSupportCompositeColumns")
        void primaryKey_ShouldSupportCompositeColumns() {
            ConstraintDefinition definition = ConstraintDefinitionBuilder.builder()
                    .name("pk_order_lines")
                    .type(ConstraintType.PRIMARY_KEY)
                    .tableId(tableId)
                    .columnIds(List.of(firstColumnId, secondColumnId))
                    .build();

            PrimaryKeyConstraint composite = (PrimaryKeyConstraint) constraintFactory.create(definition);

            assertEquals(
                    List.of(firstColumnId, secondColumnId),
                    composite.getColumnIds());
        }

    }

    @Nested
    @DisplayName("Unique Definition Tests")
    class UniqueDefinitionTests {

        @Test
        @DisplayName("unique_ShouldReturnUniqueType")
        void unique_ShouldReturnUniqueType() {
            assertEquals(ConstraintType.UNIQUE, uniqueConstraint.getType());
        }

        @Test
        @DisplayName("unique_ShouldValidateCompleteDefinition")
        void unique_ShouldValidateCompleteDefinition() {
            assertTrue(uniqueConstraint.validateDefinition());
        }

        @Test
        @DisplayName("unique_ShouldSupportCompositeColumns")
        void unique_ShouldSupportCompositeColumns() {
            ConstraintDefinition definition = ConstraintDefinitionBuilder.builder()
                    .name("uq_full_name")
                    .type(ConstraintType.UNIQUE)
                    .tableId(tableId)
                    .columnIds(List.of(firstColumnId, secondColumnId))
                    .build();

            UniqueConstraint composite = (UniqueConstraint) constraintFactory.create(definition);

            assertEquals(
                    List.of(firstColumnId, secondColumnId),
                    composite.getColumnIds());
        }

    }

    @Nested
    @DisplayName("Not Null Definition Tests")
    class NotNullDefinitionTests {

        @Test
        @DisplayName("notNull_ShouldReturnNotNullType")
        void notNull_ShouldReturnNotNullType() {
            assertEquals(ConstraintType.NOT_NULL, notNullConstraint.getType());
        }

        @Test
        @DisplayName("notNull_ShouldValidateSingleColumnDefinition")
        void notNull_ShouldValidateSingleColumnDefinition() {
            assertTrue(notNullConstraint.validateDefinition());
        }

        @Test
        @DisplayName("notNull_ShouldRejectMultipleColumns")
        void notNull_ShouldRejectMultipleColumns() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> ConstraintDefinitionBuilder.builder()
                            .name("nn_full_name")
                            .type(ConstraintType.NOT_NULL)
                            .tableId(tableId)
                            .columnIds(List.of(firstColumnId, secondColumnId))
                            .build());
        }

    }

    @Nested
    @DisplayName("Foreign Key Definition Tests")
    class ForeignKeyDefinitionTests {

        @Test
        @DisplayName("foreignKey_ShouldReturnForeignKeyType")
        void foreignKey_ShouldReturnForeignKeyType() {
            assertEquals(ConstraintType.FOREIGN_KEY, foreignKey.getType());
        }

        @Test
        @DisplayName("foreignKey_ShouldValidateCompleteDefinition")
        void foreignKey_ShouldValidateCompleteDefinition() {
            assertTrue(foreignKey.validateDefinition());
        }

        @Test
        @DisplayName("foreignKey_ShouldExposeReferencedTableId")
        void foreignKey_ShouldExposeReferencedTableId() {
            assertEquals(referencedTableId, foreignKey.getReferencedTableId());
        }

        @Test
        @DisplayName("foreignKey_ShouldExposeReferencedColumnIds")
        void foreignKey_ShouldExposeReferencedColumnIds() {
            assertEquals(
                    List.of(referencedColumnId),
                    foreignKey.getReferencedColumnIds());
        }

    }

    @Nested
    @DisplayName("Check Definition Tests")
    class CheckDefinitionTests {

        @Test
        @DisplayName("check_ShouldReturnCheckType")
        void check_ShouldReturnCheckType() {
            assertEquals(ConstraintType.CHECK, checkConstraint.getType());
        }

        @Test
        @DisplayName("check_ShouldValidateCompleteDefinition")
        void check_ShouldValidateCompleteDefinition() {
            assertTrue(checkConstraint.validateDefinition());
        }

        @Test
        @DisplayName("check_ShouldExposeExpression")
        void check_ShouldExposeExpression() {
            assertEquals("age >= 18", checkConstraint.getExpression());
        }

    }

    @Nested
    @DisplayName("Metadata Composite Tests")
    class MetadataCompositeTests {

        @Test
        @DisplayName("constraint_ShouldReturnConstraintMetadataType")
        void constraint_ShouldReturnConstraintMetadataType() {
            assertEquals(MetadataType.CONSTRAINT, primaryKey.getMetadataType());
        }

        @Test
        @DisplayName("constraint_ShouldBeLeafMetadataComponent")
        void constraint_ShouldBeLeafMetadataComponent() {
            assertTrue(primaryKey.isLeaf());
        }

        @Test
        @DisplayName("constraint_ShouldReturnEmptyChildren")
        void constraint_ShouldReturnEmptyChildren() {
            assertTrue(primaryKey.getChildren().isEmpty());
        }

        @Test
        @DisplayName("constraint_ShouldRejectAddChild")
        void constraint_ShouldRejectAddChild() {
            assertThrows(
                    UnsupportedOperationException.class,
                    () -> primaryKey.addChild(uniqueConstraint));
        }

        @Test
        @DisplayName("constraint_ShouldRejectRemoveChild")
        void constraint_ShouldRejectRemoveChild() {
            assertThrows(
                    UnsupportedOperationException.class,
                    () -> primaryKey.removeChild(UUID.randomUUID()));
        }

    }

    @Nested
    @DisplayName("Prototype Tests")
    class PrototypeTests {

        @Test
        @DisplayName("copyAs_ShouldCreateDifferentConstraintInstance")
        void copyAs_ShouldCreateDifferentConstraintInstance() {
            Constraint copy = primaryKey.copyAs("new_pk_users", UUID.randomUUID());

            assertNotSame(primaryKey, copy);
        }

        @Test
        @DisplayName("copyAs_ShouldGenerateDifferentConstraintId")
        void copyAs_ShouldGenerateDifferentConstraintId() {
            Constraint copy = primaryKey.copyAs("new_pk_users", UUID.randomUUID());

            assertNotEquals(primaryKey.getId(), copy.getId());
        }

        @Test
        @DisplayName("copyAs_ShouldPreserveConstraintName")
        void copyAs_ShouldPreserveConstraintName() {
            Constraint copy = primaryKey.copyAs("new_pk_users", UUID.randomUUID());

            assertEquals(primaryKey.getName(), copy.getName());
        }

        @Test
        @DisplayName("copyAs_ShouldPreserveConstraintType")
        void copyAs_ShouldPreserveConstraintType() {
            Constraint copy = primaryKey.copyAs("new_pk_users", UUID.randomUUID());

            assertEquals(primaryKey.getType(), copy.getType());
        }

        @Test
        @DisplayName("copyAs_ShouldPreserveDefinitionData")
        void copyAs_ShouldPreserveDefinitionData() {
            Constraint copy = foreignKey.copyAs("new_fk_orders_user", UUID.randomUUID());

            assertAll(
                    () -> assertEquals(foreignKey.getTableId(), copy.getTableId()),
                    () -> assertEquals(foreignKey.getColumnIds(), copy.getColumnIds()),
                    () -> assertEquals(
                            foreignKey.getReferencedTableId(),
                            ((ForeignKeyConstraint) copy).getReferencedTableId()),
                    () -> assertEquals(
                            foreignKey.getReferencedColumnIds(),
                            ((ForeignKeyConstraint) copy).getReferencedColumnIds()));
        }

    }

    private ConstraintDefinitionBuilder validPrimaryKeyBuilder() {
        return ConstraintDefinitionBuilder.builder()
                .name("pk_users")
                .type(ConstraintType.PRIMARY_KEY)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId));
    }

    private ConstraintDefinitionBuilder validForeignKeyBuilder() {
        return ConstraintDefinitionBuilder.builder()
                .name("fk_orders_user")
                .type(ConstraintType.FOREIGN_KEY)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .referencedTableId(referencedTableId)
                .referencedColumnIds(List.of(referencedColumnId));
    }

    private ConstraintDefinitionBuilder validCheckBuilder() {
        return ConstraintDefinitionBuilder.builder()
                .name("ck_users_age")
                .type(ConstraintType.CHECK)
                .tableId(tableId)
                .columnIds(List.of(firstColumnId))
                .expression("age >= 18");
    }

}