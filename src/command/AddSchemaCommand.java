package command;

import classes.database.Database;
import classes.metadata.Schema;

public class AddSchemaCommand implements IMetadataCommand {
    private Database database;
    private Schema schema;

    public AddSchemaCommand(
            Database database,
            Schema schema) {
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

    public Database getDatabase() {
        return null;
    }

    public Schema getSchema() {
        return null;
    }
}
