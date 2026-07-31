package core.classes.queryprocessor.node;

import core.classes.authentication.ASTNodeType;
import core.interfaces.ASTVisitor;
import core.interfaces.IASTNode;

public class DeleteNode implements IASTNode {
    TableNode table;
    ExpressionNode whereClause;
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
