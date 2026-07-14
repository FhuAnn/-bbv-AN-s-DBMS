package classes;

public class PhysicalIndexScan extends PhysicalOperatorNode {
    final String indexId;
    final Object scanKey;

    PhysicalIndexScan(String indexId, Object scanKey) {
        this.indexId = indexId;
        this.scanKey = scanKey;
    }
}
