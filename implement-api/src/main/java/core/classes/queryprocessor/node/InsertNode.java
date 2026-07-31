package core.classes.queryprocessor.node;

import java.util.List;

import core.classes.authentication.ASTNodeType;
import core.interfaces.ASTVisitor;
import core.interfaces.IASTNode;

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
