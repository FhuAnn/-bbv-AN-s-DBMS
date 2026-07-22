package classes.metadata;

import java.util.List;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import classes.abstraction.Constraint;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.IConstraint;
import interfaces.MetadataComponent;

public class NotNullConstraint extends Constraint {

    private final UUID id;
    private String name;
    private final UUID tableId;
    private final UUID columnId;

    public NotNullConstraint(
            String name,
            UUID tableId,
            UUID columnId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.columnId = columnId;
    }

    public UUID getTableId() {
        return tableId;
    }

    public UUID getColumnId() {
        return columnId;
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.NOT_NULL;
    }

    @Override
    public boolean validateDefinition() {
        return name != null
                && !name.isBlank()
                && tableId != null
                && columnId != null;
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.CONSTRAINT;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

    @Override
    public List<UUID> getColumnIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumnIds'");
    }

}
