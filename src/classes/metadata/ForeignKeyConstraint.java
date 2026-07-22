package classes.metadata;

import java.util.List;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import classes.abstraction.Constraint;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.IConstraint;
import interfaces.MetadataComponent;

public class ForeignKeyConstraint extends Constraint {

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
        this.tableId = tableId;
        this.columnIds = columnIds == null
                ? List.of()
                : List.copyOf(columnIds);
        this.referencedTableId = referencedTableId;
        this.referencedColumnIds = referencedColumnIds == null
                ? List.of()
                : List.copyOf(referencedColumnIds);
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
    public boolean validateDefinition() {
        return false;
    }

    @Override
    public ConstraintType getType() {
        return ConstraintType.FOREIGN_KEY;
    }

}
