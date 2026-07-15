package classes.queryprocessor;

import interfaces.ASTNode;

public abstract class AbstractASTNode implements ASTNode {
    @Override
    public String toString() {
        return getType().name();
    }
}
