package classes.metadata;

import java.util.List;
import java.util.UUID;

public class Schema {
    private UUID id;
    private String name;
    private UUID databaseId;
    private UUID ownerId;
    private List<TableMetadata> tables;

    public Schema(String name, UUID databaseId, UUID ownerId) {
        this.name = name;
        this.databaseId = databaseId;
        this.ownerId = ownerId;
    }

    public Schema() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(UUID databaseId) {
        this.databaseId = databaseId;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public List<TableMetadata> getTables() {
        return tables;
    }

    public void setTables(List<TableMetadata> tables) {
        this.tables = tables;
    }
}
