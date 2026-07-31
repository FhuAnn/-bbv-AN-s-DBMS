package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.metadata.Schema;
import core.classes.metadata.repository.SchemaRepository;
import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.schema.SchemaResponse;
import dbms_api.mappers.SchemaMapper;

@Service
public class SchemaService {
    private final SchemaRepository schemaRepository;
    private final SchemaMapper schemaMapper;

    public SchemaService(SchemaRepository schemaRepository, SchemaMapper schemaMapper) {
        this.schemaRepository = schemaRepository;
        this.schemaMapper = schemaMapper;
    }

    public SchemaResponse createSchema(UUID databaseId, CreateSchemaRequest request) {
        return null;
    }

    public SchemaResponse getSchema(UUID schemaId) {
        return null;
    }

    public List<SchemaResponse> listSchemas(UUID databaseId) {
        return List.of();
    }

    public SchemaResponse renameSchema(UUID schemaId, String newName) {
        return null;
    }

    public SchemaResponse copySchema(UUID schemaId, String newName) {
        return null;
    }

    public void deleteSchema(UUID schemaID) {
        // TODO
    }

    private Schema findSchema(UUID schemaId) {
        return null;
    }
}
