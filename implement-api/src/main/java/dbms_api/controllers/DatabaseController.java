package dbms_api.controllers;

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

    @PostMapping
    public ResponseEntity<DatabaseResponse> createDatabase(@RequestBody CreateDatabaseRequest request) {
        return null;
    }

    @GetMapping("/{databaseId}")
    public ResponseEntity<DatabaseResponse> getDatabase(@PathVariable UUID databaseId) {
        return null;
    }

    @GetMapping
    public ResponseEntity<DatabaseResponse> listDatabases() {
        return null;
    }

    @PatchMapping("/{databaseId}/name")
    public ResponseEntity<DatabaseResponse> renameDatabase(@PathVariable UUID databaseId,
            @RequestBody RenameDatabaseRequest request) {
        return null;
    }

    @PostMapping("/{databaseId}/close")
    public ResponseEntity<DatabaseResponse> closeDatabase(
            @PathVariable UUID databaseId) {

        // TODO
        return null;
    }

    @PatchMapping("/{databaseId}/read-only")
    public ResponseEntity<DatabaseResponse> setReadOnly(
            @PathVariable UUID databaseId,
            @RequestBody SetReadOnlyRequest request) {

        // TODO
        return null;
    }

    @DeleteMapping("/{databaseId}")
    public ResponseEntity<Void> deleteDatabase(
            @PathVariable UUID databaseId) {

        // TODO
        return null;
    }
}
