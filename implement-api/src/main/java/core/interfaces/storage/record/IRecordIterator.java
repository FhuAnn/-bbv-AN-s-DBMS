package core.interfaces.storage.record;
import core.classes.storageengine.Record;
public interface IRecordIterator {
    public boolean hasNext();

    public Record next();

    public void reset();
}
