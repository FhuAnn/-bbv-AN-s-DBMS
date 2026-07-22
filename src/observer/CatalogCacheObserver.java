package observer;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import enums.MetadataEventType;

public final class CatalogCacheObserver implements IMetadataObserver {
    private final Map<UUID, String> namesById = new ConcurrentHashMap<>();

    @Override
    public void onMetadataChanged(MetadataEvent event) {
        if (event.eventType() == MetadataEventType.DELETED) {
            namesById.remove(event.metadataId());
        } else {
            namesById.put(event.metadataId(), event.metadataName());
        }
    }

    public boolean contains(UUID metadataId) {
        return namesById.containsKey(metadataId);
    }

    public String getName(UUID metadataId) {
        return namesById.get(metadataId);
    }
}
