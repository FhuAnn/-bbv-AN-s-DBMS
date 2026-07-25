package classes.metadata.repository.memory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import classes.metadata.repository.MetadataRepository;
import interfaces.MetadataComponent;

public abstract class AbstractInMemoryMetadataRepository<T extends MetadataComponent>
        implements MetadataRepository<T> {

    private final Map<UUID, T> storage;

    protected AbstractInMemoryMetadataRepository() {
        // TODO: Implement
        this.storage = null;
    }

    @Override
    public T save(T entity) {
        // TODO: Implement
        return null;
    }

    @Override
    public Optional<T> findById(UUID id) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public Optional<T> findByName(String name) {
        // TODO: Implement
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        // TODO: Implement
        return List.of();
    }

    @Override
    public boolean existsById(UUID id) {
        // TODO: Implement
        return false;
    }

    @Override
    public boolean existsByName(String name) {
        // TODO: Implement
        return false;
    }

    @Override
    public void deleteById(UUID id) {
        // TODO: Implement
    }

    @Override
    public long count() {
        // TODO: Implement
        return 0;
    }

    @Override
    public void clear() {
        // TODO: Implement
    }

    protected Map<UUID, T> getStorage() {
        // TODO: Implement
        return null;
    }

    protected boolean isSameNamespace(
            T first,
            T second) {
        // TODO: Implement
        return false;
    }
}
