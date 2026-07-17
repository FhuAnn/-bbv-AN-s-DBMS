import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.mockito.Mockito.mock;

@DisplayName("Database Tests")
class DatabaseTests {

    private static final String DATABASE_NAME = "test_database";
    private static final String SCHEMA_NAME = "sales";

    private Catalog mockCatalog;
    private StorageEngine mockStorageEngine;
    private TransactionManager mockTransactionManager;
    private SecurityManager mockSecurityManager;

    private Database database;

    @BeforeEach
    void setUp() {
        mockCatalog = mock(Catalog.class);
        mockStorageEngine = mock(StorageEngine.class);
        mockTransactionManager = mock(TransactionManager.class);
        mockSecurityManager = mock(SecurityManager.class);
        mockRecoveryManager = mock(RecoveryManager.class);

        database = new Database(
                DATABASE_NAME,
                mockCatalog,
                mockStorageEngine,
                mockTransactionManager,
                mockSecurityManager,
                mockRecoveryManager
        );
    }

    // ==================================================
    // Constructor Tests
    // ==================================================

    @Test
    @DisplayName("Should create database with valid name and generated ID")
    void Constructor_ShouldCreateDatabase() {
        Database createdDatabase = new Database(
                DATABASE_NAME,
                mockCatalog,
                mockStorageEngine,
                mockTransactionManager,
                mockSecurityManager,
                mockRecoveryManager
        );

        assertEquals(DATABASE_NAME, createdDatabase.getName());
        assertNotNull(createdDatabase.getId());
    }

    @Test
    @DisplayName("Should initialize all injected dependencies")
    void Constructor_ShouldInitializeDependencies() {
        assertSame(mockCatalog, database.getCatalog());
        assertSame(mockStorageEngine, database.getStorageEngine());
        assertSame(
                mockTransactionManager,
                database.getTransactionManager()
        );
        assertSame(mockSecurityManager, database.getSecurityManager());
        assertSame(mockRecoveryManager, database.getRecoveryManager());
    }

    @Test
    @DisplayName("Should initialize an empty schema collection")
    void Constructor_ShouldInitializeSchemaCollection() {
        assertNotNull(database.getSchemas());
        assertTrue(database.getSchemas().isEmpty());
    }

    // ==================================================
    // Lifecycle Tests
    // ==================================================

    @Test
    @DisplayName("Should load database successfully")
    void Open_ShouldLoadDatabaseSuccessfully() {
        doNothing().when(mockStorageEngine)
                .openDatabase(database.getId());

        database.open();

        assertEquals(DatabaseState.OPEN, database.getState());

        verify(mockStorageEngine, times(1))
                .openDatabase(database.getId());
    }

    @Test
    @DisplayName("Should flush and close storage successfully")
    void Close_ShouldFlushResourcesSuccessfully() {
        doNothing().when(mockStorageEngine).flush();

        doNothing().when(mockStorageEngine)
                .closeDatabase(database.getId());

        database.open();
        database.close();

        assertEquals(DatabaseState.CLOSED, database.getState());

        verify(mockStorageEngine, times(1)).flush();

        verify(mockStorageEngine, times(1))
                .closeDatabase(database.getId());
    }

    // ==================================================
    // Database Mode Tests
    // ==================================================

    @Test
    @DisplayName("Should change database to read-only mode")
    void SetReadOnly_ShouldChangeDatabaseMode() {
        UserSession session = mock(UserSession.class);

        when(
                mockSecurityManager.authorize(
                        session,
                        Permission.ALTER_DATABASE
                )
        ).thenReturn(true);

        database.setReadOnly(true, session);

        assertTrue(database.isReadOnly());

        verify(mockSecurityManager, times(1))
                .authorize(session, Permission.ALTER_DATABASE);
    }

    @Test
    @DisplayName("Should reject unauthorized read-only mode change")
    void SetReadOnly_ShouldRejectUnauthorizedUser() {
        UserSession session = mock(UserSession.class);

        when(
                mockSecurityManager.authorize(
                        session,
                        Permission.ALTER_DATABASE
                )
        ).thenReturn(false);

        assertThrows(
                AuthorizationException.class,
                () -> database.setReadOnly(true, session)
        );

        assertFalse(database.isReadOnly());
    }

    // ==================================================
    // Schema Tests
    // ==================================================

    @Test
    @DisplayName("Should register schema in catalog")
    void AddSchema_ShouldRegisterSchema() {
        Schema schema = createSchema();

        when(mockCatalog.schemaExists(SCHEMA_NAME))
                .thenReturn(false);

        database.addSchema(schema);

        verify(mockCatalog, times(1))
                .schemaExists(SCHEMA_NAME);

        verify(mockCatalog, times(1))
                .registerSchema(schema);

        assertTrue(database.getSchemas().contains(schema));
    }

    @Test
    @DisplayName("Should reject duplicate schema")
    void AddSchema_ShouldRejectDuplicateSchema() {
        Schema schema = createSchema();

        when(mockCatalog.schemaExists(SCHEMA_NAME))
                .thenReturn(true);

        assertThrows(
                DuplicateSchemaException.class,
                () -> database.addSchema(schema)
        );

        verify(mockCatalog, never())
                .registerSchema(schema);
    }

