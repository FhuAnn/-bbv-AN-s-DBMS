
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import classes.database.Database;
import classes.metadata.Catalog;
import classes.metadata.ColumnMetadata;
import classes.metadata.Schema;
import classes.metadata.Table;
import enums.DataType;
import enums.DatabaseStateType;
import exception.InvalidSchemaException;
import exception.ReadOnlyDatabaseException;
import exception.SchemaAlreadyExistsException;
import exception.SchemaNotEmptyException;
import exception.SchemaNotFoundException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Database Tests")
class DatabaseTests {

    private Database database;
    private UUID ownerId;

    @BeforeEach
    void setUp() {
        database = new Database("shop_db");
        ownerId = UUID.randomUUID();
    }

    private Schema createSchema(String name) {
        return new Schema(
                name,
                database.getId(),
                ownerId
        );
    }

    // =========================================================
    // Constructor Tests
    // =========================================================

    @Nested
    @DisplayName("Constructor")
    class ConstructorTests {
        @Test
        @DisplayName("Should create database successfully")
        void constructor_ShouldCreateDatabase() {
            Database result = new Database("shop_db");

            assertNotNull(result);
            assertEquals("shop_db", result.getName());
        }

        @Test
        @DisplayName("Should generate database ID")
        void constructor_ShouldGenerateDatabaseId() {
            Database result = new Database("shop_db");

            assertNotNull(result.getId());
        }

        @Test
        @DisplayName("Should generate different IDs for different databases")
        void constructor_ShouldGenerateUniqueDatabaseId() {
            Database firstDatabase = new Database("database_1");
            Database secondDatabase = new Database("database_2");

            assertNotNull(firstDatabase.getId());
            assertNotNull(secondDatabase.getId());

            assertNotEquals(
                    firstDatabase.getId(),
                    secondDatabase.getId()
            );
        }

        @Test
        @DisplayName("Should initialize catalog")
        void constructor_ShouldInitializeCatalog() {
            Catalog catalog = database.getCatalog();

            assertNotNull(catalog);
            assertNotNull(catalog.getSchemas());
            assertNotNull(catalog.getTables());

            assertTrue(catalog.getSchemas().isEmpty());
            assertTrue(catalog.getTables().isEmpty());
        }

        @Test
        @DisplayName("Should initialize schema collection")
        void constructor_ShouldInitializeSchemaCollection() {
            List<Schema> schemas = database.getSchemas();
            assertNotNull(schemas);
            assertTrue(schemas.isEmpty());
        }

        @Test
        @DisplayName("Should initialize database as closed")
        void constructor_ShouldInitializeClosedState() {
            assertEquals(DatabaseStateType.CLOSED, database.getState());
        }

        @Test
        @DisplayName("Should initialize database as writable")
        void constructor_ShouldInitializeWritableMode() {
            assertFalse(database.isReadOnly());
        }

