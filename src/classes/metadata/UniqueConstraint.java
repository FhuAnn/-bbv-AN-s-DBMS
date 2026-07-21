package classes.metadata;

import java.util.List;
import java.util.UUID;

import enums.ConstraintType;
import interfaces.IConstraint;

public class UniqueConstraint implements IConstraint {

    private final UUID id;
    private final String name;
    private final UUID tableId;
    private final List<UUID> columnIds;

    public UniqueConstraint(
            String name,
            UUID tableId,
            List<UUID> columnIds
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.columnIds = columnIds == null
                ? List.of()
                : List.copyOf(columnIds);
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

    public List<UUID> getColumnIds() {
        return columnIds;
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.UNIQUE;
    }

    @Override
    public boolean validateDefinition() {
        return false;
    }
}
