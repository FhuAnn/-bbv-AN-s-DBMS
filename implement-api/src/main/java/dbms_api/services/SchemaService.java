package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.metadata.Schema;
import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.schema.SchemaResponse;
import dbms_api.mappers.SchemaMapper;
import dbms_api.repositories.ISchemaRepository;

@Service
public class SchemaService {
    private final ISchemaRepository schemaRepository;
    private final SchemaMapper schemaMapper;

    public SchemaService(ISchemaRepository schemaRepository, SchemaMapper schemaMapper) {
        this.schemaRepository = schemaRepository;
        this.schemaMapper = schemaMapper;
    }

    public SchemaResponse createSchema(UUID databaseId, CreateSchemaRequest request) {
        var schemaDomain = schemaMapper.toDomain(databaseId, request);
        schemaDomain = schemaRepository.save(schemaDomain);
        return schemaMapper.toResponse(schemaDomain);
    }

    public SchemaResponse getSchema(UUID schemaId) {
        var schema = schemaRepository.findById(schemaId).orElseThrow(() -> new RuntimeException("Schema not found"));
        return schemaMapper.toResponse(schema);
    }

    public List<SchemaResponse> listSchemas(UUID databaseId) {
        return schemaRepository.findByDatabaseId(databaseId).stream()
                .map(schemaMapper::toResponse)
                .toList();
    }

    public SchemaResponse renameSchema(UUID schemaId, String newName) {
        var schema = schemaRepository.findById(schemaId).orElseThrow(() -> new RuntimeException("Schema not found"));
        schema.setName(newName);
        var savedSchema = schemaRepository.save(schema);
        return schemaMapper.toResponse(savedSchema);
    }

    public SchemaResponse copySchema(UUID schemaId, String newName) {
        var schema = schemaRepository.findById(schemaId).orElseThrow(() -> new RuntimeException("Schema not found"));
        var copiedSchema = schema.copyAs(newName, schemaId);
        copiedSchema = schemaRepository.save(copiedSchema);
        return schemaMapper.toResponse(copiedSchema);
    }

    public void deleteSchema(UUID schemaID) {
        schemaRepository.findById(schemaID).orElseThrow(() -> new RuntimeException("Schema not found"));
        schemaRepository.deleteById(schemaID);
    }

    private Schema findSchema(UUID schemaId) {
        var schema = schemaRepository.findById(schemaId).orElseThrow(() -> new RuntimeException("Schema not found"));
        return schema;
    }
}
