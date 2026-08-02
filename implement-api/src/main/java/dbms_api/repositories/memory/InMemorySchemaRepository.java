package dbms_api.repositories.memory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import core.classes.database.Database;
import core.classes.metadata.Schema;
import dbms_api.repositories.ISchemaRepository;

@Repository
@Profile("mock")
public class InMemorySchemaRepository implements ISchemaRepository {
    private final Map<UUID, Schema> storage = new LinkedHashMap<>();

    public InMemorySchemaRepository() {
        // TODO
    }

    @Override
    public Schema save(Schema schema) {

        storage.put(schema.getId(), schema);
        return schema;
    }

    @Override
    public Optional<Schema> findById(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Optional<Schema> findByDatabaseIdAndName(UUID databaseId, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDatabaseIdAndName'");
    }

    @Override
    public List<Schema> findByDatabaseId(UUID databaseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDatabaseId'");
    }

    @Override
    public boolean existsById(UUID schemaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsById'");
    }

    @Override
    public boolean existsByDatabaseIdAndName(UUID databaseId, String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByDatabaseIdAndName'");
    }

    @Override
    public void deleteById(UUID schemaId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public long countByDatabaseId(UUID databaseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByDatabaseId'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }
}
