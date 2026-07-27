package interfaces;

import classes.metadata.Table;

public interface IQueryValidationCatalog {
    boolean schemaExists(String schemaName);

    Table getTable(
            String schemaName,
            String tableName);
}
