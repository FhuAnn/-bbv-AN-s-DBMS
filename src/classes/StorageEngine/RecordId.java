package classes.storageengine;

public class RecordId {
    public int pageId;
    public int slotId;

    public RecordId(int pageId, int slotId) {
        this.pageId = pageId;
        this.slotId = slotId;
    }
}
