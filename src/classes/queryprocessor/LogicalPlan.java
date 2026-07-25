package classes.queryprocessor;

import java.util.Objects;

import builder.ASTBuilder.LogicalPlanNode;

public class LogicalPlan {
    private final LogicalPlanNode root;

    public LogicalPlan(LogicalPlanNode root) {
        this.root = Objects.requireNonNull(root);
    }

    public LogicalPlanNode getRoot() {
        return root;
    }
}
