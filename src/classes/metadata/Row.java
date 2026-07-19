package classes.metadata;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Row {
    private UUID id;
    private Map<String, Object> values;
    private boolean deleted;
    private long version;

    public Row() {
        this.id = UUID.randomUUID();
        this.values = new LinkedHashMap<>();
        this.deleted = false;
        this.version = 1L;
    };

    private Row(
            Map<String, Object> values,
            boolean deleted,
            long version) {
        this.id = UUID.randomUUID();
        this.values = new LinkedHashMap<>(values);
        this.deleted = deleted;
        this.version = version;
    }

    public UUID getId() {
        return id;
    };

    public void setValue(String columnName, Object value) {
        validateColumnName(columnName);
        values.put(columnName, value);
    };

    public Object getValue(String columnName) {
        validateColumnName(columnName);
        return values.get(columnName);
    };

    public boolean containsColumn(String columnName) {
        validateColumnName(columnName);
        return values.containsKey(columnName);
    };

    public Object removeValue(String columnName) {
        validateColumnName(columnName);
        return values.remove(columnName);
    };
    
    public Map<String, Object> getValues() {
        return Collections.unmodifiableMap(values);

    };

    public void updateValues(Map<String, Object> newValues) {
        if (newValues == null) {
            throw new IllegalArgumentException(
                    "Values map must not be null.");
        }

        for (String columnName : newValues.keySet()) {
            validateColumnName(columnName);
        }

        if (newValues.isEmpty()) {
            return;
        }

        values.putAll(newValues);
        incrementVersion();
    };

    public void markDeleted() {
        deleted = true;
    };

    public void restore() {
        deleted = false;
    };

    public boolean isActive() {
        return !deleted;
    }

    public boolean isDeleted() {
        return deleted;
    };

    public Row copy() {
        return new Row(values, deleted, version);
    };

    public int calculateSize() {
        int size = 0;

        for (Map.Entry<String, Object> entry : values.entrySet()) {
            size += byteLength(entry.getKey());
            size += calculateValueSize(entry.getValue());
        }

        return size;
    };

    public long getVersion() {
        return version;
    }

    public void incrementVersion() {
        version++;
    }

    private static int calculateValueSize(Object value) {
        if (value == null) {
            return 0;
        }

        if (value instanceof String stringValue) {
            return byteLength(stringValue);
        }

        if (value instanceof Integer || value instanceof Float) {
            return 4;
        }

        if (value instanceof Long || value instanceof Double) {
            return 8;
        }

        if (value instanceof Short || value instanceof Character) {
            return 2;
        }

        if (value instanceof Byte || value instanceof Boolean) {
            return 1;
        }

        if (value instanceof UUID) {
            return 16;
        }

        if (value instanceof byte[] bytes) {
            return bytes.length;
        }

        return byteLength(value.toString());
    }

    private static int byteLength(String value) {
        return value.getBytes(StandardCharsets.UTF_8).length;
    }

    private static void validateColumnName(String columnName) {
        if (columnName == null || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Column name must not be null or blank.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof Row other)) {
            return false;
        }

        return deleted == other.deleted
                && version == other.version
                && Objects.equals(values, other.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values, deleted, version);
    }

    @Override
    public String toString() {
        return "Row{"
                + "id=" + id
                + ", values=" + values
                + ", deleted=" + deleted
                + ", version=" + version
                + '}';
    }
}