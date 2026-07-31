package core.classes.factories;

import java.util.logging.LogRecord;

import core.classes.storageengine.LogRecordDefinition;

public interface ILogRecordFactory {

    LogRecord createLogRecord(
            LogRecordDefinition definition
    );
}
