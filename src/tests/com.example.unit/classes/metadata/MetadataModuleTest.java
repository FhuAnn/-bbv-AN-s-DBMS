
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.metadata.Catalog;
import classes.metadata.ColumnMetadata;
import classes.metadata.ForeignKeyConstraint;
import classes.metadata.Schema;
import classes.metadata.TableMetadata;
import classes.metadata.TableStats;
import enums.DataType;
import enums.ReferentialAction;

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
        idCol.setId(UUID.randomUUID());
        idCol.setName("id");
        idCol.setType(DataType.BIGINT);
        idCol.setNullable(false);
        idCol.setPosition(1);

        ColumnMetadata nameCol = new ColumnMetadata();
        nameCol.setId(UUID.randomUUID());
        nameCol.setName("username");
        nameCol.setType(DataType.VARCHAR);
        nameCol.setNullable(true);
        nameCol.setPosition(2);

        // 3. Initialize Table
        usersTable = new TableMetadata();
        usersTable.setId(UUID.randomUUID());
        usersTable.setName("users");
        usersTable.setSchemaId(publicSchema.getId());
        usersTable.setColumns(Arrays.asList(idCol, nameCol));
        usersTable.setConstraints(new ArrayList<>());
        usersTable.setIndexes(new ArrayList<>());

        // Populate table-level stats
        TableStats stats = new TableStats();
        stats.setRowCount(15000L);
        stats.setPageCount(340L);
        stats.setDeadTuples(45L);
        usersTable.setStats(stats);

        // Register in Catalog
        catalog.putTable(usersTable);
        publicSchema.getTables().add(usersTable);
    }

    @Test
    void testTableSchemaAssociationAndStats() {
       
    }

    @Test
    void testColumnDefinitionsAndTypes() {
        
    }

    @Test
    void testForeignKeyConstraintProperties() {
        ForeignKeyConstraint fk = new ForeignKeyConstraint(
            UUID.randomUUID(),
            Collections.singletonList(usersTable.getColumns().get(0).getId()),
            UUID.randomUUID(),
            UUID.randomUUID(),
            ReferentialAction.CASCADE,
            ReferentialAction.RESTRICT
        );

        usersTable.addConstraint(fk);

        assertEquals(1, usersTable.getConstraints().size());
        ForeignKeyConstraint checkedFk = (ForeignKeyConstraint) usersTable.getConstraints().get(0);
        assertEquals(ReferentialAction.CASCADE, checkedFk.getOnDelete());
        assertEquals(ReferentialAction.RESTRICT, checkedFk.getOnUpdate());
    }
}