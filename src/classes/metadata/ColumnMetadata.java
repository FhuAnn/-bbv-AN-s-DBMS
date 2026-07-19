package classes.metadata;

import java.util.UUID;

import enums.DataType;

public class ColumnMetadata {

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

    public ColumnMetadata(String name, DataType dataType) {
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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        // TODO: Implement
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
        // TODO: Implement
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        // TODO: Implement
        this.position = position;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        // TODO: Implement
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        // TODO: Implement
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        // TODO: Implement
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        // TODO: Implement
    }

    public long getNextIdentityValue() {
        return nextIdentityValue;
    }

    public long generateIdentityValue() {
        return 0L;
    }

    public boolean validateValue(Object value) {
        return false;
    }

    public boolean validateLength(Object value) {
        return false;
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
}