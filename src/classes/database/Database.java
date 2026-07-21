package classes.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import classes.metadata.Catalog;
import classes.metadata.Schema;
import classes.queryprocessor.QueryResult;
import classes.storageengine.StorageEngine;
import classes.tx.TransactionManager;
import enums.DatabaseState;
import enums.MetadataType;
import exception.InvalidSchemaException;
import exception.ReadOnlyDatabaseException;
import exception.SchemaAlreadyExistsException;
import exception.SchemaNotEmptyException;
import exception.SchemaNotFoundException;
import interfaces.MetadataComponent;

public class Database extends AbstractMetadataComponent {

    private final UUID id;
    private String name;
    private boolean readOnly;
    private final Catalog catalog;
    private final List<Schema> schemas;
    private DatabaseState state;

    private static final int MAX_NAME_LENGTH = 128;

    public Database(String name) {
        validateName(name);

        this.id = UUID.randomUUID();
        this.name = name;
        this.readOnly = false;
        this.catalog = new Catalog();
        this.schemas = new ArrayList<>();
        this.state = DatabaseState.CLOSED;
    }

    public void open() {
        state = DatabaseState.OPEN;
    }

    public void close() {
        state = DatabaseState.CLOSED;
    }

    public void addSchema(Schema schema) {
        Objects.requireNonNull(
                schema,
                "Schema must not be null");

        if (readOnly) {
            throw new ReadOnlyDatabaseException(name);
        }

        if (!id.equals(schema.getDatabaseId())) {
            throw new InvalidSchemaException(
                    "Schema belongs to another database");
        }

        if (containsSchema(schema.getName())) {
            throw new SchemaAlreadyExistsException(
                    schema.getName());
        }

        schemas.add(schema);
        catalog.addSchema(schema);
    }

    public void removeSchema(String schemaName) {
        if (readOnly) {
            throw new ReadOnlyDatabaseException(name);
        }

        validateObjectName(schemaName, "Schema");

        Schema schema = getSchema(schemaName);

        if (!schema.isEmpty()) {
            throw new SchemaNotEmptyException(schemaName);
        }

        schemas.remove(schema);
        catalog.removeSchema(schema.getId());
    }

    public Schema getSchema(String schemaName) {
        validateObjectName(schemaName, "Schema");

        return schemas.stream()
                .filter(schema -> schema.getName()
                        .equalsIgnoreCase(schemaName))
                .findFirst()
                .orElseThrow(() -> new SchemaNotFoundException(schemaName));
    }

    public boolean containsSchema(String schemaName) {
        if (schemaName == null || schemaName.isBlank()) {
            return false;
        }

        return schemas.stream()
                .anyMatch(schema -> schema.getName()
                        .equalsIgnoreCase(schemaName));
    }

    public List<Schema> getSchemas() {
        return Collections.unmodifiableList(
                new ArrayList<>(schemas));
    }
    

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.DATABASE;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.copyOf(schemas);
    }

    @Override
    public void addChild(MetadataComponent child) {
        if (!(child instanceof Schema schema)) {
            throw new IllegalArgumentException(
                    "Database can contain only Schema objects");
        }

        addSchema(schema);
    }

    @Override
    public MetadataComponent removeChild(UUID childId) {
        Objects.requireNonNull(
                childId,
                "Child ID must not be null");

        Schema schema = schemas.stream()
                .filter(item -> item.getId().equals(childId))
                .findFirst()
                .orElse(null);

        if (schema == null) {
            return null;
        }

        removeSchema(schema.getName());
        return schema;
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

   

    private static void validateObjectName(
            String value,
            String objectType) {

        if (value == null) {
            throw new IllegalArgumentException(
                    objectType + " name must not be null");
        }

        if (value.isBlank()) {
            throw new IllegalArgumentException(
                    objectType + " name must not be blank");
        }
    }

}
