import org.junit.jupiter.api.DisplayName;

@DisplayName("Database Unit Tests")
class DatabaseTests {

    // ============================
    // Dependencies
    // ============================

    private Database database;

    @Mock
    private Catalog catalog;

    @Mock
    private StorageEngine storageEngine;

    @Mock
    private TransactionManager transactionManager;

    @Mock
    private SecurityManager securityManager;

    // ============================
    // Setup
    // ============================

    @BeforeEach
    void setUp();

    @AfterEach
    void tearDown();

    // ============================
    // Constructor Tests
    // ============================

    @Test
    void shouldCreateDatabase();

    @Test
    void shouldGenerateDatabaseId();

    @Test
    void shouldInitializeCatalog();

    ...

    // ============================
    // Execute SQL Tests
    // ============================

    @Nested
    class ExecuteSQLTests{

        @Test
        void shouldExecuteSelect();

        @Test
        void shouldExecuteInsert();

        @Test
        void shouldRollbackOnFailure();

    }

    // ============================
    // Metadata Tests
    // ============================

    @Nested
    class MetadataTests{

        @Test
        void shouldReturnCatalog();

        @Test
        void shouldReturnStorageEngine();

    }

}