    @Test
    @DisplayName("Should remove schema metadata")
    void RemoveSchema_ShouldRemoveSchemaMetadata() {
        Schema schema = createSchema();

        when(mockCatalog.schemaExists(SCHEMA_NAME))
                .thenReturn(false);

        database.addSchema(schema);

        when(mockCatalog.getSchema(SCHEMA_NAME))
                .thenReturn(schema);

        database.removeSchema(SCHEMA_NAME);

        verify(mockCatalog, times(1))
                .unregisterSchema(schema.getId());

        assertFalse(database.getSchemas().contains(schema));
    }

    @Test
    @DisplayName("Should throw when schema does not exist")
    void RemoveSchema_ShouldThrowWhenSchemaNotFound() {
        when(mockCatalog.getSchema("unknown"))
                .thenReturn(null);

        assertThrows(
                SchemaNotFoundException.class,
                () -> database.removeSchema("unknown")
        );

        verify(mockCatalog, never())
                .unregisterSchema(org.mockito.ArgumentMatchers.any());
    }

    // ==================================================
    // Access Control Tests
    // ==================================================

    @Test
    @DisplayName("Should assign permission to valid user")
    void GrantAccess_ShouldAssignPermission() {
        UUID userId = UUID.randomUUID();
        Permission permission = Permission.READ_DATABASE;

        when(mockSecurityManager.userExists(userId))
                .thenReturn(true);

        database.grantAccess(userId, permission);

        verify(mockSecurityManager, times(1))
                .grantPermission(
                        userId,
                        database.getId(),
                        permission
                );
    }

    @Test
    @DisplayName("Should throw when granting access to invalid user")
    void GrantAccess_ShouldThrowWhenUserInvalid() {
        UUID userId = UUID.randomUUID();
        Permission permission = Permission.READ_DATABASE;

        when(mockSecurityManager.userExists(userId))
                .thenReturn(false);

        assertThrows(
                UserNotFoundException.class,
                () -> database.grantAccess(userId, permission)
        );

        verify(mockSecurityManager, never())
                .grantPermission(
                        userId,
                        database.getId(),
                        permission
                );
    }

    @Test
    @DisplayName("Should revoke existing user permission")
    void RevokeAccess_ShouldRemovePermission() {
        UUID userId = UUID.randomUUID();
        Permission permission = Permission.READ_DATABASE;

        when(
                mockSecurityManager.hasPermission(
                        userId,
                        database.getId(),
                        permission
                )
        ).thenReturn(true);

        database.revokeAccess(userId, permission);

        verify(mockSecurityManager, times(1))
                .revokePermission(
                        userId,
                        database.getId(),
                        permission
                );
    }

    @Test
    @DisplayName("Should throw when revoking missing permission")
    void RevokeAccess_ShouldThrowWhenPermissionMissing() {
        UUID userId = UUID.randomUUID();
        Permission permission = Permission.READ_DATABASE;

        when(
                mockSecurityManager.hasPermission(
                        userId,
                        database.getId(),
                        permission
                )
        ).thenReturn(false);

        assertThrows(
                PermissionNotFoundException.class,
                () -> database.revokeAccess(userId, permission)
        );

        verify(mockSecurityManager, never())
                .revokePermission(
                        userId,
                        database.getId(),
                        permission
                );
    }

    // ==================================================
    // Statistics and Size Tests
    // ==================================================

    @Test
    @DisplayName("Should refresh database statistics")
    void UpdateStatistics_ShouldRefreshDatabaseStatistics() {
        DatabaseStatistics expectedStatistics =
                mock(DatabaseStatistics.class);

        when(
                mockCatalog.calculateStatistics(database.getId())
        ).thenReturn(expectedStatistics);

        DatabaseStatistics actualStatistics =
                database.updateStatistics();

        assertSame(expectedStatistics, actualStatistics);

        verify(mockCatalog, times(1))
                .updateDatabaseStatistics(
                        database.getId(),
                        expectedStatistics
                );
    }

    @Test
    @DisplayName("Should calculate the correct database size")
    void CalculateDatabaseSize_ShouldReturnCorrectSize() {
        long expectedSize = 1024L;

        when(
                mockStorageEngine.calculateDatabaseSize(
                        database.getId()
                )
        ).thenReturn(expectedSize);

        long actualSize = database.calculateDatabaseSize();

        assertEquals(expectedSize, actualSize);
    }

    // ==================================================
    // Recovery Tests
    // ==================================================

    @Test
    @DisplayName("Should update supported recovery model")
    void ChangeRecoveryModel_ShouldUpdateConfiguration() {
        when(mockRecoveryManager.supports(RecoveryModel.FULL))
                .thenReturn(true);

        database.changeRecoveryModel(RecoveryModel.FULL);

        assertEquals(
                RecoveryModel.FULL,
                database.getRecoveryModel()
        );

        verify(mockRecoveryManager, times(1))
                .updateModel(
                        database.getId(),
                        RecoveryModel.FULL
                );
    }

