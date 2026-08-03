package dbms_api.mappers;

import java.util.UUID;

import org.springframework.stereotype.Component;

import core.classes.metadata.Table;
import dbms_api.dtos.table.CreateTableRequest;
import dbms_api.dtos.table.TableResponse;

@Component
public class TableMapper {
    public TableMapper() {
    }

    public Table toDomain(UUID schemaId, CreateTableRequest request) {
        return new Table(request.name(), schemaId);
    }

    public TableResponse toResponse(Table table) {
        return new TableResponse(
                table.getId(),
                table.getName(),
                table.getSchemaId(),
                table.getColumns(),
                table.getIndexes(),
                table.getConstraints());

    }
}
