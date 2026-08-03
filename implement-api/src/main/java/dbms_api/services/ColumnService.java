package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.metadata.ColumnMetadata;
import core.classes.metadata.Table;
import dbms_api.dtos.column.ColumnResponse;
import dbms_api.dtos.column.CreateColumnRequest;
import dbms_api.dtos.column.UpdateColumnRequest;
import dbms_api.mappers.ColumnMapper;
import dbms_api.repositories.IColumnRepository;
import dbms_api.repositories.ITableRepository;

@Service
public class ColumnService {
    private final IColumnRepository columnRepository;
    private final ITableRepository tableRepository;
    private final ColumnMapper columnMapper;

    public ColumnService(
            IColumnRepository columnRepository,
            ITableRepository tableRepository,
            ColumnMapper columnMapper) {

        this.columnRepository = columnRepository;
        this.tableRepository = tableRepository;
        this.columnMapper = columnMapper;
    }

    public ColumnResponse createColumn(
            UUID tableId,
            CreateColumnRequest request) {

        Table table = findTable(tableId);

        boolean duplicated = table.getColumns()
                .stream()
                .anyMatch(column -> column.getName()
                        .equalsIgnoreCase(request.name()));

        if (duplicated) {
            throw new RuntimeException(
                    "Column already exists: " + request.name());
        }

        ColumnMetadata column = columnMapper.toDomain(tableId, request);

        table.addColumn(column);

        tableRepository.save(table);

        return columnMapper.toResponse(column);
    }

    public ColumnResponse getColumn(
            UUID tableId,
            UUID columnId) {

        Table table = findTable(tableId);
        ColumnMetadata column = findColumn(table, columnId);

        return columnMapper.toResponse(column);
    }

    public List<ColumnResponse> listColumns(UUID tableId) {
        Table table = findTable(tableId);

        return table.getColumns()
                .stream()
                .map(columnMapper::toResponse)
                .toList();
    }

    public ColumnResponse renameColumn(
            UUID tableId,
            UUID columnId,
            String newName) {

        Table table = findTable(tableId);
        ColumnMetadata column = findColumn(table, columnId);

        boolean duplicated = table.getColumns()
                .stream()
                .filter(existing -> !existing.getId().equals(columnId))
                .anyMatch(existing -> existing.getName()
                        .equalsIgnoreCase(newName));

        if (duplicated) {
            throw new RuntimeException(
                    "Column already exists: " + newName);
        }

        column.rename(newName);

        tableRepository.save(table);

        return columnMapper.toResponse(column);
    }

    public ColumnResponse updateColumn(
            UUID tableId,
            UUID columnId,
            UpdateColumnRequest request) {

        Table table = findTable(tableId);
        ColumnMetadata column = findColumn(table, columnId);

        columnMapper.updateDomain(column, request);

        tableRepository.save(table);

        return columnMapper.toResponse(column);
    }

    public void deleteColumn(
            UUID tableId,
            UUID columnId) {

        Table table = findTable(tableId);

        findColumn(table, columnId);

        table.removeColumn(columnId);

        tableRepository.save(table);
    }

    private Table findTable(UUID tableId) {
        return tableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found " + tableId));
    }

    private ColumnMetadata findColumn(
            Table table,
            UUID columnId) {

        return table.getColumns()
                .stream()
                .filter(column -> column.getId().equals(columnId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Column not found: " + columnId));
    }
}
