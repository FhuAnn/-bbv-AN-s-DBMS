package dbms_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Table;

public interface ITableRepository {
        Table save(Table table);

        Optional<Table> findById(UUID tableId);

        Optional<Table> findBySchemaIdAndName(
                        UUID schemaId,
                        String name);

        List<Table> findBySchemaId(UUID schemaId);

        boolean existsById(UUID tableId);

        boolean existsBySchemaIdAndName(
                        UUID schemaId,
                        String name);

        void deleteById(UUID tableId);

        long countBySchemaId(UUID schemaId);

        void clear();

}
