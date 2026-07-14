package classes;

import java.util.List;

public class LogicalInsert extends LogicalOperator {
    final String tableName;
    final List<Tuple> rows;

    LogicalInsert(String tableName, List<Tuple> rows) {
        this.tableName = tableName;
        this.rows = rows;
    }
}
