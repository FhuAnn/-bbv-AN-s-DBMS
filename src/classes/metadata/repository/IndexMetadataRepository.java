package classes.metadata.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import classes.metadata.Index;

public interface IndexMetadataRepository
        extends MetadataRepository<Index> {

    List<Index> findByTableId(UUID tableId);

    Optional<Index> findByTableIdAndName(
            UUID tableId,
            String name
    );
}
