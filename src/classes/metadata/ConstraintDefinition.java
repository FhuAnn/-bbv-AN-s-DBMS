package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

import classes.abstraction.AbstractMetadataComponent;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.MetadataComponent;

public class ConstraintDefinition {

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
    public String getName() {
        return null;
    }
}