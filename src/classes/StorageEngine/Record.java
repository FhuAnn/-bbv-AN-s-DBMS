package classes.storageengine;

public class Record {
    private RecordId recordId;
    private byte[] data;
    private boolean isDeleted;

    public Record(RecordId recordId, byte[] data) {
        this.recordId = recordId;
        this.data = data;
    }

    public RecordId getRecordId() {
        return recordId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void markDeleted() {
    }

    public int getSize() {
        return 0;
    }
}
