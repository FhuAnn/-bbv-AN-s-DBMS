package dbms_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.ColumnMetadata;

public interface IColumnRepository {
        ColumnMetadata save(ColumnMetadata column);

        Optional<ColumnMetadata> findById(UUID columnId);

        Optional<ColumnMetadata> findByTableIdAndName(
                        UUID tableId,
                        String name);

        List<ColumnMetadata> findByTableId(UUID tableId);

        boolean existsById(UUID tableId);

        boolean existsByTableIdAndName(
                        UUID tableId,
                        String name);

        void deleteById(UUID tableId);

        long countBySchemaId(UUID schemaId);

        void clear();
}
