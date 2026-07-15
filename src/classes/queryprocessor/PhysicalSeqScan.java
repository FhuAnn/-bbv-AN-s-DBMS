package classes.queryprocessor;

public class PhysicalSeqScan extends PhysicalOperatorNode {
    final String tableId;

    public PhysicalSeqScan(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }
}
