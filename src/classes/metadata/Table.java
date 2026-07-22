package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import classes.abstraction.Constraint;
import classes.prototype.MetadataPrototype;
import enums.MetadataType;
import interfaces.IConstraint;
import interfaces.MetadataComponent;

public class Table extends AbstractMetadataComponent implements MetadataPrototype<Table> {

    private UUID id;
    private String name;
    private UUID schemaId;

    private final List<ColumnMetadata> columns;
    private final List<Index> indexes;
    private final List<Constraint> constraints;
    private final List<Row> rows;

    public Table(String name, UUID schemaId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Table name must not be blank");
        }

        if (schemaId == null) {
            throw new IllegalArgumentException("Schema ID must not be null.");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.schemaId = Objects.requireNonNull(schemaId);
        this.columns = new ArrayList<>();
        this.indexes = new ArrayList<>();
        this.constraints = new ArrayList<>();
        this.rows = new ArrayList<>();
    }

    public UUID getSchemaId() {
        return schemaId;
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.TABLE;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        List<MetadataComponent> children = new ArrayList<>();

        children.addAll(columns);
        children.addAll(indexes);
        children.addAll(constraints);

        return List.copyOf(children);
    }

    @Override
    public void addChild(MetadataComponent child) {
        if (child instanceof ColumnMetadata column) {
            addColumn(column);
            return;
        }

        if (child instanceof Index index) {
            addIndex(index);
            return;
        }

        if (child instanceof Constraint constraint) {
            addConstraint(constraint);
            return;
        }

        throw new IllegalArgumentException(
                "Unsupported table child: "
                        + child.getMetadataType());
    }

    public void addColumn(ColumnMetadata column) {
        // Objects.requireNonNull(column);

        // boolean duplicate = columns.stream()
        // .anyMatch(item -> item.getName().equalsIgnoreCase(
        // column.getName()));

        // if (duplicate) {
        // throw new IllegalArgumentException(
        // "Duplicate column: " + column.getName());
        // }

        // columns.add(column);
    }

    public void addIndex(Index index) {
        Objects.requireNonNull(index);
        indexes.add(index);
    }

    public void addConstraint(Constraint constraint) {
        // Objects.requireNonNull(constraint);
        // constraints.add(constraint);
    }

    public List<ColumnMetadata> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public Table copyAs(String newName, UUID targetSchemaId) {
        return null; // TODO: Implement
    }

    public Integer getColumnCount() {
        return columns.size();
    }

    public List<Constraint> getConstraints() {
        return Collections.unmodifiableList(constraints);
    }

    public List<Index> getIndexes() {
        return Collections.unmodifiableList(indexes);
    }

    public Boolean containsColumn(String colId) {
        return columns.stream()
                .anyMatch(column -> column.getName().equalsIgnoreCase(colId));
    }

    public ColumnMetadata getColumn(String colId) {
        return columns.stream()
                .filter(column -> column.getName().equalsIgnoreCase(colId))
                .findFirst()
                .orElse(null);
    }

    public Constraint removeConstraint(String constraintName) {
        Constraint removedConstraint = constraints.stream()
                .filter(constraint -> constraint.getName().equalsIgnoreCase(constraintName))
                .findFirst()
                .orElse(null);
        if (removedConstraint != null) {
            constraints.remove(removedConstraint);
        }
        return removedConstraint;
    }

    public Boolean containsConstraint(String constraintName) {
        return getConstraint(constraintName) != null;
    }

    public Constraint getConstraint(String constraintName) {
        return constraints.stream()
                .filter(constraint -> constraint.getName().equalsIgnoreCase(constraintName))
                .findFirst()
                .orElse(null);
    }

    // rows
    public Row insertRow(Row row) {
        // Objects.requireNonNull(row);
        // rows.add(row);
        return null;
    }

    public void truncate() {
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }

    public Integer getRowCount() {
        return rows.size();
    }

    public void removeRow(Row row) {
    }

    public Row deleteRow(UUID id) {
        return null;
    }

    public Row getRow(UUID id) {
        return null;
    }

    public Row updateRow(UUID id, Row newRow) {
        return null;
    }

    public ColumnMetadata removeColumn(String columnName) {
        return null;
    }

    // Index
    public Index getIndex(String indexName) {
        return indexes.stream()
                .filter(index -> index.getName().equalsIgnoreCase(indexName))
                .findFirst()
                .orElse(null);
    }

    public boolean containsIndex(String indexName) {
        return getIndex(indexName) != null;
    }

    public Index removeIndex(String indexName) {
        return null;
    }

    public Integer getIndexCount() {
        return indexes.size();
    }

    public Boolean isEmpty() {
        return true;
    }
    public Integer getConstraintCount() {
        return constraints.size();
    }
}