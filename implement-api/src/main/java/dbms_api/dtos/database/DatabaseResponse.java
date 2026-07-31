package dbms_api.dtos.database;

import java.util.UUID;

public record DatabaseResponse(
        UUID id,
        String name,
        String state,
        boolean readOnly,
        int schemaCount) {
}
