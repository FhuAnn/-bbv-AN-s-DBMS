package core.interfaces;

import core.classes.authentication.ASTNodeType;

public interface IASTNode {
    Object accept(ASTVisitor visitor);

    ASTNodeType getType();

    String toString();
}
