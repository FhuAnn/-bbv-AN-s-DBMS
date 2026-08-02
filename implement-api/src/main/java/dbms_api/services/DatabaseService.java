package dbms_api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import core.classes.database.Database;
import dbms_api.dtos.database.CreateDatabaseRequest;
import dbms_api.dtos.database.DatabaseResponse;
import dbms_api.dtos.database.RenameDatabaseRequest;
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
        Database database = databaseMapper.toDomain(request);
        database = databaseRepository.save(database);
        return databaseMapper.toResponse(database);
    }

    public DatabaseResponse getDatabase(UUID databaseId) {

        Database database = databaseRepository.findById(databaseId).orElseThrow();
        return databaseMapper.toResponse(database);
    }

    public List<DatabaseResponse> listDatabases() {
        return databaseRepository.findAll().stream()
                .map(databaseMapper::toResponse)
                .toList();
    }

    public DatabaseResponse renameDatabase(
            UUID databaseId,
            RenameDatabaseRequest request) {
        Database db = databaseRepository.findById(databaseId).orElseThrow(() -> new RuntimeException("Not found"));
        db.doRename(request.newName());
        Database savedDatabase = databaseRepository.save(db);
        return databaseMapper.toResponse(savedDatabase);
    }

    public DatabaseResponse openDatabase(UUID databaseId) {
        // TODO
        return null;
    }

    public DatabaseResponse closeDatabase(UUID databaseId) {
        var db = databaseRepository.findById(databaseId).orElseThrow(() -> new RuntimeException("Not Found"));
        db.close();
        return databaseMapper.toResponse(db);
    }

    public DatabaseResponse setReadOnly(
            UUID databaseId,
            boolean readOnly) {

        var db = databaseRepository.findById(databaseId).orElseThrow(() -> new RuntimeException("Not Found"));
        db.setReadOnly(readOnly);
        return databaseMapper.toResponse(db);
    }

    public void deleteDatabase(UUID databaseId) {
        databaseRepository.findById(databaseId).orElseThrow(() -> new RuntimeException("Not Found"));
        databaseRepository.deleteById(databaseId);
    }

    private Database findDatabase(UUID databaseId) {
        // TODO
        return null;
    }
}
