package dbms_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Schema;

public interface ISchemaRepository {
        Schema save(Schema schema);

        Optional<Schema> findById(UUID id);

        Optional<Schema> findByDatabaseIdAndName(
                        UUID databaseId,
                        String name);

        List<Schema> findByDatabaseId(UUID databaseId);

        boolean existsById(UUID schemaId);

        boolean existsByDatabaseIdAndName(
                        UUID databaseId,
                        String name);

        void deleteById(UUID schemaId);

        long countByDatabaseId(UUID databaseId);

        void clear();
}
