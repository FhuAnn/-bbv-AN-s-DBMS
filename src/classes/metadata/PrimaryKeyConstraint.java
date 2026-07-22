package classes.metadata;

import java.util.List;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import classes.abstraction.Constraint;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.IConstraint;
import interfaces.MetadataComponent;

public class PrimaryKeyConstraint extends Constraint {

    private final UUID id;
    private String name;
    private final UUID tableId;
    private final List<UUID> columnIds;

    public PrimaryKeyConstraint(
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
        return ConstraintType.PRIMARY_KEY;
    }

    @Override
    public boolean validateDefinition() {
        return name != null
                && !name.isBlank()
                && tableId != null
                && columnIds != null
                && !columnIds.isEmpty()
                && columnIds.stream().allMatch(id -> id != null);
    }

}
