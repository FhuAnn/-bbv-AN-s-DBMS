package core.classes.metadata;

import java.util.List;
import java.util.UUID;

import core.classes.abstraction.Constraint;
import core.enums.ConstraintType;


public class UniqueConstraint extends Constraint  {

    private final UUID id;
    private String name;
    private final UUID tableId;
    private final List<UUID> columnIds;

    public UniqueConstraint(
            String name,
            UUID tableId,
            List<UUID> columnIds) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.columnIds = columnIds == null
                ? List.of()
                : List.copyOf(columnIds);
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
