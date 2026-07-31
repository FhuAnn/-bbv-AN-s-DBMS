package core.classes.metadata.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Table;

public interface TableMetadataRepository
        extends MetadataRepository<Table> {

    List<Table> findBySchemaId(UUID schemaId);

    Optional<Table> findBySchemaIdAndName(
            UUID schemaId,
            String name
    );
}
