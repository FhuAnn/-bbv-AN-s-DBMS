package classes;

import java.util.List;

public class PhysicalInsert extends PhysicalOperatorNode {
    final String tableId;
    final List<Tuple> rows;

    PhysicalInsert(String tableId, List<Tuple> rows) {
        this.tableId = tableId;
        this.rows = rows;
    }
}
