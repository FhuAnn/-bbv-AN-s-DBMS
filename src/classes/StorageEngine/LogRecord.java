package classes.storageengine;

import enums.LogType;

import java.util.UUID;

public class LogRecord {
    public long lsn;
    public LogType type;
    public UUID txId;
}