package classes.database;

import classes.metadata.Schema;
import enums.DatabaseStateType;
import interfaces.IDatabaseState;

public class OpenDatabaseState
        implements IDatabaseState {

    public OpenDatabaseState() {
        // TODO: Implement
    }

    @Override
    public void open(Database database) {
        // TODO: Implement
    }

    @Override
    public void close(Database database) {
        // TODO: Implement
    }

    @Override
    public void setReadOnly(
            Database database,
            boolean readOnly) {
        // TODO: Implement
    }

    @Override
    public void addSchema(
            Database database,
            Schema schema) {
        // TODO: Implement
    }

    @Override
    public Schema removeSchema(
            Database database,
            String name) {
        return null;
    }

    @Override
    public void rename(
            Database database,
            String newName) {
        // TODO: Implement
    }

    @Override
    public DatabaseStateType getType() {
        return null;
    }
}
