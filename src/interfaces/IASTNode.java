package interfaces;

import classes.authentication.ASTNodeType;

public interface IASTNode {
    Object accept(ASTVisitor visitor);

    ASTNodeType getType();

    String toString();
}
