package classes.storageengine;

import java.util.ArrayList;
import java.util.List;

import chain.ConstraintValidationChain;
import interfaces.storage.record.IRecordIterator;

public class RecordManager {
    private ConstraintValidationChain constraintValidationChain;

    public RecordManager() {
    }

    public RecordManager(
            ConstraintValidationChain constraintValidationChain) {
        // TODO: Implement
    }

    public RecordId insert(Record record, String tableFile) {
        return null;
    }

    public Record getRecord(RecordId recordId, String tableFile) {
        return null;
    }

    public void update(RecordId recordId, Record newRecord, String tableFile) {
    }

    public void delete(RecordId recordId, String tableFile) {
    }

    public List<Record> scan(Page page) {
        List<Record> records = new ArrayList<>();

        IRecordIterator iterator = page.iterator();

        while (iterator.hasNext()) {
            records.add(iterator.next());
        }

        return records;
    }
}
