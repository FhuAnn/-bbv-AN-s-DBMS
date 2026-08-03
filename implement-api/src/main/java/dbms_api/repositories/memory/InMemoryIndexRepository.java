package dbms_api.repositories.memory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import core.classes.metadata.Index;
import dbms_api.repositories.IIndexRepository;

public class InMemoryIndexRepository implements IIndexRepository {
    private final Map<UUID, Index> storage;

    public InMemoryIndexRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public Index save(Index index) {
        storage.put(index.getId(), index);
        return index;
    }

    @Override
    public Optional<Index> findById(UUID indexId) {
        return Optional.ofNullable(storage.get(indexId));
    }

    @Override
    public Optional<Index> findByTableIdAndName(UUID tableId, String name) {
        return storage.values()
                .stream()
                .filter(index -> index.getTableId().equals(tableId))
                .filter(index -> index.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Index> findByTableId(UUID tableId) {
        return storage.values()
                .stream()
                .filter(index -> index.getTableId().equals(tableId))
                .toList();
    }

    @Override
    public boolean existsById(UUID indexId) {
        return storage.containsKey(indexId);
    }

    @Override
    public boolean existsByTableIdAndName(UUID tableId, String name) {
        return storage.values()
                .stream()
                .anyMatch(index -> index.getTableId().equals(tableId) && index.getName().equalsIgnoreCase(name));
    }

    @Override
    public void deleteById(UUID indexId) {
        storage.remove(indexId);
    }

    @Override
    public long countByTableId(UUID tableId) {
        return storage.values()
                .stream()
                .filter(index -> index.getTableId().equals(tableId))
                .count();
    }

    @Override
    public void clear() {
        storage.clear();
    }

}
