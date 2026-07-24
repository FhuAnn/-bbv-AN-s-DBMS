package factories;

import classes.storageengine.LogRecord;
import classes.storageengine.LogRecordDefinition;

public interface ILogRecordFactory {

    LogRecord createLogRecord(
            LogRecordDefinition definition
    );
}
