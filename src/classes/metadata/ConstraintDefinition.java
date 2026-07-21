package classes.metadata;

import java.util.List;
import java.util.UUID;

public class ConstraintDefinition {

    public enum ConstraintType {
        PRIMARY_KEY,
        UNIQUE,
        NOT_NULL,
        FOREIGN_KEY,
        CHECK
    }

    private final String name;
    private final ConstraintType type;
    private final UUID tableId;
    private final List<UUID> columnIds;
    private final UUID referencedTableId;
    private final List<UUID> referencedColumnIds;
    private final String expression;

    public ConstraintDefinition(
            String name,
            ConstraintType type,
            UUID tableId,
            List<UUID> columnIds,
            UUID referencedTableId,
            List<UUID> referencedColumnIds,
            String expression) {
        this.name = name;
        this.type = type;
        this.tableId = tableId;
        this.columnIds = columnIds == null
                ? List.of()
                : List.copyOf(columnIds);
        this.referencedTableId = referencedTableId;
        this.referencedColumnIds = referencedColumnIds == null
                ? List.of()
                : List.copyOf(referencedColumnIds);
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public ConstraintType getType() {
        return type;
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

    public String getExpression() {
        return null;
    }

}