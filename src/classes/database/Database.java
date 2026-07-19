package classes.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import classes.metadata.Catalog;
import classes.metadata.Schema;
import classes.queryprocessor.QueryResult;
import classes.storageengine.StorageEngine;
import classes.tx.TransactionManager;
import enums.DatabaseState;
import exception.InvalidSchemaException;
import exception.ReadOnlyDatabaseException;
import exception.SchemaAlreadyExistsException;
import exception.SchemaNotEmptyException;
import exception.SchemaNotFoundException;

public class Database {
    private UUID id;
    private String name;
    private boolean readOnly;
    private Catalog catalog;
    private List<Schema> schemas;
	private DatabaseState state;
    private static final int MAX_NAME_LENGTH = 128;

    public Database(String name)
	{
        if (name == null) {
            throw new IllegalArgumentException("Database name must not be null");
        }

        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Database name must not exceed 128 characters"
            );
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("Database name must not be blank");
        }
        id = UUID.randomUUID();
        schemas = new ArrayList<>();
        catalog = new Catalog();
        state = DatabaseState.CLOSED;

        this.name = name;
	};

    public void open() {
        state = DatabaseState.OPEN;
    };
    public void close() {
        state = DatabaseState.CLOSED;
    };

    public void addSchema(Schema schema) {
        if (schema == null) {
            throw new IllegalArgumentException("Schema must not be null");
        }
        
        if(readOnly) {
            throw new ReadOnlyDatabaseException(name);
        }

        Schema result =schemas.stream()
                .filter(s -> s.getName().equals(schema.getName()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            throw new SchemaAlreadyExistsException(schema.getName());
        }
        if (schema.getDatabaseId() != this.getId()) {
            throw new InvalidSchemaException( "Schema already belongs to a database");
        }
        schemas.add(schema);
        catalog.addSchema(schema);
    };
    public void removeSchema(String schemaName) {
        if(readOnly) {
            throw new ReadOnlyDatabaseException(name);
        }
        if(schemaName == null) {
            throw new IllegalArgumentException("Schema name must not be null");
        }
        if(schemaName.isBlank()) {
            throw new IllegalArgumentException("Schema name must not be blank");
        }
        Schema result =schemas.stream()
                .filter(schema -> schema.getName().equals(schemaName))
                .findFirst()
                .orElse(null);
        if (result == null) {
            throw new SchemaNotFoundException(schemaName);
        }

        if(result.getTables().size() > 0) {
            throw new SchemaNotEmptyException(schemaName);
        }
         schemas.remove(result);
            catalog.removeSchema(result.getId());
    };

    public Schema getSchema(String schemaName) { 
        Schema result =schemas.stream()
                .filter(schema -> schema.getName().equals(schemaName))
                .findFirst()
                .orElse(null);
        if (result == null) {
            throw new SchemaNotFoundException(schemaName);
        }
        return result;
    }
    public boolean containsSchema(String schemaName) {
		return schemas.stream()
                .anyMatch(schema -> schema.getName().equals(schemaName));
	};
    public List<Schema> getSchemas() {
        return  Collections.unmodifiableList(schemas);
    }

    public void rename(String newName) {
        if (newName == null) {
            throw new IllegalArgumentException("Database name must not be null");
        }

        if (newName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(
                    "Database name must not exceed 128 characters"
            );
        }
        if (newName.isBlank()) {
            throw new IllegalArgumentException("Database name must not be blank");
        }
        if(this.readOnly) {
            throw new ReadOnlyDatabaseException(name);
        }
        this.name = newName;
    };
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    };

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
