package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
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
    private final List<IConstraint> constraints;

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

        if (child instanceof IConstraint constraint) {
            addConstraint(constraint);
            return;
        }

        throw new IllegalArgumentException(
                "Unsupported table child: "
                        + child.getMetadataType());
    }

    public void addColumn(ColumnMetadata column) {
        Objects.requireNonNull(column);

        boolean duplicate = columns.stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(
                        column.getName()));

        if (duplicate) {
            throw new IllegalArgumentException(
                    "Duplicate column: " + column.getName());
        }

        columns.add(column);
    }

    public void addIndex(Index index) {
        Objects.requireNonNull(index);
        indexes.add(index);
    }

    public void addConstraint(IConstraint constraint) {
        Objects.requireNonNull(constraint);
        constraints.add(constraint);
    }

    public List<ColumnMetadata> getColumns() {
        return Collections.unmodifiableList(columns);
    }

    public Table copyAs(String newName, UUID targetSchemaId) {
        return null; // TODO: Implement
    }
}