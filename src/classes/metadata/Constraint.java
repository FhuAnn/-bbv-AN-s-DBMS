package classes.metadata;

import java.util.List;
import java.util.UUID;

import enums.ConstraintType;

public class Constraint {
    public UUID id;
    public ConstraintType type;
    public List<UUID> columnIds;

    public Constraint(UUID id, ConstraintType type, List<UUID> columnIds) {
        this.id = id;
        this.type = type;
        this.columnIds = columnIds;
    }

    public Constraint() {
    }
}
