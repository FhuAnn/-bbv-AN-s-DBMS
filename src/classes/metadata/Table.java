
package classes.metadata;

import java.util.ArrayList;
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
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.constraints = new ArrayList<>();
        this.indexes = new ArrayList<>();
    }

    public Table(String name, UUID schemaId) {
        validateName(name);

        if (schemaId == null) {
            throw new IllegalArgumentException("Schema ID must not be null.");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.schemaId = schemaId;
        this.columns = new ArrayList<>();
        this.rows = new ArrayList<>();
        this.constraints = new ArrayList<>();
        this.indexes = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Table ID must not be null.");
        }

        this.id = id;
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
        if (schemaId == null) {
            throw new IllegalArgumentException("Schema ID must not be null.");
        }

        this.schemaId = schemaId;
    }

    public List<ColumnMetadata> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public void setColumns(List<ColumnMetadata> columns) {
        // TODO: Implement
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public void setRows(List<Row> rows) {
        // TODO: Implement
    }

    public List<Constraint> getConstraints() {
        return Collections.unmodifiableList(constraints);
    }

    public void setConstraints(List<Constraint> constraints) {
        // TODO: Implement
    }

    public List<Index> getIndexes() {
        return Collections.unmodifiableList(indexes);
    }

    public void setIndexes(List<Index> indexes) {
        // TODO: Implement
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Table name must not be null or blank.");
        }
        this.name = newName;
    }

    public void addColumn(ColumnMetadata column) {
        if (column == null) {
            throw new IllegalArgumentException("Column must not be null.");
        }

        String columnName = requireObjectName(column.getName(), "Column");

        if (containsColumn(columnName)) {
            throw new IllegalArgumentException(
                    "Column already exists: " + columnName);
        }

        columns.add(column);
    }

    public ColumnMetadata getColumn(String columnName) {
        if (columnName == null) {
            return null;
        }

        for (ColumnMetadata column : columns) {
            if (columnName.equals(column.getName())) {
                return column;
            }
        }

        return null;
    }

    public boolean containsColumn(String columnName) {
        return getColumn(columnName) != null;
    }

    public ColumnMetadata removeColumn(String columnName) {
        ColumnMetadata column = getColumn(columnName);

        if (column == null) {
            return null;
        }

        columns.remove(column);
        return column;
    }

    public void renameColumn(String currentName, String newName) {

    }

    public Row insertRow(Row row) {
        if (row == null) {
            throw new IllegalArgumentException("Row must not be null.");
        }

        if (row.getId() == null) {
            throw new IllegalArgumentException("Row ID must not be null.");
        }

        rows.add(row);
        return row;
    }

    public Row getRow(UUID rowId) {
        if (rowId == null) {
            return null;
        }

        for (Row row : rows) {
            if (rowId.equals(row.getId())) {
                return row;
            }
        }

        return null;
    }

    public boolean containsRow(UUID rowId) {
        return false;
    }

    public Row updateRow(UUID rowId, Row newRow) {
        if (rowId == null || newRow == null) {
            return null;
        }

        for (int index = 0; index < rows.size(); index++) {
            Row currentRow = rows.get(index);

            if (rowId.equals(currentRow.getId())) {
                rows.set(index, newRow);
                return newRow;
            }
        }

        return null;
    }

    public Row deleteRow(UUID rowId) {
        Row row = getRow(rowId);

        if (row == null) {
            return null;
        }

        rows.remove(row);
        return row;
    }

    public void truncate() {
        rows.clear();
    }

    public void addConstraint(Constraint constraint) {
        if (constraint == null) {
            throw new IllegalArgumentException("Constraint must not be null.");
        }

        String constraintName = requireObjectName(
                constraint.getName(),
                "Constraint");

        if (containsConstraint(constraintName)) {
            throw new IllegalArgumentException(
                    "Constraint already exists: " + constraintName);
        }

        constraints.add(constraint);
    }

    public Constraint getConstraint(String constraintName) {
        if (constraintName == null) {
            return null;
        }

        for (Constraint constraint : constraints) {
            if (constraintName.equals(constraint.getName())) {
                return constraint;
            }
        }

        return null;
    }

    public boolean containsConstraint(String constraintName) {
        return getConstraint(constraintName) != null;
    }

    public Constraint removeConstraint(String constraintName) {
        Constraint constraint = getConstraint(constraintName);

        if (constraint == null) {
            return null;
        }

        constraints.remove(constraint);
        return constraint;
    }

    public void addIndex(Index index) {
        if (index == null) {
            throw new IllegalArgumentException("Index must not be null.");
        }

        String indexName = requireObjectName(index.getName(), "Index");

        if (containsIndex(indexName)) {
            throw new IllegalArgumentException(
                    "Index already exists: " + indexName);
        }

        indexes.add(index);
    }

    public Index getIndex(String indexName) {
        if (indexName == null) {
            return null;
        }

        for (Index index : indexes) {
            if (indexName.equals(index.getName())) {
                return index;
            }
        }

        return null;
    }

    public boolean containsIndex(String indexName) {
        return getIndex(indexName) != null;
    }

    public Index removeIndex(String indexName) {
        Index index = getIndex(indexName);

        if (index == null) {
            return null;
        }

        indexes.remove(index);
        return index;
    }

    public int getColumnCount() {
        return columns.size();
    }

    public int getRowCount() {
        return rows.size();
    }

    public int getConstraintCount() {
        return constraints.size();
    }

    public int getIndexCount() {
        return indexes.size();
    }

    public boolean isEmpty() {
        return rows.isEmpty();
    }

    private static void validateName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Table name must not be null or blank.");
        }
    }

    private static String requireObjectName(
            String value,
            String objectType) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    objectType + " name must not be null or blank.");
        }

        return value;
    }
}