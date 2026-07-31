package core.classes.metadata;

import java.util.List;
import java.util.UUID;

import core.classes.abstraction.AbstractMetadataComponent;
import core.classes.abstraction.Constraint;
import core.enums.ConstraintType;
import core.enums.MetadataType;
import core.interfaces.IConstraint;
import core.interfaces.MetadataComponent;

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
