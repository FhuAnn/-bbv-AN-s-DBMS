package command;

import classes.metadata.Schema;
import classes.metadata.Table;

public class RemoveTableCommand implements IMetadataCommand {
    private Schema schema;
    private String tableName;

    public RemoveTableCommand(Schema schema, String tableName) {
        this.schema = schema;
        this.tableName = tableName;
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

    public Schema getSchema() {
        return null;
    }

    public String getTableName() {
        return null;
    }

    public Table getRemovedTable() {
        return null;
    }
}
