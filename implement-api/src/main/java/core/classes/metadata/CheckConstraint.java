package core.classes.metadata;

import java.util.List;
import java.util.UUID;

import core.classes.abstraction.AbstractMetadataComponent;
import core.classes.abstraction.Constraint;
import core.enums.ConstraintType;
import core.enums.MetadataType;
import core.interfaces.IConstraint;
import core.interfaces.MetadataComponent;

public class CheckConstraint extends Constraint {

    private final UUID id;
    private String name;
    private final UUID tableId;
    private final String expression;

    public CheckConstraint(
            String name,
            UUID tableId,
            String expression) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tableId = tableId;
        this.expression = expression;
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

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

    @Override
    public List<UUID> getColumnIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getColumnIds'");
    }

    public Constraint copyAs(String newName, UUID newParentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyAs'");
    }
}
