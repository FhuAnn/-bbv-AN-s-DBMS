package observer;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import enums.MetadataEventType;
import enums.MetadataType;

public record MetadataEvent(
        MetadataEventType eventType,
        MetadataType metadataType,
        UUID metadataId,
        String metadataName,
        Instant occurredAt,
        Map<String, Object> details
) {
    public MetadataEvent {
        details = details == null ? Map.of() : Map.copyOf(details);
    }
}

