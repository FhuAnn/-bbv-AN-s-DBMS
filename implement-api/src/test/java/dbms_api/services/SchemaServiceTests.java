package dbms_api.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import core.classes.metadata.Schema;
import core.classes.metadata.repository.SchemaRepository;
import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.schema.SchemaResponse;
import dbms_api.mappers.SchemaMapper;

class SchemaServiceTests {

    @Mock
    private SchemaRepository schemaRepository;

    @Mock
    private SchemaMapper schemaMapper;

    @InjectMocks
    private SchemaService schemaService;

    private UUID databaseId;
    private UUID schemaId;
    private UUID ownerId;

    private Schema schema;
    private SchemaResponse response;

    @BeforeEach
    void setUp() {
        databaseId = UUID.randomUUID();
        schemaId = UUID.randomUUID();
        ownerId = UUID.randomUUID();

        schema = null;

        response = new SchemaResponse(
                schemaId,
                databaseId,
                ownerId,
                "sales",
                0,
                0);
    }

    @Test
    void createSchema_ShouldMapSaveAndReturnResponse() {
        CreateSchemaRequest request = new CreateSchemaRequest("sales", ownerId);

        when(schemaMapper.toDomain(databaseId, request))
                .thenReturn(schema);

        when(schemaRepository.save(schema))
                .thenReturn(schema);

        when(schemaMapper.toResponse(schema))
                .thenReturn(response);

        SchemaResponse result = schemaService.createSchema(databaseId, request);

        assertSame(response, result);

        verify(schemaMapper).toDomain(databaseId, request);
        verify(schemaRepository).save(schema);
        verify(schemaMapper).toResponse(schema);
    }

    @Test
    void getSchema_ShouldFindAndMapSchema() {
        when(schemaRepository.findById(schemaId))
                .thenReturn(Optional.ofNullable(schema));

        when(schemaMapper.toResponse(schema))
                .thenReturn(response);

        SchemaResponse result = schemaService.getSchema(schemaId);

        assertSame(response, result);

        verify(schemaRepository).findById(schemaId);
        verify(schemaMapper).toResponse(schema);
    }

    @Test
    void listSchemas_ShouldMapAllSchemas() {
        SchemaResponse secondResponse = new SchemaResponse(
                UUID.randomUUID(),
                databaseId,
                ownerId,
                "reporting",
                0,
                0);

        when(schemaRepository.findByDatabaseId(databaseId))
                .thenReturn(List.of(schema, schema));

        when(schemaMapper.toResponse(schema))
                .thenReturn(response, secondResponse);

        List<SchemaResponse> result = schemaService.listSchemas(databaseId);

        assertEquals(2, result.size());
        assertSame(response, result.get(0));
        assertSame(secondResponse, result.get(1));

        verify(schemaRepository).findByDatabaseId(databaseId);
    }

    @Test
    void renameSchema_ShouldFindSaveAndReturnResponse() {
        String newName = "sales_management";

        when(schemaRepository.findById(schemaId))
                .thenReturn(Optional.ofNullable(schema));

        when(schemaRepository.save(schema))
                .thenReturn(schema);

        when(schemaMapper.toResponse(schema))
                .thenReturn(response);

        SchemaResponse result = schemaService.renameSchema(schemaId, newName);

        assertSame(response, result);

        verify(schemaRepository).findById(schemaId);
        verify(schemaRepository).save(schema);
        verify(schemaMapper).toResponse(schema);
    }

    @Test
    void copySchema_ShouldFindSaveAndReturnResponse() {
        String newName = "sales_backup";

        when(schemaRepository.findById(schemaId))
                .thenReturn(Optional.ofNullable(schema));

        when(schemaRepository.save(schema))
                .thenReturn(schema);

        when(schemaMapper.toResponse(schema))
                .thenReturn(response);

        SchemaResponse result = schemaService.copySchema(schemaId, newName);

        assertSame(response, result);

        verify(schemaRepository).findById(schemaId);
        verify(schemaRepository).save(schema);
        verify(schemaMapper).toResponse(schema);
    }

    @Test
    void deleteSchema_ShouldDeleteSchema() {
        when(schemaRepository.findById(schemaId))
                .thenReturn(Optional.ofNullable(schema));

        schemaService.deleteSchema(schemaId);

        verify(schemaRepository).findById(schemaId);
        verify(schemaRepository).deleteById(schemaId);
    }
}
