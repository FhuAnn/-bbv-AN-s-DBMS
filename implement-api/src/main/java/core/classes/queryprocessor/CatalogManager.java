package core.classes.queryprocessor;

import core.classes.metadata.Catalog;
import core.classes.metadata.Table;
import core.interfaces.IQueryValidationCatalog;

public class CatalogManager implements IQueryValidationCatalog {
    private final Catalog catalog;

    public CatalogManager(Catalog catalog) {
        this.catalog = catalog;
    }

    public void registerTable(String tableName) {
    }

    public Object getTableSchema(String tableName) {
        return null;
    }

    public Object getTableStatistics(String tableName) {
        return null;
    }

    public Object getBufferPoolManager() {
        return null;
    }

    @Override
    public boolean schemaExists(String schemaName) {
        return catalog.getSchemas()
                .values()
                .stream()
                .anyMatch(schema -> schema.getName().equalsIgnoreCase(schemaName));
    }

    @Override
    public Table getTable(String schemaName, String tableName) {
        return catalog.getSchemas()
                .values()
                .stream()
                .filter(schema -> schema.getName().equalsIgnoreCase(schemaName))
                .flatMap(schema -> schema.getTables().stream())
                .filter(table -> table.getName().equalsIgnoreCase(tableName))
                .findFirst()
                .orElse(null);
    }
}
