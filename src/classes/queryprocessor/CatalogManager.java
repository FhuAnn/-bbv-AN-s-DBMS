package classes.queryprocessor;

import java.util.HashMap;
import java.util.Map;

public class CatalogManager {
    private final Map<String, SchemaInfo> schemas = new HashMap<>();
    private final Map<String, TableStatistics> statistics = new HashMap<>();
    private final BufferPoolManager bufferPoolManager = new BufferPoolManager();

    public void registerTable(String tableName, SchemaInfo schemaInfo) {
        schemas.put(tableName, schemaInfo);
        TableStatistics stats = new TableStatistics();
        stats.rowCount = 0;
        stats.pageCount = 1;
        statistics.put(tableName, stats);
        bufferPoolManager.getTableRows(tableName);
    }

    public TableStatistics getTableStatistics(String tableName) {
        return statistics.computeIfAbsent(tableName, key -> new TableStatistics());
    }

    public SchemaInfo getTableSchema(String tableName) {
        SchemaInfo schemaInfo = schemas.get(tableName);
        if (schemaInfo == null) {
            throw new InvalidASTException("Unknown table: " + tableName);
        }
        return schemaInfo;
    }

    public BufferPoolManager getBufferPoolManager() {
        return bufferPoolManager;
    }
}
