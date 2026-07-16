package classes.queryprocessor;

import java.util.HashMap;
import java.util.Map;

public class CatalogManager {
    private final Map<String, SchemaInfo> schemas = new HashMap<>();
    private final Map<String, TableStatistics> statistics = new HashMap<>();
    private final BufferPoolManager bufferPoolManager = new BufferPoolManager();

    public void registerTable(String tableName, SchemaInfo schemaInfo) {
    }

    public TableStatistics getTableStatistics(String tableName) {
        return null;
    }

    public SchemaInfo getTableSchema(String tableName) {
        return null;
    }

    public BufferPoolManager getBufferPoolManager() {
        return null;
    }
}
