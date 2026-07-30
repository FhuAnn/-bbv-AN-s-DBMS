package dbms_api.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import dbms_api.dto.CreateSchemaRequest;
import dbms_api.dto.SchemaResponse;

@Component
public class SchemaMapper {
    public CreateSchemaCommand toCreateCommand(UUID databaseId, CreateSchemaRequest request) {
        // TODO: Implement the mapping logic from CreateSchemaRequest to
        // CreateSchemaCommand
        return null;
    }

    public SchemaResponse toResponse(Schema schema) {
        // TODO: Implement the mapping logic from Schema to SchemaResponse
        return null;
    }
}