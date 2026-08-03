package dbms_api.dtos.table;

import java.util.List;
import java.util.UUID;

import core.classes.abstraction.Constraint;
import core.classes.metadata.ColumnMetadata;
import core.classes.metadata.Index;

public record TableResponse(
        UUID id,
        String name,
        UUID schemaId,

        List<ColumnMetadata> columns,
        List<Index> indexes,
        List<Constraint> constraints) {
}
