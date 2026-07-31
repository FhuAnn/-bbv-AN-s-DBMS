package core.classes.abstraction;
import java.util.List;
import java.util.UUID;

import core.classes.prototype.MetadataPrototype;
import core.enums.ConstraintType;
import core.enums.MetadataType;
import core.interfaces.MetadataComponent;


public abstract class Constraint extends AbstractMetadataComponent implements MetadataPrototype<Constraint> {

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

    public Constraint copyAs(String newName, UUID newParentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copyAs'");
    }
}
