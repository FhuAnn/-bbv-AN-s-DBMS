package classes;

public class ColumnInfo {
    final String name;
    final String dataType;
    final Boolean nullable;

    ColumnInfo(String name, String dataType, Boolean nullable) {
        this.name = name;
        this.dataType = dataType;
        this.nullable = nullable;
    }
}
