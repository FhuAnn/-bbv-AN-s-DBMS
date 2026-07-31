package core.interfaces;

import core.classes.database.Database;
import core.classes.metadata.Schema;
import core.enums.DatabaseStateType;

public interface IDatabaseState {

    void open(Database database);

    void close(Database database);

    void setReadOnly(
            Database database,
            boolean readOnly);

    void addSchema(
            Database database,
            Schema schema);

    Schema removeSchema(
            Database database,
            String name);

    void rename(
            Database database,
            String newName);

    DatabaseStateType getType();
}
