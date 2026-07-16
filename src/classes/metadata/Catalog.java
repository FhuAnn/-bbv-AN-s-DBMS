package classes.metadata;

import java.util.*;

public class Catalog {
    private Map<UUID, TableMetadata> tables = new HashMap<>();
    private Map<UUID, Schema> schemas = new HashMap<>();

    public TableMetadata getTable(String name) {
        return null;
    }

    public void registerTable(TableMetadata meta) {
    }
}