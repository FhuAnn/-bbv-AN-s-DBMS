package classes.queryprocessor.node;

import classes.authentication.ASTNodeType;
import interfaces.ASTVisitor;
import interfaces.IASTNode;

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
