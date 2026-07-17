package interfaces;

import classes.authentication.ASTNodeType;

public interface ASTNode {
    ASTNodeType getType();

    String toString();
}
