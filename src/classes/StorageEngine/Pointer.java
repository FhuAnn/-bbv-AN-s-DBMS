package classes.storageengine;

public class Pointer {
    public int pageId;
    public int slotId;

    public Pointer(int pageId, int slotId) {
        this.pageId = pageId;
        this.slotId = slotId;
    }
}