package builder;

import classes.metadata.ColumnMetadata;
import enums.DataType;

public class ColumnMetadataBuilder {
    private String name;
    private DataType dataType;

    private boolean nullable = true;
    private Object defaultValue;
    private int position = 0;

    private Integer length;
    private Integer precision;
    private Integer scale;

    private boolean identity = false;
    private long nextIdentityValue = 1L;

    private ColumnMetadataBuilder() {
    }

    public static ColumnMetadataBuilder builder() {
        return new ColumnMetadataBuilder();
    }

    public ColumnMetadataBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ColumnMetadataBuilder dataType(DataType dataType) {
        this.dataType = dataType;
        return this;
    }

    public ColumnMetadataBuilder nullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    public ColumnMetadataBuilder defaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public ColumnMetadataBuilder position(int position) {
        this.position = position;
        return this;
    }

    public ColumnMetadataBuilder length(Integer length) {
        this.length = length;
        return this;
    }

    public ColumnMetadataBuilder precision(Integer precision) {
        this.precision = precision;
        return this;
    }

    public ColumnMetadataBuilder scale(Integer scale) {
        this.scale = scale;
        return this;
    }

    public ColumnMetadataBuilder identity(boolean identity) {
        this.identity = identity;
        return this;
    }

    public ColumnMetadataBuilder nextIdentityValue(
            long nextIdentityValue) {

        this.nextIdentityValue = nextIdentityValue;
        return this;
    }

    public ColumnMetadata build() {
        validateRequiredFields();
        validateConfiguration();

        return null;
    }

    private void validateRequiredFields() {

    }

    private void validateConfiguration() {

    }
}
