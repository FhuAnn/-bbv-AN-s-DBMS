package dbms_api.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import core.classes.metadata.Schema;
import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.schema.SchemaResponse;

@Component
public class SchemaMapper {
    public Schema toDomain(UUID databaseId, CreateSchemaRequest request) {
        return null;
    }
    public SchemaResponse toResponse(Schema schema) {
        return null;
    }
}
