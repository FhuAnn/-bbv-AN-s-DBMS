package classes.abstraction;

import java.util.List;
import java.util.UUID;

import classes.metadata.Table;
import classes.prototype.MetadataPrototype;
import enums.ConstraintType;
import enums.MetadataType;
import interfaces.MetadataComponent;

public abstract class Constraint extends AbstractMetadataComponent implements MetadataPrototype<Table> {

    public abstract UUID getTableId();

    public abstract List<UUID> getColumnIds();

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.CONSTRAINT;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

    public abstract boolean validateDefinition();
    public abstract ConstraintType getType();
    @Override
    public Table copyAs(String newName, UUID newParentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyAs'");
    }
}
