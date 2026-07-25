package classes.metadata.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import classes.metadata.Index;
import classes.metadata.repository.IndexMetadataRepository;

public class InMemoryIndexMetadataRepository
        extends AbstractInMemoryMetadataRepository<Index>
        implements IndexMetadataRepository {

    public InMemoryIndexMetadataRepository() {
        // TODO: Implement
    }

    @Override
    public List<Index> findByTableId(UUID tableId) {
        // TODO: Implement
        return List.of();
    }

    @Override
    public Optional<Index> findByTableIdAndName(
            UUID tableId,
            String name
    ) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    protected boolean isSameNamespace(
            Index first,
            Index second
    ) {
        // TODO: Implement
        return false;
    }
}
