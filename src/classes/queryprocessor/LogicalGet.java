package classes.queryprocessor;

public class LogicalGet extends LogicalOperator {
    final String tableName;

    LogicalGet(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
