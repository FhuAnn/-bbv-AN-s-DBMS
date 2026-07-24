package classes.queryprocessor.node;

import java.util.List;

import classes.authentication.ASTNodeType;
import interfaces.ASTVisitor;
import interfaces.IASTNode;

public class UpdateNode implements IASTNode {
    TableNode node;
    List<ColumnNode> columns;
    List<ExpressionNode> values;

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
