package dbms_api.dtos.schema;

import java.util.UUID;

public record SchemaResponse(
        UUID id,
        UUID databaseId,
        UUID ownerId,
        String name,
        int tableCount,
        int viewCount) {
}
