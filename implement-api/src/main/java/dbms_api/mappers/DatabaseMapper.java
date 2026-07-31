package dbms_api.mappers;

import org.springframework.stereotype.Component;

import core.classes.database.Database;
import dbms_api.dtos.database.CreateDatabaseRequest;
import dbms_api.dtos.database.DatabaseResponse;

@Component
public class DatabaseMapper {
    public DatabaseMapper() {

    }
    public Database toDomain(CreateDatabaseRequest request) {
        return new Database(request.name());
    }

    public DatabaseResponse toResponse(Database database) {
        return null;
    }
}
