package classes.queryprocessor;

import java.util.ArrayList;
import java.util.List;

public class SchemaInfo {
    final String tableName;
    final List<ColumnInfo> columns;
    final List<ConstraintInfo> constraints;

    public SchemaInfo(String tableName, List<ColumnInfo> columns, List<ConstraintInfo> constraints) {
        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
        this.constraints = new ArrayList<>(constraints);
    }

    public ColumnInfo findColumn(String name) {
        return null;
    }
}
