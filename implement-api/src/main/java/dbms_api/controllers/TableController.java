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

import dbms_api.dtos.schema.CreateSchemaRequest;
import dbms_api.dtos.table.CreateTableRequest;
import dbms_api.dtos.table.TableResponse;
import dbms_api.services.TableService;

public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/schemas/{schemaId}/tables")
    public ResponseEntity<TableResponse> createTable(@PathVariable UUID schemaId,
            @RequestBody CreateTableRequest request) {
        var result = tableService.createTable(schemaId, request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/schemas/{schemaId}/tables")
    public ResponseEntity<List<TableResponse>> getTables(@PathVariable UUID schemaId) {
        var result = tableService.getTables(schemaId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<TableResponse> getTableById(@PathVariable UUID tableId) {
        var result = tableService.getTable(tableId);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/tables/{tableId}")
    public ResponseEntity<Void> deleteTable(@PathVariable UUID tableId) {

    }

    @PatchMapping("/tables/{tableId}/name")
    public ResponseEntity<TableResponse> rename(@PathVariable UUID tableId) {

    }

    @PostMapping("/tables/{tableId}/copies")
    public ResponseEntity<TableResponse> copyTable(@PathVariable UUID tableId) {

    }
}