        @Test
        @DisplayName("Should reject null database name")
        void constructor_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Database(null)
            );
        }

        @Test
        @DisplayName("Should reject empty database name")
        void constructor_ShouldRejectEmptyName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Database("")
            );
        }

        @Test
        @DisplayName("Should reject blank database name")
        void constructor_ShouldRejectBlankName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Database("   ")
            );
        }

        @Test
        @DisplayName("Should reject database name longer than 128 characters")
        void constructor_ShouldRejectTooLongName() {
            String invalidName = "a".repeat(129);
            assertThrows(
                    IllegalArgumentException.class,
                    () -> new Database(invalidName)
            );
        }

        @Test
        @DisplayName("Should accept database name containing 128 characters")
        void constructor_ShouldAcceptMaximumLengthName() {
            String validName = "a".repeat(128);

            Database result = new Database(validName);

            assertEquals(validName, result.getName());
        }
    }

    // =========================================================
    // Lifecycle Tests
    // =========================================================

    @Nested
    @DisplayName("Lifecycle")
    class LifecycleTests {

        @Test
        @DisplayName("Open should change database state to OPEN")
        void open_ShouldChangeDatabaseStateToOpen() {
            database.open();

            assertEquals(DatabaseStateType.OPEN, database.getState());
        }

        @Test
        @DisplayName("Open should be idempotent")
        void open_ShouldBeIdempotent() {
            database.open();
            database.open();

            assertEquals(DatabaseStateType.OPEN, database.getState());
        }

        @Test
        @DisplayName("Close should change database state to CLOSED")
        void close_ShouldChangeDatabaseStateToClosed() {
            database.open();

            database.close();

            assertEquals(DatabaseStateType.CLOSED, database.getState());
        }

        @Test
        @DisplayName("Close should be idempotent")
        void close_ShouldBeIdempotent() {
            database.close();
            database.close();

            assertEquals(DatabaseStateType.CLOSED, database.getState());
        }

        @Test
        @DisplayName("Database should be able to reopen after closing")
        void open_ShouldReopenClosedDatabase() {
            database.open();
            database.close();

            database.open();

            assertEquals(DatabaseStateType.OPEN, database.getState());
        }
    }

    // =========================================================
    // Schema Management Tests
    // =========================================================

    @Nested
    @DisplayName("Schema management")
    class SchemaManagementTests {

        @Test
        @DisplayName("Add schema should register schema")
        void addSchema_ShouldRegisterSchema() {
            Schema schema = createSchema("sales");

            database.addSchema(schema);

            assertTrue(database.containsSchema("sales"));
            assertSame(schema, database.getSchema("sales"));
        }

        @Test
        @DisplayName("Add schema should increase schema count")
        void addSchema_ShouldIncreaseSchemaCount() {
            Schema schema = createSchema("sales");

            database.addSchema(schema);

            assertEquals(1, database.getSchemas().size());
        }

        @Test
        @DisplayName("Add multiple schemas should register all schemas")
        void addSchema_ShouldRegisterMultipleSchemas() {
            database.addSchema(createSchema("public"));
            database.addSchema(createSchema("sales"));
            database.addSchema(createSchema("report"));

            assertEquals(3, database.getSchemas().size());

            assertTrue(database.containsSchema("public"));
            assertTrue(database.containsSchema("sales"));
            assertTrue(database.containsSchema("report"));
        }

        @Test
        @DisplayName("Add schema should reject null schema")
        void addSchema_ShouldRejectNullSchema() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.addSchema(null)
            );
        }

        @Test
        @DisplayName("Add schema should reject duplicate schema name")
        void addSchema_ShouldRejectDuplicateSchemaName() {
            Schema firstSchema = createSchema("sales");
            Schema duplicateSchema = createSchema("sales");

            database.addSchema(firstSchema);

            assertThrows(
                    SchemaAlreadyExistsException.class,
                    () -> database.addSchema(duplicateSchema)
            );

            assertEquals(1, database.getSchemas().size());
        }

        @Test
        @DisplayName("Add schema should reject schema belonging to another database")
        void addSchema_ShouldRejectSchemaFromAnotherDatabase() {
            Schema schema = new Schema(
                    "sales",
                    UUID.randomUUID(),
                    ownerId
            );

            assertThrows(
                    InvalidSchemaException.class,
                    () -> database.addSchema(schema)
            );
        }

        @Test
        @DisplayName("Get schema should return existing schema")
        void getSchema_ShouldReturnExistingSchema() {
            Schema schema = createSchema("sales");
            database.addSchema(schema);

            Schema result = database.getSchema("sales");

            assertSame(schema, result);
        }

        @Test
        @DisplayName("Get schema should throw when schema is missing")
        void getSchema_ShouldThrowWhenSchemaDoesNotExist() {
            assertThrows(
                    SchemaNotFoundException.class,
                    () -> database.getSchema("unknown")
            );
        }

        @Test
        @DisplayName("Contains schema should return true for existing schema")
        void containsSchema_ShouldReturnTrueForExistingSchema() {
            database.addSchema(createSchema("sales"));

            boolean result = database.containsSchema("sales");

            assertTrue(result);
        }

        @Test
        @DisplayName("Contains schema should return false for missing schema")
        void containsSchema_ShouldReturnFalseForMissingSchema() {
            boolean result = database.containsSchema("unknown");

            assertFalse(result);
        }

        @Test
        @DisplayName("Remove schema should remove existing schema")
        void removeSchema_ShouldRemoveExistingSchema() {
            database.addSchema(createSchema("sales"));

            database.removeSchema("sales");

            assertFalse(database.containsSchema("sales"));
        }

        @Test
        @DisplayName("Remove schema should decrease schema count")
        void removeSchema_ShouldDecreaseSchemaCount() {
            database.addSchema(createSchema("sales"));

            assertEquals(1, database.getSchemas().size());

            database.removeSchema("sales");

            assertEquals(0, database.getSchemas().size());
        }

        @Test
        @DisplayName("Remove schema should throw when schema is missing")
        void removeSchema_ShouldThrowWhenSchemaNotFound() {
            assertThrows(
                    SchemaNotFoundException.class,
                    () -> database.removeSchema("unknown")
            );
        }

        @Test
        @DisplayName("Remove schema should reject null schema name")
        void removeSchema_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.removeSchema(null)
            );
        }

        @Test
        @DisplayName("Remove schema should reject blank schema name")
        void removeSchema_ShouldRejectBlankName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.removeSchema(" ")
            );
        }

        @Test
        @DisplayName("Remove schema should reject schema containing tables")
        void removeSchema_ShouldRejectNonEmptySchema() {
            // Arrange
            Schema schema = createSchema("sales");

            Table table = new Table(
                    "orders",
                    schema.getId()
            );

            schema.addTable(table);
            database.addSchema(schema);

            // Act
            SchemaNotEmptyException exception = assertThrows(
                    SchemaNotEmptyException.class,
                    () -> database.removeSchema("sales")
            );

            // Assert
            assertNotNull(exception);
            assertTrue(database.containsSchema("sales"));
            assertEquals(1, database.getSchemas().size());
            assertEquals(1, schema.getTables().size());
        }
    }

    // =========================================================
    // Read-only Tests
    // =========================================================

    @Nested
    @DisplayName("Read-only mode")
    class ReadOnlyTests {

        @Test
        @DisplayName("Set read-only should change database mode")
        void setReadOnly_ShouldChangeDatabaseMode() {
            database.setReadOnly(true);

            assertTrue(database.isReadOnly());
        }

        @Test
        @DisplayName("Disable read-only should restore writable mode")
        void setReadOnlyFalse_ShouldRestoreWritableMode() {
            database.setReadOnly(true);
            database.setReadOnly(false);

            assertFalse(database.isReadOnly());
        }

        @Test
        @DisplayName("Add schema should throw when database is read-only")
        void addSchema_ShouldRejectWhenDatabaseIsReadOnly() {
            database.setReadOnly(true);

            Schema schema = createSchema("sales");

            assertThrows(
                    ReadOnlyDatabaseException.class,
                    () -> database.addSchema(schema)
            );

            assertFalse(database.containsSchema("sales"));
        }

        @Test
        @DisplayName("Remove schema should throw when database is read-only")
        void removeSchema_ShouldRejectWhenDatabaseIsReadOnly() {
            Schema schema = createSchema("sales");
            database.addSchema(schema);

            database.setReadOnly(true);

            assertThrows(
                    ReadOnlyDatabaseException.class,
                    () -> database.removeSchema("sales")
            );

            assertTrue(database.containsSchema("sales"));
        }

        @Test
        @DisplayName("Writable database should allow schema modification")
        void writableDatabase_ShouldAllowSchemaModification() {
            database.setReadOnly(true);
            database.setReadOnly(false);

            database.addSchema(createSchema("sales"));

            assertTrue(database.containsSchema("sales"));
        }
    }

    // =========================================================
    // Rename Tests
    // =========================================================

    @Nested
    @DisplayName("Rename database")
    class RenameTests {

        @Test
        @DisplayName("Rename should change database name")
        void rename_ShouldChangeDatabaseName() {
            database.rename("new_shop_db");

            assertEquals("new_shop_db", database.getName());
        }

        @Test
        @DisplayName("Rename should reject null name")
        void rename_ShouldRejectNullName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.rename(null)
            );

            assertEquals("shop_db", database.getName());
        }

        @Test
        @DisplayName("Rename should reject blank name")
        void rename_ShouldRejectBlankName() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.rename("   ")
            );

            assertEquals("shop_db", database.getName());
        }

        @Test
        @DisplayName("Rename should reject name longer than 128 characters")
        void rename_ShouldRejectTooLongName() {
            String invalidName = "a".repeat(129);

            assertThrows(
                    IllegalArgumentException.class,
                    () -> database.rename(invalidName)
            );

            assertEquals("shop_db", database.getName());
        }

        @Test
        @DisplayName("Rename should reject modification in read-only mode")
        void rename_ShouldRejectWhenDatabaseIsReadOnly() {
            database.setReadOnly(true);

            assertThrows(
                    ReadOnlyDatabaseException.class,
                    () -> database.rename("new_name")
            );

            assertEquals("shop_db", database.getName());
        }
    }

    // =========================================================
    // Collection Safety Tests
    // =========================================================

    @Nested
    @DisplayName("Collection safety")
    class CollectionSafetyTests {

        @Test
        @DisplayName("Get schemas should return unmodifiable collection")
        void getSchemas_ShouldReturnUnmodifiableCollection() {
            database.addSchema(createSchema("sales"));

            List<Schema> schemas = database.getSchemas();

            assertThrows(
                    UnsupportedOperationException.class,
                    () -> schemas.add(createSchema("report"))
            );
        }

        @Test
        @DisplayName("External collection modification should not affect database")
        void getSchemas_ShouldProtectInternalCollection() {
            database.addSchema(createSchema("sales"));

            List<Schema> schemas = database.getSchemas();

            assertThrows(
                    UnsupportedOperationException.class,
                    schemas::clear
            );

            assertEquals(1, database.getSchemas().size());
            assertTrue(database.containsSchema("sales"));
        }
    }

    // =========================================================
    // Catalog Consistency Tests
    // =========================================================

    @Nested
    @DisplayName("Catalog consistency")
    class CatalogConsistencyTests {

        @Test
        @DisplayName("Catalog should contain schema after adding it")
        void catalog_ShouldRemainConsistentAfterAddingSchema() {
            Schema schema = createSchema("sales");

            database.addSchema(schema);

            assertTrue(
                    database.getCatalog()
                            .getSchemas()
                            .containsKey(schema.getId())
            );

            assertSame(
                    schema,
                    database.getCatalog()
                            .getSchemas()
                            .get(schema.getId())
            );
        }

        @Test
        @DisplayName("Catalog should not contain schema after removing it")
        void catalog_ShouldRemainConsistentAfterRemovingSchema() {
            Schema schema = createSchema("sales");
            database.addSchema(schema);

            database.removeSchema("sales");

            assertFalse(
                    database.getCatalog()
                            .getSchemas()
                            .containsKey(schema.getId())
            );
        }

        @Test
        @DisplayName("Database and catalog should contain the same schema count")
        void catalog_ShouldHaveSameSchemaCountAsDatabase() {
            database.addSchema(createSchema("public"));
            database.addSchema(createSchema("sales"));

            assertEquals(
                    database.getSchemas().size(),
                    database.getCatalog().getSchemas().size()
            );
        }
    }

    // =========================================================
    // Simple Concurrency Tests
    // =========================================================

    @Nested
    @DisplayName("Concurrency")
    class ConcurrencyTests {

        @Test
        @DisplayName("Concurrent open should maintain OPEN state")
        void concurrentOpen_ShouldMaintainDatabaseState() throws Exception {
            int threadCount = 10;

            ExecutorService executor =
                    Executors.newFixedThreadPool(threadCount);

            CountDownLatch startLatch = new CountDownLatch(1);

            try {
                Future<?>[] futures = new Future<?>[threadCount];

                for (int index = 0; index < threadCount; index++) {
                    futures[index] = executor.submit(() -> {
                        startLatch.await();
                        database.open();
                        return null;
                    });
                }

                startLatch.countDown();

                for (Future<?> future : futures) {
                    future.get();
                }

                assertEquals(
                        DatabaseStateType.OPEN,
                        database.getState()
                );
            } finally {
                executor.shutdownNow();
            }
        }

        @Test
        @DisplayName("Concurrent schema reads should return same schema")
        void concurrentSchemaReads_ShouldReturnConsistentResult()
                throws Exception {

            Schema schema = createSchema("sales");
            database.addSchema(schema);

            ExecutorService executor =
                    Executors.newFixedThreadPool(2);

            try {
                Future<Schema> firstResult =
                        executor.submit(() -> database.getSchema("sales"));

                Future<Schema> secondResult =
                        executor.submit(() -> database.getSchema("sales"));

                assertSame(schema, firstResult.get());
                assertSame(schema, secondResult.get());
                assertSame(firstResult.get(), secondResult.get());
            } finally {
                executor.shutdownNow();
            }
        }

        @Test
        @DisplayName("Concurrent duplicate schema creation should create only one")
        void concurrentSchemaCreation_ShouldPreventDuplicateSchema()
                throws Exception {

            ExecutorService executor =
                    Executors.newFixedThreadPool(2);

            CountDownLatch startLatch = new CountDownLatch(1);

            Schema firstSchema = createSchema("sales");
            Schema secondSchema = createSchema("sales");

            try {
                Future<Boolean> firstResult = executor.submit(() -> {
                    startLatch.await();

                    try {
                        database.addSchema(firstSchema);
                        return true;
                    } catch (SchemaAlreadyExistsException exception) {
                        return false;
                    }
                });

                Future<Boolean> secondResult = executor.submit(() -> {
                    startLatch.await();

                    try {
                        database.addSchema(secondSchema);
                        return true;
                    } catch (SchemaAlreadyExistsException exception) {
                        return false;
                    }
                });

                startLatch.countDown();

                boolean firstSucceeded = firstResult.get();
                boolean secondSucceeded = secondResult.get();

                assertNotEquals(firstSucceeded, secondSucceeded);

                assertEquals(1, database.getSchemas().size());
                assertTrue(database.containsSchema("sales"));
            } finally {
                executor.shutdownNow();
            }
        }
    }

    // =========================================================
    // Simple Integration Tests
    // =========================================================

    @Nested
    @DisplayName("Integration")
    class IntegrationTests {

        @Test
        @DisplayName("Database and schema should maintain relationship")
        void databaseSchemaIntegration_ShouldMaintainRelationship() {
            Schema schema = createSchema("sales");

            database.addSchema(schema);

            Schema storedSchema = database.getSchema("sales");

            assertEquals(database.getId(), storedSchema.getDatabaseId());
            assertSame(schema, storedSchema);
        }

        @Test
        @DisplayName("Database, schema and table should build metadata hierarchy")
        void databaseSchemaTableIntegration_ShouldBuildMetadataHierarchy() {
            Schema schema = createSchema("sales");

            Table table = new Table(
                    "orders",
                    schema.getId()
            );

            ColumnMetadata idColumn = new ColumnMetadata(
                    "id",
                    DataType.INTEGER
            );

            idColumn.setNullable(false);
            idColumn.setPosition(0);

            table.addColumn(idColumn);
            schema.addTable(table);

            database.addSchema(schema);

            Schema storedSchema = database.getSchema("sales");
            Table storedTable = storedSchema.getTables().getFirst();
            ColumnMetadata storedColumn = storedTable.getColumns().getFirst();

            assertEquals("sales", storedSchema.getName());
            assertEquals("orders", storedTable.getName());
            assertEquals("id", storedColumn.getName());
            assertEquals(DataType.INTEGER, storedColumn.getType());
            assertFalse(storedColumn.isNullable());
        }
    }
}