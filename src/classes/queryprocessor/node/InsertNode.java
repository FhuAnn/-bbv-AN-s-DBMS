package classes.queryprocessor.node;

import java.util.List;

import classes.authentication.ASTNodeType;
import interfaces.ASTVisitor;
import interfaces.IASTNode;

public class InsertNode implements IASTNode {
    private String tableName;
    private List<ColumnNode> columns;
    private List<ExpressionNode> values;
    public InsertNode() { }
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
