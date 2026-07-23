package command;

import classes.abstraction.Constraint;
import classes.metadata.Table;

public class AddConstraintCommand implements IMetadataCommand {
    private Table table;
    private Constraint constraint;

    public AddConstraintCommand(
            Table table,
            Constraint constraint) {
        // TODO: Implement
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'undo'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getName'");
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canUndo'");
    }

    public Table getTable() {
        return null;
    }

    public Constraint getConstraint() {
        return null;
    }
}
