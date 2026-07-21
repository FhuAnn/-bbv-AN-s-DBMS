package classes.metadata;

import java.util.UUID;

import enums.ConstraintType;
import interfaces.IConstraint;

public class CheckConstraint implements IConstraint {

    private final UUID id;
    private final String name;
    private final UUID tableId;
    private final String expression;

    public CheckConstraint(
            String name,
            UUID tableId,
            String expression
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.expression = expression;
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

    public String getExpression() {
        return expression;
    }

    @Override
    public ConstraintType getType() {
        return null;
    }

    @Override
    public boolean validateDefinition() {
        return false;
    }
}
