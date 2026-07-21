package classes.metadata;

import java.util.UUID;

import enums.ConstraintType;
import interfaces.IConstraint;

public class NotNullConstraint implements IConstraint {

    private final UUID id;
    private final String name;
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

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
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
}
