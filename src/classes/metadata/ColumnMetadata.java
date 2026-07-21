package classes.metadata;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import classes.abstraction.AbstractMetadataComponent;
import enums.DataType;
import enums.MetadataType;
import interfaces.MetadataComponent;

public class ColumnMetadata extends AbstractMetadataComponent {

    private UUID id;
    private String name;
    private DataType dataType;
    private boolean nullable;
    private Object defaultValue;
    private int position;
    private Integer length;
    private Integer precision;
    private Integer scale;
    private boolean identity;
    private long nextIdentityValue;

    public ColumnMetadata(
            String name,
            DataType dataType,
            boolean nullable,
            Object defaultValue,
            int position,
            Integer length,
            Integer precision,
            Integer scale,
            boolean identity,
            long nextIdentityValue) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    "Column name must not be null or blank");
        }

        if (dataType == null) {
            throw new IllegalArgumentException(
                    "Data type must not be null");
        }

        if (position < 0) {
            throw new IllegalArgumentException(
                    "Column position must not be negative");
        }

        if (length != null && length <= 0) {
            throw new IllegalArgumentException(
                    "Length must be greater than zero");
        }

        if (precision != null && precision <= 0) {
            throw new IllegalArgumentException(
                    "Precision must be greater than zero");
        }

        if (scale != null && scale < 0) {
            throw new IllegalArgumentException(
                    "Scale must not be negative");
        }

        if (precision != null
                && scale != null
                && scale > precision) {

            throw new IllegalArgumentException(
                    "Scale must not be greater than precision");
        }

        if (identity && nullable) {
            throw new IllegalArgumentException(
                    "Identity column must not be nullable");
        }

        if (nextIdentityValue < 1L) {
            throw new IllegalArgumentException(
                    "Next identity value must be at least 1");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.dataType = dataType;
        this.nullable = nullable;
        this.defaultValue = defaultValue;
        this.position = position;
        this.length = length;
        this.precision = precision;
        this.scale = scale;
        this.identity = identity;
        this.nextIdentityValue = nextIdentityValue;
    }

    public ColumnMetadata() {
        this.id = UUID.randomUUID();
        this.name = "";
        this.dataType = null;
        this.nullable = true;
        this.defaultValue = null;
        this.position = 0;
        this.length = null;
        this.precision = null;
        this.scale = null;
        this.identity = false;
        this.nextIdentityValue = 1L;
    }

    @Override
    public MetadataType getMetadataType() {
        return MetadataType.COLUMN;
    }

    @Override
    public List<MetadataComponent> getChildren() {
        return List.of();
    }

    public ColumnMetadata(String name, DataType dataType) {
        validateName(name);
        if (dataType == null) {
            throw new IllegalArgumentException(
                    "Data type must not be null.");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.dataType = dataType;
        this.nullable = true;
        this.defaultValue = null;
        this.position = 0;
        this.length = null;
        this.precision = null;
        this.scale = null;
        this.identity = false;
        this.nextIdentityValue = 1L;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        if (dataType == null) {
            throw new IllegalArgumentException(
                    "Data type must not be null.");
        }

        this.dataType = dataType;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    public void setNullable(boolean nullable) {
        // TODO: Implement
        this.nullable = nullable;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        if (defaultValue != null && !isValueCompatible(defaultValue)) {
            throw new IllegalArgumentException(
                    "Default value is not compatible with the column data type.");
        }

        this.defaultValue = defaultValue;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        if (position < 0) {
            throw new IllegalArgumentException(
                    "Column position must not be negative.");
        }

        this.position = position;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        if (length != null && length <= 0) {
            throw new IllegalArgumentException(
                    "Length must be greater than zero.");
        }

        this.length = length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        if (precision != null && precision <= 0) {
            throw new IllegalArgumentException(
                    "Precision must be greater than zero.");
        }

        if (precision != null && scale != null && scale > precision) {
            throw new IllegalArgumentException(
                    "Scale must not be greater than precision.");
        }

        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        if (scale != null && scale < 0) {
            throw new IllegalArgumentException(
                    "Scale must not be negative.");
        }

        if (scale != null && precision != null && scale > precision) {
            throw new IllegalArgumentException(
                    "Scale must not be greater than precision.");
        }

        this.scale = scale;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;

        if (identity && nextIdentityValue < 1L) {
            nextIdentityValue = 1L;
        }
    }

    public long getNextIdentityValue() {
        return nextIdentityValue;
    }

    public void setNextIdentityValue(long nextIdentityValue) {
        if (nextIdentityValue < 1L) {
            throw new IllegalArgumentException(
                    "Next identity value must be at least 1.");
        }

        this.nextIdentityValue = nextIdentityValue;
    }

    public long generateNextIdentityValue() {
        if (!identity) {
            throw new IllegalStateException(
                    "Column is not configured as an identity column.");
        }

        long generatedValue = nextIdentityValue;
        nextIdentityValue++;
        return generatedValue;
    }

    public Object resolveValue(Object suppliedValue) {
        if (suppliedValue != null) {
            validateValue(suppliedValue);
            return suppliedValue;
        }

        if (identity) {
            return generateNextIdentityValue();
        }

        if (defaultValue != null) {
            return defaultValue;
        }

        if (nullable) {
            return null;
        }

        throw new IllegalArgumentException(
                "A value is required for non-nullable column: " + name);
    }

    public boolean validateValue(Object value) {
        if (value == null) {
            if (!nullable) {
                throw new IllegalArgumentException(
                        "Null is not allowed for column: " + name);
            }

            return true;
        }

        if (!isValueCompatible(value)) {
            throw new IllegalArgumentException(
                    "Value is not compatible with data type " + dataType);
        }

        if (value instanceof String text
                && length != null
                && text.length() > length) {
            throw new IllegalArgumentException(
                    "String value exceeds maximum length " + length);
        }

        return true;
    }

    public boolean validateLength(Object value) {
        return false;
    }

    public boolean isValidDefinition() {
        if (id == null
                || name == null
                || name.trim().isEmpty()
                || dataType == null
                || position < 0) {
            return false;
        }

        if (length != null && length <= 0) {
            return false;
        }

        if (precision != null && precision <= 0) {
            return false;
        }

        if (scale != null && scale < 0) {
            return false;
        }

        return precision == null
                || scale == null
                || scale <= precision;
    }

    public boolean validatePrecision(Object value) {
        return false;
    }

    public Object applyDefaultValue(Object value) {
        return null;
    }

    public DataType getType() {
        return this.dataType;
    }

    private boolean isValueCompatible(Object value) {
        if (dataType == null || value == null) {
            return value == null;
        }

        String typeName = dataType.name().toUpperCase();

        return switch (typeName) {
            case "INT", "INTEGER", "SMALLINT", "TINYINT" ->
                value instanceof Byte
                        || value instanceof Short
                        || value instanceof Integer;
            case "BIGINT", "LONG" ->
                value instanceof Byte
                        || value instanceof Short
                        || value instanceof Integer
                        || value instanceof Long;
            case "FLOAT", "REAL" ->
                value instanceof Float
                        || value instanceof Integer
                        || value instanceof Long;
            case "DOUBLE" ->
                value instanceof Number;
            case "DECIMAL", "NUMERIC" ->
                value instanceof Number;
            case "BOOLEAN", "BOOL" ->
                value instanceof Boolean;
            case "CHAR", "VARCHAR", "TEXT", "STRING" ->
                value instanceof String;
            case "UUID" ->
                value instanceof UUID;
            case "DATE" ->
                value instanceof java.time.LocalDate;
            case "TIME" ->
                value instanceof java.time.LocalTime;
            case "DATETIME", "TIMESTAMP" ->
                value instanceof java.time.LocalDateTime
                        || value instanceof java.time.Instant;
            case "BINARY", "VARBINARY", "BLOB", "BYTE_ARRAY" ->
                value instanceof byte[];
            default -> true;
        };
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof ColumnMetadata other)) {
            return false;
        }

        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ColumnMetadata{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", dataType=" + dataType
                + ", nullable=" + nullable
                + ", position=" + position
                + ", identity=" + identity
                + '}';
    }

}