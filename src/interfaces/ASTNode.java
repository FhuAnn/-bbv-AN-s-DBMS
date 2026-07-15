package interfaces;

import classes.authentication.ASTNodeType;

public interface ASTNode {
    ASTNodeType getType();

    <T> T accept(ASTVisitor<T> visitor);

    String toString();
}
