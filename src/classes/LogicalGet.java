package classes;

public class LogicalGet extends LogicalOperator {
    final String tableName;

    LogicalGet(String tableName) {
        this.tableName = tableName;
    }
}
