package core.classes.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import core.classes.abstraction.AbstractMetadataComponent;
import core.classes.metadata.Catalog;
import core.classes.metadata.Schema;
import core.enums.DatabaseStateType;
import core.enums.MetadataType;
import core.interfaces.IDatabaseState;
import core.interfaces.MetadataComponent;

public class Database extends AbstractMetadataComponent {

    private final UUID id;
    private boolean readOnly;
    private final Catalog catalog;
    private final List<Schema> schemas;
    private IDatabaseState state;
    private static final int MAX_NAME_LENGTH = 128;

    public Database(String name) {
        super(name);
        validateName(name);

        this.id = UUID.randomUUID();
        this.readOnly = false;
        this.catalog = new Catalog();
        this.schemas = new ArrayList<>();
        this.state = new ClosedDatabaseState();
    }

    public DatabaseStateType getState() {
        return state.getType();
    }

    public void open() {
        state.open(this);
    }

    public void close() {
        state.close(this);
    }

    public void setReadOnly(boolean readOnly) {
        state.setReadOnly(this, readOnly);
    }

    public void addSchema(Schema schema) {
        state.addSchema(this, schema);
    }

    public Schema removeSchema(String schemaName) {
        return state.removeSchema(
                this,
                schemaName);
    }

    /*
     * =====================================================
     * State transition
     * Concrete State sử dụng method này để thay đổi hành vi
     * của Database.
     * =====================================================
     */

    public void changeState(IDatabaseState newState) {
        this.state = Objects.requireNonNull(
                newState,
                "Database state must not be null");
    }
    /*
     * =====================================================
     * Internal operations
     * Chỉ state phù hợp mới được gọi các method này.
     * =====================================================
     */

    public void doAddSchema(Schema schema) {
        Objects.requireNonNull(
                schema,
                "Schema must not be null");

        if (!id.equals(schema.getDatabaseId())) {
            throw new IllegalArgumentException(
                    "Schema belongs to another database");
        }

        if (containsSchema(schema.getName())) {
            throw new IllegalArgumentException(
                    "Duplicate schema name: "
                            + schema.getName());
        }

        schemas.add(schema);
        // catalog.putSchema(schema);
    }

    public Schema doRemoveSchema(String schemaName) {
        return null;
    }

    public void doRename(String newName) {
        rename(newName);
    }

    /*
     * =====================================================
     * Read operations
     * Những thao tác đọc không cần delegate qua State.
     * =====================================================
     */

    public Schema getSchema(String schemaName) {
        return null;
    }

    public boolean containsSchema(String schemaName) {
        return true;
    }

    public List<Schema> getSchemas() {
        return this.schemas;
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.DATABASE;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return null;
    }

    @Override
    public void addChild(MetadataComponent child) {

    }

    @Override
    public MetadataComponent removeChild(UUID childId) {
        return null;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    private static void validateObjectName(
            String value,
            String objectType) {

    }

    private static void validateSchemaName(
            String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Schema name must not be null or blank");
        }
    }
}
