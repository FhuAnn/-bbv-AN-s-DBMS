package dbms_api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import core.classes.database.Database;

public interface IDatabaseRepository {
    Database save(Database database);

    Optional<Database> findById(UUID databaseId);

    Optional<Database> findByName(String name);

    List<Database> findAll();

    boolean existsById(UUID databaseId);

    boolean existsByName(String name);

    void deleteById(UUID databaseId);

    long count();

    void clear();
}
