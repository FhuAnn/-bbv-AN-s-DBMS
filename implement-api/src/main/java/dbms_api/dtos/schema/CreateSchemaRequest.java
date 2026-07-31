package dbms_api.dtos.schema;

import java.util.UUID;

public record CreateSchemaRequest(
        String name,
        UUID ownerId) {
}
