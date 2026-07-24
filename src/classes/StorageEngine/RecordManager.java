package classes.storageengine;

import chain.ConstraintValidationChain;

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
}
