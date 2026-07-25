package builder.ASTBuilder;

import java.beans.Expression;
import java.util.List;
import java.util.Objects;

import classes.metadata.Table;
import classes.queryprocessor.LogicalPlan;
import interfaces.ILogicalPlanNode;

public class LogicalPlanBuilder {
    private ILogicalPlanNode root;

    public LogicalPlanBuilder tableScan(Table table) {
        return this;
    }

    public LogicalPlanBuilder filter(Expression predicate) {
        requireRoot();
        return this;
    }

    public LogicalPlanBuilder project(List<ColumnReference> columns) {
        requireRoot();
        return this;
    }

    public LogicalPlanBuilder join(LogicalPlanNode right, JoinCondition condition) {
        requireRoot();
        return this;
    }

    public LogicalPlanBuilder limit(long limit) {
        requireRoot();
        return this;
    }

    public LogicalPlan build() {
        return null;
    }

    private void requireRoot() {
        if (root == null) {
            throw new IllegalStateException("Call tableScan() first");
        }
    }
}
