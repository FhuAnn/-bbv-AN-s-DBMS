package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.database.Database;
import dbms_api.dtos.database.CreateDatabaseRequest;
import dbms_api.dtos.database.DatabaseResponse;
import dbms_api.mappers.DatabaseMapper;
import dbms_api.repositories.IDatabaseRepository;

@Service
public class DatabaseService {
    private final IDatabaseRepository databaseRepository;
    private final DatabaseMapper databaseMapper;

    public DatabaseService(
            IDatabaseRepository databaseRepository,
            DatabaseMapper databaseMapper) {

        // TODO
        this.databaseRepository = databaseRepository;
        this.databaseMapper = databaseMapper;
    }

    public DatabaseResponse createDatabase(
            CreateDatabaseRequest request) {

        // TODO
        // Database database = databaseMapper.toDomain(request);
        // database = databaseRepository.save(database);
        // return databaseMapper.toResponse(database);
        return null;
    }

    public DatabaseResponse getDatabase(UUID databaseId) {
        // TODO
        return null;
    }

    public List<DatabaseResponse> listDatabases() {
        // TODO
        return List.of();
    }

    public DatabaseResponse renameDatabase(
            UUID databaseId,
            String newName) {

        // TODO
        return null;
    }

    public DatabaseResponse openDatabase(UUID databaseId) {
        // TODO
        return null;
    }

    public DatabaseResponse closeDatabase(UUID databaseId) {
        // TODO
        return null;
    }

    public DatabaseResponse setReadOnly(
            UUID databaseId,
            boolean readOnly) {

        // TODO
        return null;
    }

    public void deleteDatabase(UUID databaseId) {
        // TODO
    }

    private Database findDatabase(UUID databaseId) {
        // TODO
        return null;
    }
}
