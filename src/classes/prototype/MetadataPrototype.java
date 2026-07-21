package classes.prototype;

import java.util.UUID;

public interface MetadataPrototype<T> {
    T copyAs(String newName, UUID newParentId);
}
