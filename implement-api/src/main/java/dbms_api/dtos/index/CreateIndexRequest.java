package dbms_api.dtos.index;

import java.util.List;

import core.enums.IndexType;

public record CreateIndexRequest(
        String name,
        IndexType type,
        List<String> columnNames,
        boolean unique) {
}
