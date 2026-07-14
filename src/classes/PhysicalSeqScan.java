package classes;

public class PhysicalSeqScan extends PhysicalOperatorNode {
    final String tableId;

    PhysicalSeqScan(String tableId) {
        this.tableId = tableId;
    }
}
