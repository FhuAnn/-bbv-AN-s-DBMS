package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.metadata.Index;
import core.classes.metadata.Table;
import dbms_api.dtos.index.CreateIndexRequest;
import dbms_api.dtos.index.IndexResponse;
import dbms_api.dtos.index.RenameIndexRequest;
import dbms_api.mappers.IndexMapper;
import dbms_api.repositories.IIndexRepository;
import dbms_api.repositories.ITableRepository;

@Service
public class IndexService {
    private final ITableRepository tableRepository;
    private final IIndexRepository indexRepository;
    private final IndexMapper indexMapper;

    public IndexService(ITableRepository tableRepository, IIndexRepository indexRepository, IndexMapper indexMapper) {
        this.tableRepository = tableRepository;
        this.indexRepository = indexRepository;
        this.indexMapper = indexMapper;
    }

    public IndexResponse createIndex(UUID tableId, CreateIndexRequest request) {
        Table table = findTable(tableId);

        boolean duplicate = table.getIndexes().stream()
                .anyMatch(index -> index.getName().equalsIgnoreCase(request.name()));

        if (duplicate) {
            throw new RuntimeException("Index already exists: " + request.name());
        }

        Index index = indexMapper.toDomain(tableId, request);
        table.addIndex(index);
        tableRepository.save(table);
        indexRepository.save(index);
        return indexMapper.toResponse(index);
    }

    public List<IndexResponse> listIndexes(UUID tableId) {
        Table table = findTable(tableId);
        return table.getIndexes().stream().map(indexMapper::toResponse).toList();
    }

    public IndexResponse getIndex(UUID tableId, UUID indexId) {
        Table table = findTable(tableId);
        Index index = findIndex(table, indexId);
        return indexMapper.toResponse(index);
    }

    public IndexResponse renameIndex(UUID tableId, UUID indexId, RenameIndexRequest request) {
        Table table = findTable(tableId);
        Index index = findIndex(table, indexId);
        index.rename(request.newName());
        tableRepository.save(table);
        return indexMapper.toResponse(index);
    }

    public void deleteIndex(UUID tableId, UUID indexId) {
        Table table = findTable(tableId);
        Index index = findIndex(table, indexId);
        table.getIndexes().remove(index);
        tableRepository.save(table);
    }

    private Table findTable(UUID tableId) {
        return tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found " + tableId));
    }

    private Index findIndex(Table table, UUID indexId) {
        return table.getIndexes().stream()
                .filter(index -> index.getId().equals(indexId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Index not found: " + indexId));
    }
}
