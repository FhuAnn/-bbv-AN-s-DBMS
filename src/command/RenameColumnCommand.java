package command;

import classes.metadata.ColumnMetadata;

public class RenameColumnCommand implements IMetadataCommand {
    private ColumnMetadata column;
    private String newName;
    private String previousName;

    public RenameColumnCommand(
            ColumnMetadata column,
            String newName) {
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

    public ColumnMetadata getColumn() {
        return null;
    }

    public String getNewName() {
        return null;
    }

    public String getPreviousName() {
        return null;
    }

}
