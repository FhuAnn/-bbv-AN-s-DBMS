package classes.storageengine;

public class PageFrame {
    public Page page;
    public boolean dirty;
    public int pinCount;

    public PageFrame(Page page) {
        this.page = page;
    }
}