package core.classes.builder.ASTBuilder;

import java.util.List;

import core.classes.queryprocessor.ASTBuildResult;
import core.classes.queryprocessor.node.JoinNode;
import core.classes.queryprocessor.node.OrderItem;
import core.interfaces.IASTNode;


public class ASTBuilder {
    private IASTNode root;
    private List<IASTNode> nodes;

    public ASTBuilder(IASTNode root, List<IASTNode> nodes) {
        this.root = root;
        this.nodes = nodes;
    }

    public ASTBuilder build(List<String> columns) {
        return this;
    }

    public ASTBuilder from(String tableName) {
        return this;
    }

    public ASTBuilder where(String condition) {
        return this;
    }

    public ASTBuilder groupBy(List<String> columns) {
        return this;
    }

    public ASTBuilder join(JoinNode joinNode) {
        return this;
    }

    public ASTBuilder orderBy(List<OrderItem> items) {
        return this;
    }
    public ASTBuilder limit(int limit) {
        return this;
    }

    public ASTBuildResult build() {
        return new ASTBuildResult(true, root, null);
    }
}
