package dbms_api.repositories.memory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import core.classes.metadata.Schema;
import dbms_api.repositories.ISchemaRepository;

@Repository
@Profile("mock")
public class InMemorySchemaRepository implements ISchemaRepository {
    private final Map<UUID, Schema> storage = new LinkedHashMap<>();

    public InMemorySchemaRepository() {
    }

    @Override
    public Schema save(Schema schema) {
        if (schema.getId() == null) {
            schema.setId(UUID.randomUUID());
        }
        storage.put(schema.getId(), schema);
        return schema;
    }

    @Override
    public Optional<Schema> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Optional<Schema> findByDatabaseIdAndName(UUID databaseId, String name) {
        return storage.values().stream()
                .filter(schema -> schema.getDatabaseId() != null && schema.getDatabaseId().equals(databaseId))
                .filter(schema -> schema.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Schema> findByDatabaseId(UUID databaseId) {
        return storage.values().stream()
                .filter(schema -> schema.getDatabaseId() != null && schema.getDatabaseId().equals(databaseId))
                .toList();
    }

    @Override
    public boolean existsById(UUID schemaId) {
        return storage.containsKey(schemaId);
    }

    @Override
    public boolean existsByDatabaseIdAndName(UUID databaseId, String name) {
        return findByDatabaseIdAndName(databaseId, name).isPresent();
    }

    @Override
    public void deleteById(UUID schemaId) {
        storage.remove(schemaId);
    }

    @Override
    public long countByDatabaseId(UUID databaseId) {
        return findByDatabaseId(databaseId).size();
    }

    @Override
    public void clear() {
        storage.clear();
    }
}
