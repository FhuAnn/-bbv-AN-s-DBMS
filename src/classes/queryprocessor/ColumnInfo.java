package classes.queryprocessor;

public class ColumnInfo {
    final String name;
    final String dataType;
    final Boolean nullable;

    public ColumnInfo(String name, String dataType, Boolean nullable) {
        this.name = name;
        this.dataType = dataType;
        this.nullable = nullable;
    }
}
