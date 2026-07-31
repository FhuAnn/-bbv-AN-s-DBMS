package core.classes.storageengine.iterator;

import java.util.NoSuchElementException;

import  core.classes.storageengine.Page;
import  core.interfaces.storage.record.IRecordIterator;
import  core.classes.storageengine.Record;
public class PageRecordIterator implements IRecordIterator {
    private Page page;
    private int currentPosition;

    public PageRecordIterator(Page page) {
        this.page = page;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNext() {
        return findNextValidPosition() < page.getRecordCount();
    }

    @Override
    public Record next() {
        int nextPosition = findNextValidPosition();

        if (nextPosition >= page.getRecordCount()) {
            throw new NoSuchElementException(
                    "No more records in page");
        }

        currentPosition = nextPosition + 1;

        return page.getRecord(nextPosition);
    }

    @Override
    public void reset() {

    }

    private int findNextValidPosition() {
        // TODO: Implement
        return 0;
    }
}
