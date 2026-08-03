package dbms_api.controllers;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dbms_api.dtos.index.CreateIndexRequest;
import dbms_api.dtos.index.IndexResponse;
import dbms_api.dtos.index.RenameIndexRequest;
import dbms_api.services.IndexService;

@RestController
@RequestMapping("/api/v1")
public class IndexController {
    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping("/tables/{tableId}/indexes")
    public ResponseEntity<IndexResponse> createIndex(@PathVariable UUID tableId,
            @RequestBody CreateIndexRequest request) {
        return ResponseEntity.ok(indexService.createIndex(tableId, request));
    }

    @GetMapping("/tables/{tableId}/indexes")
    public ResponseEntity<List<IndexResponse>> listIndexes(@PathVariable UUID tableId) {
        return ResponseEntity.ok(indexService.listIndexes(tableId));
    }

    @GetMapping("/indexes/{indexId}")
    public ResponseEntity<IndexResponse> getIndex(@PathVariable UUID indexId, @RequestParam UUID tableId) {
        return ResponseEntity.ok(indexService.getIndex(tableId, indexId));
    }

    @DeleteMapping("/indexes/{indexId}")
    public ResponseEntity<Void> deleteIndex(@PathVariable UUID indexId, @RequestParam UUID tableId) {
        indexService.deleteIndex(tableId, indexId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/indexes/{indexId}/name")
    public ResponseEntity<IndexResponse> renameIndex(@PathVariable UUID indexId, @RequestParam UUID tableId,
            @RequestBody RenameIndexRequest request) {
        return ResponseEntity.ok(indexService.renameIndex(tableId, indexId, request));
    }
}
