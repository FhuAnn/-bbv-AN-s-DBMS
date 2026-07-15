package classes.queryprocessor;

public abstract class AbstractASTNode implements ASTNode {
    @Override
    public String toString() {
        return getType().name();
    }
}
