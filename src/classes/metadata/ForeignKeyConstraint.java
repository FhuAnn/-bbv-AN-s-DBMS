package classes.metadata;

import java.util.List;
import java.util.UUID;

import enums.ConstraintType;
import interfaces.IConstraint;

public class ForeignKeyConstraint implements IConstraint {

    private final UUID id;
    private final String name;
    private final UUID tableId;
    private final List<UUID> columnIds;
    private final UUID referencedTableId;
    private final List<UUID> referencedColumnIds;

    public ForeignKeyConstraint(
            String name,
            UUID tableId,
            List<UUID> columnIds,
            UUID referencedTableId,
            List<UUID> referencedColumnIds) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.columnIds = columnIds == null
                ? List.of()
                : List.copyOf(columnIds);
        this.referencedTableId = referencedTableId;
        this.referencedColumnIds = referencedColumnIds == null
                ? List.of()
                : List.copyOf(referencedColumnIds);
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public String getName() {
        return null;

    }

    public UUID getTableId() {
        return null;

    }

    public List<UUID> getColumnIds() {
        return null;

    }

    public UUID getReferencedTableId() {
        return null;

    }

    public List<UUID> getReferencedColumnIds() {
        return null;

    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.FOREIGN_KEY;
    }

    @Override
    public boolean validateDefinition() {
        return false;
    }
}
