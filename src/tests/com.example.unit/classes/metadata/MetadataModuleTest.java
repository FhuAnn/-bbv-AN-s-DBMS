
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.metadata.Catalog;
import classes.metadata.ColumnMetadata;
import classes.metadata.Schema;
import classes.metadata.TableMetadata;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class MetadataModuleTest {

    private Catalog catalog;
    private Schema publicSchema;
    private TableMetadata usersTable;

    @BeforeEach
    void setUp() {
        catalog = new Catalog();
        catalog.setTables(new HashMap<>());
        catalog.setSchemas(new HashMap<>());

        // 1. Initialize Schema
        publicSchema = new Schema();
        publicSchema.setId(UUID.randomUUID());
        publicSchema.setName("public");
        publicSchema.setTables(new ArrayList<>());
        catalog.getSchemas().put(publicSchema.getId(), publicSchema);

        // 2. Setup Columns for Users table
        ColumnMetadata idCol = new ColumnMetadata();
        idCol.id = UUID.randomUUID();
        idCol.name = "id";
        idCol.type = DataType.BIGINT;
        idCol.nullable = false;
        idCol.position = 1;

        ColumnMetadata nameCol = new ColumnMetadata();
        nameCol.id = UUID.randomUUID();
        nameCol.name = "username";
        nameCol.type = DataType.VARCHAR;
        nameCol.nullable = true;
        nameCol.position = 2;

        // 3. Initialize Table
        usersTable = new TableMetadata();
        usersTable.id = UUID.randomUUID();
        usersTable.name = "users";
        usersTable.schemaId = publicSchema.id;
        usersTable.columns = Arrays.asList(idCol, nameCol);
        usersTable.constraints = new ArrayList<>();
        usersTable.indexes = new ArrayList<>();

        // Populate table-level stats
        TableStats stats = new TableStats();
        stats.rowCount = 15000L;
        stats.pageCount = 340L;
        stats.deadTuples = 45L;
        usersTable.stats = stats;

        // Register in Catalog
        catalog.tables.put(usersTable.id, usersTable);
        publicSchema.tables.add(usersTable);
    }

    @Test
    void testTableSchemaAssociationAndStats() {
        assertNotNull(catalog.tables.get(usersTable.id));
        assertEquals("users", catalog.tables.get(usersTable.id).name);
        assertEquals(15000L, usersTable.stats.rowCount);
        assertEquals(340L, usersTable.stats.pageCount);
        
        // Assert schema references are correct
        assertEquals(publicSchema.id, usersTable.schemaId);
        assertTrue(publicSchema.tables.contains(usersTable));
    }

    @Test
    void testColumnDefinitionsAndTypes() {
        TableMetadata retrievedTable = catalog.tables.get(usersTable.id);
        List<ColumnMetadata> cols = retrievedTable.columns;

        assertEquals(2, cols.size());
        
        ColumnMetadata primaryCol = cols.get(0);
        assertEquals("id", primaryCol.name);
        assertEquals(DataType.BIGINT, primaryCol.type);
        assertFalse(primaryCol.nullable);
        assertEquals(1, primaryCol.position);
    }

    @Test
    void testForeignKeyConstraintProperties() {
        ForeignKeyConstraint fk = new ForeignKeyConstraint();
        fk.id = UUID.randomUUID();
        fk.type = ConstraintType.FOREIGN_KEY;
        fk.columnIds = Collections.singletonList(usersTable.columns.get(0).id);
        fk.referenceTableId = UUID.randomUUID();
        fk.referenceColumnId = UUID.randomUUID();
        fk.onDelete = ReferentialAction.CASCADE;
        fk.onUpdate = ReferentialAction.RESTRICT;

        usersTable.constraints.add(fk);

        assertEquals(1, usersTable.constraints.size());
        ForeignKeyConstraint checkedFk = (ForeignKeyConstraint) usersTable.constraints.get(0);
        assertEquals(ReferentialAction.CASCADE, checkedFk.onDelete);
        assertEquals(ReferentialAction.RESTRICT, checkedFk.onUpdate);
    }
}