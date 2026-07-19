package classes.metadata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;

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
            boolean unique) {

        validateName(name);

        if (tableId == null) {
            throw new IllegalArgumentException(
                    "Table ID must not be null.");
        }

        validateColumnNames(columnNames);

        if (type == null) {
            throw new IllegalArgumentException(
                    "Index type must not be null.");
        }

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
        validateName(newName);
        this.name = newName;
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
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void insert(Object key, UUID rowId) {
        ensureEnabled();

        if (key == null) {
            throw new IllegalArgumentException(
                    "Index key must not be null.");
        }

        if (rowId == null) {
            throw new IllegalArgumentException(
                    "Row ID must not be null.");
        }

        List<UUID> rowIds = entries.get(key);

        if (rowIds == null) {
            rowIds = new ArrayList<>();
            entries.put(key, rowIds);
        }

        if (unique && !rowIds.isEmpty()) {
            throw new IllegalArgumentException(
                    "Duplicate key is not allowed for a unique index: " + key);
        }

        if (!rowIds.contains(rowId)) {
            rowIds.add(rowId);
        }
    }

    public List<UUID> search(Object key) {
        if (key == null) {
            throw new IllegalArgumentException(
                    "Index key must not be null.");
        }

        List<UUID> rowIds = entries.get(key);

        if (rowIds == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(rowIds);
    }

    public boolean containsKey(Object key) {
        if (key == null) {
            throw new IllegalArgumentException(
                    "Index key must not be null.");
        }

        return entries.containsKey(key);
    }

    public boolean delete(Object key, UUID rowId) {
        ensureEnabled();

        if (key == null) {
            throw new IllegalArgumentException(
                    "Index key must not be null.");
        }

        if (rowId == null) {
            throw new IllegalArgumentException(
                    "Row ID must not be null.");
        }

        List<UUID> rowIds = entries.get(key);

        if (rowIds == null) {
            return false;
        }

        boolean removed = rowIds.remove(rowId);

        if (rowIds.isEmpty()) {
            entries.remove(key);
        }

        return removed;
    }

    public boolean deleteKey(Object key) {
        ensureEnabled();

        if (key == null) {
            throw new IllegalArgumentException(
                    "Index key must not be null.");
        }

        return entries.remove(key) != null;
    }

    public int getKeyCount() {
        return entries.size();
    }

    public int getEntryCount() {
        int total = 0;

        for (List<UUID> rowIds : entries.values()) {
            total += rowIds.size();
        }

        return total;
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public void clear() {
        ensureEnabled();
        entries.clear();
    }

    public Map<Object, List<UUID>> getEntries() {
        Map<Object, List<UUID>> snapshot = new LinkedHashMap<>();

        for (Map.Entry<Object, List<UUID>> entry : entries.entrySet()) {
            snapshot.put(
                    entry.getKey(),
                    Collections.unmodifiableList(
                            new ArrayList<>(entry.getValue())));
        }

        return Collections.unmodifiableMap(snapshot);
    }

    public boolean isValidDefinition() {
        return id != null
                && name != null
                && !name.trim().isEmpty()
                && tableId != null
                && type != null
                && !columnNames.isEmpty();
    }

    private void ensureEnabled() {
        if (!enabled) {
            throw new IllegalStateException(
                    "Index is disabled.");
        }
    }

    private static void validateName(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Index name must not be null or blank.");
        }
    }

    private static void validateColumnNames(
            List<String> columnNames) {
        if (columnNames == null || columnNames.isEmpty()) {
            throw new IllegalArgumentException(
                    "Index must contain at least one column.");
        }

        for (String columnName : columnNames) {
            if (columnName == null || columnName.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "Column name must not be null or blank.");
            }
        }
    }
}