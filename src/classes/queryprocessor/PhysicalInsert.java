package classes.queryprocessor;

import java.util.List;

public class PhysicalInsert extends PhysicalOperatorNode {
    final String tableId;
    final List<Tuple> rows;

    public PhysicalInsert(String tableId, List<Tuple> rows) {
        this.tableId = tableId;
        this.rows = rows;
    }
}
