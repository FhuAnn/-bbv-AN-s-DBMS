package classes.database;

import java.util.List;
import java.util.UUID;

import classes.metadata.Catalog;
import classes.metadata.Schema;
import classes.queryprocessor.QueryResult;
import classes.storageengine.StorageEngine;
import classes.tx.TransactionManager;
import enums.DatabaseState;

public class Database {
    private UUID id;
    private String name;
    private boolean readOnly;
    private Catalog catalog;
    private List<Schema> schemas;
	private DatabaseState state;

    public Database(String name)
	{

	};

    public void open() {};
    public void close() {};

    public void addSchema(Schema schema) {};
    public void removeSchema(String schemaName) {};
    public Schema getSchema(String schemaName) { return null; };
    public boolean containsSchema(String schemaName) {
		return false;
	};
    public List<Schema> getSchemas() {
        return schemas;
    }

    public void rename(String newName) {};
    public void setReadOnly(boolean readOnly) {};

    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public boolean isReadOnly() {
        return readOnly;
    }
    public Catalog getCatalog() {
        return catalog;
    }
	public DatabaseState getState() {
		return state;
	}
}
