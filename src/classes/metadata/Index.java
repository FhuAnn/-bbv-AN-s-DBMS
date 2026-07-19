package classes.metadata;


import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Index {

    public enum IndexType {
        HASH,
        BTREE
    }

    private UUID id;
    private String name;
    private IndexType type;
    private UUID tableId;
    private List<String> columnNames;
    private boolean unique;
    private boolean enabled;
    private Map<Object, List<UUID>> entries;

    public Index() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.type = null;
        this.tableId = null;
        this.columnNames = Collections.emptyList();
        this.unique = false;
        this.enabled = true;
        this.entries = Collections.emptyMap();
    }

    public Index(
            String name,
            IndexType type,
            UUID tableId,
            List<String> columnNames,
            boolean unique
    ) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.type = type;
        this.tableId = tableId;
        this.columnNames = columnNames;
        this.unique = unique;
        this.enabled = true;
        this.entries = Collections.emptyMap();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        // TODO: Implement
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        // TODO: Implement
    }

    public IndexType getType() {
        return type;
    }

    public void setType(IndexType type) {
        // TODO: Implement
    }

    public UUID getTableId() {
        return tableId;
    }

    public void setTableId(UUID tableId) {
        // TODO: Implement
    }

    public List<String> getColumnNames() {
        return Collections.emptyList();
    }

    public void setColumnNames(List<String> columnNames) {
        // TODO: Implement
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        // TODO: Implement
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        // TODO: Implement
    }

    public void disable() {
        // TODO: Implement
    }

    public void insert(Object key, UUID rowId) {
        // TODO: Implement
    }

    public List<UUID> search(Object key) {
        return Collections.emptyList();
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean delete(Object key, UUID rowId) {
        return false;
    }

    public boolean deleteKey(Object key) {
        return false;
    }

    public int getKeyCount() {
        return 0;
    }

    public int getEntryCount() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public void clear() {
        // TODO: Implement
    }

    public Map<Object, List<UUID>> getEntries() {
        return Collections.emptyMap();
    }

    public boolean isValidDefinition() {
        return false;
    }
}