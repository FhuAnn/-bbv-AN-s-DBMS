package core.classes.builder.ASTBuilder;

import java.util.List;
import java.util.Objects;

import core.classes.iterator.FilterOperator;
import core.classes.iterator.LimitOperator;
import core.classes.iterator.ProjectionOperator;
import core.classes.metadata.Row;
import core.classes.queryprocessor.PhysicalPlan;
import core.interfaces.IExecutionOperator;
import core.interfaces.IRowPredicate;


public class PhysicalPlanBuilder {
    private IExecutionOperator root;

    public PhysicalPlanBuilder tableScan(List<Row> rows) {
        return this;
    }

    public PhysicalPlanBuilder filter(IRowPredicate predicate) {
        requireRoot();
        root = new FilterOperator(root, Objects.requireNonNull(predicate));
        return this;
    }

    public PhysicalPlanBuilder project(List<ColumnReference> columns) {
        requireRoot();
        root = new ProjectionOperator(root, columns);
        return this;
    }

    public PhysicalPlanBuilder limit(long limit) {
        requireRoot();
        if (limit < 0)
            throw new IllegalArgumentException("Limit must be non-negative");
        root = new LimitOperator(root, limit);
        return this;
    }

    public PhysicalPlan build() {
        requireRoot();
        return new PhysicalPlan(root);
    }

    private void requireRoot() {
        if (root == null) {
            throw new IllegalStateException("Call tableScan() first");
        }
    }
}
