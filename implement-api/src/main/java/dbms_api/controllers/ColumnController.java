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

import dbms_api.dtos.column.ColumnResponse;
import dbms_api.dtos.column.CreateColumnRequest;
import dbms_api.dtos.column.RenameColumnRequest;
import dbms_api.dtos.column.UpdateColumnRequest;
import dbms_api.services.ColumnService;

@RestController
@RequestMapping("api/v1/columns")
public class ColumnController {
    private final ColumnService columnService;

    public ColumnController(ColumnService columnService) {
        this.columnService = columnService;
    }

    @PostMapping("/tables/{tableId}/columns")
    public ResponseEntity<ColumnResponse> createColumn(@PathVariable UUID tableId,
            @RequestBody CreateColumnRequest requestBody) {
        ColumnResponse response = columnService.createColumn(tableId, requestBody);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tables/{tableId}/columns")
    public ResponseEntity<List<ColumnResponse>> listColumns(@PathVariable UUID tableId) {

    }

    @GetMapping("/tables/{tableId}/columns/{columnId}")
    public ResponseEntity<ColumnResponse> getColumn(@PathVariable UUID tableId, @PathVariable UUID columnID) {

    }

    @PatchMapping("/tables/{tableId}/columns/{columnId}")
    public ResponseEntity<ColumnResponse> updateColumn(@PathVariable UUID tableId, @PathVariable UUID columnId,
            @RequestBody UpdateColumnRequest requestBody) {

    }

    @DeleteMapping("/tables/{tableId}/columns/{columnId}")
    public ResponseEntity<Void> deleteColumn(@PathVariable UUID tableId, @PathVariable UUID columnId) {
        columnService.deleteColumn(tableId, columnId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/tables/{tableId}/columns/{columnId}/name")
    public ResponseEntity<ColumnResponse> renameColumn(@PathVariable UUID tableId, @PathVariable UUID columnId,
            @RequestBody RenameColumnRequest requestBody) {
    }
}
