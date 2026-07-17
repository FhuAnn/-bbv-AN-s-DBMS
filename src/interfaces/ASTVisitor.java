package interfaces;

public interface ASTVisitor<T> {
    T visit(ASTNode node);
}
