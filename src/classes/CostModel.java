package classes;

public class CostModel {
    double calculateCost(PhysicalOperatorNode node, TableStatistics stats) {
        return Math.max(1.0, stats.rowCount + stats.pageCount);
    }
}
