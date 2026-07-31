package core.classes.metadata.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Schema;

public interface SchemaRepository
        extends MetadataRepository<Schema> {

    List<Schema> findByDatabaseId(UUID databaseId);

    Optional<Schema> findByDatabaseIdAndName(
            UUID databaseId,
            String name
    );
}
