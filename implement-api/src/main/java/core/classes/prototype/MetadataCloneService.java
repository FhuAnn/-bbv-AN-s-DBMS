package core.classes.prototype;
import java.util.UUID;

import core.classes.metadata.Schema;
import core.classes.metadata.Table;

public final class MetadataCloneService  {
    public Table cloneTable(Table source, String newName, UUID targetSchemaId) {
        return source.copyAs(newName, targetSchemaId);
    }

    public Schema cloneSchema(Schema source, String newName, UUID targetDatabaseId) {
        return source.copyAs(newName, targetDatabaseId);
    }
}
