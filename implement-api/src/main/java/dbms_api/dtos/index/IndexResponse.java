package dbms_api.dtos.index;

import java.util.List;
import java.util.UUID;

import core.enums.IndexType;

public record IndexResponse(
        UUID id,
        UUID tableId,
        String name,
        IndexType type,
        List<String> columnNames,
        boolean unique) {
}
