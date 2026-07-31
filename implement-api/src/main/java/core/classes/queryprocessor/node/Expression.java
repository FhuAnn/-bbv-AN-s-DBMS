package core.classes.queryprocessor.node;

import core.classes.authentication.ASTNodeType;
import core.interfaces.ASTVisitor;
import core.interfaces.IASTNode;

public class Expression implements IASTNode {
    private final String text;

    public Expression(String text) {
        this.text = text;
    }

    @Override
    public Object accept(ASTVisitor visitor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'accept'");
    }

    @Override
    public ASTNodeType getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }
}
