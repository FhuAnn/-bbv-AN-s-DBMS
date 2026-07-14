package classes;

import java.util.HashMap;
import java.util.Map;

public class CatalogManager {
    private final Map<String, SchemaInfo> schemas = new HashMap<>();
    private final Map<String, TableStatistics> statistics = new HashMap<>();
    private final BufferPoolManager bufferPoolManager = new BufferPoolManager();

    void registerTable(String tableName, SchemaInfo schemaInfo) {
        schemas.put(tableName, schemaInfo);
        TableStatistics stats = new TableStatistics();
        stats.rowCount = 0;
        stats.pageCount = 1;
        statistics.put(tableName, stats);
        bufferPoolManager.getTableRows(tableName);
    }

    TableStatistics getTableStatistics(String tableName) {
        return statistics.computeIfAbsent(tableName, key -> new TableStatistics());
    }

    SchemaInfo getTableSchema(String tableName) {
        SchemaInfo schemaInfo = schemas.get(tableName);
        if (schemaInfo == null) {
            throw new InvalidASTException("Unknown table: " + tableName);
        }
        return schemaInfo;
    }

    BufferPoolManager getBufferPoolManager() {
        return bufferPoolManager;
    }
}
