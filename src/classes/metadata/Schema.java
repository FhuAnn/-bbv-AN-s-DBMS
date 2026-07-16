package classes.metadata;

import java.util.*;

public class Schema {
    private UUID id;
    private String name;
    private UUID databaseId;
    private UUID ownerId;
    private List<TableMetadata> tables = new ArrayList<>();

    public Schema(String name, UUID databaseId, UUID ownerId) {
        return null;
    }
    // Getters + Setters
}