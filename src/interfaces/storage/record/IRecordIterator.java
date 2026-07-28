package interfaces.storage.record;
import classes.storageengine.Record;
public interface IRecordIterator {
    public boolean hasNext();

    public Record next();

    public void reset();
}
