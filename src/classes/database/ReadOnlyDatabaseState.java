package classes.database;

import classes.metadata.Schema;
import enums.DatabaseStateType;
import interfaces.IDatabaseState;

public final class ReadOnlyDatabaseState
        implements IDatabaseState {

    @Override
    public void open(Database database) {
        /*
         * Read-only vẫn là trạng thái database đang mở.
         */
    }

    @Override
    public void close(Database database) {
        database.changeState(
                new ClosedDatabaseState());
    }

    @Override
    public void setReadOnly(
            Database database,
            boolean readOnly) {
        if (!readOnly) {
            database.changeState(
                    new OpenDatabaseState());
        }
    }

    @Override
    public void addSchema(
            Database database,
            Schema schema) {
        throw new IllegalStateException(
                "Cannot add schema while database is read-only");
    }

    @Override
    public Schema removeSchema(
            Database database,
            String schemaName) {
        throw new IllegalStateException(
                "Cannot remove schema while database is read-only");
    }

    @Override
    public void rename(
            Database database,
            String newName) {
        throw new IllegalStateException(
                "Cannot rename database while it is read-only");
    }

    @Override
    public DatabaseStateType getType() {
        return null;
    }

}