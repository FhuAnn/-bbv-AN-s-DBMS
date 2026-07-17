package classes.metadata;

import java.util.Map;

public class Catalog {
    private Map tables;
    private Map schemas;

    public TableMetadata getTable(String name) {
        return null;
    }

    public void registerTable(TableMetadata meta) {
    }

    public Map getTables() {
        return tables;
    }

    public void setTables(Map tables) {
        this.tables = tables;
    }

    public Map getSchemas() {
        return schemas;
    }

    public void setSchemas(Map schemas) {
        this.schemas = schemas;
    }

    public Catalog() {
    }

    public void putTable(TableMetadata table) {
    }

    public void putSchema(Schema schema) {
    }
}
