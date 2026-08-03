package dbms_api.repositories.memory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import core.classes.metadata.Table;
import dbms_api.repositories.ITableRepository;

@Repository
@Profile("mock")
public class InMemoryTableRepository implements ITableRepository {
    private final Map<UUID, Table> storage;

    public InMemoryTableRepository() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public Table save(Table table) {
        storage.put(table.getId(), table);
        return table;
    }

    @Override
    public Optional<Table> findById(UUID tableId) {
        return Optional.ofNullable(storage.get(tableId));
    }

    @Override
    public Optional<Table> findBySchemaIdAndName(UUID schemaId, String name) {
        // TODO Auto-generated method stub
        return storage.values()
                .stream()
                .filter(table -> table.getSchemaId().equals(schemaId))
                .filter(table -> table.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Table> findBySchemaId(UUID schemaId) {
        return storage.values()
                .stream()
                .filter(table -> table.getSchemaId().equals(schemaId))
                .toList();
    }

    @Override
    public boolean existsById(UUID tableId) {
        return storage.containsKey(tableId);
    }

    @Override
    public boolean existsBySchemaIdAndName(UUID schemaId, String name) {
        return storage.values()
                .stream()
                .anyMatch(table -> table.getSchemaId().equals(schemaId)
                        && table.getName()
                                .equalsIgnoreCase(name));
    }

    @Override
    public void deleteById(UUID tableId) {
        storage.remove(tableId);
    }

    @Override
    public long countBySchemaId(UUID schemaId) {
        return storage.values()
                .stream()
                .filter(table -> table.getSchemaId().equals(schemaId))
                .count();
    }

    @Override
    public void clear() {
        storage.clear();
    }

}
