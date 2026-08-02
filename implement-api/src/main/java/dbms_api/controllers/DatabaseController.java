package dbms_api.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dbms_api.dtos.database.CreateDatabaseRequest;
import dbms_api.dtos.database.DatabaseResponse;
import dbms_api.dtos.database.RenameDatabaseRequest;
import dbms_api.dtos.database.SetReadOnlyRequest;
import dbms_api.services.DatabaseService;

@RestController
@RequestMapping("/api/v1/databases")
public class DatabaseController {
    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // Done
    @PostMapping
    public ResponseEntity<DatabaseResponse> createDatabase(@RequestBody CreateDatabaseRequest request) {
        return ResponseEntity.ok(databaseService.createDatabase(request));
    }

    // Done
    @GetMapping("/{databaseId}")
    public ResponseEntity<DatabaseResponse> getDatabase(@PathVariable UUID databaseId) {
        return ResponseEntity.ok(databaseService.getDatabase(databaseId));
    }

    // Done
    @GetMapping
    public ResponseEntity<List<DatabaseResponse>> listDatabases() {
        var result = databaseService.listDatabases();
        return ResponseEntity.ok(result);
    }

    // Done
    @PatchMapping("/{databaseId}/name")
    public ResponseEntity<DatabaseResponse> renameDatabase(@PathVariable UUID databaseId,
            @RequestBody RenameDatabaseRequest request) {
        var result = databaseService.renameDatabase(databaseId, request);
        return ResponseEntity.ok(result);
    }

    // Done
    @PostMapping("/{databaseId}/close")
    public ResponseEntity<DatabaseResponse> closeDatabase(
            @PathVariable UUID databaseId) {
        var result = databaseService.closeDatabase(databaseId);
        return ResponseEntity.ok(result);
    }

    // Done
    @PatchMapping("/{databaseId}/read-only")
    public ResponseEntity<DatabaseResponse> setReadOnly(
            @PathVariable UUID databaseId,
            @RequestBody SetReadOnlyRequest request) {
        var result = databaseService.setReadOnly(databaseId, false);
        return ResponseEntity.ok(result);
    }

    // Done
    @DeleteMapping("/{databaseId}")
    public ResponseEntity<Void> deleteDatabase(
            @PathVariable UUID databaseId) {
        databaseService.deleteDatabase(databaseId);
        return null;
    }
}
