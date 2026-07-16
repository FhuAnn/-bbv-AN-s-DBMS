package classes.metadata;



import java.util.*;



public class Schema {

    private UUID id;

    private String name;

    private UUID databaseId;

    private UUID ownerId;

    private List<TableMetadata> tables = new ArrayList<>();

    public Schema(String name, UUID databaseId, UUID ownerId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.databaseId = databaseId;
        this.ownerId = ownerId;
    }

    public Schema() {
        this.id = UUID.randomUUID();
    }

    // Getters + Setters

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