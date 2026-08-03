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

import dbms_api.dtos.table.CreateTableRequest;
import dbms_api.dtos.table.TableResponse;
import dbms_api.services.TableService;

@RestController
@RequestMapping("/api/v1")
public class TableController {
    private final TableService tableService;

    public TableController(TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/schemas/{schemaId}/tables")
    public ResponseEntity<TableResponse> createTable(@PathVariable UUID schemaId,
            @RequestBody CreateTableRequest request) {
        return ResponseEntity.ok(tableService.createTable(schemaId, request));
    }

    @GetMapping("/schemas/{schemaId}/tables")
    public ResponseEntity<List<TableResponse>> getTables(@PathVariable UUID schemaId) {
        return ResponseEntity.ok(tableService.getTables(schemaId));
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<TableResponse> getTableById(@PathVariable UUID tableId) {
        return ResponseEntity.ok(tableService.getTable(tableId));
    }

    @DeleteMapping("/tables/{tableId}")
    public ResponseEntity<Void> deleteTable(@PathVariable UUID tableId) {
        tableService.deleteTable(tableId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/tables/{tableId}/name")
    public ResponseEntity<TableResponse> rename(@PathVariable UUID tableId,
            @RequestBody java.util.Map<String, String> body) {
        return ResponseEntity.ok(tableService.renameTable(tableId, body.get("newName")));
    }

    @PostMapping("/tables/{tableId}/copies")
    public ResponseEntity<TableResponse> copyTable(@PathVariable UUID tableId,
            @RequestBody java.util.Map<String, Object> body) {
        String newName = body.get("newName") == null ? null : body.get("newName").toString();
        return ResponseEntity.ok(tableService.copyTable(tableId, newName));
    }
}
