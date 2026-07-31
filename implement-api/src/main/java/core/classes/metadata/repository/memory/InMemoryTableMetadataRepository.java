package core.classes.metadata.repository.memory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.metadata.Table;
import core.classes.metadata.repository.TableMetadataRepository;

public class InMemoryTableMetadataRepository
        extends AbstractInMemoryMetadataRepository<Table>
        implements TableMetadataRepository {

    public InMemoryTableMetadataRepository() {
        // TODO: Implement
    }

    @Override
    public List<Table> findBySchemaId(UUID schemaId) {
        // TODO: Implement
        return List.of();
    }

    @Override
    public Optional<Table> findBySchemaIdAndName(
            UUID schemaId,
            String name) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    protected boolean isSameNamespace(
            Table first,
            Table second) {
        // TODO: Implement
        return false;
    }
}
