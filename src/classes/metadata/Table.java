
package classes.metadata;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Table {

    private UUID id;
    private String name;
    private UUID schemaId;
    private List<ColumnMetadata> columns;
    private List<Row> rows;
    private List<Constraint> constraints;
    private List<Index> indexes;

    public Table() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.schemaId = null;
        this.columns = Collections.emptyList();
        this.rows = Collections.emptyList();
        this.constraints = Collections.emptyList();
        this.indexes = Collections.emptyList();
    }

    public Table(String name, UUID schemaId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.schemaId = schemaId;
        this.columns = Collections.emptyList();
        this.rows = Collections.emptyList();
        this.constraints = Collections.emptyList();
        this.indexes = Collections.emptyList();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        // TODO: Implement
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // TODO: Implement
    }

    public UUID getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(UUID schemaId) {
        // TODO: Implement
    }

    public List<ColumnMetadata> getColumns() {
        return Collections.emptyList();
    }

    public void setColumns(List<ColumnMetadata> columns) {
        // TODO: Implement
    }

    public List<Row> getRows() {
        return Collections.emptyList();
    }

    public void setRows(List<Row> rows) {
        // TODO: Implement
    }

    public List<Constraint> getConstraints() {
        return Collections.emptyList();
    }

    public void setConstraints(List<Constraint> constraints) {
        // TODO: Implement
    }

    public List<Index> getIndexes() {
        return Collections.emptyList();
    }

    public void setIndexes(List<Index> indexes) {
        // TODO: Implement
    }

    public void rename(String newName) {
        // TODO: Implement
    }

    public void addColumn(ColumnMetadata column) {
        // TODO: Implement
    }

    public ColumnMetadata getColumn(String columnName) {
        return null;
    }

    public boolean containsColumn(String columnName) {
        return false;
    }

    public ColumnMetadata removeColumn(String columnName) {
        return null;
    }

    public void renameColumn(String currentName, String newName) {
        // TODO: Implement
    }

    public Row insertRow(Row row) {
        return null;
    }

    public Row getRow(UUID rowId) {
        return null;
    }

    public boolean containsRow(UUID rowId) {
        return false;
    }

    public Row updateRow(UUID rowId, Row newRow) {
        return null;
    }

    public Row deleteRow(UUID rowId) {
        return null;
    }

    public void truncate() {
        // TODO: Implement
    }

    public void addConstraint(Constraint constraint) {
        // TODO: Implement
    }

    public Constraint getConstraint(String constraintName) {
        return null;
    }

    public boolean containsConstraint(String constraintName) {
        return false;
    }

    public Constraint removeConstraint(String constraintName) {
        return null;
    }

    public void addIndex(Index index) {
        // TODO: Implement
    }

    public Index getIndex(String indexName) {
        return null;
    }

    public boolean containsIndex(String indexName) {
        return false;
    }

    public Index removeIndex(String indexName) {
        return null;
    }

    public int getColumnCount() {
        return 0;
    }

    public int getRowCount() {
        return 0;
    }

    public int getConstraintCount() {
        return 0;
    }

    public int getIndexCount() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }
}