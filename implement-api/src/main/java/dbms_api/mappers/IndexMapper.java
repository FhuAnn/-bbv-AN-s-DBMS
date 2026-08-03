package dbms_api.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import core.classes.metadata.Index;
import dbms_api.dtos.index.CreateIndexRequest;
import dbms_api.dtos.index.IndexResponse;

@Component
public class IndexMapper {
    public Index toDomain(UUID tableId, CreateIndexRequest request) {
        return new Index(request.name(), tableId, request.columnNames(), request.type(), request.unique());
    }

    public IndexResponse toResponse(Index index) {
        return new IndexResponse(
                index.getId(),
                index.getTableId(),
                index.getName(),
                index.getType(),
                index.getColumnNames(),
                index.isUnique());
    }
}
