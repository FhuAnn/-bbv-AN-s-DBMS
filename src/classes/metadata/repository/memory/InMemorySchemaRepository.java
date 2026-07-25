package classes.metadata.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import classes.metadata.Schema;
import classes.metadata.repository.SchemaRepository;

public class InMemorySchemaRepository
        extends AbstractInMemoryMetadataRepository<Schema>
        implements SchemaRepository {

    public InMemorySchemaRepository() {
        // TODO: Implement
    }

    @Override
    public List<Schema> findByDatabaseId(UUID databaseId) {
        // TODO: Implement
        return List.of();
    }

    @Override
    public Optional<Schema> findByDatabaseIdAndName(
            UUID databaseId,
            String name) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    protected boolean isSameNamespace(
            Schema first,
            Schema second) {
        // TODO: Implement
        return false;
    }
}
