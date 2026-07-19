package classes.metadata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Catalog {

    private final Map<UUID, Table> tables;
    private final Map<UUID, Schema> schemas;

    public Catalog() {
        this.tables = new HashMap<>();
        this.schemas = new HashMap<>();
    }

    public void addSchema(Schema schema) {
        if (schema == null) {
            throw new IllegalArgumentException("Schema must not be null");
        }

        schemas.put(schema.getId(), schema);
    }

    public void removeSchema(UUID schemaId) {
        if (schemaId == null) {
            throw new IllegalArgumentException("Schema id must not be null");
        }

        schemas.remove(schemaId);
    }

    public Schema getSchema(UUID schemaId) {
        return schemas.get(schemaId);
    }

    public Map<UUID, Schema> getSchemas() {
        return Collections.unmodifiableMap(schemas);
    }

    public void registerTable(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("Table must not be null");
        }

        tables.put(table.getId(), table);
    }

    public void putTable(Table table) {
        registerTable(table);
    }

    public Table getTable(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Table name must not be blank");
        }

        return tables.values()
                .stream()
                .filter(table -> table.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Map<UUID, Table> getTables() {
        return Collections.unmodifiableMap(tables);
    }
}