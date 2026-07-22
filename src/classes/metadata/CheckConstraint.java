package classes.metadata;

import java.util.List;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.IConstraint;
import interfaces.MetadataComponent;

public class CheckConstraint extends AbstractMetadataComponent implements IConstraint {

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
    public MetadataType getMetadataType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMetadataType'");
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

   
}
