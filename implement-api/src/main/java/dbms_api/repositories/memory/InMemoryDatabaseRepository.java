package dbms_api.repositories.memory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import core.classes.database.Database;
import dbms_api.repositories.IDatabaseRepository;

@Repository
@Profile("mock")
public class InMemoryDatabaseRepository implements IDatabaseRepository {
    private final Map<UUID, Database> storage = new LinkedHashMap<>();

    public InMemoryDatabaseRepository() {
    }

    @Override
    public Database save(Database database) {
        storage.put(database.getId(), database);
        return database;
    }

    @Override
    public Optional<Database> findById(UUID databaseId) {
        return Optional.ofNullable(storage.get(databaseId));
    }

    @Override
    public Optional<Database> findByName(String name) {
        return storage.values().stream()
                .filter(database -> database.getName().equals(name))
                .findFirst();
    }

    @Override
    public List<Database> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public boolean existsById(UUID databaseId) {
        return storage.containsKey(databaseId);
    }

    @Override
    public boolean existsByName(String name) {
        return storage.values().stream()
                .anyMatch(database -> database.getName().equals(name));
    }

    @Override
    public void deleteById(UUID databaseId) {
        storage.remove(databaseId);
    }

    @Override
    public long count() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