    @Test
    @DisplayName("Should accept supported recovery model")
    void ValidateRecoveryModel_ShouldAcceptSupportedMode() {
        when(mockRecoveryManager.supports(RecoveryModel.FULL))
                .thenReturn(true);

        boolean supported =
                database.validateRecoveryModel(RecoveryModel.FULL);

        assertTrue(supported);

        verify(mockRecoveryManager, times(1))
                .supports(RecoveryModel.FULL);
    }

    // ==================================================
    // Metadata Validation Tests
    // ==================================================

    @Test
    @DisplayName("Should report consistent database metadata")
    void ValidateDatabaseMetadata_ShouldRemainConsistent() {
        when(mockCatalog.validateMetadata(database.getId()))
                .thenReturn(true);

        boolean valid = database.validateMetadata();

        assertTrue(valid);

        verify(mockCatalog, times(1))
                .validateMetadata(database.getId());
    }

    // ==================================================
    // Exception Tests
    // ==================================================

    @Test
    @DisplayName("Should throw when storage is unavailable")
    void Open_ShouldThrowWhenStorageUnavailable() {
        doThrow(new StorageException("Storage unavailable"))
                .when(mockStorageEngine)
                .openDatabase(database.getId());

        assertThrows(
                DatabaseOpenException.class,
                database::open
        );

        assertEquals(DatabaseState.ERROR, database.getState());
    }

    @Test
    @DisplayName("Should throw when storage flush fails")
    void Close_ShouldThrowWhenFlushFails() {
        database.open();

        doThrow(new FlushException("Flush failed"))
                .when(mockStorageEngine)
                .flush();

        assertThrows(
                DatabaseCloseException.class,
                database::close
        );

        verify(mockStorageEngine, never())
                .closeDatabase(database.getId());
    }

    // ==================================================
    // Concurrency Tests
    // ==================================================

    @Test
    @DisplayName("Should open storage only once under concurrent calls")
    void ConcurrentOpen_ShouldMaintainDatabaseState()
            throws InterruptedException {

        int threadCount = 2;

        ExecutorService executor =
                Executors.newFixedThreadPool(threadCount);

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch =
                new CountDownLatch(threadCount);

        Runnable openTask = () -> {
            try {
                startLatch.await();
                database.open();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            } finally {
                finishLatch.countDown();
            }
        };

        executor.submit(openTask);
        executor.submit(openTask);

        startLatch.countDown();

        assertTrue(finishLatch.await(5, TimeUnit.SECONDS));

        executor.shutdown();

        assertEquals(DatabaseState.OPEN, database.getState());

        verify(mockStorageEngine, times(1))
                .openDatabase(database.getId());
    }

    @Test
    @DisplayName("Should prevent duplicate concurrent schema creation")
    void ConcurrentSchemaCreation_ShouldPreventConflict()
            throws InterruptedException {

        Schema schema = createSchema();

        when(mockCatalog.schemaExists(SCHEMA_NAME))
                .thenReturn(false)
                .thenReturn(true);

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(2);

        Runnable addSchemaTask = () -> {
            try {
                startLatch.await();

                try {
                    database.addSchema(schema);
                } catch (DuplicateSchemaException ignored) {
                    // One concurrent operation is expected to fail.
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            } finally {
                finishLatch.countDown();
            }
        };

        executor.submit(addSchemaTask);
        executor.submit(addSchemaTask);

        startLatch.countDown();

        assertTrue(finishLatch.await(5, TimeUnit.SECONDS));

        executor.shutdown();

        verify(mockCatalog, times(1))
                .registerSchema(schema);
    }

    @Test
    @DisplayName("Should safely process concurrent permission updates")
    void ConcurrentPermissionUpdate_ShouldBeThreadSafe()
            throws InterruptedException {

        UUID userId = UUID.randomUUID();
        Permission permission = Permission.READ_DATABASE;

        when(mockSecurityManager.userExists(userId))
                .thenReturn(true);

        when(
                mockSecurityManager.hasPermission(
                        userId,
                        database.getId(),
                        permission
                )
        ).thenReturn(true);

        ExecutorService executor =
                Executors.newFixedThreadPool(2);

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(2);

        executor.submit(() -> {
            try {
                startLatch.await();
                database.grantAccess(userId, permission);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            } finally {
                finishLatch.countDown();
            }
        });

        executor.submit(() -> {
            try {
                startLatch.await();
                database.revokeAccess(userId, permission);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            } finally {
                finishLatch.countDown();
            }
        });

        startLatch.countDown();

        assertTrue(finishLatch.await(5, TimeUnit.SECONDS));

        executor.shutdown();

        verify(mockSecurityManager, times(1))
                .grantPermission(
                        userId,
                        database.getId(),
                        permission
                );

        verify(mockSecurityManager, times(1))
                .revokePermission(
                        userId,
                        database.getId(),
                        permission
                );
    }

    // ==================================================
    // Test Data Helpers
    // ==================================================

    private Schema createSchema() {
        Schema schema = mock(Schema.class);

        when(schema.getId()).thenReturn(UUID.randomUUID());
        when(schema.getName()).thenReturn(SCHEMA_NAME);

        return schema;
    }
}