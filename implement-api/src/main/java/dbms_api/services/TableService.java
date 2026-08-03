package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.metadata.Schema;
import core.classes.metadata.Table;
import dbms_api.dtos.table.CreateTableRequest;
import dbms_api.dtos.table.TableResponse;
import dbms_api.mappers.TableMapper;
import dbms_api.repositories.ISchemaRepository;
import dbms_api.repositories.ITableRepository;

@Service
public class TableService {
    private final ITableRepository tableRepository;
    private final ISchemaRepository schemaRepository;
    private final TableMapper tableMapper;

    public TableService(ITableRepository tableRepository, ISchemaRepository schemaRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.schemaRepository = schemaRepository;
        this.tableMapper = tableMapper;
    }

    public TableResponse createTable(UUID schemaId, CreateTableRequest request) {
        Schema schema = findSchema(schemaId);

        boolean duplicated = schema.getTables().stream()
                .anyMatch(tbl -> tbl.getName().equalsIgnoreCase(request.name()));

        if (duplicated) {
            throw new RuntimeException("Table already exists: " + request.name());
        }

        Table table = tableMapper.toDomain(schemaId, request);
        tableRepository.save(table);
        return tableMapper.toResponse(table);
    }

    public List<TableResponse> getTables(UUID schemaId) {
        return tableRepository.findBySchemaId(schemaId).stream()
                .map(tableMapper::toResponse)
                .toList();
    }

    public TableResponse getTable(UUID tableId) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found: " + tableId));
        return tableMapper.toResponse(table);
    }

    public TableResponse renameTable(UUID tableId, String newName) {
        Table table = findTable(tableId);
        table.rename(newName);
        tableRepository.save(table);
        return tableMapper.toResponse(table);
    }

    public TableResponse copyTable(UUID tableId, String newName) {
        Table source = findTable(tableId);
        Table copied = source.copyAs(newName == null || newName.isBlank() ? source.getName() + "_copy" : newName,
                source.getSchemaId());
        tableRepository.save(copied);
        return tableMapper.toResponse(copied);
    }

    public void deleteTable(UUID tableId) {
        findTable(tableId);
        tableRepository.deleteById(tableId);
    }

    private Schema findSchema(UUID schemaId) {
        return schemaRepository.findById(schemaId)
                .orElseThrow(() -> new RuntimeException("Schema not found: " + schemaId));
    }

    private Table findTable(UUID tableId) {
        return tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found: " + tableId));
    }
}
