package classes.core;
import java.util.UUID;

public class Database {
    private String name;
    private UUID id;
    private Catalog catalog;
    private StorageEngine storage;
    private TransactionManager txManager;
    private SecurityManager security;

    public Database(String name) {
        return null;
    }

    public QueryResult executeSQL(String sql) {
        return null;
    }

    // Getters and Setters
    public String getName() {
        return null;
    }
    public UUID getId() {
        return null;
    }
    public Catalog getCatalog() {
        return null;
    }
    public StorageEngine getStorage() {
        return null;
    }
}