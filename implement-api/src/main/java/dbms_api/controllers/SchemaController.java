package dbms_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbms_api.dtos.schema.CopySchemaRequest;
import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.schema.RenameSchemaRequest;
import dbms_api.dtos.schema.SchemaResponse;
import dbms_api.services.SchemaService;

@RestController
@RequestMapping("/api/v1")
public class SchemaController {
    private final SchemaService schemaService;

    public SchemaController(SchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @PostMapping("/databases/{databaseId}/schemas")
    public ResponseEntity<SchemaResponse> createSchema(@PathVariable UUID databaseId,
            @RequestBody CreateSchemaRequest request) {
        var result = schemaService.createSchema(databaseId, request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/schemas/{schemaId}")
    public ResponseEntity<SchemaResponse> getSchema(@PathVariable UUID schemaId) {
        var result = schemaService.getSchema(schemaId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/databases/{databaseId}/schemas")
    public ResponseEntity<List<SchemaResponse>> listSchemas(@PathVariable UUID databaseId) {
        var result = schemaService.listSchemas(databaseId);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/schemas/{schemaId}/name")
    public ResponseEntity<SchemaResponse> renameSchema(@PathVariable UUID schemaId,
            @RequestBody RenameSchemaRequest request) {
        var result = schemaService.renameSchema(schemaId, request.name());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/schemas/{schemaId}/copies")
    public ResponseEntity<SchemaResponse> copySchema(@PathVariable UUID schemaId,
            @RequestBody CopySchemaRequest request) {
        var result = schemaService.copySchema(schemaId, request.newName());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/schemas/{schemaId}")
    public ResponseEntity<Void> deleteSchema(@PathVariable UUID schemaId) {
        schemaService.deleteSchema(schemaId);
        return ResponseEntity.noContent().build();
    }
}
