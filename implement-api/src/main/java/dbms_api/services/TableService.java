package dbms_api.services;

import java.util.List;
import java.util.UUID;

import core.classes.metadata.ColumnMetadata;
import core.classes.metadata.Schema;
import core.classes.metadata.Table;
import dbms_api.dtos.table.CreateTableRequest;
import dbms_api.dtos.table.TableResponse;
import dbms_api.mappers.TableMapper;
import dbms_api.repositories.ISchemaRepository;
import dbms_api.repositories.ITableRepository;

public class TableService {
    private final ITableRepository tableRepository;
    private final ISchemaRepository schemaRepository;
    private final TableMapper tableMapper;

    public TableService(ITableRepository tableRepository, ISchemaRepository schemaRepository, TableMapper tablemapper) {
        this.tableRepository = tableRepository;
        this.schemaRepository = schemaRepository;
        this.tableMapper = tablemapper;
    }

    public TableResponse createTable(UUID schemaId, CreateTableRequest request) {
        Schema schema = findSchema(schemaId);

        boolean duplicated = schema.getTables().stream()
                .anyMatch(tbl -> tbl.getName().equalsIgnoreCase(request.name()));

        if (duplicated) {
            throw new RuntimeException(
                    "Table already exists: " + request.name());
        }

        Table table = tableMapper.toDomain(schemaId, request);
        schema.addTable(table);
        schemaRepository.save(schema);
        return tableMapper.toResponse(table);
    }

    public List<TableResponse> getTables(UUID schemaUId) {
        List<Table> table = tableRepository.findBySchemaId(schemaUId);
        return table.stream().map(tableMapper::toResponse).toList();
    }

    public TableResponse getTable(UUID tableId) {
        Table table = tableRepository.findById(tableId).orElseThrow(() -> new RuntimeException(
                "Column not found: " + tableId));
        return tableMapper.toResponse(table);
    }

    private Schema findSchema(UUID schemaId) {
        return schemaRepository.findById(schemaId)
                .orElseThrow(() -> new RuntimeException("Table not found " + schemaId));
    }

    private Table findTable(
            Schema schema,
            UUID tableId) {
        return schema.getTables()
                .stream()
                .filter(tbl -> tbl.getId().equals(tableId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Table not found: " + tableId));
    }
}
